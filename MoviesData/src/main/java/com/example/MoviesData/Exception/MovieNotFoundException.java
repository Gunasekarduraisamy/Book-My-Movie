package com.example.MoviesData.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND , reason = "Movie with specified id is not found")
public class MovieNotFoundException extends Exception{
}
