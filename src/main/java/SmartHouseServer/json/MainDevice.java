package SmartHouseServer.json;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MainDevice {

	@SerializedName("devices")
	@Expose
	private List<DeviceJson> devices = null;

	public List<DeviceJson> getDevices() {
		return devices;
	}
	public void setDevices(List<DeviceJson> devices) {
		this.devices = devices;
	}
}