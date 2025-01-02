package com.jaas.tutorial.security;

import javax.security.auth.callback.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class CallbackHandlerImpl implements CallbackHandler {

    @Override
    public void handle(Callback[] callbacks) throws IOException, UnsupportedCallbackException {
        NameCallback nameCallback = null;
        PasswordCallback passwordCallback = null;

        for (Callback callback : callbacks) {
            if (callback instanceof NameCallback) {
                nameCallback = (NameCallback) callback;
                System.out.println(nameCallback.getPrompt());
                nameCallback.setName(new BufferedReader(new InputStreamReader(System.in)).readLine());
            } else if (callback instanceof PasswordCallback) {
                passwordCallback = (PasswordCallback) callback;
                System.out.println(passwordCallback.getPrompt());
                passwordCallback.setPassword(
                        new BufferedReader(new InputStreamReader(System.in)).readLine().toCharArray());
            }
        }
    }
}
