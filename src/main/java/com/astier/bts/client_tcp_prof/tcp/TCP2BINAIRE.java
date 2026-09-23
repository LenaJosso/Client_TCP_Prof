/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.astier.bts.client_tcp_prof.tcp;


import com.astier.bts.client_tcp_prof.Aes_cbc;
import com.astier.bts.client_tcp_prof.DiagnosticException;
import com.astier.bts.client_tcp_prof.HelloController;
import com.astier.bts.client_tcp_prof.modeles.ConfigAES;
import com.sun.nio.sctp.SctpSocketOption;
import javafx.application.Platform;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;



import static javafx.scene.paint.Color.RED;

/**
 * @author Michael
 */
public class TCP2BINAIRE extends Thread {

    int port;
    InetAddress serveur;
    public Socket socket;
    boolean marche = false;
    boolean connection = false;
    OutputStream out;
    InputStream in;
    byte [] bufferEntree = new byte[65535];
    HelloController fxmlCont;
    Aes_cbc aes = new Aes_cbc(ConfigAES.mdpByte(), ConfigAES.ivByte());

    public TCP2BINAIRE() {
    }

    public TCP2BINAIRE(InetAddress serveur, int port, HelloController fxmlCont) throws IOException {
        this.port = port;
        this.serveur = serveur;
        this.fxmlCont = fxmlCont;

        System.out.println("@ serveur: " + serveur + " port: " + port);
    }



    public void connection() throws IOException {
        if (this.isAlive()){
            return;
        } try {
            this.socket = new Socket();
            SocketAddress endpoint = new InetSocketAddress(serveur, port);
            this.socket.connect(endpoint, 2000);
            out = socket.getOutputStream();
            in = socket.getInputStream();
            marche = true;
            this.start();
        }catch (IOException e){
            updateMessage(DiagnosticException.afficheException(e));
        }
    }

    public void deconnection() throws InterruptedException, IOException {
        if(!this.isAlive()) return;
        try {
            marche=false;
            fxmlCont.voyant.setFill(RED);
            out.write("exit".getBytes(StandardCharsets.UTF_8));
            out.flush();
            Thread.sleep(1000);
            in.close();
            out.close();
            socket.close();
        }catch(IOException e){
            updateMessage(DiagnosticException.afficheException(e));
        }

    }

    public void requette(String laRequette) throws IOException {
         out.write(aes.cryptage((laRequette + "\n").getBytes(StandardCharsets.UTF_8)));
         out.flush();
         System.out.println("la requette \n" + laRequette);
    }

    public void run() {
        String message;

        while (marche) {
            int nbLus = 0;
            try {
                nbLus = in.read(bufferEntree);
            } catch (IOException e) {
                updateMessage(DiagnosticException.afficheException(e));
            }
            if (nbLus == 0)
                break;

            byte [] trame = Arrays.copyOfRange(bufferEntree, 0, nbLus);
            message = new String (aes.decryptage(trame));
            updateMessage(message);
            System.out.println("MESSAGE SERVEUR" + "\n" + message + "\n");

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