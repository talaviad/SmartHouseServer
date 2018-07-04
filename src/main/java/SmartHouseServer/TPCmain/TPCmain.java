package SmartHouseServer.TPCmain;

import SmartHouseServer.bidi.BidiMessageEncoderDecoder;
import SmartHouseServer.bidi.SmartFunctionalityServiceProtocol;
import SmartHouseServer.server.Server;
import SmartHouseServer.json.UserDataBase;
import SmartHouseServer.json.ParsingUsers;
import SmartHouseServer.json.DevicesDataBase;
import SmartHouseServer.json.ParsingDevices;


public class TPCmain {
	
	public static UserDataBase userDataBase = ParsingUsers.parseUsers();
	public static DevicesDataBase deviceDataBase=ParsingDevices.parseDevices(userDataBase);
	
	public static void main(String args[]){
		if (args.length<1) {
			System.out.println("Error, no port provided");
			System.exit(-1);
		}
		//arg[0] = 5555,  is entered by eclipse, should be changed if you want running it from command line or use another port
		Server.threadPerClient(Integer.parseInt(args[0]),()-> new SmartFunctionalityServiceProtocol(deviceDataBase), 
				()->  new BidiMessageEncoderDecoder()).serve();
	}
}
