package com.example.prison_management.mainuser;

import java.io.Serializable;

public class VisitRules implements Serializable {

    private String rules;

    public VisitRules(String rules) {
        this.rules = rules;
    }

    public String getRules() {
        return rules;
    }

    public void setRules(String rules) {
        this.rules = rules;
    }

    @Override
    public String toString() {
        return "VisitRules{" +
                "rules='" + rules + '\'' +
                '}';
    }
}