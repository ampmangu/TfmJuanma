package com.mangu.tfmjuanma.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Objects;

public class Tense {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("firstsingular")
    @Expose
    private String firstsingular;
    @SerializedName("secondsingular")
    @Expose
    private String secondsingular;
    @SerializedName("thirdsingular")
    @Expose
    private String thirdsingular;
    @SerializedName("firstplural")
    @Expose
    private String firstplural;
    @SerializedName("secondplural")
    @Expose
    private String secondplural;
    @SerializedName("thirdplural")
    @Expose
    private String thirdplural;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstsingular() {
        return firstsingular;
    }

    public void setFirstsingular(String firstsingular) {
        this.firstsingular = firstsingular;
    }

    public String getSecondsingular() {
        return secondsingular;
    }

    public void setSecondsingular(String secondsingular) {
        this.secondsingular = secondsingular;
    }

    public String getThirdsingular() {
        return thirdsingular;
    }

    public void setThirdsingular(String thirdsingular) {
        this.thirdsingular = thirdsingular;
    }

    public String getFirstplural() {
        return firstplural;
    }

    public void setFirstplural(String firstplural) {
        this.firstplural = firstplural;
    }

    public String getSecondplural() {
        return secondplural;
    }

    public void setSecondplural(String secondplural) {
        this.secondplural = secondplural;
    }

    public String getThirdplural() {
        return thirdplural;
    }

    public void setThirdplural(String thirdplural) {
        this.thirdplural = thirdplural;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tense tense = (Tense) o;
        return Objects.equals(name, tense.name) && Objects.equals(firstsingular, tense.firstsingular) && Objects.equals(secondsingular, tense.secondsingular) && Objects.equals(thirdsingular, tense.thirdsingular) && Objects.equals(firstplural, tense.firstplural) && Objects.equals(secondplural, tense.secondplural) && Objects.equals(thirdplural, tense.thirdplural);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, firstsingular, secondsingular, thirdsingular, firstplural, secondplural, thirdplural);
    }

    @NonNull
    @Override
    public String toString() {
        return "Tense{" +
                "name='" + name + '\'' +
                ", firstsingular='" + firstsingular + '\'' +
                ", secondsingular='" + secondsingular + '\'' +
                ", thirdsingular='" + thirdsingular + '\'' +
                ", firstplural='" + firstplural + '\'' +
                ", secondplural='" + secondplural + '\'' +
                ", thirdplural='" + thirdplural + '\'' +
                '}';
    }
}
