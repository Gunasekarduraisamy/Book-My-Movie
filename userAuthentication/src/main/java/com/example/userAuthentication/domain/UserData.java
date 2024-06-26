package com.example.userAuthentication.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class UserData {
    @Id
    private String userEmail;

    private String password;


    public UserData() {
    }

    public UserData(String userEmail, String password) {
        this.userEmail = userEmail;

        this.password = password;

    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    @Override
    public String toString() {
        return "UserData{" +
                "userEmail='" + userEmail + '\'' +

                ", password='" + password + '\'' +

                '}';
    }
}
