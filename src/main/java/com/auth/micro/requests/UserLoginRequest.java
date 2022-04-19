package com.auth.micro.requests;

import java.io.Serializable;

public class UserLoginRequest  implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -6628097687835093244L;
    
    private String email;
    private String password;
    
    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }

}
