package com.mangu.tfmjuanma.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhrasalVerb {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("meaning")
    @Expose
    private String meaning;
    @SerializedName("example")
    @Expose
    private String example;
    @SerializedName("translation")
    @Expose
    private String translation;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
