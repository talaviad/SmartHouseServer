# SmartHouseServer

1. Unzip the 'SmartHouseServer' file and import the attached project to eclipse
2. In src\main\java under SmartHouseServer.TPCmain package you can find the TPCmain.java file and run it.

	OR
1. Enter the command line and direct to the smarthouse.jar location
2. Make sure the 'Database' folder in the same folder as the jar file
3. Run it with some port number

 Pay attention, i entered the '5555' port automaticlly by eclipse




Some notes:

1. I implemented the server in Thread Per Client model but there is an infrastructure for other kind of implementations(Reactor etc)
2. I supplied the server with small database, you can see it under the 'Database' folder
3. The 'DevicesIdsAndNames.json' file under 'Database'  is not in use but i left it for further implementations

***********************
******Important note******
***********************

I tested the server several times over telnet and its all work fine so
please let me know if for some reason the project doesnt work.

If some things are uncleared please contact me and i will try to explain everything

Thanks and have a nice and easy use :)
