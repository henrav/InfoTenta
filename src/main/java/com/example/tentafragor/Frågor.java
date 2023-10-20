package com.example.tentafragor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Frågor {
    private String QuestionText;
    private String[] AnswerText;
    private int CorrectAnswer;
    private String explanation;

    public Frågor(String QuestionText, String[] AnswerText, int CorrectAnswer, String explanation) {
        this.QuestionText = QuestionText;
        this.AnswerText = AnswerText;
        this.CorrectAnswer = CorrectAnswer;
        this.explanation = explanation;
    }
    public String getExplanation() {
        return explanation;
    }

    public String getQuestionText() {
        return QuestionText;
    }

    public String[] getAnswerText() {
        return AnswerText;
    }

    public int getCorrectAnswer() {
        return CorrectAnswer;
    }

    public void setQuestionText(String QuestionText) {
        this.QuestionText = QuestionText;
    }

    public void setAnswerText(String[] AnswerText) {
        this.AnswerText = AnswerText;
    }

    public void setCorrectAnswer(int CorrectAnswer) {
        this.CorrectAnswer = CorrectAnswer;
    }

    public String toString() {
        return QuestionText + " " + AnswerText + " " + CorrectAnswer;
    }

    public boolean returnCorrectAnswer(int Answer) {
        if (Answer == CorrectAnswer) {
            return true;
        } else {
            return false;
        }
    }
    // frågorLista[0] = new Frågor("Vad är 2+2?", new String[]{"1", "2", "3", "4"}, 4);
    public String[] getAlternativText() {
        return AnswerText;
    }
    public void shuffleAnswers(){
        ArrayList hej = new ArrayList();
        String hejsan = AnswerText[CorrectAnswer - 1];
        for (int i = 0; i < AnswerText.length; i++) {
            hej.add(AnswerText[i]);
        }
        Collections.shuffle(hej);
        for (int i = 0; i < AnswerText.length; i++) {
            AnswerText[i] = (String) hej.get(i);
        }
        for (int i = 0; i < AnswerText.length; i++) {
            if (AnswerText[i].equals(hejsan)) {
                CorrectAnswer = i + 1;
            }
        }
    }

    // frågorLista[0] = new Frågor("Vad är 2+2?", new String[]{"1", "2", "3", "4"}, 4);
    //get alternativ text x utan 4

}
