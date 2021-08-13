package org.cannedcoffee.springboot.config.auth.dto;

import lombok.Getter;
import org.cannedcoffee.springboot.domain.user.User;

import java.io.Serializable;

@Getter
//this class will contain authorized information
public class SessionUser implements Serializable {

    private String name;
    private String email;
    private String picture;

    public SessionUser (User user){
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }

}
