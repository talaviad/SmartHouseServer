package SmartHouseServer.bidi;

import java.util.function.Supplier;

public class BidiEncoderDecoderFactory implements Supplier<BidiMessageEncoderDecoder>{

	public BidiMessageEncoderDecoder get() {
		return new BidiMessageEncoderDecoder();
	}
}
