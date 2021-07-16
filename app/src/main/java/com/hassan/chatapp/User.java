package com.hassan.chatapp;

public class User {
    private String UserName;
    private String Email;
    private String profilePicture;

    public User(String userName, String email, String profilePicture) {
        UserName = userName;
        Email = email;
        this.profilePicture = profilePicture;
    }

    public User() {

    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getProfilePicture() {
        return profilePicture;
    }

    public void setProfilePicture(String profilePicture) {
        this.profilePicture = profilePicture;
    }
}
