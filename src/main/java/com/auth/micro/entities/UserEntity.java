package com.auth.micro.entities;

import static javax.persistence.GenerationType.AUTO;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="users")
public class UserEntity implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = -2441789809697968445L;

    @Id
    @GeneratedValue(strategy = AUTO)
    private long   id;
    
    @Column(nullable = false)
    private String userId;
    
    @Column(nullable = false)
    private String nom;
    
    @Column(nullable = false)
    private String prenom;
    
    @Column(nullable = false)
    private String userName;
    
    @Column(nullable = false)
    private String encryptedPassword;
    
    @Column(nullable = false)
    private String email;

    public UserEntity() {
        super();
    }

    public UserEntity(String nom, String prenom, String userName, String encryptedPassword, String email) {
        super();
        this.nom               = nom;
        this.prenom            = prenom;
        this.userName          = userName;
        this.encryptedPassword = encryptedPassword;
        this.email             = email;
    }

    public UserEntity(String userId, String nom, String prenom, String userName, String encryptedPassword, String email) {
        super();
        this.userId            = userId;
        this.nom               = nom;
        this.prenom            = prenom;
        this.userName          = userName;
        this.encryptedPassword = encryptedPassword;
        this.email             = email;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public String getEncryptedPassword() {
        return encryptedPassword;
    }

    public void setEncryptedPassword(String encryptedPassword) {
        this.encryptedPassword = encryptedPassword;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
