#!/bin/sh

## Administrator credentials
ADMIN_USERNAME='admin'
ADMIN_PASSWORD='admin'

## Database credentials
DB_ROOT_PASS='_DBrP445!'
DB_USERNAME='mirth'
DB_PASSWORD='Mirth!123'

## Application Paths
CONF_ROOT='/vagrant/configs'
MIRTH_ROOT='/opt/MirthConnect'

## Client API settings
API_URL="https://192.168.100.101:8443/api"
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
sudo sed -e 's,${MIRTH_ROOT},'"$MIRTH_ROOT"',g' -e 's,${DB_USERNAME},'"$DB_USERNAME"',g' \
    -e 's,${DB_PASSWORD},'"$DB_PASSWORD"',g' -i $MIRTH_ROOT/custom-lib/CDA.properties

## Reload Resources
printf '\nReloading MirthConnect Resources...\n'

execAction "/server/resources/Default%20Resource/_reload" "resources_reload.out"

## Update Global Scripts
printf '\nUpdating Global Scripts...\n'

execUpdate "/server/globalScripts" "$CONF_ROOT/obib/OBIB_global_scripts.xml" "scripts_update.out"

## Update Channel Group
printf '\nUpdating Update Channel Group...\n'

#GROUP_FILE="$CONF_ROOT/obib/OBIB_channel_group.xml"
#GROUP_OUTPUT='template_update.out'
#GROUP_RESP=$(curl -X POST $ARGS $AUTH --data @$GROUP_FILE "$API_URL/channelgroups/_bulkUpdate?override=true" --output $GROUP_OUTPUT $OUT_ARGS)

#checkResponse $GROUP_RESP $GROUP_OUTPUT

## Redeploy All Channels
printf '\nRedeploying All Channels...\n'

execAction "/channels/_redeployAll?returnErrors=true" "channels_redeploy.out"
