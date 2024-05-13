package com.example.MovieService.controller;


import com.example.MovieService.domain.BookingData;
import com.example.MovieService.domain.MovieData;
import com.example.MovieService.domain.UserData;
import com.example.MovieService.exception.UserAlreadyExistsException;
import com.example.MovieService.service.MovieService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@ExtendWith(MockitoExtension.class)
public class ControllerTest {
    private MockMvc mockMvc;
    @Mock
    private MovieService movie1Service;
    @InjectMocks
    private MovieController movieController;
    private MovieData movieData1,movieData2;
    private MovieData movie1,movie2;
    List<MovieData> movieDataList;
    List<MovieData> movieData;
    BookingData bookingData1,bookingData2;
    UserData userData;

    @BeforeEach
    void setUp () {

        bookingData1=new BookingData("101","A-12","CLASSIC");
        movie1=new MovieData("1001","RRR","English","10001","ARRS","DELHI",120.00,bookingData1);
        movieData1=new MovieData("1001","RRR","English","10001","ARRS","DELHI",120.00,bookingData1);
        bookingData2=new BookingData("102","A-13","PREMIUM");
        movie2=new MovieData("1001","RRR","English","10001","ARRS","DELHI",120.00,bookingData1);
        movieData2=new MovieData("1002","OPERATION RED SEA","Chinese","10002","IMAX","BEIJING",300.00,bookingData2);
        userData=new UserData();
        userData.setUserEmail("abc@gmail.com");
        userData.setUserName("abc");
        userData.setMobileNo("1234567890");
        userData.setPassword("12345");
        movieDataList = Arrays.asList(movieData1,movieData2);
        movieData=Arrays.asList(movie1,movie2);
        userData.setMovieData(movieDataList);
        userData.setMovieData(movieData);
        mockMvc = MockMvcBuilders.standaloneSetup(movieController).build();
    }

    @AfterEach
    void tearDown() {
        userData=null;
    }

    @Test
    public void testRegisterUser() throws  Exception {
        when(movie1Service.registerUser(any())).thenReturn(userData);
        mockMvc.perform(post("/api/v2/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(userData)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(movie1Service,times(1)).registerUser(any());
    }

    @Test
    public void registerUserFailure() throws Exception {
        when(movie1Service.registerUser(any())).thenThrow(UserAlreadyExistsException.class);
        mockMvc.perform(post("/api/v2/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(userData)))
                .andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }

    private static String jsonToString(final Object abc) throws Exception {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(abc);
            result = jsonContent;
        } catch (JsonProcessingException e) {
            result = "JSON processing error";
        }
        return result;
    }
}
