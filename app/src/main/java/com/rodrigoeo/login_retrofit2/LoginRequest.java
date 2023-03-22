package com.rodrigoeo.login_retrofit2;

public class LoginRequest {

    private String usuario;
    private String contrasena;


    public String getUser() {
        return usuario;
    }

    public void setUser(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return contrasena;
    }

    public void setPassword(String contrasena) {
        this.contrasena = contrasena;
    }
}
