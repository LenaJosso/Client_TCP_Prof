/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.astier.bts.client_tcp_prof.tcp;


import com.astier.bts.client_tcp_prof.HelloController;
import com.sun.nio.sctp.SctpSocketOption;
import javafx.application.Platform;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;


import static javafx.scene.paint.Color.RED;

/**
 * @author Michael
 */
public class TCP extends Thread {
    int port;
    InetAddress serveur;
    Socket socket;
    boolean marche = false;
    boolean connection = false;
    PrintStream out;
    BufferedReader in;

    HelloController fxmlCont;

    public TCP() {
    }

    public TCP(InetAddress serveur, int port, HelloController fxmlCont) throws IOException {
        this.port = port;
        this.serveur = serveur;
        this.fxmlCont = fxmlCont;
        System.out.println("@ serveur: " + serveur + " port: " + port);
    }



    public void connection() throws IOException {
       if (this.isAlive()){
           return;
        }
            marche = true;
            socket = new Socket(serveur.getHostName(), port);
            in = new BufferedReader ( new InputStreamReader(socket.getInputStream()));
            out = new PrintStream(socket.getOutputStream(), true);
            this.start();
       }

    public void deconnection() throws InterruptedException, IOException {
        in.close();
        out.close();
    }

    public void requette(String laRequette) throws IOException {
        out.println(laRequette);  // envoi reseau
        System.out.println("la requette " + laRequette);
    }

    public void run() {
        while (marche) {
            String message = null;
            char [] buffer = new char[65335];
            int nbLus = 0;
            try {
                nbLus = in.read(buffer);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            if (nbLus > 0 ){
                message = new String(buffer, 0, nbLus);
                updateMessage(message);
            }
        }
    }


    /*
    Pour déclencher une opération graphique en dehors du thread graphique  utiliser
    javafx.application.Platform.runLater(java.lang.Runnable)
    Cette méthode permet d'éxécuter le code du runnable par le thread graphique de JavaFX.
    */
    protected void updateMessage(String message) {
        Platform.runLater(() -> fxmlCont.TextAreaReponses.appendText("    MESSAGE SERVEUR >  \n      " + message + "\n"));
    }
}