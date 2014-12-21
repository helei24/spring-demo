package com.stone.commons.core.beans;

public interface Identifiable<ID> {

    /**
     * 
     * @return The value of the field identifying the implementing entity.
     */
    ID getId();

    /**
     * Set the value of the field identifying the implementing entity.
     * 
     * @param id
     *            ID value
     */
    void setId(ID id);
}
