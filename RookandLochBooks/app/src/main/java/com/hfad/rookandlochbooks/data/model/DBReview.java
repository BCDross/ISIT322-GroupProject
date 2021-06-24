package com.hfad.rookandlochbooks.data.model;

public class DBReview {
    private int reviewid;
    private String title, description;
    private int userid;

    public DBReview(int reviewid, String title, String description, int userid) {
        this.reviewid = reviewid;
        this.title = title;
        this.description = description;
        this.userid = userid;
    }

    public int getReviewid() {
        return reviewid;
    }

    public void setReviewid(int reviewid) {
        this.reviewid = reviewid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }



}
