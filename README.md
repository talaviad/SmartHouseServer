# SmartHouseServer

1. import the attached project to eclipse
2. in src\main\java under SmartHouseServer.TPCmain package you can find the TPCmain.java file and run it.

	OR
1. enter the command line and direct to the smarthouse.jar location
2. make sure the 'Database' folder in the same folder as the jar file
3. run it with some port number

 pay attention, i entered the '5555' port automaticlly by eclipse




some notes:

1. i implemented the server in Thread Per Client model but there is an infrastructure for other kind of implementations(Reactor etc)
2. i supplied the server with small database, you can see it under the 'Database' folder
3. the 'DevicesIdsAndNames.json' file under 'Database'  is not in use but i left it for further implementations

***********************
******important note******
***********************

i tested the server several times over telnet and its all work fine so
please let me know if for some reason the project doesnt work.

if some things are uncleared please contact me and i will try to explain everything

thanks and have a nice and easy use :)
