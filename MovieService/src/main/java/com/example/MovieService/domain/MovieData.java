package com.example.MovieService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
public class MovieData {
    @Id
    private String movieId;
    private String movieName;
    private String movieLanguage;
    private String theaterId;
    private String theaterName;
    private String theaterCity;
    private double rate;
    private BookingData bookingData;

    public MovieData(String movieId, String movieName, String movieLanguage, String theaterId, String theaterName, String theaterCity, double rate, BookingData bookingData) {
        this.movieId = movieId;
        this.movieName = movieName;
        this.movieLanguage = movieLanguage;
        this.theaterId = theaterId;
        this.theaterName = theaterName;
        this.theaterCity = theaterCity;
        this.rate = rate;
        this.bookingData = bookingData;
    }

    public MovieData() {
    }

    @Override
    public String toString() {
        return "MovieData{" +
                "movieId='" + movieId + '\'' +
                ", movieName='" + movieName + '\'' +
                ", movieLanguage='" + movieLanguage + '\'' +
                ", theaterId='" + theaterId + '\'' +
                ", theaterName='" + theaterName + '\'' +
                ", theaterCity='" + theaterCity + '\'' +
                ", rate=" + rate +
                ", bookingData=" + bookingData +
                '}';
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

    public BookingData getBookingData() {
        return bookingData;
    }

    public void setBookingData(BookingData bookingData) {
        this.bookingData = bookingData;
    }
}
