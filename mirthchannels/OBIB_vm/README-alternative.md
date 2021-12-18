# OBIB Without VM



## Deploying OBIB

1. Execute the maven deploy from the OBIB root folder or from the IDE to generate a **obib-&lt;version-number&gt;.zip** file inside the target folder.

    ```bash
    $ mvn deploy
    ```

    This zip file has all the artifacts needed to deploy OBIB in an Ubunto machine.
 
2. Uncompress the zip file and enter in the extracted folder. e.g:

    ```bash
    $ unzip obib-1.0.zip -d obib-1.0
    $ cd obib-1.0
    ```

3. Modify the shell scripts as follow:

    - **mirth_connect.sh**: 
        
        - Adjust the path of the property CNF_ROOT (line 23), e.g: ```/home/obib/obib-1.0/configs```
        
        - Change the other properties as needed.
        
    - **install.sh**:
    
        - Remove the "/vagrant/" from the mirth_connect.sh path (line 4), e.g: ```. ./mirth_connect.sh```
        
        - Comment out or ignore during the installation the last echo message: "Setup completed, please restart the VM..."

    - **deploy.sh**:
    
        - Remove the "/vagrant/" from the mirth_connect.sh path (line 4), e.g: ```. ./mirth_connect.sh```

4. Execute the install script:

    ```bash
    $ ./install.sh
    ```

5. Reload the machine to ensure that all services are starting correctly.

6. Deploy OBIB:

    ```bash
    $ ./deploy.sh
    ```

## Registering/Unregistering a Clinic

1. Execute the register script with the required option:

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
