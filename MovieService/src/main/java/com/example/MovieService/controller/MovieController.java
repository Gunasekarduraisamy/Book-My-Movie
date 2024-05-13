package com.example.MovieService.controller;

import com.example.MovieService.domain.Favourite;
import com.example.MovieService.domain.MovieData;
import com.example.MovieService.domain.UserData;
import com.example.MovieService.exception.MovieAlreadyExistsException;
import com.example.MovieService.exception.MovieNotFoundException;
import com.example.MovieService.exception.UserAlreadyExistsException;
import com.example.MovieService.exception.UserNotFoundException;
import com.example.MovieService.service.MovieService;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2")
public class MovieController {
    private MovieService movieService;
    private ResponseEntity responseEntity;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    private String getEmailIdFromClaims(HttpServletRequest request) {
        System.out.println("header " + request.getHeader("Authorization"));
        Claims claims = (Claims) request.getAttribute("claims");
        System.out.println("Email ID from claims :: " + claims.getSubject());
        return claims.getSubject();
    }

    @PostMapping("/register") //
    public ResponseEntity<?> registerUserData(@RequestBody UserData userData) throws UserAlreadyExistsException {
        try {
            responseEntity = new ResponseEntity<>(movieService.registerUser(userData), HttpStatus.OK);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        } catch (Exception e) {
            System.out.println(e.getMessage() + " Movie");
//            responseEntity = new ResponseEntity<>("Try After SomeTime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }


    @PostMapping("/user/movie")
    public ResponseEntity<?> saveMovie(@RequestBody MovieData movieData, HttpServletRequest request) throws MovieAlreadyExistsException {
        try {
            String id = getEmailIdFromClaims(request);
            responseEntity = new ResponseEntity<>(movieService.saveUserDataMovieToWishList(movieData, id), HttpStatus.OK);
        } catch (MovieAlreadyExistsException e) {
            throw new MovieAlreadyExistsException();
        } catch (Exception e) {
            System.out.println(e);
            responseEntity = new ResponseEntity<>("Try After Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("user/movies")
    public ResponseEntity<?> getAllMovies(HttpServletRequest request) {
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("UserEmail from claims" + claims.getSubject());
            String userEmail = claims.getSubject();
            System.out.println("userEmail" + claims.getSubject());
            responseEntity = new ResponseEntity<>(movieService.getAllUserMovieDataFromWishList(userEmail), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Try After Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @DeleteMapping("user/movie/{movieId}")
    public ResponseEntity<?> deleteMovie(@PathVariable String movieId, HttpServletRequest request) throws MovieNotFoundException {
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("UserEmail from claims" + claims.getSubject());
            String userEmail = claims.getSubject();
            System.out.println("userEmail" + claims.getSubject());
            responseEntity = new ResponseEntity<>(movieService.deleteMovie(userEmail, movieId), HttpStatus.OK);
        } catch (MovieNotFoundException e) {
            throw new MovieNotFoundException();
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Try After Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("user/movies")
    public ResponseEntity<?> updateMovie(@RequestBody MovieData movieData, HttpServletRequest request) {
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("UserEmail from claims" + claims.getSubject());
            String userEmail = claims.getSubject();
            System.out.println("userEmail" + claims.getSubject());
            responseEntity = new ResponseEntity<>(movieService.updateMovie(userEmail, movieData), HttpStatus.OK);
        } catch (Exception e) {
            responseEntity = new ResponseEntity<>("Try After Sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/user/getuser")
    public ResponseEntity<?> getUserData(HttpServletRequest request)throws UserNotFoundException{
       try {
           System.out.println("header" + request.getHeader("Authorization"));
           Claims claims = (Claims) request.getAttribute("claims");
           System.out.println("UserEmail from claims" + claims.getSubject());
           String userEmail = claims.getSubject();
           System.out.println("userEmail" + claims.getSubject());
           responseEntity=new ResponseEntity<>(movieService.getUserData(userEmail),HttpStatus.OK);
       }
       catch (UserNotFoundException e){
           throw new UserNotFoundException();
       }
       return responseEntity;
    }

    @PutMapping("/user/updateuser")
    public ResponseEntity<?> updateUserData(@RequestBody UserData userData,HttpServletRequest request)throws UserNotFoundException{
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("UserEmail from claims" + claims.getSubject());
            String userEmail = claims.getSubject();
            System.out.println("userEmail" + claims.getSubject());
            responseEntity=new ResponseEntity<>(movieService.updateUserData(userEmail,userData),HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @PostMapping("/user/favourite")
    public ResponseEntity<?> saveFavouriteList(@RequestBody Favourite favourite, HttpServletRequest request)throws UserNotFoundException{
     try {
         String id = getEmailIdFromClaims(request);
//         System.out.println("header" + request.getHeader("Authorization"));
//         Claims claims = (Claims) request.getAttribute("claims");
//         System.out.println("UserEmail from claims" + claims.getSubject());
//         String userEmail = claims.getSubject();
//         System.out.println("userEmail" + claims.getSubject());
         responseEntity=new ResponseEntity<>(movieService.saveMovieToFavouriteList(favourite,id),HttpStatus.OK);
     }
     catch (UserNotFoundException e){
         throw new UserNotFoundException();
     }
     catch (Exception e){
         responseEntity=new ResponseEntity("Try After SomeTime ",HttpStatus.INTERNAL_SERVER_ERROR);
     }
     return responseEntity;
    }

    @GetMapping("/user/favourites")
    public ResponseEntity<?> getAllFavourite(HttpServletRequest request)throws UserNotFoundException{
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("UserEmail from claims" + claims.getSubject());
            String userEmail = claims.getSubject();
            System.out.println("userEmail" + claims.getSubject());
            responseEntity=new ResponseEntity(movieService.getFavouriteList(userEmail),HttpStatus.OK);
        }
        catch (UserNotFoundException e){
            throw new UserNotFoundException();
        }
        return responseEntity;
    }

    @DeleteMapping("/user/favourite/{movieId}")
    public ResponseEntity<?> deleteFavouriteList(@PathVariable String movieId,HttpServletRequest request)throws MovieNotFoundException{
        try {
            System.out.println("header" + request.getHeader("Authorization"));
            Claims claims = (Claims) request.getAttribute("claims");
            System.out.println("UserEmail from claims" + claims.getSubject());
            String userEmail = claims.getSubject();
            System.out.println("userEmail" + claims.getSubject());
            responseEntity=new ResponseEntity(movieService.deleteFavouriteList(userEmail,movieId),HttpStatus.OK);
        }
        catch (MovieNotFoundException e){
            throw new MovieNotFoundException();
        }
        catch (Exception e){
            responseEntity=new ResponseEntity("Try After SomeTime",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
