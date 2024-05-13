package com.example.MoviesData.repository;

import com.example.MoviesData.domain.MovieData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MovieDataRepository extends MongoRepository<MovieData,String> {
}
