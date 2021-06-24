package com.hfad.rookandlochbooks.data.model;

public class DBLinkedReviewBook {
    private int linkedid, reviewid,bookid;


    public DBLinkedReviewBook(int linkedid, int reviewid, int bookid) {
        this.linkedid = linkedid;
        this.reviewid = reviewid;
        this.bookid = bookid;
    }

    public int getLinkedid() {
        return linkedid;
    }

    public void setLinkedid(int linkedid) {
        this.linkedid = linkedid;
    }

    public int getReviewid() {
        return reviewid;
    }

    public void setReviewid(int reviewid) {
        this.reviewid = reviewid;
    }

    public int getBookid() {
        return bookid;
    }

    public void setBookid(int bookid) {
        this.bookid = bookid;
    }
}
