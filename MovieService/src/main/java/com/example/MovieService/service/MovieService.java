package com.example.MovieService.service;

import com.example.MovieService.domain.Favourite;
import com.example.MovieService.domain.MovieData;
import com.example.MovieService.domain.UserData;
import com.example.MovieService.exception.*;

import java.util.List;

public interface MovieService {
    UserData registerUser(UserData user) throws UserAlreadyExistsException;
    UserData getUserData(String userEmail)throws UserNotFoundException;
    UserData updateUserData(String userEmail,UserData userData)throws UserNotFoundException;
    UserData saveUserDataMovieToWishList(MovieData movie, String userEmail) throws MovieAlreadyExistsException, UserNotFoundException;
    List<MovieData> getAllUserMovieDataFromWishList(String userEmail) throws UserCredentialException;
    UserData deleteMovie(String userEmail,String movieId) throws MovieNotFoundException, UserNotFoundException;
    UserData updateMovie(String userEmail, MovieData movie) throws UserNotFoundException,MovieNotFoundException;

    UserData saveMovieToFavouriteList(Favourite favourite,String userEmail)throws MovieAlreadyExistsException,UserNotFoundException;

    List<Favourite> getFavouriteList(String userEmail)throws UserNotFoundException;

   UserData deleteFavouriteList(String userEmail,String movieId)throws UserNotFoundException,MovieNotFoundException;
}
