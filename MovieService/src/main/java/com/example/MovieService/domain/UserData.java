package com.example.MovieService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document
public class UserData {
    @Id
    private String userEmail;
    private String userName;
    private String mobileNo;
    private String password;
    private List<MovieData> movieData;

    private  List<Favourite> favourite;

    public UserData() {
    }

    public UserData(String userEmail, String userName, String mobileNo, String password, List<MovieData> movieData, List<Favourite> favourite) {
        this.userEmail = userEmail;
        this.userName = userName;
        this.mobileNo = mobileNo;
        this.password = password;
        this.movieData = movieData;
        this.favourite = favourite;
    }

    public List<Favourite> getFavourite() {
        return favourite;
    }

    public void setFavourite(List<Favourite> favourite) {
        this.favourite = favourite;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMobileNo() {
        return mobileNo;
    }

    public void setMobileNo(String mobileNo) {
        this.mobileNo = mobileNo;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<MovieData> getMovieData() {
        return movieData;
    }

    public void setMovieData(List<MovieData> movieData) {
        this.movieData = movieData;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "userEmail='" + userEmail + '\'' +
                ", userName='" + userName + '\'' +
                ", mobileNo='" + mobileNo + '\'' +
                ", password='" + password + '\'' +
                ", movieData=" + movieData +
                ", favourite=" + favourite +
                '}';
    }
}
