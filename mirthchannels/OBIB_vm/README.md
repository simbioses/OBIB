# Installing the OBIB VM

## Step 1: Check out the OBIB from git

## Step 2: Enter the /OBIB/mirthchannels/OBIB_vm folder

## Step 3: Adjust the Vagrantfile (optional)

Change the configurations as needed, e.g. VM IP address:
```bash
config.vm.network "private_network", ip: "192.168.56.105"
```

## Step 4: Adjust the mirth_connect.sh script (optional)

If IP address was ajusted in Vagrantfile, adjust here as well:

```bash
## Server Settings
SERVER_IP='192.168.56.105'
```

Change other settings as needed. Such as database credentials, MirthConnect root path, timezone, and so on.

## Step 5: Run vagrant up from host machine

```bash
$ vagrant up
```
This will install all the required dependencies. 

Then, restart the VM to ensure that all services are stating on boot time.

```bash
$ vagrant reload
```

## Step 6: Verify the Instalation

From the host machine, login into the VM 

```bash
$ vagrant ssh
```

Verify if the MirthConnect and MariaDB services are running

```bash
$ sudo systemctl status mirth
$ sudo systemctl status mariadb
```

Check if the IP address is correctly assigned

```bash
$ ifconfig
```

## Step 7: Install OBIB on MirthConnect

Execute the **updates** provision from host machine:

```bash
$ vagrant provision --provision-with update
```

## *Step 7: (Manual option) Install OBIB Global Scripts*

Open browser on Mirthconnect admin web site: **http://192.168.56.105:8080** and Launch or Download the MirthConnect Administrator.

*Link to download other Administrator Launcher options: https://www.nextgen.com/products-and-services/NextGen-Connect-Integration-Engine-Downloads*

1. Login into the MirthConnect Administrator
2. Click on **Channels** in Mirth Connect menus.
3. Click on **Edit Global Scripts** in Channel Tasks menus.
4. Click on **Import Scripts** in Script Tasks menus.
5. Select the **OBIB_global_scripts.xml** file from */mirthchannels/OBIB_vm/configs/obib/* folder.
6. Click on **Save Scripts** in Script Tasks menus.

## *Step 8: (Manual option) Install OBIB Channel Group*

In MirthConnect Administrator:

1. Click on **Channels** in Mirth Connect menus.
2. Click on **Import Group** in Group Tasks menu.
3. Select the **OBIB_channel_group.xml** file from */mirthchannels/OBIB_vm/configs/obib/* folder.
4. Answer **Yes** to import the Code Template Libraries as well.
5. Select the **OBIB** group on main screen and clic on **Redeploy all** in the Channel Tasks menus.

# Updating the OBIB VM

Execute the **update** provision from host machine:

```
$ vagrant provision --provision-with update
```
