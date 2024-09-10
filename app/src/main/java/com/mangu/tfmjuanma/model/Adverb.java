package com.mangu.tfmjuanma.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Adverb {
    @SerializedName("adverb")
    @Expose
    private String adverb;
    @SerializedName("translation")
    @Expose
    private String translation;

    public String getAdverb() {
        return adverb;
    }

    public void setAdverb(String adverb) {
        this.adverb = adverb;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public boolean adverbInQuery(String query) {
        return (query != null && !query.isEmpty()) &&
                (adverb.toLowerCase().contains(query.toLowerCase())
                        || translation.toLowerCase().contains(query.toLowerCase())
                );
    }
}
