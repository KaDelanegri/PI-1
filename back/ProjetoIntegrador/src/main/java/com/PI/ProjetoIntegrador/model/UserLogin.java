package com.PI.ProjetoIntegrador.model;

import java.sql.Blob;

public class UserLogin {

    private String username;

    private String email;

    private String password;

    private String token;

    private Blob photo;

    private String vocationalTest;

    public String getUsername() {
        return username;
    }

    public void setUsername(final String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }

    public Blob getPhoto() {
        return photo;
    }

    public void setPhoto(final Blob photo) {
        this.photo = photo;
    }

    public String getVocationalTest() {
        return vocationalTest;
    }

    public void setVocationalTest(final String vocationalTest) {
        this.vocationalTest = vocationalTest;
    }
}
