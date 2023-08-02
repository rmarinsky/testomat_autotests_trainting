package io.testomat.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.experimental.UtilityClass;

import java.util.Properties;

@UtilityClass
public class CredentialsLoader {

    Properties properties = new Properties();

    public Credentials getAdmin() {
        return new Credentials("newromka@gmail.com", "p8qfCZ7Jv7pT!hh");
    }

    public Credentials getPecoCreds() {
        return new Credentials(properties.getProperty("admin.username"), properties.getProperty("admin.password"));
    }

    @Data
    @AllArgsConstructor
    public static class Credentials {

        private String username;
        private String password;

    }


}
