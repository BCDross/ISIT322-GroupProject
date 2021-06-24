package com.hfad.rookandlochbooks.data.model;

public class DBLinkedBookBookshelf {
    private int linkedid,reviewid,bookshelfid;

    public DBLinkedBookBookshelf(int linkedid, int reviewid, int bookshelfid) {
        this.linkedid = linkedid;
        this.reviewid = reviewid;
        this.bookshelfid = bookshelfid;
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

    public int getBookshelfid() {
        return bookshelfid;
    }

    public void setBookshelfid(int bookshelfid) {
        this.bookshelfid = bookshelfid;
    }
}
