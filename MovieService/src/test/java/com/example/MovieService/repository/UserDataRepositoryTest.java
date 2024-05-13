package com.example.MovieService.repository;

import com.example.MovieService.domain.BookingData;
import com.example.MovieService.domain.MovieData;
import com.example.MovieService.domain.UserData;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataMongoTest
public class UserDataRepositoryTest {
    @Autowired
    private UserDataRepository userDataRepository;
    private BookingData bookingData1,bookingData2;
    private MovieData movieData1,movieData2;
    private UserData userData;
    List<MovieData> movieDataList;

    @BeforeEach
    void setUp() {

        bookingData1=new BookingData("101","A-12","CLASSIC");
        movieData1=new MovieData("1001","RRR","English","10001","ARRS","DELHI",120.00,bookingData1);
        bookingData2=new BookingData("102","A-13","PREMIUM");
        movieData2=new MovieData("1002","OPERATION RED SEA","Chinese","10002","IMAX","BEIJING",300.00,bookingData2);
        userData=new UserData();
        userData.setUserEmail("abc@gmail.com");
        userData.setUserName("abc");
        userData.setMobileNo("1234567890");
        userData.setPassword("12345");
        movieDataList = Arrays.asList(movieData1,movieData2);
        userData.setMovieData(movieDataList);
    }

    @AfterEach
    void tearDown() {
        movieData1=movieData2=null;
        bookingData1=bookingData2=null;
        userDataRepository.deleteAll();
    }

    @Test
    void givenUserDataMovieDataToSaveShouldReturnSavedMovieData() {
        userDataRepository.save(userData);
        UserData userData1 = userDataRepository.findById(userData.getUserEmail()).get();

        assertNotNull(userData1);
        assertEquals(userData1.getUserEmail(),userData.getUserEmail());
    }

    @Test
    void givenMovieDataToDeleteShouldDeleteMovieData() {
        userDataRepository.insert(userData);
        UserData userData1 = userDataRepository.findById(userData.getUserEmail()).get();

        userDataRepository.delete(userData1);
        assertEquals(Optional.empty(),userDataRepository.findById(userData.getUserEmail()));
    }
}
