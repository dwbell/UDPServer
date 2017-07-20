package udpserver;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class Client {

    private InetAddress address;
    private DatagramPacket packet;
    private byte[] buf;

    public Client(InetAddress address, DatagramPacket packet) {
        this.address = address;
        this.packet = packet;
        buf = new byte[256];
    }

}
