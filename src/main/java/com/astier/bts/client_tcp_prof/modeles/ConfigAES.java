package com.astier.bts.client_tcp_prof.modeles;

import com.astier.bts.client_tcp_prof.Outils.Outils;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public record ConfigAES(@SerializedName("motDePasse") String mdp, @SerializedName("iv") String vi) {
    public ConfigAES{
        Objects.requireNonNull(mdp, "truc");
        Objects.requireNonNull(vi, "bidule");
    }
    public byte [] SetmdpByte(){
        return Outils.normalizeChaine(mdp, 16);
    }
    public byte [] SetIvByte(){
        return Outils.normalizeChaine(vi, 16);
    }

}
