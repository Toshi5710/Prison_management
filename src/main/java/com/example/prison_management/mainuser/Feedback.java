package com.example.prison_management.mainuser;

import java.io.Serializable;

public class Feedback implements Serializable {

    private String feedback;
    private String rating;

    public Feedback(String feedback, String rating) {
        this.feedback = feedback;
        this.rating = rating;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Feedback{" +
                "feedback='" + feedback + '\'' +
                ", rating='" + rating + '\'' +
                '}';
    }
}