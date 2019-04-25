# Installing the OBIB VM

## Step 1: Check out the OBIB from git
## Step 2: Cd to OBIB/mirthchannels/OBIB_VM
## Step 3: Adjust Vagrant file (optional)

    Adjust the IP: config.vm.network "private_network", ip: **"192.168.56.105"** 

## Step 4: Adjust mirth_connect.sh script (optional)

    if IP address was adjusted, adjust here as well:

    SERVER_IP=**'192.168.56.105'**

## Step 5: run vagrant up on the command line, inside OBIB path. All dependencies will be installed

    vagrant reload afterwards to restart VM

## Step 6: Verify

    login with vagrant ssh
    Verify the IP address of the vagrant instance with ifconfig then open another command prompt to ping the vangrant IP address outside vm

## Step 7: Install OBIB-global-scripts (will be automated later)

    open browser on Mirthconnect admin web site: **http://192.168.56.105:8080**
    launch MirthConnect Administrator
    click "Channels" (top left)
    Click on "Edit Global Scripts"
    Click "Import Scripts"
    Import "OBIB_global_scripts.xml" from mirthchannels subdirectory
    Click "Save Scripts"

## Step 8: Install OBIB_channel_group (automated later)

    Click "Group Task"
    Import "OBIB_channel_group" (answer "yes" to include code templates)
    Select imported group and "redeploy all"
