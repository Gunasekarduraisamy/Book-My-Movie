package com.example.userAuthentication.services;

import com.example.userAuthentication.domain.UserData;
import com.example.userAuthentication.exception.InvalidCredentialsException;
import com.example.userAuthentication.exception.UserAlreadyExistsException;
import com.example.userAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService{
    private UserRepository userRepository;
   @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserData saveUser(UserData userData) throws UserAlreadyExistsException {
       if(userRepository.findById(userData.getUserEmail()).isPresent()){
      throw new UserAlreadyExistsException();
       }
        return userRepository.save(userData);
    }

    @Override
    public UserData getUserByUserEmailAndPassword(String userEmail, String password) throws InvalidCredentialsException {
         UserData userData1= userRepository.findByUserEmailAndPassword(userEmail,password);
         if(userData1==null){
             throw new InvalidCredentialsException();
         }
        return userData1;
    }
}
