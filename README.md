# SmartHouseServer
## Installing

* Clone the git repository.
* The entry point is under [*src\main\java\SmartHouseServer\TPCmain\TPCmain.java*](https://github.com/talaviad/SmartHouseServer/blob/master/src/main/java/SmartHouseServer/TPCmain/TPCmain.java).

### OR
* Enter the command line and direct to the smarthouse.jar location.
* Make sure the *Database* folder in the same folder as the jar file.
* Run it with some port number.


## Some notes:

* I implemented the server in Thread Per Client model but there is an infrastructure for other kind of implementations (Reactor etc).
* I supplied the server with a small database, you can see it under the *Database* folder.
* The *DevicesIdsAndNames.json* file under *Database*  is not in use but I left it for further implementations.

***********************
******Important note******
***********************

I tested the server several times over telnet and its all work fine so please let me know if for some reason the project doesnt work.
If some things are uncleared please contact me and i will try to explain everything.

Thanks and have a nice and easy use :)
