package SmartHouseServer.server;

import java.io.Closeable;


public interface ConnectionHandler<T> extends Closeable{

    void send(T msg) ;

}
