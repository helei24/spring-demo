package com.stone.common.hibernate;

import java.io.Serializable;
import java.util.List;

public interface GenericDao<T, ID extends Serializable> {

    /**
     * Finds an instance with the provided ID.
     * 
     * @param id
     * @param lock
     * @return
     */
    T findById(ID id, boolean lock);

    T findById(ID id);

    List<T> findByIds(ID... ids);

    /**
     * Gets all instances of type T
     * 
     * @return
     */
    List<T> findAll();

    /**
     * Gets all instances of type T whose properties match those of the example
     * instance. Properties can be excluded from the comparison by listing the
     * in the excludeProperty parameter.
     * 
     * @param exampleInstance
     * @param excludeProperty
     * @return
     */
    List<T> findByExample(T exampleInstance, String... excludeProperty);

    ID save(T entity);

    /**
     * Updates an already persisted object, saving recent changes to the
     * database.
     * 
     * @param entity
     */
    void update(T entity);

    void delete(T entity);

    void saveOrUpdate(T entity);

    /**
     * Force current session to flush.
     */
    void flush();

    void clear();

    void evict(T entity);

    T merge(T entity);
}
