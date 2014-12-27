package com.stone.common.hibernate;

import static com.stone.common.lang.Validators.ia;
import static java.lang.String.format;

import org.hibernate.LockMode;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

public abstract class GenericDaoHibernate<T, ID extends Serializable> extends HibernateDaoSupport
        implements GenericDao<T, ID> {

    protected final Class<T> persistentClass;

    @Resource
    public void setSessionFactoryResource(final SessionFactory sessionFactory) {

        setSessionFactory(sessionFactory);
    }

    @SuppressWarnings("unchecked")
    public GenericDaoHibernate() {

        Type parameterizedType = getParameterizedType(getClass());

        ia.notNull(parameterizedType,
                "The abstract type used by the class [%s] have no parametrized type", getClass()
                        .getName());

        this.persistentClass = (Class<T>) ((ParameterizedType) parameterizedType)
                .getActualTypeArguments()[0];
    }

    private static Type getParameterizedType(final Class<?> clazz) {

        boolean isObject = clazz.getName().equals(Object.class.getName());
        if (isObject) {
            return null;
        }

        Type type = clazz.getGenericSuperclass();
        boolean isParametizedType = type instanceof ParameterizedType;

        if (isParametizedType) {
            return type;
        } else {
            Class<?> superClass = clazz.getSuperclass();
            return getParameterizedType(superClass);
        }

    }

    @Override
    @SuppressWarnings("unchecked")
    public ID save(final T entity) {

        return (ID) getHibernateTemplate().save(entity);
    }

    @Override
    public void update(final T entity) {

        getHibernateTemplate().update(entity);
    }

    @Override
    public void saveOrUpdate(final T entity) {

        getHibernateTemplate().saveOrUpdate(entity);
    }

    @Override
    public void delete(final T entity) {

        getHibernateTemplate().delete(entity);
    }

    @Override
    public final void flush() {

        getHibernateTemplate().flush();
    }

    @Override
    public final void clear() {

        getHibernateTemplate().clear();
    }

    @Override
    public final void evict(final T entity) {

        getHibernateTemplate().evict(entity);
    }

    @Override
    public T merge(final T entity) {

        return getHibernateTemplate().merge(entity);
    }

    @Override
    public final T findById(final ID id) {

        return findById(id, false);
    }

    @Override
    public final T findById(final ID id, final boolean lock) {

        T entity = null;

        if (lock) {
            entity = getHibernateTemplate().get(persistentClass, id, LockMode.UPGRADE);
        } else {
            entity = getHibernateTemplate().get(persistentClass, id);
        }
        return entity;
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<T> findByIds(final ID... ids) {

        ia.isTrue(ids.length > 0, "Id array must contain atleast 1 element");

        String idInString = createInString(ids);

        List<T> entries = (List<T>) getHibernateTemplate().find(
                format("from %s as entry where entry.id in %s", persistentClass.getSimpleName(),
                        idInString));
        return entries;
    }

    @Override
    public final List<T> findAll() {

        return findByCriteria();
    }

    @Override
    @SuppressWarnings("unchecked")
    public final List<T> findByExample(final T exampleInstance, final String... excludeProperty) {

        Example example = Example.create(exampleInstance);

        for (String exclude : excludeProperty) {
            example.excludeProperty(exclude);
        }

        DetachedCriteria criteria = getDefaultCriteria().add(example);
        return (List<T>) getHibernateTemplate().findByCriteria(criteria);
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    @SuppressWarnings("unchecked")
    protected final List<T> findByCriteria(final Criterion... criterion) {

        DetachedCriteria crit = getDefaultCriteria();

        for (Criterion c : criterion) {
            crit.add(c);
        }

        return (List<T>) getHibernateTemplate().findByCriteria(crit);
    }

    /**
     * Use this inside subclasses as a convenience method.
     */
    protected final DetachedCriteria getDefaultCriteria() {

        return DetachedCriteria.forClass(persistentClass);
    }

    protected void update(final String sql, final Object... params) {

        getHibernateTemplate().bulkUpdate(sql, params);
    }

    protected static <T> T firstOrNull(final List<T> entries) {

        if (entries == null || entries.size() == 0) {
            return null;
        }
        return entries.get(0);
    }

    protected String createInString(final ID... ids) {

        String inString = "(";
        for (Object object : ids) {
            inString += object.toString() + ", ";
        }
        inString = inString.substring(0, inString.length() - 2);
        inString += ")";
        return inString;
    }

}
