# SmartHouseServer
## Installing

* Unzip the 'SmartHouseServer' file and import the attached project to eclipse
* In src\main\java under SmartHouseServer.TPCmain package you can find the TPCmain.java file and run it.

### OR
* Enter the command line and direct to the smarthouse.jar location
* Make sure the 'Database' folder in the same folder as the jar file
* Run it with some port number

 Pay attention, I entered the **5555** port automatically by eclipse.




Some notes:

* I implemented the server in Thread Per Client model but there is an infrastructure for other kind of implementations (Reactor etc).
* I supplied the server with a small database, you can see it under the *Database* folder.
* The *DevicesIdsAndNames.json* file under *Database*  is not in use but I left it for further implementations.

***********************
******Important note******
***********************

I tested the server several times over telnet and its all work fine so please let me know if for some reason the project doesnt work.

If some things are uncleared please contact me and i will try to explain everything.

Thanks and have a nice and easy use :)
