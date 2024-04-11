package com.mangu.tfmjuanma.model;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Hobby {
    @SerializedName("hobby")
    @Expose
    public String hobby;
    @SerializedName("translation")
    @Expose
    public String translation;

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }

    public boolean queryInHobby(String query) {
        return (query != null && !query.isEmpty()) &&
                (hobby.toLowerCase().contains(query.toLowerCase()) ||
                        translation.toLowerCase().contains(query.toLowerCase()));
    }
}
