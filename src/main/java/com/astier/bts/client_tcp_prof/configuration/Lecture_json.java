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

    public void configAES(String filePath) throws FileNotFoundException {
        Gson gson = new Gson();
        FileReader fR = new FileReader(filePath);
        JsonReader jR = new JsonReader(fR);
        gson.fromJson(jR, ConfigAES.class);
    }
}
