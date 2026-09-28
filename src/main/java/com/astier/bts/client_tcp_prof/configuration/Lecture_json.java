package com.astier.bts.client_tcp_prof.configuration;

import com.astier.bts.client_tcp_prof.modeles.ConfigAES;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;

public class Lecture_json {
    String filePath;

    public Lecture_json(String _filePath) {
        this.filePath = _filePath;
    }

    public ConfigAES getConfigAES() {
        try {
            Gson gson = new Gson();
            FileReader fR = null;
            fR = new FileReader(filePath);
            JsonReader jR = new JsonReader(fR);
            return gson.fromJson(jR, ConfigAES.class);
        } catch (FileNotFoundException e) {
            System.err.println(e);
            return null;
        }
    }
}
