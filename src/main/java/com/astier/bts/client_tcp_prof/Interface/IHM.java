package com.astier.bts.client_tcp_prof.Interface;

import java.net.SocketException;
import java.util.ArrayList;

public class IHM {
    public static void main(String[] args) throws SocketException {
        ArrayList<Ipv4> lesIpv4 = Interface.getSystemIP();
        lesIpv4.forEach(ipv4 ->
                        lesIpv4.forEach(System.out::println));
    }
}
