package com.mangu.tfmjuanma.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PhrasalVerb {

    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("meaning")
    @Expose
    public String meaning;
    @SerializedName("example")
    @Expose
    public String example;
    @SerializedName("translation")
    @Expose
    public String translation;

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

    public boolean queryInVerb(String query) {
        return (query != null && !query.isEmpty()) && (name.toLowerCase().contains(query.toLowerCase())
                || meaning.toLowerCase().contains(query.toLowerCase()));
    }
}
