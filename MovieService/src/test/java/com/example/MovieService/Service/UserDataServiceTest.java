package com.example.MovieService.Service;

import com.example.MovieService.domain.BookingData;
import com.example.MovieService.domain.MovieData;
import com.example.MovieService.domain.UserData;
import com.example.MovieService.exception.UserCredentialException;
import com.example.MovieService.repository.UserDataRepository;
import com.example.MovieService.service.MovieServiceImpl;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
public class UserDataServiceTest {
    @Mock
    private UserDataRepository userDataRepository;

    @InjectMocks
    private MovieServiceImpl movieService;
    private MovieData movie1, movie2;
    List<MovieData> movieData;
    BookingData Ticket1, Ticket2;
    UserData user;


    @BeforeEach
    void setUp() {

        Ticket1 = new BookingData("Ha2345", "A-12", "PREMIUM");
        movie1 = new MovieData("HR100", "King", "English", "1001", "PVS",
                "CHENNAI", 150, Ticket1);
        Ticket2 = new BookingData("Ha102", "A-13", "PREMIUM");
        movie2 = new MovieData("HR101", "OPERATION RED SEA", "Chinese", "10002", "IMAX",
                "BEIJING", 300.00, Ticket2);
        user = new UserData();
        user.setUserEmail("abc@gmail.com");
        user.setUserName("abc");
        user.setMobileNo("1234567890");
        user.setPassword("12345");
        movieData = Arrays.asList(movie1, movie2);
        user.setMovieData(movieData);

    }

    @AfterEach
    void tearDown() {
        movie1 = null;
        movie2 = null;
        user = null;
    }

    @Test
    public void getAllUserMovieDataFromWishListSuccess() throws UserCredentialException {
//        when(userDataRepository.findById(anyString())).thenReturn(Optional.ofNullable(user));
        when(userDataRepository.findById(anyString())).thenReturn(Optional.of(user));
        assertEquals(movieData, movieService.getAllUserMovieDataFromWishList(user.getUserEmail()));
        verify(userDataRepository, times(2)).findById(anyString());

    }

    @Test

    public void getAllUserMovieDataFailure() throws UserCredentialException {
        when(userDataRepository.findById(anyString())).thenReturn(Optional.ofNullable(user));
        assertEquals(movieData, movieService.getAllUserMovieDataFromWishList(user.getUserEmail()));
        verify(userDataRepository, times(2)).findById(anyString());
    }
}
