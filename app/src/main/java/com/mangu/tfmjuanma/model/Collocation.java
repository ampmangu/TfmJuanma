package com.mangu.tfmjuanma.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Collocation {
    @SerializedName("collocation")
    @Expose
    public String collocation;
    @SerializedName("meaning")
    public String meaning;

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

    public boolean queryInCollocation(String query) {
        return (query != null && !query.isEmpty()) &&
                (collocation.toLowerCase().contains(query.toLowerCase()) ||
                        meaning.toLowerCase().contains(query.toLowerCase()));
    }
}
