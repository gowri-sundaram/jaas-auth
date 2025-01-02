package com.jaas.tutorial.security;

import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

public class Driver {
    public static void main(String[] args) {
        LoginContext loginContext;
        // The below config can be moved to a VM argument under run configuration.
        // -Djava.security.auth.login.config=file:/Users/gsundaram/IdeaProjects/jaas-auth/src/jaas.config
        // Can also be moved to the java.security file.
        // /Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home/conf/security
        // login.config.url.1=file:/Users/gsundaram/IdeaProjects/jaas-auth/src/jaas.config
        // System.setProperty("java.security.auth.login.config", "/Users/gsundaram/IdeaProjects/jaas-auth/src/jaas.config");
        try {
            loginContext = new LoginContext("JaasConfig", new CallbackHandlerImpl());
        } catch (LoginException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                loginContext.login();
                break;
            } catch (LoginException e) {
                e.printStackTrace();
            }
        }
    }
}