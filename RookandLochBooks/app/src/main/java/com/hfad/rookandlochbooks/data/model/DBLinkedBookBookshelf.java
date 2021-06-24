package com.hfad.rookandlochbooks.data.model;

public class DBLinkedBookBookshelf {
    private int linkedid,bookid,bookshelfid;

    public DBLinkedBookBookshelf(int linkedid, int bookid, int bookshelfid) {
        this.linkedid = linkedid;
        this.bookid = bookid    ;
        this.bookshelfid = bookshelfid;
    }

    public int getLinkedid() {
        return linkedid;
    }

    public void setLinkedid(int linkedid) {
        this.linkedid = linkedid;
    }

    public int getbookid() {
        return bookid;
    }

    public void setbookid(int reviewid) {
        this.bookid = reviewid;
    }

    public int getBookshelfid() {
        return bookshelfid;
    }

    public void setBookshelfid(int bookshelfid) {
        this.bookshelfid = bookshelfid;
    }
}
