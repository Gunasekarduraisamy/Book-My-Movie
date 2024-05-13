package com.example.userAuthentication.services;

import com.example.userAuthentication.domain.UserData;
import com.example.userAuthentication.exception.UserAlreadyExistsException;
import com.example.userAuthentication.repository.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServicesImplTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;
    private UserData userData;

    @BeforeEach
    public void setUp() throws Exception {
        userData = new UserData("John@gmail.com" , "JOHN123" );
    }
    @AfterEach
    public void tearDown() throws Exception {
        userData = null;
    }
    @Test
    public void givenCustomerToSaveReturnSavedCustomerSuccess() throws  UserAlreadyExistsException {
        when(userRepository.findById(anyString())).thenReturn(Optional.ofNullable(null));
        when(userRepository.save(any())).thenReturn(userData);
        assertEquals(userData,userService.saveUser(userData));
        verify(userRepository,times(1)).save(any());
        verify(userRepository,times(1)).findById(any());
    }

    @Test
    public void givenUserToSaveReturnSavedUserFailure() throws UserAlreadyExistsException {
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(userData));
        assertThrows(UserAlreadyExistsException.class,()->userService.saveUser(userData));
        verify(userRepository,times(1)).findById(any());
        verify(userRepository,times(0)).save(any());
    }
    @Test
    public void givenUserLoginSuccessReturnRetrievedUser()
    {
        when(userRepository.findByUserEmailAndPassword(anyString(),any())).thenReturn(userData);

        assertEquals(userData,userRepository.findByUserEmailAndPassword(userData.getUserEmail(),userData.getPassword()));
        verify(userRepository,times(1)).findByUserEmailAndPassword(anyString(),any());
    }
}
