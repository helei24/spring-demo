package com.stone.dao.impl;

import static com.stone.common.lang.ListUtils.getFirstOrNull;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.stone.common.hibernate.GenericDaoHibernate;
import com.stone.dao.UserDao;
import com.stone.domain.User;

public class UserDaoImpl extends GenericDaoHibernate<User, Long> implements UserDao {

    @SuppressWarnings("unchecked")
    @Override
    public User findUserById(int id) {

        // String hql = "SELECT  FROM user WHERE user.id = :id ";
        // Session session =
        // getHibernateTemplate().getSessionFactory().getCurrentSession();
        // Query query = session.createQuery(hql);
        // query.setParameter("id", id);
        // List<User> users = query.list();

        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("id", id));

        List<Object> users = getHibernateTemplate().findByCriteria(criteria);
        return (User) getFirstOrNull(users);
    }

    @Override
    public User findUserByName(String name) {

        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("name", name));

        List<Object> users = getHibernateTemplate().findByCriteria(criteria);
        return (User) getFirstOrNull(users);
    }

    @Override
    public List<User> findAllUser() {

        return this.findAll();
    }

}
