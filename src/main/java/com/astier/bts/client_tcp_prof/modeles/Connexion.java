package com.astier.bts.client_tcp_prof.modeles;

import java.net.InetAddress;

public record Connexion(InetAddress addr, int portTCP, int portUDP) {
}
