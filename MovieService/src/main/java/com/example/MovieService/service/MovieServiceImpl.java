package com.example.MovieService.service;

import com.example.MovieService.domain.Favourite;
import com.example.MovieService.domain.MovieData;
import com.example.MovieService.domain.UserData;
import com.example.MovieService.exception.*;
import com.example.MovieService.proxy.UserProxy;
import com.example.MovieService.repository.UserDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {
    private UserDataRepository userDataRepository;
    private UserProxy userProxy;

    @Autowired
    public MovieServiceImpl(UserDataRepository userDataRepository, UserProxy userProxy) {
        this.userDataRepository = userDataRepository;
        this.userProxy = userProxy;
    }

    @Override
    public UserData registerUser(UserData user) throws UserAlreadyExistsException {
        if (userDataRepository.findById(user.getUserEmail()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        UserData user1 = userDataRepository.save(user);
        System.out.println(user1);
        if (!(user1.getUserEmail().isEmpty())) {
            ResponseEntity response = userProxy.saveUser(user);
            System.out.println(response.getBody());
        }
        return user1;
    }

    @Override
    public UserData getUserData(String userEmail) throws UserNotFoundException {
        if(userDataRepository.findById(userEmail).isEmpty()){
            throw new UserNotFoundException();
        }
        return userDataRepository.findById(userEmail).get();
    }

    @Override
    public UserData updateUserData(String userEmail, UserData userData) throws UserNotFoundException {
        Optional<UserData> optUser=userDataRepository.findById(userEmail);
        if(optUser.isEmpty()){
            throw new UserNotFoundException();
        }
        UserData userData1=optUser.get();
        userData1.setPassword(userData.getPassword());
        userData1.setMobileNo(userData.getMobileNo());
          userDataRepository.save(userData1);

        return userData1;
    }

    @Override
    public UserData saveUserDataMovieToWishList(MovieData movie, String userEmail) throws MovieAlreadyExistsException, UserNotFoundException {
        System.out.println(userEmail);
        if (userDataRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        }
        UserData user2 = userDataRepository.findById(userEmail).get();

        if (user2.getMovieData() == null) {
            user2.setMovieData(Arrays.asList(movie));
        } else {
            List<MovieData> list = user2.getMovieData();
            list.add(movie);
            user2.setMovieData(list);
        }

        return userDataRepository.save(user2);
    }

    @Override
    public List<MovieData> getAllUserMovieDataFromWishList(String userEmail) throws UserCredentialException {
        if (userDataRepository.findById(userEmail).isEmpty()) {
            throw new UserCredentialException();
        }
        return userDataRepository.findById(userEmail).get().getMovieData();
    }

    @Override
    public UserData deleteMovie(String userEmail, String movieId) throws MovieNotFoundException, UserNotFoundException {
        boolean movie = false;
        UserData user4 = userDataRepository.findById(userEmail).get();
        List<MovieData> list = user4.getMovieData();
        if (userDataRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        } else {
            movie = list.removeIf(a -> a.getMovieId().equals(movieId));
            if (!movie) {
                throw new MovieNotFoundException();
            } else {
                user4.setMovieData(list);
            }

        }
        return userDataRepository.save(user4);
    }

    // Update service impl method is not correct
    @Override
    public UserData updateMovie(String userEmail, MovieData movie) throws UserNotFoundException, MovieNotFoundException {
        Optional<UserData> optTrack = userDataRepository.findById(userEmail);
        boolean flag = false;
        if (optTrack.isEmpty()) {
            throw new UserNotFoundException();
        }
        UserData user1 = optTrack.get();
        List<MovieData> tracks = user1.getMovieData();
        Iterator<MovieData> movieIterator = tracks.iterator();
        while (movieIterator.hasNext()) {
            MovieData track1 = movieIterator.next();
            if (track1.getMovieId().equals(movie.getMovieId())) {
                if (movie.getMovieName() != null)
                    track1.setMovieName(movie.getMovieName());
                if (movie.getMovieLanguage() != null)
                    track1.setMovieLanguage(movie.getMovieLanguage());
                if(movie.getTheaterId()!=null)
                    track1.setMovieId(movie.getTheaterId());
                 if(movie.getTheaterName()!=null)
                     track1.setTheaterName(movie.getTheaterName());
                 if(movie.getTheaterCity()!=null)
                     track1.setTheaterCity(movie.getTheaterCity());
                 if(movie.getRate()!=0)
                     track1.setRate(movie.getRate());
                flag = true;
            }
        }
        if (!flag) {
            throw new MovieNotFoundException();
        }
        user1.setMovieData(tracks);

        return userDataRepository.save(user1);
    }

    @Override
    public UserData saveMovieToFavouriteList(Favourite favourite, String userEmail) throws MovieAlreadyExistsException,UserNotFoundException {
        if (userDataRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        }
        UserData user2 = userDataRepository.findById(userEmail).get();
        System.out.println(user2);
        if (user2.getFavourite() == null) {
            user2.setFavourite(Arrays.asList(favourite));
        } else {
            List<Favourite> list = user2.getFavourite();
            list.add(favourite);
            user2.setFavourite(list);
        }

        return userDataRepository.save(user2);
    }

    @Override
    public List<Favourite> getFavouriteList(String userEmail) throws UserNotFoundException {
        if (userDataRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        }
        return userDataRepository.findById(userEmail).get().getFavourite();
    }

    @Override
    public UserData deleteFavouriteList(String userEmail, String movieId) throws UserNotFoundException, MovieNotFoundException {
        boolean movie = false;
        UserData user4 = userDataRepository.findById(userEmail).get();
        List<Favourite> list = user4.getFavourite();
        if (userDataRepository.findById(userEmail).isEmpty()) {
            throw new UserNotFoundException();
        } else {
            movie = list.removeIf(a -> a.getMovieId().equals(movieId));
            if (!movie) {
                throw new MovieNotFoundException();
            } else {
                user4.setFavourite(list);
            }

        }
        return userDataRepository.save(user4);
    }
}
