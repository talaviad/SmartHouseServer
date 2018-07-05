package SmartHouseServer.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DeviceJson {
	//{  "id": 0, "state": "off", "value": 0, "holdingDifferentValues": true },
	@SerializedName("id")
	@Expose
	private int id;
	@SerializedName("state")
	@Expose
	private String state;
	@SerializedName("value")
	@Expose
	private double value;
	@SerializedName("holdingDifferentValues")
	@Expose
	private boolean holdingDifferentValues;
	@SerializedName("name")
	@Expose
	private String name;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public boolean getHoldingDifferentValues() {
		return holdingDifferentValues;
	}

	public void setHoldingDifferentValues(boolean holdingDifferentValues) {
		this.holdingDifferentValues = holdingDifferentValues;
	}
	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

}