package com.mangu.tfmjuanma.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Country {
    @SerializedName("sentence")
    @Expose
    public String sentence;
    @SerializedName("example")
    @Expose
    public String example;

    public String getSentence() {
        return sentence;
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public boolean queryInCountry(String query) {
        return (query != null && !query.isEmpty()) &&
                (sentence.toLowerCase().contains(query.toLowerCase()) ||
                        example.toLowerCase().contains(query.toLowerCase()));
    }
}
