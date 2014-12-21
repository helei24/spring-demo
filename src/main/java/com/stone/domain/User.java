package com.stone.domain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity(name = "user")
public class User extends AbstractDomainObject {

    private static final long serialVersionUID = 1971806339120949315L;

    @JsonProperty("username")
    @JoinColumn(name = "name")
    private String name;

    @JoinColumn(name = "password")
    private String password;

    public String getName() {

        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }

}
