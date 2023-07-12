package com.example.takeadoggy.Models;

public class Question {
    private String[] answers;
    private String description;

    public String getQuestion() {
        return question;
    }

    public Question setQuestion(String question) {
        this.question = question;
        return this;
    }

    private String question;

    public String getDescription() {
        return description;
    }

    public Question setDescription(String description) {
        this.description = description;
        return this;
    }

    public String[] getAnswers() {
        return answers;
    }

    public Question setAnswers(String[] answers) {
        this.answers = answers;
        return this;
    }


}
