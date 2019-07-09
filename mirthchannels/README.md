# OBIB Vagrant VM

## Exporting Mirth Connector Artifacts

###1. Export OBIB Group

1. Open the Channels view (Option **Channels** in *Mirth Connect* menu)

2. Select the **OBIB** group

3. Click on **Export Group** in *Group tasks* menu

    *Attention.: When asked to include the code template libraries, answer **YES**.* 

4. Save the XML in the folder **OBIB_vm/configs/obib**

###2. Export Global Scripts

1. Open the Channels view (Option **Channels** in *Mirth Connect* menu)

2. Click on **Edit Global Scripts** in *Channel tasks* menu

3. Click on **Export Scripts** in *Script tasks* menu

4. Save the XML in the folder *OBIB_vm/configs/obib*

###3. Export Code Templates

1. Open the Channels view (Option **Channels** in *Mirth Connect* menu)

2. Click on **Edit Code Templates** in *Channel tasks* menu

4. Save the XML in the folder *OBIB_vm/configs/obib*


## Important files and folders

**/OBIB_vm/** - Vagrant root folder

**/OBIB_vm/configs/appdata/** - MirthConnect's appdata folder

**/OBIB_vm/configs/certs/** - Clinics certificates folder

**/OBIB_vm/configs/conf/** - MirthConnects's conf folder

**/OBIB_vm/configs/custom-lib/** - MirthConnects's custom-lib folder

**/OBIB_vm/configs/dbscripts/** - OBIB's database scripts

**/OBIB_vm/configs/obib/** - OBIB's MirthConnect artifacts (channels, scripts and templates)

**/OBIB_vm/configs/mirth.service** - Linux service script for MirthConnect

**/OBIB_vm/install.sh** - Vagrant 'install' provision shell script

**/OBIB_vm/mirth_connect.sh** - Install and Update configurations

**/OBIB_vm/README.md** - Install and Update instructions

**/OBIB_vm/updates.sh** - Vagrant 'update' provision shell script

**/OBIB_vm/Vagrantfile** - Vagrant file

**/README.md** - This file
