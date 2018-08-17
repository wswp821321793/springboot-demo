package com.peng.model;

import java.util.List;

public class GameModel {

    private List<Integer> row1;

    private List<Integer> row2;

    private List<Integer> row3;

    private List<Integer> row4;

    private int score = 0;

    @Override
    public String toString() {
        return "GameModel{" +
                "row1=" + row1 +
                ", row2=" + row2 +
                ", row3=" + row3 +
                ", row4=" + row4 +
                ", score=" + score +
                '}';
    }

    public List<Integer> getRow1() {
        return row1;
    }

    public void setRow1(List<Integer> row1) {
        this.row1 = row1;
    }

    public List<Integer> getRow2() {
        return row2;
    }

    public void setRow2(List<Integer> row2) {
        this.row2 = row2;
    }

    public List<Integer> getRow3() {
        return row3;
    }

    public void setRow3(List<Integer> row3) {
        this.row3 = row3;
    }

    public List<Integer> getRow4() {
        return row4;
    }

    public void setRow4(List<Integer> row4) {
        this.row4 = row4;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
