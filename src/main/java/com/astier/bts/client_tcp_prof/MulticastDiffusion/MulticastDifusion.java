package com.astier.bts.client_tcp_prof.MulticastDiffusion;

import java.io.IOException;
import java.net.*;

public class MulticastDifusion {
    private final String interfaceName = "ethernet_32769";
    InetAddress ia = InetAddress.getByName("224.0.0.250");
    byte [] data = "Tu es qui?".getBytes();
    byte [] dataReponse = new byte[27];
    int port = 55555;
    int portReponse = 5556;
    byte ttl = 60;
    DatagramPacket dp;
    DatagramSocket dsReponse;
    MulticastSocket ms;

    public MulticastDifusion() throws IOException {
            ms = new MulticastSocket();
            NetworkInterface ni = NetworkInterface.getByName(interfaceName);
            ms.setNetworkInterface(ni);
            ms.setTimeToLive(ttl);
            dp = new DatagramPacket(data, data.length, ia, port);
            ms.send(dp);
            new Thread(() -> {
                dp = new DatagramPacket(dataReponse, dataReponse.length);
                System.out.println("attente");
                try {
                    dsReponse.receive(dp);
                } catch (IOException e) {
                    System.err.println(e);
                }
                System.out.println("reponse : " + new String(dataReponse));
            }).start();
        }
    }

