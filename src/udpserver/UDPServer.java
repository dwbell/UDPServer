package udpserver;

import java.io.*;
import java.net.*;

public class UDPServer extends Thread {

    //Socket through which server communicates with clients
    protected DatagramSocket socket = null;
    protected boolean moreQuotes = true;

    public UDPServer() throws IOException {
        this("QuoteServerThread");
    }

    public UDPServer(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);
    }

    @Override
    public void run() {

        while (moreQuotes) { //Looping structure here though even though not used
            try {

                // Byte array for message 
                byte[] buf = new byte[256];

                // receive request
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                // respond to request
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                packet = new DatagramPacket(buf, buf.length, address, port);
                socket.send(packet);
            } catch (IOException e) {
                e.printStackTrace();
                moreQuotes = false;
            }
        }
        socket.close();
    }
}
