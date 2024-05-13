package com.example.MoviesData.service;

import com.example.MoviesData.Exception.MovieAlreadyExistsException;
import com.example.MoviesData.Exception.MovieNotFoundException;
import com.example.MoviesData.domain.MovieData;
import com.example.MoviesData.repository.MovieDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MovieDataServiceImpl implements MovieDataService{

    private MovieDataRepository movieDataRepository;

    @Autowired
    public MovieDataServiceImpl(MovieDataRepository movieDataRepository) {
        this.movieDataRepository = movieDataRepository;
    }

    @Override
    public MovieData saveMovie(MovieData movieData) throws MovieAlreadyExistsException {
        if(movieDataRepository.findById(movieData.getMovieId()).isPresent()){
            throw new MovieAlreadyExistsException();
        }
        return movieDataRepository.save(movieData);
    }

    @Override
    public List<MovieData> getAllMovie() {
        List<MovieData> movieData=movieDataRepository.findAll();
        return movieData;
    }

    @Override
    public MovieData getMovieDataById(String movieId) throws MovieNotFoundException {
        if(movieDataRepository.findById(movieId).isEmpty()){
            throw new MovieNotFoundException();
        }
        return movieDataRepository.findById(movieId).get();
    }

    @Override
    public boolean deleteMovie(String movieId) throws MovieNotFoundException {
       boolean flag=false;
        if(movieDataRepository.findById(movieId).isEmpty()){
            throw new MovieNotFoundException();
        }
        movieDataRepository.deleteById(movieId);
        flag=true;
        return flag;
    }
}
