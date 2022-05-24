package com.auth.micro.requests;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UserRequest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -8639161769741076761L;

    @NotNull(message = "nom field cannot be null")
    @NotEmpty(message = "nom field cannot be empty")
    @Size(min = 3, message = "password size should be at least 3 characters")
    private String nom;

    @NotNull(message = "prenom field cannot be null")
    @NotEmpty(message = "prenom field cannot be empty")
    @Size(min = 3)
    private String prenom;

    @NotNull(message = "userName field cannot be null")
    @NotEmpty(message = "userName field cannot be empty")
    @Size(min = 3, message = "username size should be at least 3 characters")
    private String userName;

    @NotNull(message = "email field cannot be null")
    @NotEmpty(message = "email field cannot be empty")
    @Size(min = 8, message = "email size should be at least 8 characters")
    @Email(message = "the field doesn't well-formed as an email")
    private String email;

    @NotNull(message = "password field cannot be null")
    @NotEmpty(message = "password field cannot be empty")
    @Size(min = 8, message = "password size should be at least 8 characters")
    @Pattern(regexp = "(?=^.{8,}$)((?=.*\\d)|(?=.*\\W+))(?![.\\n])(?=.*[A-Z])(?=.*[a-z]).*$\r\n", message = "the password should be contained lowercase and uppercase and Number/SpecialChar and min 8 Chars")
    private String password;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
