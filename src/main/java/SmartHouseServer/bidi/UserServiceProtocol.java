package SmartHouseServer.bidi;

import SmartHouseServer.json.UserDataBase;



public abstract class UserServiceProtocol implements BidiMessagingProtocol<String> {

	protected boolean loggedIn;
	protected UserDataBase userDataBase;
	protected String userName;
	protected boolean shouldTerminate=false;
	protected ConnectionsImpl connections;
	protected int connId;
	
	
	public UserServiceProtocol(UserDataBase userDataBase) {
		this.userDataBase=userDataBase;
		this.loggedIn=false;
		this.userName=null;
		this.connId = -1; //not loggedin
	}
	//the main logic of proccesing the requested commands from the client
	public String process(String message) {
		//cleaning the 'carriage return' char from the end of the string
		if ((int)message.charAt(message.length()-1) == 13) { //when using telnet the 13 ascii value sticks to the end of the command
			message = message.substring(0, message.length()-1);
		}
		String firstWord=getFirstWord(message);
		String response="";
		switch(firstWord){
			case "Login":
			{
				response = generateLogin(message);
				break;
			}
			case "Logout":
			{
				response = generateLogout(message);
				break;
			}
			/* For Further Use */
			/*case "REGISTER":
			{
				if(loggedIn)
					response= "ERROR registration failed";
				else{
					//response=generateRegister(message);
				}
				break;
			} */		
			/*case "SIGNOUT":
			{
				response = generateSignOut(message);
				break;
	
			} */
			/*case "ANY-OTHER-REQUEST":
			{
				response = handle(message);
				break;
	
			} */
			default: 
				if (!(loggedIn)) {
					return "ERROR, you must log in before ask any functionality";
				}
				response = handle(message);
			}
		return response;
	}
	//implemented by child classes for layers model implementation
	public abstract String handle(String s);
	//helper function to parse the firstword string
	public String getFirstWord(String message) {
		String toSendBack = "";
		for(int i=0;i<message.length();i++){
			if(message.charAt(i)==' ')
				break;
			else
				toSendBack=toSendBack+message.charAt(i);
		}
		return toSendBack;
	}
	//checking correct and safety input 
	//excecute the logout via the connection Object
	private String generateLogout(String message) {
		if(!(loggedIn))
			return "ERROR, you are not logged in";
		String[] array = message.split(" ");
		if(array.length == 1){
			loggedIn=false;
			this.connections.logOff(this.connId);
			this.connId = -1;
		}
		else
			return "ERROR, invalid 'Logout' command, please enter 'Logout' only" ;

		return "ACK Logout succeeded";
	}
	//checking correct and safety input 
	//excecute the login via the connection Object
	private String generateLogin(String message){
		if(loggedIn)
			return "ERROR, you are already logged in";
		String[] array = message.split(" ");
		if(array.length == 2){
			if ((array[1].charAt(0) >= 'A' && array[1].charAt(0)<= 'Z') || (array[1].charAt(0) >= 'a' && array[1].charAt(0)<= 'z')) {
				this.userName=array[1];
				if (!(this.userDataBase.isRegistered(this.userName)))
					return "Error, there is no such registered user '"+this.userName+"'";
				this.connId=this.connections.logIn(this.userName);
				if (this.connId == -1)
					return "Error, another computer using this username right now, please try later";
				loggedIn=true;
			}
			else
				return "ERROR, invalid username syntax, please enter a letter on the first char" ;
		}
		else	
			return "ERROR, invalid use of 'Login' command, please do 'Login <username>'";
		
		return "ACK Login succeeded";
	}
	//not in use in this implementation
	//its important when the server and client "agree" on key word to stop the connection, for example: 'bye'
	public boolean shouldTerminate() {
		return shouldTerminate;
	}

	public boolean getLoggedIn(){
		return this.loggedIn;
	}
	
	public void start(ConnectionsImpl connections) {
		this.connections = connections;
	}
}
