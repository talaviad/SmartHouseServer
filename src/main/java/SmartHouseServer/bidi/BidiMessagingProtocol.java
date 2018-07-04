package SmartHouseServer.bidi;


public interface BidiMessagingProtocol<T> {

    T process(T message);
    
    void start(ConnectionsImpl connections);
	/**
     * @return true if the connection should be terminated
     */
    boolean shouldTerminate();
}