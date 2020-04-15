package bataranage006.controller;

public class Inspiration {

    private int textId;
    private String author;
    private String inspireText;

    public Inspiration(int textId, String author, String inspireText){
        this.textId = textId;
        this.author = author;
        this.inspireText = inspireText;

    }

    public int getTextId() {
        return textId;
    }

    public void setTextId(int textId) {
        this.textId = textId;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getInspireText() {
        return inspireText;
    }

    public void setInspireText(String inspireText) {
        this.inspireText = inspireText;
    }
}
