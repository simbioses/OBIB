# OBIB Vagrant Virtual Machine 

## Installing the OBIB VM for Development

- Step 1. Check out the OBIB from git

- Step 2: Enter the /OBIB/mirthchannels/OBIB_vm folder

- Step 3: Adjust the Vagrantfile (optional)

    Change the configurations as needed, e.g. VM IP address:

    ```bash
    config.vm.network "private_network", ip: "192.168.100.101"
    ```

- Step 4: Adjust the mirth_connect.sh script (optional)

    If the VM IP address was ajusted in the Vagrantfile on Step 3, it is necessary to adjust it here as well:

    ```bash
    ## Server Settings
    SERVER_IP='192.168.100.101'
    ```

    Change other settings as needed. Such as database credentials, MirthConnect root path, timezone, and so on.
    
- Step 5: Run vagrant up from host machine

    ```bash
    $ vagrant up
    ```
    This will install all the required dependencies. 

    Then, restart the VM to ensure that all services are stating on boot time.

    ```bash
    $ vagrant reload
    ```

- Step 6: Verify the Instalation (optional)

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

- Step 7: Install OBIB on MirthConnect

    Execute the **deploy** provision from host machine:

    ```bash
    $ vagrant provision --provision-with deploy
    ```

    *All operations should display **Response code: 2XX** in the console*. However if something goes wrong, 
    the operations returns are stored in the folder /home/vagrant/output of the vm.

- *Step 7 (Manual Option): Install OBIB Global Scripts*

    Open browser on Mirthconnect admin web site: **http://192.168.100.101:8080** and Launch or Download the MirthConnect Administrator.

    *Link to download other Administrator Launcher options: https://www.nextgen.com/products-and-services/NextGen-Connect-Integration-Engine-Downloads*

    1. Login into the MirthConnect Administrator
    2. Click on **Channels** in Mirth Connect menus.
    3. Click on **Edit Global Scripts** in Channel Tasks menus.
    4. Click on **Import Scripts** in Script Tasks menus.
    5. Select the **OBIB_global_scripts.xml** file from */mirthchannels/OBIB_vm/configs/obib/* folder.
    6. Click on **Save Scripts** in Script Tasks menus.

- *Step 8 (Manual Option): Install OBIB Channel Group*

    In MirthConnect Administrator:

    1. Click on **Channels** in Mirth Connect menus.
    2. Click on **Import Group** in Group Tasks menu.
    3. Select the **OBIB_channel_group.xml** file from */mirthchannels/OBIB_vm/configs/obib/* folder.
    4. Answer **Yes** to import the Code Template Libraries as well.
    5. Select the **OBIB** group on main screen and clic on **Redeploy all** in the Channel Tasks menus.

## Updating the OBIB VM for Development

Execute the **deploy** provision from host machine:

```bash
$ vagrant provision --provision-with deploy
```

*All operations should display **Response code: 2XX** in the console*. If something goes wrong, 
the operations responses are stored in the folder /home/vagrant/output of the vm.

## Deploying OBIB VM

1. Execute the maven deploy from the OBIB root folder or from the IDE to generate a **obib-&lt;version-number&gt;.zip** file inside the target folder.

    ```bash
    $ mvn deploy
    ```

    This zip file has all the artifacts needed to start a new Vagrant VM with an operational OBIB.
 
2. Uncompress the zip file and enter in the generated folder.

3. Change the configurations in **Vagrantfile** and **mirth_connect.sh** as needed.   

4. Create a new Vagrant machine: 

    ```bash
    $ vagrant up
    ```

5. Reload the VM:

    ```bash
    $ vagrant reload
    ```

6. Deploy OBIB:

    ```bash
    $ vagrant provision --provision-with deploy
    ```

## Registering/Unregistering a Clinic

1. Login into the OBIB's VM

    ```bash
    $ vagrant ssh
    ```
   
2. Go to the shared folder **/vagrant**: 
    
    ```bash
    $ cd /vagrant/
    ```

3. Execute the register script with the required option:

    ```bash
    $ ./register.sh 
    Usage: ./register.sh OPTION [VALUE]
    Options:
     -r | --register   : register a new clinic
     -u | --unregister : unregister an existent clinic
     -c | --check      : check if a clinic is registered.
                         requires the clinic id as [VALUE]
     -h | --help       : this help information
    ```
