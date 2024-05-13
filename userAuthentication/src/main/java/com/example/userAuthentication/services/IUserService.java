package com.example.userAuthentication.services;

import com.example.userAuthentication.domain.UserData;
import com.example.userAuthentication.exception.InvalidCredentialsException;
import com.example.userAuthentication.exception.UserAlreadyExistsException;

public interface IUserService {
UserData saveUser(UserData userData) throws UserAlreadyExistsException;
UserData getUserByUserEmailAndPassword(String userName,String password)throws InvalidCredentialsException;
}
