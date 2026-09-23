module com.astier.bts.client_tcp_prof {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.logging;
    requires jdk.sctp;
    requires java.rmi;
    requires java.net.http;
    requires com.google.gson;


    opens com.astier.bts.client_tcp_prof to javafx.fxml;
    opens com.astier.bts.client_tcp_prof.modeles to com.google.gson;
    exports com.astier.bts.client_tcp_prof;
    exports com.astier.bts.client_tcp_prof.modeles;

}