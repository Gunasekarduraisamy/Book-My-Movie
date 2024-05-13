package com.example.MovieService.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Set;
@Document
public class BookingData {
    @Id
    private String bookingId;
    private  String seatNumber;
    private String SeatType;

    @Override
    public String toString() {
        return "BookingData{" +
                "bookingId='" + bookingId + '\'' +
                ", seatNumber=" + seatNumber +
                ", SeatType='" + SeatType + '\'' +
                '}';
    }

    public BookingData() {
    }

    public BookingData(String bookingId, String seatNumber, String seatType) {
        this.bookingId = bookingId;
        this.seatNumber = seatNumber;
        SeatType = seatType;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public String getSeatType() {
        return SeatType;
    }

    public void setSeatType(String seatType) {
        SeatType = seatType;
    }
}
