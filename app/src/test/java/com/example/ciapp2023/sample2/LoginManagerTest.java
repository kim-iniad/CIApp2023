package com.example.ciapp2023.sample2;

import static org.junit.Assert.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import org.junit.Before;
import org.junit.Test;

public class LoginManagerTest {
    private LoginManager loginManager;

    @Before
    public void setUp() throws ValidateFailedException {
        loginManager = new LoginManager();
        loginManager.register("Testuser1", "password");
    }

    @Test(expected = ValidateFailedException.class)
    public void testRegisterFailed() throws ValidateFailedException {
        loginManager.register("testuser2", "password");
    }


    @Test
    public void testLoginSuccess() throws LoginFailedException {
        User user = loginManager.login("Testuser1", "password");
        assertThat(user.getUsername(), is("Testuser1"));
        assertThat(user.getPassword(), is("password"));
    }

    @Test(expected = LoginFailedException.class)
    public void testLoginWrongPassword() throws LoginFailedException {
        User user = loginManager.login("Testuser1", "1234");
    }

    @Test(expected = LoginFailedException.class)
    public void testLoginUnregisteredUser() throws LoginFailedException {
        User user = loginManager.login("iniad", "password");
    }
}