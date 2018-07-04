package SmartHouseServer.json;

public class Device {
	////{  "id": 0, "state": "off", "value": 0, "holdingDifferentValues": true },
	private int id;
	private String state,name;
	private double value;
	private boolean holdingDifferentValues;
	
	Device(int id, String state, double value, boolean holdingDifferentValues, String name) {
		this.id = id;
		this.state = state;
		this.value = (double)value;
		this.holdingDifferentValues = holdingDifferentValues;
		this.name = name;
	}
	
	public int getId() {
		return this.id;
	}
	
	public String getState() {
		return this.state;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Double getValue() {
		return this.value;
	}
	
	public void setState(String newState) {
		this.state = newState;
	}
	
	public void setName(String newName) {
		this.name = newName;
	}
	
	public void setValue(double newValue) {
		this.value = newValue;
	}
	
	public boolean isCapableOfMixedValus() {
		return holdingDifferentValues;
	}
	
	public String toString() {
		String version1 = "Device Status:\n"+
		"       id:"+this.id+"\n"+
		"         name:"+this.name+"\n"+
		"       state:"+this.state+"\n"+
		"       value:"+this.value+"\n";
		String version2 = "Device Status: id - "+this.id+", name - "+this.name+", state - "+this.state+", value - "+this.value;
		return version2;
	}
}
