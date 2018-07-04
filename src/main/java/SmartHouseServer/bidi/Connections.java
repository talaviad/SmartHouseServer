package SmartHouseServer.bidi;


public interface Connections {

    boolean isLoggedIn(String username);
    
    int logIn(String username);
    
    void logOff(int connectionId);
}