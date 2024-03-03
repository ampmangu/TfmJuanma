package com.mangu.tfmjuanma.service.impl;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mangu.tfmjuanma.model.PhrasalVerb;
import com.mangu.tfmjuanma.model.Verb;
import com.mangu.tfmjuanma.service.FileService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Use assets folder.
 */
public class LocalFileServiceImpl implements FileService {

    private Context appContext;

    public LocalFileServiceImpl(Context appContext) {
        this.appContext = appContext;
    }

    @Override
    public List<Verb> getVerbs() {
       List<Verb> verbs = new ArrayList<>();
       try {
            verbs = getVerbsFromAssets();
       } catch (IOException e) {
           Log.e("LocalFileService", e.getLocalizedMessage());
       }
       return verbs;
    }

    @Override
    public List<PhrasalVerb> getPhrasalVerbs() {
        List<PhrasalVerb> verbs = new ArrayList<>();
        try {
            verbs = getPhrasalVerbsFromAssets();
        } catch (IOException e) {
            Log.e("LocalFileService", e.getLocalizedMessage());
        }
        return verbs;
    }

    private List<Verb> getVerbsFromAssets() throws IOException {
        AssetManager assetManager = this.appContext.getAssets();
        Gson gson = new Gson();
        String[] verbs = assetManager.list("verbs");
        if (verbs == null) {
            return new ArrayList<>();
        }
        return Arrays.stream(verbs).map(json -> gson.fromJson(json, Verb.class)).collect(Collectors.toList());
    }

    private List<PhrasalVerb> getPhrasalVerbsFromAssets() throws IOException {
        AssetManager assetManager = this.appContext.getAssets();
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(assetManager.open("phrasal_verbs.json")),
                new TypeToken<List<PhrasalVerb>>(){}.getType());

    }
}
