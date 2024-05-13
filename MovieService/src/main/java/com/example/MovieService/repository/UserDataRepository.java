package com.example.MovieService.repository;

import com.example.MovieService.domain.UserData;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserDataRepository extends MongoRepository<UserData,String> {
}
