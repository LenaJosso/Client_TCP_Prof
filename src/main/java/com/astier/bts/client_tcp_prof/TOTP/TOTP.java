package com.astier.bts.client_tcp_prof.TOTP;

import com.warrenstrange.googleauth.GoogleAuthenticator;

import java.util.Timer;
import java.util.TimerTask;

public class TOTP {
    GoogleAuthenticator gAuth = new GoogleAuthenticator();
    Timer timer;
    TimerTask timerTask;
    String cleTotp = "53UPAEE2VF5CXGZ6MHDE7W64VRP7CBDB";
    int codeTotp = -1;

    public void generateTotp(){
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                codeTotp = gAuth.getTotpPassword(cleTotp);
            }
        };
        timer.scheduleAtFixedRate(timerTask, 0, 1000);
    }

    public void arreterTotp(){
        timer.cancel();
    }

    public boolean testCodeTotp(int code){
        return codeTotp == code;
    }
}
