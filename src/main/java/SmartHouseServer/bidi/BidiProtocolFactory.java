package SmartHouseServer.bidi;

import java.util.function.Supplier;



public class BidiProtocolFactory implements Supplier<BidiMessagingProtocol> {

	public BidiMessagingProtocol get() {
		return new SmartFunctionalityServiceProtocol(null/*movieDataBase*/);
	}
}