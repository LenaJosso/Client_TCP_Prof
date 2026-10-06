module com.astier.bts.client_tcp_prof {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires jdk.sctp;
    requires java.rmi;
    requires java.net.http;
    requires com.google.gson;
requires googleauth;

    opens com.astier.bts.client_tcp_prof to javafx.fxml;
    opens com.astier.bts.client_tcp_prof.modeles to com.google.gson;
    exports com.astier.bts.client_tcp_prof;
    exports com.astier.bts.client_tcp_prof.modeles;
    exports com.astier.bts.client_tcp_prof.Outils;
    opens com.astier.bts.client_tcp_prof.Outils to javafx.fxml;
    exports com.astier.bts.client_tcp_prof.Aes_cbc;
    opens com.astier.bts.client_tcp_prof.Aes_cbc to javafx.fxml;

}