package com.example.userAuthentication.repository;

import com.example.userAuthentication.domain.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserData,String> {
  UserData findByUserEmailAndPassword(String userName,String password);
}
