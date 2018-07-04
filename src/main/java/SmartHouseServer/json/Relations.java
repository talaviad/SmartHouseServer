package SmartHouseServer.json;

import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Relations {

	@SerializedName("mapping")
	@Expose
	private List<Relation> relations = null;

	public List<Relation> getRelations() {
		return relations;
	}
}