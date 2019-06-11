#!/bin/sh

## Load the settings
. /vagrant/./mirth_connect.sh

# Client API settings
API_URL="https://$SERVER_IP:8443/api"
ARGS="--header Content-Type:application/xml --header Accept:application/xml"
OUT_ARGS="--silent --location --write-out %{http_code}"
AUTH="--insecure --user $ADMIN_USERNAME:$ADMIN_PASSWORD"

# Params: <response_code>, <output_file>
checkResponse() {
    printf "Response code: %s\n" "$1"
    if [ -s $2 ]; then
        printf "Response content:\n%s\n" "`cat $2`"
    fi
}

# Params: <action_path> <output_file_name>
execAction() {
    response=$(curl -X POST $ARGS $AUTH "$API_URL$1" --output $2 $OUT_ARGS)
    checkResponse $response $2
}

# Params: <update_path> <input_file_path> <output_file_name>
execUpdate() {
    response=$(curl -X PUT $ARGS $AUTH --data-binary @$2 "$API_URL$1" --output $3 $OUT_ARGS)
    checkResponse $response $3
}

## Update OBIB Database
printf '\nUpdating OBIB Database...\n'

mysql --user=root --password=$DB_ROOT_PASS < $CONF_ROOT/dbscripts/OBIB_DB_update.sql

## Update Resources
printf '\nUpdating MirthConnect Resources...\n'

sudo cp -R $CONF_ROOT/custom-lib/ $MIRTH_ROOT/
sudo sed -e 's,${MIRTH_ROOT},'"$MIRTH_ROOT"',g' -e 's,${DB_USERNAME},'"$DB_USERNAME"',g' -e 's,${DB_PASSWORD},'"$DB_PASSWORD"',g' -i $MIRTH_ROOT/custom-lib/CDA.properties

## Reload Resources
printf '\nReloading MirthConnect Resources...\n'

execAction "/server/resources/Default%20Resource/_reload" "resources_reload.out"

## Update Global Scripts
printf '\nUpdating Global Scripts...\n'

execUpdate "/server/globalScripts" "$CONF_ROOT/obib/OBIB_global_scripts.xml" "scripts_update.out"

## Update Channels
for file in $CONF_ROOT/obib/channels/*.xml; do
    printf "\nUpdating Channel: $(basename "$file" .xml)...\n"
    
    channel_id=$(xmllint --xpath "//channel/id/text()" "$file")
    execUpdate "/channels/$channel_id?override=true" "$file" "$(basename "$file" .xml)_update.out"
    execAction "/channels/$channel_id/enabled/true" "$(basename "$file" .xml)_enabled.out"
done

## Update Code Templates
printf '\nUpdating Code Templates...\n'

execUpdate "/codeTemplateLibraries?override=true" "$CONF_ROOT/obib/OBIB_code_templates_library.xml" "templates_update.out"

## Redeploy All Channels
printf '\nRedeploying All Channels...\n'

execAction "/channels/_redeployAll?returnErrors=true" "channels_redeploy.out"
