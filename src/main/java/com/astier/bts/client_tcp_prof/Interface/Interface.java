package com.astier.bts.client_tcp_prof.Interface;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;

import java.util.Collections;

public class Interface {
    public static ArrayList<Ipv4> getSystemIP() throws SocketException {
        ArrayList<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
        ArrayList<Ipv4> ipv4s = new ArrayList<>();
        interfaces.forEach(networkInterface -> {
            try {
                if (networkInterface.isUp() && (!networkInterface.isLoopback() || !networkInterface.isVirtual())) {
                    ArrayList<InetAddress> inetAdress = Collections.list(networkInterface.getInetAddresses());
                    inetAdress.forEach(inetAddress -> ipv4s.add(new Ipv4(networkInterface.getDisplayName(), inetAddress.getHostName(), inetAddress.getHostAddress())));
                }
            } catch (SocketException e) {
                System.err.println(e);
            }
        });
        return ipv4s;
    }

}
