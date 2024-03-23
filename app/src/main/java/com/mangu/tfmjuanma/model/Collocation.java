package com.mangu.tfmjuanma.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Collocation {
    @SerializedName("collocation")
    @Expose
    private String collocation;
    @SerializedName("meaning")
    private String meaning;

    public String getCollocation() {
        return collocation;
    }

    public void setCollocation(String collocation) {
        this.collocation = collocation;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
