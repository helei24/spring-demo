package com.stone.authentication.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.stone.domain.AbstractDomainObject;
import com.stone.domain.User;

import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

@Entity(name = "role")
public class Role extends AbstractDomainObject {

  private static final long serialVersionUID = 1971806339120949315L;

  @JsonProperty("username")
  @Column(name = "name", unique = true)
  private String name;

  @Column(name = "password")
  private String password;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "userrole", joinColumns = { @JoinColumn(name = "roleid") }, inverseJoinColumns = { @JoinColumn(name = "userid") })
  private Set<User> users;

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
