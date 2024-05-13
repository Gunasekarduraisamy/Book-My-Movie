package com.example.MovieService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class Favourite {
    @Id
    private String movieId;
    private String movieName;
    private String movieLanguage;
    private String theaterId;
    private String theaterName;
    private String theaterCity;
    private double rate;

    public Favourite() {
    }

    public Favourite(String movieId, String movieName, String movieLanguage, String theaterId, String theaterName, String theaterCity, double rate) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieLanguage = movieLanguage;
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        this.theaterCity = theaterCity;
        this.rate = rate;
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

    @Override
    public String toString() {
        return "Favourite{" +
                "movieId='" + movieId + '\'' +
                ", movieName='" + movieName + '\'' +
                ", movieLanguage='" + movieLanguage + '\'' +
                ", theaterId='" + theaterId + '\'' +
                ", theaterName='" + theaterName + '\'' +
                ", theaterCity='" + theaterCity + '\'' +
                ", rate=" + rate +
                '}';
    }
}
