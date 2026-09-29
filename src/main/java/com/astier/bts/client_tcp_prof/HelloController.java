package com.astier.bts.client_tcp_prof;

import com.astier.bts.client_tcp_prof.MulticastDiffusion.MulticastDifusion;
import com.astier.bts.client_tcp_prof.tcp.TCP;
import com.astier.bts.client_tcp_prof.tcp.TCP2BINAIRE;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.rmi.ServerError;
import java.util.ResourceBundle;
import static javafx.scene.paint.Color.*;

public class HelloController implements Initializable {
    public Button button;
    public Button connecter;
    public Button deconnecter;
    public TextField textFieldIP;
    public TextField textFieldPort;
    public TextField textFieldRequette;
    public Circle voyant;
    public TextArea TextAreaReponses;
    public static TCP2BINAIRE tcp;
    static boolean enRun = false;
    String adresse,port;
    public MulticastDifusion multi;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            recupConfig();
        } catch (IOException | InterruptedException e) {
            System.err.println(e);
        }
        voyant.setFill(RED);
        connecter.setOnMouseClicked(event ->
        {
            try {
                connecter();

            } catch (IOException e) {
                System.err.println(e.getMessage());
            }
        });
        deconnecter.setOnMouseClicked(event ->
        {
            try {
                deconnecter();
            } catch (InterruptedException | IOException e) {
                System.err.println(e.getMessage());
            }
        });
        button.setOnMouseClicked(event ->
        {
            try {
                envoyer();
            } catch (InterruptedException | IOException e) {
                System.err.println(e.getMessage());
            }
        });
    }


    private void envoyer() throws InterruptedException, IOException {
        String requette = textFieldRequette.getText();
        if (requette.isEmpty()) return;
        if (requette.equalsIgnoreCase("exit")){
            tcp.deconnection();
        };
        tcp.requette(requette);
    }

    private void deconnecter() throws InterruptedException, IOException {
        if(!enRun) return;
        tcp.deconnection();
        enRun = false;
    }

    private void connecter() throws IOException {
        String adresseServeur = textFieldIP.getText();
        String portServeur = textFieldPort.getText();
        if(adresseServeur.isEmpty() || portServeur.isEmpty() || enRun) return;
        if(enRun)return;
        tcp = new TCP2BINAIRE(InetAddress.getByName(adresseServeur), Integer.parseInt(portServeur), this);
        tcp.connection();
        if(tcp.socket.isConnected()){
            voyant.setFill(GREEN);
            enRun = true;
        }
    }

    private void recupConfig() throws IOException, InterruptedException {
        multi = new MulticastDifusion();
        Thread.sleep(2000);
        textFieldIP.setText(String.valueOf(multi.connexion.addr()).replace("/", ""));
        textFieldPort.setText(String.valueOf(multi.connexion.portTCP()));
    }
}