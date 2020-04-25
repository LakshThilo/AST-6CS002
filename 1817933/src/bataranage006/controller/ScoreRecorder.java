package bataranage006.controller;

import java.io.FileWriter;
import java.io.PrintWriter;

public class ScoreRecorder {

    private String playerName;
    private int score;

    public ScoreRecorder(String playerName, int score) {

        this.playerName = playerName;
        this.score = score;
        System.out.println("debugging");
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("score.txt", true));
            String n = playerName.replaceAll(",", "_");
            pw.print(n);
            pw.print(",");
            pw.print(score);
            pw.print(",");
            pw.println(System.currentTimeMillis());
            pw.flush();
            pw.close();
        } catch (Exception e) {
            System.out.println("Something went wrong saving scores");
        }
    }
}
