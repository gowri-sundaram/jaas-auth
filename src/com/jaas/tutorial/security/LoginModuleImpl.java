package com.jaas.tutorial.security;

import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.LoginException;
import javax.security.auth.spi.LoginModule;
import java.io.IOException;
import java.util.Map;

public class LoginModuleImpl implements LoginModule {

    public static final String TEST_USER = "admin";
    public static final String TEST_PASSWORD = "password@123";
    private CallbackHandler callbackHandler = null;
    private boolean authSuccess = false;

    @Override
    public void initialize(
            Subject subject, CallbackHandler callbackHandler,
            Map<String, ?> sharedState, Map<String, ?> options) {
        this.callbackHandler = callbackHandler;
    }

    @Override
    public boolean login() throws LoginException {
        Callback[] callbackArray = new Callback[2];
        callbackArray[0] = new NameCallback("User Name:");
        callbackArray[1] = new PasswordCallback("Password:", false);
        try {
            callbackHandler.handle(callbackArray);
        } catch (IOException | UnsupportedCallbackException e) {
            throw new RuntimeException(e);
        }
        String name = ((NameCallback) callbackArray[0]).getName();
        String password = new String(((PasswordCallback) callbackArray[1]).getPassword());
        if (name.equals(TEST_USER) && password.equals(TEST_PASSWORD)) {
            System.out.println("Login success...");
            authSuccess = true;
        } else {
            authSuccess = false;
            System.out.println("Login failure..");
        }
        return authSuccess;
    }

    @Override
    public boolean commit() throws LoginException {
        return authSuccess;
    }

    @Override
    public boolean abort() throws LoginException {
        return false;
    }

    @Override
    public boolean logout() throws LoginException {
        return false;
    }
}
