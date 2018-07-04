package SmartHouseServer.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.function.Supplier;

import SmartHouseServer.bidi.BidiMessagingProtocol;
import SmartHouseServer.bidi.ConnectionsImpl;
import SmartHouseServer.protocols.MessageEncoderDecoder;



public abstract class BaseServer<T> implements Server<T> {

    private final int port;
    private final Supplier<BidiMessagingProtocol<T>> protocolFactory;
    private final Supplier<MessageEncoderDecoder<T>> encdecFactory;
    private ServerSocket sock;
    private ConnectionsImpl connections;

    public BaseServer(
            int port,
            Supplier<BidiMessagingProtocol<T>> protocolFactory,
            Supplier<MessageEncoderDecoder<T>> encdecFactory) {

        this.port = port;
        this.protocolFactory = protocolFactory;
        this.encdecFactory = encdecFactory;
		this.sock = null;
    }

    public void serve() {
        try (ServerSocket serverSock = new ServerSocket(port)) {
			System.out.println("Server started");
			connections = new ConnectionsImpl();
            this.sock = serverSock; //just to be able to close
            while (!Thread.currentThread().isInterrupted()) {
                Socket clientSock = serverSock.accept();
        		BidiMessagingProtocol bidiProtocol = protocolFactory.get();
                BlockingConnectionHandler<T> handler = new BlockingConnectionHandler<>(
                        clientSock,
                        encdecFactory.get(),
                        bidiProtocol);
                int connId=connections.createId(); 
        		bidiProtocol.start(connections);  
                execute(handler);
            }
        } catch (IOException ex) {
        	System.out.println(ex.getMessage());
        }
        System.out.println("server closed!!!");
    }

    public void close() throws IOException {
		if (sock != null)
			sock.close();
    }

    protected abstract void execute(BlockingConnectionHandler<T>  handler);
}
