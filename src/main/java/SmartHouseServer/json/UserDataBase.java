package SmartHouseServer.json;


import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;


public class UserDataBase {

	private List<String> users;
	private AtomicBoolean userDataLock;

	
	public UserDataBase(List<String> users){
		this.users = users;	
		userDataLock = new AtomicBoolean(false);
	}
	//reached from main thread only while parsing devices
	public List<String> getUsers () {
		return users;
	}
	
	public boolean isRegistered(String username) {
		boolean answer= false;
		while (userDataLock.compareAndSet(false, true));
		for(String s:users) {
			if (s.equals(username)) {
				userDataLock.compareAndSet(true, false);
				answer= true;
				break;
			}
		}
		userDataLock.compareAndSet(true, false);
		return answer;
	}
	/*for further use, should add functionality to write users to file */
}
