package com.mangu.tfmjuanma.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Parcel
public class Question {
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("answers")
    @Expose
    public List<String> answers;

    @SerializedName("positionOfAnswer")
    @Expose
    public int positionOfAnswer;

    public static Question fromString(String line) {
        Question question = new Question();
        String[] questionSplit = line.split(";");
        if (questionSplit.length > 5) {
            question.setTitle(questionSplit[0]);
            List<String> answers = new ArrayList<>();
            answers.add(questionSplit[1]);
            answers.add(questionSplit[2]);
            answers.add(questionSplit[3]);
            answers.add(questionSplit[4]);
            question.setAnswers(answers);
            question.setPositionOfAnswer(Integer.parseInt(questionSplit[5].trim()));
        }
        return question;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public int getPositionOfAnswer() {
        return positionOfAnswer;
    }

    public void setPositionOfAnswer(int positionOfAnswer) {
        this.positionOfAnswer = positionOfAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return positionOfAnswer == question.positionOfAnswer && Objects.equals(title, question.title) && Objects.equals(answers, question.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(title, answers, positionOfAnswer);
    }
}
