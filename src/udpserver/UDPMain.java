package udpserver;

import java.io.IOException;

public class UDPMain {

    public static void main(String[] args) throws IOException {
        new UDPServer().start();
    }
}
