package com.stone.domain;

import java.io.Serializable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractDomainObject implements Serializable {

    private static final long serialVersionUID = -1674280125415421762L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    public Long getId() {

        return id;
    }

    public void setId(Long id) {

        this.id = id;
    }

}
