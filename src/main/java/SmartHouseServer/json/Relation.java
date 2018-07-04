package SmartHouseServer.json;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relation {

	@SerializedName("id")
	@Expose
	private int id;
	@SerializedName("name")
	@Expose
	private String name;


	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
}