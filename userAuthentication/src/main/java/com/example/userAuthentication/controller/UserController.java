package com.example.userAuthentication.controller;

import com.example.userAuthentication.domain.UserData;
import com.example.userAuthentication.exception.InvalidCredentialsException;
import com.example.userAuthentication.exception.UserAlreadyExistsException;
import com.example.userAuthentication.security.SecurityTokenGenerator;
import com.example.userAuthentication.services.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {
private IUserService userService;
private ResponseEntity responseEntity;
private SecurityTokenGenerator securityTokenGenerator;

@Autowired
    public UserController(IUserService userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

@PostMapping("/save")
public ResponseEntity saveUser(@RequestBody UserData userData)throws UserAlreadyExistsException{
    try {
        UserData userData1=userService.saveUser(userData);
        System.out.println(userData1);
        if(userData1.getUserEmail().equals(userData.getUserEmail())){
            responseEntity=new ResponseEntity("User Is Created", HttpStatus.CREATED);
    }

    }
    catch (UserAlreadyExistsException e){
        throw new UserAlreadyExistsException();
    }
    catch (Exception e){
        responseEntity=new ResponseEntity("Try After SomeTime",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
}

@PostMapping("/login")
    public ResponseEntity login(@RequestBody UserData userData)throws InvalidCredentialsException{
    Map<String ,String> map=new HashMap<>();
    String token="";
    try {
UserData userData1=userService.getUserByUserEmailAndPassword(userData.getUserEmail(),userData.getPassword());
  if(userData1==null){
      throw new InvalidCredentialsException();
  }
    token=securityTokenGenerator.createToken(userData);
        System.out.println(token);
  map.put(userData1.getUserEmail(),token);
  responseEntity=new ResponseEntity(token,HttpStatus.OK);
    }
    catch (Exception e){
        responseEntity=new ResponseEntity("Try After Sometime",HttpStatus.INTERNAL_SERVER_ERROR);
    }
    return responseEntity;
}


}
