package com.ofonesie.ofonesie.models.forms;

import javax.validation.constraints.NotNull;

public class LoginForm {

    @NotNull (message = "Must include Username")
    String username;

    @NotNull(message = "Must have a Password")
    String password;

    public LoginForm(){}

    public LoginForm(String aUsername, String aPassword){
        this.username = aUsername;
        this.password = aPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
