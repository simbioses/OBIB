#!/bin/bash

## Load the settings
. /vagrant/./mirth_connect.sh

# Client API settings
API_URL="https://$SERVER_IP:8443/api"
ARGS="--header Content-Type:application/xml --header Accept:application/xml"
ARGS2="--header Accept:application/xml"
OUT_ARGS="--silent --location --write-out %{http_code}"
AUTH="--insecure --user $ADMIN_USERNAME:$ADMIN_PASSWORD"

# (Re)Create temporary dirs
rm -rf output
rm -rf channels
rm -rf templates
mkdir -p output
mkdir -p channels
mkdir -p templates

# Params: <response_code>, <output_file>
checkResponse() {
    printf "Response code: %s\n" "$1"
    if [ -s "output/$2" ]; then
        printf "Response content:\n%s\n" "$(head "output/$2")"
    fi
}

# Params: <action_path> <output_file_name>
execAction() {
    response=$(curl -X POST $ARGS $AUTH "$API_URL$1" --output "output/$2" $OUT_ARGS)
    checkResponse $response $2
}

# Params: <update_path> <input_file_path> <output_file_name>
execUpdate() {
    response=$(curl -X PUT $ARGS $AUTH --data-binary @$2 "$API_URL$1" --output "output/$3" $OUT_ARGS)
    checkResponse $response $3
}

# Params: <update_path> <input_parameter_name> <input_file_path> <output_file_name>
execBulkUpdate() {
    response=$(curl -X POST $ARGS2 $AUTH --form $2=@$3 "$API_URL$1" --output "output/$4" $OUT_ARGS)
    checkResponse $response $4
}

# Params: <channel_file>
configureSMTPSenderConnector() {
    # check if the channel has any smtp sender connector
    smtp_path="//*/connector/properties[contains(@class, 'SmtpDispatcherProperties')]"
    if [ "$(xmllint --xpath "count($smtp_path)" "$1")" -gt 0 ]; then
        # update the smtp properties
        xmlstarlet ed --inplace --update "$smtp_path/smtpHost" --value "$SMTP_HOST" "$1"
        xmlstarlet ed --inplace --update "$smtp_path/smtpPort" --value "$SMTP_PORT" "$1"
        xmlstarlet ed --inplace --update "$smtp_path/encryption" --value "$SMTP_ENCRYPTION" "$1"
        xmlstarlet ed --inplace --update "$smtp_path/authentication" --value "$SMTP_AUTHENTICATION" "$1"
        xmlstarlet ed --inplace --update "$smtp_path/username" --value "$SMTP_USERNAME" "$1"
        xmlstarlet ed --inplace --update "$smtp_path/password" --value "$SMTP_PASSWORD" "$1"
        xmlstarlet ed --inplace --update "$smtp_path/from" --value "$SMTP_FROM" "$1"
        xmlstarlet ed --inplace --update "$smtp_path/to" --value "$SMTP_TO" "$1"
    fi
}

# Params: <group_set_file>
extractChannels() {
    n=$(xmllint --xpath 'count(//set/channelGroup/channels/channel)' "$1")
    for (( i=1; i<="$n"; i++ )); do
        channel_name=$(xmllint --xpath "string(//set/channelGroup/channels/channel[$i]/name)" "$1")
        xmllint --xpath "//set/channelGroup/channels/channel[$i]" "$1" > "channels/${channel_name//[ |\/]/_}.xml"
    done
}

# Params: <templates_library_file>
extractTemplates() {
    n=$(xmllint --xpath 'count(//list/codeTemplateLibrary)' "$1")
    for (( i=1; i<="$n"; i++ )); do
        m=$(xmllint --xpath "count(//list/codeTemplateLibrary[$i]/codeTemplates/codeTemplate)" "$1")
        for (( j=1; j<="$m"; j++ )); do
            template_name=$(xmllint --xpath "string(//list/codeTemplateLibrary[$i]/codeTemplates/codeTemplate[$j]/name)" "$1")
            xmllint --xpath "//list/codeTemplateLibrary[$i]/codeTemplates/codeTemplate[$j]" "$1" > "templates/${template_name//[ |\/]/_}.xml"
        done
    done
}

