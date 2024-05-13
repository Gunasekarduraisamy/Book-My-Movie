package com.example.MovieService.proxy;



import com.example.MovieService.domain.UserData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-authentication-service",url="localhost:8083")
public interface UserProxy {

    @PostMapping("/api/v1/save")
    public ResponseEntity<?> saveUser(@RequestBody UserData user);
}
