package com.stone.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "user")
public class User extends AbstractDomainObject {

    private static final long serialVersionUID = 1971806339120949315L;

    @JsonProperty("username")
    @Column(name = "name")
    private String name;

    @Column(name = "password")
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
