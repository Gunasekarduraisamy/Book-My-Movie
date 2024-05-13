package com.example.MoviesData.controller;

import com.example.MoviesData.Exception.MovieAlreadyExistsException;
import com.example.MoviesData.Exception.MovieNotFoundException;
import com.example.MoviesData.domain.MovieData;
import com.example.MoviesData.repository.MovieDataRepository;
import com.example.MoviesData.service.MovieDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v3")
public class MovieDataController {

    private MovieDataService movieDataService;
    private ResponseEntity responseEntity;
   @Autowired
    public MovieDataController(MovieDataService movieDataService) {
        this.movieDataService = movieDataService;
    }

@PostMapping("/save")
public ResponseEntity<?> saveMovieData(@RequestBody MovieData movieData)throws MovieAlreadyExistsException{
try {
    responseEntity=new ResponseEntity(movieDataService.saveMovie(movieData),HttpStatus.CREATED);
}
catch (MovieAlreadyExistsException e){
throw new MovieAlreadyExistsException();
}
catch (Exception e){
  responseEntity=new ResponseEntity("Try After SomeTime",HttpStatus.INTERNAL_SERVER_ERROR);
}
return responseEntity;
}

@GetMapping("/movies")
    public ResponseEntity<?> getAllMovies(){
    List<MovieData> movieData=movieDataService.getAllMovie();
    return new ResponseEntity<>(movieData,HttpStatus.OK);
}

@DeleteMapping("/delete")
    public ResponseEntity<?> deleteMovie(@PathVariable String movieId)throws MovieNotFoundException{
  try {

      responseEntity=new ResponseEntity<>(movieDataService.deleteMovie(movieId),HttpStatus.OK);
  }
  catch (MovieNotFoundException e){
      throw new MovieNotFoundException();
  }
  catch (Exception e){

      responseEntity=new ResponseEntity<>("Try After SomeTime",HttpStatus.INTERNAL_SERVER_ERROR);
  }
  return responseEntity;
}

@GetMapping("/movies/{movieId}")
    public ResponseEntity<?> getMovieByMovieId(@PathVariable String movieId)throws MovieNotFoundException{
       try {
         MovieData movieData=movieDataService.getMovieDataById(movieId);
         return new ResponseEntity<>(movieData,HttpStatus.OK);
       }
       catch (MovieNotFoundException e){
           throw new MovieNotFoundException();
       }
       catch (Exception e){
           return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
       }
}


}
