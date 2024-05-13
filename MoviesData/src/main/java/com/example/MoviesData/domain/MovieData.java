package com.example.MoviesData.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class MovieData {
    @Id
    private String movieId;
    private String image;
    private String movieName;
    private String movieLanguage;
    private String theaterId;
    private String theaterName;
    private String theaterCity;
    private double rate;

    public MovieData() {
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getMovieId() {
        return movieId;
    }

    public void setMovieId(String movieId) {
        this.movieId = movieId;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieLanguage() {
        return movieLanguage;
    }

    public void setMovieLanguage(String movieLanguage) {
        this.movieLanguage = movieLanguage;
    }

    public String getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(String theaterId) {
        this.theaterId = theaterId;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public String getTheaterCity() {
        return theaterCity;
    }

    public void setTheaterCity(String theaterCity) {
        this.theaterCity = theaterCity;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public MovieData(String movieId, String image, String movieName, String movieLanguage, String theaterId, String theaterName, String theaterCity, double rate) {
        this.movieId = movieId;
        this.image = image;
        this.movieName = movieName;
        this.movieLanguage = movieLanguage;
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        this.theaterCity = theaterCity;
        this.rate = rate;
    }

    @Override
    public String toString() {
        return "MovieData{" +
                "movieId='" + movieId + '\'' +
                ", image='" + image + '\'' +
                ", movieName='" + movieName + '\'' +
                ", movieLanguage='" + movieLanguage + '\'' +
                ", theaterId='" + theaterId + '\'' +
                ", theaterName='" + theaterName + '\'' +
                ", theaterCity='" + theaterCity + '\'' +
                ", rate=" + rate +
                '}';
    }
}
