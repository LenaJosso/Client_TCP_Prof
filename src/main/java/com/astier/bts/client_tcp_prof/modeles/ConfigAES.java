package com.astier.bts.client_tcp_prof.modeles;

import com.astier.bts.client_tcp_prof.Outils;
import com.google.gson.annotations.SerializedName;

public record ConfigAES(@SerializedName("motDePasse") String mdp, @SerializedName("iv") String vi) {
    public byte [] mdpByte(){
        return Outils.normalizeChaine(mdp, 16);
    }
    public byte [] ivByte(){
        return Outils.normalizeChaine(vi, 16);
    }

}
