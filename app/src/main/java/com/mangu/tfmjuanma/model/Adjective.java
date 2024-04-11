package com.mangu.tfmjuanma.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Adjective {
    @SerializedName("adjective")
    @Expose
    public String adjective;
    @SerializedName("translation")
    @Expose
    public String translation;

    public String getAdjective() {
        return adjective;
    }

    public void setAdjective(String adjective) {
        this.adjective = adjective;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public boolean queryInAdjective(String query) {
        return (query != null && !query.isEmpty()) &&
                (adjective.toLowerCase().contains(query.toLowerCase()) ||
                        translation.toLowerCase().contains(query.toLowerCase()));
    }
}
