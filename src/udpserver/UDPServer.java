package udpserver;

import java.io.*;
import java.net.*;
import java.util.ArrayList;

public class UDPServer extends Thread {

    private boolean running = true;
    private DatagramSocket socket = null;
    private ArrayList<ClientInfo> clients = new ArrayList<>();

    public UDPServer() throws IOException {
        this("ServerThread");
    }

    public UDPServer(String name) throws IOException {
        super(name);
        socket = new DatagramSocket(4445);
    }

    @Override
    public void run() {

        //Infinite Loop
        while (running) {

            try {
                /*Recieve a packet*/
                byte[] buf = new byte[256];
                DatagramPacket packet = new DatagramPacket(buf, buf.length);
                socket.receive(packet);

                //First new client
                if (clients.isEmpty()) {
                    clients.add(new ClientInfo(packet, buf));

                    //Check if client is a new client
                } else {
                    boolean match = false;
                    for (ClientInfo client : clients) {
                        //Check for a match with existing clients
                        if (packet.getAddress().equals(client.getAddress()) && packet.getPort() == client.getPortNo()) {
                            match = true;
                        }
                    }

                    //If there was no match add it to list
                    if (!match) {
                        clients.add(new ClientInfo(packet, buf));
                    } else {
                        //if there was a match behaviour here
                    }
                }

                /*Respond to all clients with echo of there own message*/
                for (ClientInfo client : clients) {
                    System.out.println("Address: " + client.getAddress());
                    System.out.println("Port Number: " + client.getPortNo());
                    System.out.println("# of Clients: " + clients.size());
                    System.out.println();
                    packet = new DatagramPacket(buf, buf.length, client.getAddress(), client.getPortNo());
                    socket.send(packet);
                }

            } catch (IOException e) {
                e.printStackTrace();
                running = false;
            }

        }
        socket.close();
    }
}