## Update OBIB Database
printf '\nUpdating OBIB Database\n'
mysql --user=root --password="$DB_ROOT_PASS" < "$CONF_ROOT/dbscripts/OBIB_DB_update.sql"

## Update Settings
#printf '\nUpdating MirthConnect Settings\n'
# TODO configure global the smtp credentials

## Update Resources
printf '\nUpdating MirthConnect Resources\n'
sudo rm -rf "$MIRTH_ROOT/custom-lib"
sudo cp -R "$CONF_ROOT/custom-lib/" "$MIRTH_ROOT/"
sudo sed -e 's,${TIMEZONE},'"$TIMEZONE"',g' -e 's,${DB_USERNAME},'"$DB_USERNAME"',g' \
 -e 's,${DB_PASSWORD},'"$DB_PASSWORD"',g' -e 's,${MIRTH_ROOT},'"$MIRTH_ROOT"',g' -i "$MIRTH_ROOT/custom-lib/obib.properties"

## Reload Resources
printf '\nReloading MirthConnect Resources\n'
execAction "/server/resources/Default%20Resource/_reload" "resources_reload.out"

## Update Global Scripts
printf '\nUpdating Global Scripts\n'
execUpdate "/server/globalScripts" "$CONF_ROOT/obib/OBIB_global_scripts.xml" "scripts_update.out"

## Update Channels
printf "\nUpdating Channel Group: %s\n" "$(xmllint --xpath 'string(//channelGroup/name)' "$CONF_ROOT/obib/OBIB_channel_group.xml")"
# 1 - encapsulate <channelGroup/> with <set/> - required by '/channelgroups/_bulkUpdate' method
channel_group=$(cat "$CONF_ROOT/obib/OBIB_channel_group.xml")
echo "<set>$channel_group</set>" > "OBIB_channel_group_set.xml"
# 2 - update the "channelGroups"
execBulkUpdate "/channelgroups/_bulkUpdate?override=true" "channelGroups" "OBIB_channel_group_set.xml" "group_update.out"
# 3 - extract all <channel/> from <channelGroup/>
extractChannels "OBIB_channel_group_set.xml"
# 4 - update and enable all channels
for file in channels/*.xml; do
    printf "\nUpdating Channel: %s\n" "$(basename "$file" .xml)"
    configureSMTPSenderConnector "$file"
    channel_id=$(xmllint --xpath "//channel/id/text()" "$file")
    execUpdate "/channels/$channel_id?override=true" "$file" "$(basename "$file" .xml)_update.out"
    execAction "/channels/$channel_id/enabled/true" "$(basename "$file" .xml)_enabled.out"
done

## Update Code Templates
printf '\nUpdating Code Templates Libraries\n'
# 1 - update the "codeTemplateLibraries"
execUpdate "/codeTemplateLibraries?override=true" "$CONF_ROOT/obib/OBIB_code_templates_library.xml" "templates_update.out"
# 2 - extract all <codeTemplate/> from <codeTemplateLibrary/>
extractTemplates "$CONF_ROOT/obib/OBIB_code_templates_library.xml"
# 3 - update all codeTemplates
for file in templates/*.xml; do
    printf "\nUpdating Template: %s\n" "$(basename "$file" .xml)"
    template_id=$(xmllint --xpath "//codeTemplate/id/text()" "$file")
    execUpdate "/codeTemplates/$template_id?override=true" "$file" "$(basename "$file" .xml)_update.out"
done

## Redeploy All Channels
printf '\nRedeploying All Channels\n'
execAction "/channels/_redeployAll?returnErrors=true" "channels_redeploy.out"
