package bataranage006.controller;

public enum TopicFinder {

    MAIN_MENU("Main menu"), RULES("Rules"), HIGH_SCORE("High Scores");

    private String topic;

    TopicFinder(String s) {

        this.topic = s;
    }

    public String getTopic() {
        return topic;
    }
}
