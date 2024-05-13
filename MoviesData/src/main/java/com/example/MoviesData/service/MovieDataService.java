package com.example.MoviesData.service;

import com.example.MoviesData.Exception.MovieAlreadyExistsException;
import com.example.MoviesData.Exception.MovieNotFoundException;
import com.example.MoviesData.domain.MovieData;

import java.util.List;

public interface MovieDataService {
MovieData saveMovie(MovieData movieData)throws MovieAlreadyExistsException;
List<MovieData> getAllMovie();
 MovieData getMovieDataById(String movieId)throws MovieNotFoundException;
boolean deleteMovie(String movieId)throws MovieNotFoundException;
}
