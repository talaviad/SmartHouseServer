package SmartHouseServer.bidi;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicBoolean;

//responsible for managing the connected users

public class ConnectionsImpl implements Connections{

	private  Map<Integer, String> activeUsers;
	private int counter; 
	private AtomicBoolean connLock;
	
	public ConnectionsImpl(){
		activeUsers = new ConcurrentHashMap<Integer, String>();
		counter=0;
		connLock=new AtomicBoolean(false);
	}
	public Map getActiveUsers(){
		return activeUsers;
	}
	//create a unique id
	public int createId(){
		return counter++;
	}
	
	public boolean isLoggedIn(String username) {
		for(Integer i: activeUsers.keySet()) {
			if (activeUsers.get(i).equals(username)) {
				return true;
			}	
		}
		return false;
	}
	
	public void logOff(int connectionId) {
		while (connLock.compareAndSet(false, true));
		activeUsers.remove(connectionId);
		connLock.compareAndSet(true, false);
	}
	
	public int logIn(String username) {
		int connId = -1;
		while (connLock.compareAndSet(false, true));
		boolean loggedInOrNot = isLoggedIn(username);
		if (!(loggedInOrNot)) {
			connId = createId();
			getActiveUsers().put(connId,username);
		}
		connLock.compareAndSet(true, false);
		return connId;
	}
}
