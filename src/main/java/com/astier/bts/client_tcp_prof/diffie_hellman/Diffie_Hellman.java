package com.astier.bts.client_tcp_prof.diffie_hellman;

import com.astier.bts.client_tcp_prof.tcp.TCP2BINAIRE;

import java.io.IOException;
import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Arrays;

public class Diffie_Hellman {
    TCP2BINAIRE tcp;
    int nbBits;
    BigInteger p, a, g, K, A, B;
    byte[] byte_B = new byte[65535];

    public Diffie_Hellman(TCP2BINAIRE tcp, int nbBits) {
        this.tcp = tcp;
        this.nbBits = nbBits;
        a = new BigInteger(nbBits, new SecureRandom());
        p = BigInteger.probablePrime(nbBits, new SecureRandom());
        BigInteger g;
        do {
            g = new BigInteger(nbBits, new SecureRandom());
            System.out.println("g = " + g);
        } while (g.compareTo(p) > 0);
    }

    public byte[] recupParam() {
        try {
            byte[] byteArray;
            tcp.out.write(g.toByteArray());
            tcp.out.write(p.toByteArray());
            tcp.out.write(a.toByteArray());
            nbBits = tcp.in.read(byte_B);
            B = new BigInteger(Arrays.copyOfRange(byte_B, 0, nbBits));
            K = B.modPow(a, p);
            return K.toByteArray();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
