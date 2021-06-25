package com.hfad.rookandlochbooks.data.model;

public class DBUser {
    int UserID;
    String FirstName,LastName,EmailAddress;
     int deleted;

    public DBUser(int userID, String firstName, String lastName, String emailAddress, int deleted) {
        UserID = userID;
        FirstName = firstName;
        LastName = lastName;
        EmailAddress = emailAddress;
        this.deleted = deleted;
    }

    public int getUserID() {
        return UserID;
    }

    public void setUserID(int userID) {
        UserID = userID;
    }

    public String getFirstName() {
        return FirstName;
    }

    public void setFirstName(String firstName) {
        FirstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public int getDeleted() {
        return deleted;
    }

    public void setDeleted(int deleted) {
        this.deleted = deleted;
    }
}
