package com.astier.bts.client_tcp_prof;

import com.astier.bts.client_tcp_prof.tcp.TCP;
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
    public TextField TextFieldIP;
    public TextField TextFieldPort;
    public TextField TextFieldRequette;
    public Circle voyant;
    public TextArea TextAreaReponses;
    static public TCP tcp;
    static boolean enRun = false;
    String adresse,port;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            TCP tcp = new TCP(InetAddress.getByName("127.0.0.1"), 4000, this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        voyant.setFill(RED);
        connecter.setOnMouseClicked(event ->
                {
                    try {
                        connecter();
                    } catch (IOException e) {
                        System.err.println(e.getMessage());
                    }
                }
        );
        deconnecter.setOnMouseClicked(event ->
        {
            try {
                deconnecter();
            } catch (InterruptedException | IOException e) {
                System.err.println(e.getMessage());
            }
        });
    }


    private void envoyer() throws InterruptedException {

    }

    private void deconnecter() throws InterruptedException, IOException {
        //todo
        voyant.setFill(RED);
        tcp.deconnection();
    }

    private void connecter() throws IOException {
        //todo
        voyant.setFill(GREEN);
        tcp.connection();
    }

}