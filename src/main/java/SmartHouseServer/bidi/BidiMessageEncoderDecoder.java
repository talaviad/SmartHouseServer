package SmartHouseServer.bidi;


import java.nio.charset.StandardCharsets;
import java.util.Arrays;

import SmartHouseServer.protocols.MessageEncoderDecoder;

public class BidiMessageEncoderDecoder implements MessageEncoderDecoder<String> {

    private byte[] bytes = new byte[1 << 10]; //start with 1k
    private int len = 0;

 
    public String decodeNextByte(byte nextByte) {
        //notice that the top 128 ascii characters have the same representation as their utf-8 counterparts
        //this allow to do the following comparison
        if (nextByte == '\n') {
            return popString();
        }
        pushByte(nextByte);
        return null; //not a line yet
    }

    private void pushByte(byte nextByte) {
        if (len >= bytes.length) {
            bytes = Arrays.copyOf(bytes, len * 2);
        }
        bytes[len++] = nextByte;
    }

    private String popString() {
        String result = new String(bytes, 0, len, StandardCharsets.UTF_8);
        len = 0;
        return result;
    }

	public byte[] encode(String message) {
		return (message + "\n").getBytes(); //uses utf8 by default
	}

}
