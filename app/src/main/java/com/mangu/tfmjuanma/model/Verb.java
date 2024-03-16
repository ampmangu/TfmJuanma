package com.mangu.tfmjuanma.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;
@Parcel
public class Verb {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("tenses")
    @Expose
    private List<Tense> tenses;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Tense> getTenses() {
        return tenses;
    }

    public void setTenses(List<Tense> tenses) {
        this.tenses = tenses;
    }
}
