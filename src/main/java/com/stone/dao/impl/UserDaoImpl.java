package com.stone.dao.impl;

import static com.stone.common.lang.ListUtils.getFirstOrNull;

import com.stone.common.hibernate.GenericDaoHibernate;
import com.stone.dao.UserDao;
import com.stone.domain.User;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl extends GenericDaoHibernate<User, Long> implements UserDao {

    @Override
    public User findUserByName(String name) {

        DetachedCriteria criteria = DetachedCriteria.forClass(User.class);
        criteria.add(Restrictions.eq("name", name));

        List<Object> users = getHibernateTemplate().findByCriteria(criteria);
        return (User) getFirstOrNull(users);
    }
}
