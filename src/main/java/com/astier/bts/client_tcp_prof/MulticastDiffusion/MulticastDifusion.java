package com.astier.bts.client_tcp_prof.MulticastDiffusion;

import com.astier.bts.client_tcp_prof.modeles.Connexion;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.*;
import java.nio.charset.StandardCharsets;

public class MulticastDifusion {
    private final String interfaceName = "ethernet_32769";
    InetAddress ia = InetAddress.getByName("224.0.0.250");
    byte [] data = "Tu es qui?".getBytes(StandardCharsets.UTF_8);
    byte [] dataReponse = new byte[27];
    int port = 5555;
    int portReponse = 5556;
    byte ttl = 60;
    DatagramPacket dp;
    DatagramSocket dsReponse;
    MulticastSocket ms;
    public Connexion connexion;

    public MulticastDifusion() throws IOException {
            ms = new MulticastSocket();
            NetworkInterface ni = NetworkInterface.getByName(interfaceName);
            ms.setNetworkInterface(ni);
            ms.setTimeToLive(ttl);
            dp = new DatagramPacket(data, data.length, ia, port);
            dsReponse = new DatagramSocket(portReponse);
            ms.send(dp);
            new Thread(() -> {
                dp = new DatagramPacket(dataReponse, dataReponse.length);
                System.out.println("attente");
                try {
                    dsReponse.receive(dp);
                    String s = new String(dp.getData(),0 , dp.getLength());
                    String [] tableau = s.split(";");
                    connexion = new Connexion(InetAddress.getByName(tableau[0]), Integer.parseInt(tableau[1]), Integer.parseInt(tableau[2]));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("reponse : " + new String(dataReponse));
            }).start();
        }
    }

