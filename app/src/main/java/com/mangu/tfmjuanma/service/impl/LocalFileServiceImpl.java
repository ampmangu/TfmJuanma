package com.mangu.tfmjuanma.service.impl;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mangu.tfmjuanma.model.Adjective;
import com.mangu.tfmjuanma.model.Collocation;
import com.mangu.tfmjuanma.model.Country;
import com.mangu.tfmjuanma.model.Health;
import com.mangu.tfmjuanma.model.Hobby;
import com.mangu.tfmjuanma.model.Method;
import com.mangu.tfmjuanma.model.PhrasalVerb;
import com.mangu.tfmjuanma.model.Verb;
import com.mangu.tfmjuanma.service.FileService;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
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

    @Override
    public List<Collocation> getCollocations() {
        List<Collocation> collocations = new ArrayList<>();
        try {
            collocations = getCollocationsFromAssets();
        } catch (IOException e) {
            Log.e("LocalFileService", e.getLocalizedMessage());
        }
        return collocations;
    }

    @Override
    public List<Adjective> getAdjectives() {
        List<Adjective> adjectiveList = new ArrayList<>();
        try {
            adjectiveList = getAdjectivesFromAssets();
        } catch (IOException e) {
            Log.e("LocalFileService", e.getLocalizedMessage());
        }
        return adjectiveList;
    }

    @Override
    public List<Hobby> getHobbies() {
        List<Hobby> hobbyList = new ArrayList<>();
        try {
            hobbyList = getHobbiesFromAssets();
        } catch (IOException e) {
            Log.e("LocalFileService", e.getLocalizedMessage());
        }
        return hobbyList;
    }

    @Override
    public List<Country> getCountries() {
        List<Country> countryList = new ArrayList<>();
        try {
            countryList = getCountriesFromAssets();
        } catch (IOException e) {
            Log.e("LocalFileService", e.getLocalizedMessage());
        }
        return countryList;
    }

    @Override
    public List<Health> getHealthElements() {
        List<Health> healthList = new ArrayList<>();
        try {
            healthList = getHealthElementsFromAssets();
        } catch (IOException e) {
            Log.e("LocalFileService", e.getLocalizedMessage());
        }
        return healthList.stream()
                .filter(health -> !health.getWord().isEmpty()).collect(Collectors.toList());
    }

    @Override
    public List<Method> getMethods() {
        List<Method> methodList = new ArrayList<>();
        try {
            methodList = getMethodsFromAssets();
        } catch (IOException e) {
            Log.e("LocalFileService", e.getLocalizedMessage());
        }
        return methodList;
    }

    private List<Method> getMethodsFromAssets() throws IOException {
        AssetManager assetManager = this.appContext.getAssets();
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(assetManager.open("methods.json")),
                new TypeToken<List<Method>>() {
                }.getType());
    }

    private List<Health> getHealthElementsFromAssets() throws IOException {
        AssetManager assetManager = this.appContext.getAssets();
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(assetManager.open("health.json")),
                new TypeToken<List<Health>>() {
                }.getType());
    }

    private List<Country> getCountriesFromAssets() throws IOException {
        AssetManager assetManager = this.appContext.getAssets();
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(assetManager.open("countries.json")),
                new TypeToken<List<Country>>() {
                }.getType());
    }

    private List<Hobby> getHobbiesFromAssets() throws IOException {
        AssetManager assetManager = this.appContext.getAssets();
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(assetManager.open("hobbies.json")),
                new TypeToken<List<Hobby>>() {
                }.getType());
    }

    private List<Adjective> getAdjectivesFromAssets() throws IOException {
        AssetManager assetManager = this.appContext.getAssets();
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(assetManager.open("adjectives.json")),
                new TypeToken<List<Adjective>>() {
                }.getType());
    }

    private List<Collocation> getCollocationsFromAssets() throws IOException {
        AssetManager assetManager = this.appContext.getAssets();
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(assetManager.open("collocations.json")),
                new TypeToken<List<Collocation>>() {
                }.getType());
    }

    private List<Verb> getVerbsFromAssets() throws IOException {
        List<Verb> verbList = new ArrayList<>();
        AssetManager assetManager = this.appContext.getAssets();
        Gson gson = new Gson();
        String[] verbs = assetManager.list("verbs");
        if (verbs == null) {
            return verbList;
        }
        for (String json : verbs) {
            verbList.add(gson.fromJson(
                    new InputStreamReader(assetManager.open("verbs/" + json)), Verb.class));
        }
        return verbList;
    }

    private List<PhrasalVerb> getPhrasalVerbsFromAssets() throws IOException {
        AssetManager assetManager = this.appContext.getAssets();
        Gson gson = new Gson();
        return gson.fromJson(new InputStreamReader(assetManager.open("phrasal_verbs.json")),
                new TypeToken<List<PhrasalVerb>>() {
                }.getType());

    }
}
