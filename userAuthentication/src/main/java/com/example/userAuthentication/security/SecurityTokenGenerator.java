package com.example.userAuthentication.security;

import com.example.userAuthentication.domain.UserData;

public interface SecurityTokenGenerator {
String createToken(UserData userData);
}
