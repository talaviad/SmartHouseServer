package SmartHouseServer.json;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;

import com.google.gson.Gson;

public class ParsingUsers {

	public static UserDataBase parseUsers(){
		Gson gson =new Gson
				();
		BufferedReader br=null;
		try{
			br=new BufferedReader(new FileReader("Database//Users.json"));
		}
		catch(FileNotFoundException e){
			e.printStackTrace();
		}
		MainUsers mainUsers = gson.fromJson(br, MainUsers.class);
		if(mainUsers!=null){
			List<String> users=mainUsers.getUsers();
			return new UserDataBase(users);
		}
		return null;
	}
}