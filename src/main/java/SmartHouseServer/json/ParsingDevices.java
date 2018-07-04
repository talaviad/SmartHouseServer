package SmartHouseServer.json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

public class ParsingDevices {

	public static DevicesDataBase parseDevices(UserDataBase userDataBase){
		List<String> users = userDataBase.getUsers();
		DevicesDataBase deviceDataBase = new DevicesDataBase(userDataBase);
		Gson gson =new Gson();
		BufferedReader br=null;
		for (int i=0; i<users.size(); i++ ) {
			try{
				br=new BufferedReader(new FileReader("Database//"+users.get(i)+".json"));
			}
			catch(FileNotFoundException e){
				e.printStackTrace();
			}
			MainDevice mainDevice = gson.fromJson(br, MainDevice.class);
			if(mainDevice!=null){
				List<DeviceJson> jsonDevices =mainDevice.getDevices();
				ArrayList<Device> devices = new ArrayList<Device>();
				for(int j=0;j<jsonDevices.size();j++){
					Device device = new Device(
							jsonDevices.get(j).getId(),
							jsonDevices.get(j).getState(),
							jsonDevices.get(j).getValue(),
							jsonDevices.get(j).getHoldingDifferentValues(),
							jsonDevices.get(j).getName()
					);
					devices.add(device);
				}
				deviceDataBase.addUserAndDevices(users.get(i),devices);
			}
		}
		try{
			br=new BufferedReader(new FileReader("Database//DevicesIdsAndNames.json"));
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		Relations relations = gson.fromJson(br, Relations.class);
		for (int k=0;k<relations.getRelations().size();k++) {
			int deviceId = relations.getRelations().get(k).getId();
			String deviceName = relations.getRelations().get(k).getName();
			deviceDataBase.addMappedIdAndName(deviceId, deviceName);
		}
		return deviceDataBase;
	}
}
