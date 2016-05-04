package com.svs.myprojects.bollywoodquiz.models;

/**
 * Created by snehalsutar on 4/24/16.
 */
public class ScoreModel {
    private int offline_level_1;
    private int offline_level_2;
    private int offline_level_3;
    private int online_level_1;
    private int online_level_2;
    private int online_level_3;
//
//    private String name;
//    private String descriptions;
//    private int level;
//    private int score;
//    public ScoreModel() {
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getDescriptions() {
//        return descriptions;
//    }
//
//    public void setDescriptions(String descriptions) {
//        this.descriptions = descriptions;
//    }
//
//    public int getLevel() {
//        return level;
//    }
//
//    public void setLevel(int level) {
//        this.level = level;
//    }
//
//    public int getScore() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }

    public ScoreModel(int offline_level_1, int offline_level_2, int offline_level_3, int online_level_1, int online_level_2, int online_level_3) {
        this.offline_level_1 = offline_level_1;
        this.offline_level_2 = offline_level_2;
        this.offline_level_3 = offline_level_3;
        this.online_level_1 = online_level_1;
        this.online_level_2 = online_level_2;
        this.online_level_3 = online_level_3;
    }

    public int getOffline_level_1() {
        return offline_level_1;
    }

    public void setOffline_level_1(int offline_level_1) {
        this.offline_level_1 = offline_level_1;
    }

    public int getOffline_level_2() {
        return offline_level_2;
    }

    public void setOffline_level_2(int offline_level_2) {
        this.offline_level_2 = offline_level_2;
    }

    public int getOffline_level_3() {
        return offline_level_3;
    }

    public void setOffline_level_3(int offline_level_3) {
        this.offline_level_3 = offline_level_3;
    }

    public int getOnline_level_1() {
        return online_level_1;
    }

    public void setOnline_level_1(int online_level_1) {
        this.online_level_1 = online_level_1;
    }

    public int getOnline_level_2() {
        return online_level_2;
    }

    public void setOnline_level_2(int online_level_2) {
        this.online_level_2 = online_level_2;
    }

    public int getOnline_level_3() {
        return online_level_3;
    }

    public void setOnline_level_3(int online_level_3) {
        this.online_level_3 = online_level_3;
    }
}
