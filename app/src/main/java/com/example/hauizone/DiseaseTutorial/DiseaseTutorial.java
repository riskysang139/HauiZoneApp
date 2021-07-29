package com.example.hauizone.DiseaseTutorial;

public class DiseaseTutorial {
    private String title;
    private int image;
    private int backgroundMain;
    private int backgroundImage;

    public DiseaseTutorial(String title, int image, int backgroundMain, int backgroundImage) {
        this.title = title;
        this.image = image;
        this.backgroundMain = backgroundMain;
        this.backgroundImage = backgroundImage;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public int getBackgroundMain() {
        return backgroundMain;
    }

    public void setBackgroundMain(int backgroundMain) {
        this.backgroundMain = backgroundMain;
    }

    public int getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(int backgroundImage) {
        this.backgroundImage = backgroundImage;
    }
}
