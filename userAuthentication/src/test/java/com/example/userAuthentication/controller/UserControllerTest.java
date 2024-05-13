package com.example.userAuthentication.controller;

import com.example.userAuthentication.domain.UserData;
import com.example.userAuthentication.exception.UserAlreadyExistsException;
import com.example.userAuthentication.services.UserServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class UserControllerTest {
 private MockMvc mockMvc;
    @Mock
    private UserServiceImpl userService;

    @InjectMocks
    private UserController userController;
    private UserData userData;

    @BeforeEach
    public void setUp() throws Exception {
        userData = new UserData("John@gmail.com",  "JOHN123" );
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }
    @AfterEach
    public void tearDown() throws Exception {
        userData = null;
    }

    @Test
    public void givenUserToSaveReturnUserSuccess() throws Exception {
        when(userService.saveUser(any())).thenReturn(userData);
        mockMvc.perform(post("/api/v1/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(userData)))
                .andExpect(status().isCreated()).andDo(MockMvcResultHandlers.print());
        verify(userService,times(1)).saveUser(any());

    }
    @Test
    public void givenUserToSaveReturnUserFailure() throws Exception {
        when(userService.saveUser(any())).thenThrow(UserAlreadyExistsException.class);
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/save")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonToString(userData)))
                .andExpect(status().isConflict())
                .andDo(MockMvcResultHandlers.print());
    }
    private static String jsonToString(final Object ob) throws JsonProcessingException {
        String result;
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonContent = mapper.writeValueAsString(ob);
            result = jsonContent;
        } catch(JsonProcessingException e) {
            result = "JSON processing error";
        }

        return result;
    }
}
