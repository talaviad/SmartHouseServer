package SmartHouseServer.bidi;

import java.util.ArrayList;
import java.util.Random;
import SmartHouseServer.json.Device;
import SmartHouseServer.json.DevicesDataBase;

//The smarthouse layer

public class SmartFunctionalityServiceProtocol extends UserServiceProtocol {

	private DevicesDataBase deviceDataBase;
	private ArrayList<Device> devices;

	public SmartFunctionalityServiceProtocol (DevicesDataBase deviceDataBase) {
		super(deviceDataBase.getUserDataBase());
		this.deviceDataBase=deviceDataBase;
		this.devices = null;
	} 
	//the main logic of this protocol
	public String handle(String s) {
		String[] arrOfWords = s.split(" ");
		String firstWord=arrOfWords[0];
		String response=null;
		switch(firstWord){
			case "ListDevices":  
			{	
				if (arrOfWords.length == 1)	
					response = this.deviceDataBase.getDevices(userName);
				else
					response = "ERROR, invalid use of 'ListDevices' command, please do 'ListDevices'";
					
				break;
			}
			case "SetState":  
			{
				if (arrOfWords.length != 3 || (!(checkSetCommandsValidity(arrOfWords,"SetState"))))
					return "ERROR, invalid use of 'SetState' command, please do 'SetState <deviceid> <on|off>'"; 
				Integer id = Integer.parseInt(arrOfWords[1]);
				String newState = arrOfWords[2];
				String cuurentState = this.deviceDataBase.getDeviceState(id,userName);
				if (cuurentState == null)
					return "Error, device with id <"+id+"> could not found";
				if (cuurentState.equals("off") && newState.equals("on")) { 
					Random ran = new Random();
					int probablity;
					if ((probablity=ran.nextInt(10) + 1) == 1) {
						response = "Error, for some reason the device could not turned on";
						break;
					}
				}
				this.deviceDataBase.setState(userName, id, newState);
				response = "ACK, set new state is done";
				break;
			}
			case "SetValue":
			{
				if (arrOfWords.length != 3 || (!(checkSetCommandsValidity(arrOfWords,"SetValue"))))
					return "ERROR, invalid use of 'SetValue' command, please do 'SetValue <deviceid> <value: double>'"; 
				String isConfirmed =this.deviceDataBase.setValue(userName, Integer.parseInt(arrOfWords[1]), Double.parseDouble(arrOfWords[2]));
				if (isConfirmed.equals("ok"))
					response = "ACK, set value is done";
				else if (isConfirmed.equals("wrong id"))
					response = "Error, no such device with the specified id";
				else
					response="Error, could not set value for this device";
				
				break;
			}
			
			default:
				return "Error, no such command '"+s+"'";
		}
		return response;
	}
	//check validity of Set commands arguments
	public boolean checkSetCommandsValidity(String[] arr,String mainCommand) {
		try {
			Integer.parseInt(arr[1]);
		}
		catch (Exception e) {
			return false;
		}
		switch (mainCommand) {
				case "SetState":
				{
					if (!(arr[2].equals("on") || arr[2].equals("off")))
						return false;
					break;
				}
				case "SetValue":
				{
					try {
						Double.parseDouble(arr[2]);
						break;
					}
					catch (Exception e) {
						return false;
					}
				}
		}
		//everything is ok
		return true;
	}

}
