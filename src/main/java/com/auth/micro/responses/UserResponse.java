package com.auth.micro.responses;

import java.io.Serializable;

public class UserResponse implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 5817632212016918738L;

    private String nom;

    private String prenom;

    private String userName;
    
    private String userId;

    
    public String getUserId() {
        return userId;
    }

    
    public void setUserId(String userId) {
        this.userId = userId;
    }

    private String email;



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
