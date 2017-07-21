package udpserver;

import java.net.DatagramPacket;
import java.net.InetAddress;

public class ClientInfo {

    private InetAddress address;
    private int port;
    private DatagramPacket packet;
    private byte[] buf = new byte[256];

    public ClientInfo(DatagramPacket packet, byte[] buf) {
        this.packet = packet;
        this.buf = buf;
        this.address = packet.getAddress();
        this.port = packet.getPort();
    }

    public InetAddress getAddress() {
        return this.address;
    }

    public int getPortNo() {
        return this.port;
    }

    public byte[] getBuf() {
        return this.buf;
    }
}
