package SmartHouseServer.json;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;


public class DevicesDataBase {

	private Map devices;
	private Map idsAndNames;
	private UserDataBase userDataBase=null;

	/*there is no need to synchronize this database because of the "each user get his own file" implementation */
	public DevicesDataBase(UserDataBase userDataBase){
		this.userDataBase = userDataBase;
		devices = new ConcurrentHashMap<String,ArrayList<Device>>(); 
		idsAndNames = new ConcurrentHashMap<Integer,String>();
	}
	//in the beginning of the program
	public void addUserAndDevices(String user, ArrayList<Device> devices){
		this.devices.putIfAbsent(user, devices);	
	}
	
	public void addMappedIdAndName(int deviceId, String deviceName) {
		idsAndNames.put(new Integer(deviceId), deviceName);
	}
	
	public UserDataBase getUserDataBase() {
		return this.userDataBase;
	}

	public void writeToFiles(String user){
		ConcurrentHashMap<String,ArrayList<Device>> devices= (ConcurrentHashMap<String, ArrayList<Device>>) this.devices;
		MainDevice mainDevice = new MainDevice();
		ArrayList<DeviceJson> list = new ArrayList<DeviceJson>();
		mainDevice.setDevices(list);	
		for(int i=0; i<devices.get(user).size(); i++ ){
			DeviceJson dj = new DeviceJson();
			dj.setId(devices.get(user).get(i).getId());
			dj.setState(devices.get(user).get(i).getState());
			dj.setValue(devices.get(user).get(i).getValue());
			dj.setHoldingDifferentValues(devices.get(user).get(i).isCapableOfMixedValus());
			dj.setState(devices.get(user).get(i).getName());
			list.add(dj);
		}
		ObjectMapper mapper = new ObjectMapper();
		try {
			//to write to the needed file
			mapper.writerWithDefaultPrettyPrinter().writeValue(new File("Database\\"+user+".json"),mainDevice);
		}
		catch (JsonGenerationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
	
	public ArrayList<Device> getUserDevices(String user){
		return (ArrayList<Device>) this.devices.get(user);
	}
	
	public String getDevices(String user) {
		ArrayList<Device> listOfDevices =(ArrayList<Device>) this.devices.get(user);
		String userDevices="";
		for(int i=0; i<listOfDevices.size(); i++) {
			userDevices=userDevices+listOfDevices.get(i).toString()+"\n";
		}
		return userDevices;
	}
	
	public void setState(String user, int idOfDevice, String newState) {
		getUserDevices(user).get(idOfDevice).setState(newState);
		writeToFiles(user);
	}
	//SetValue <deviceid> <value: double> -
	public String setValue(String user,int idOfDevice, double newValue) {
		ArrayList<Device> userDevices = getUserDevices(user);
		if (userDevices.size() < (idOfDevice+1))
			return "wrong id";
		if(userDevices.get(idOfDevice).isCapableOfMixedValus()) {
			userDevices.get(idOfDevice).setValue(newValue);
			writeToFiles(user);
			return "ok";
		}
		return "not available";  //device cannot be set to various values
	}
	
	public String getDeviceState(int idOfDevice, String user) {
		ArrayList<Device> userDevices = getUserDevices(user);
		if (userDevices.size() < (idOfDevice+1))
			return null;
		return userDevices.get(idOfDevice).getState();
	}
}
