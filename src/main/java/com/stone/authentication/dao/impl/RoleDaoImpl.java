package com.stone.authentication.dao.impl;

import static com.stone.common.lang.ListUtils.getFirstOrNull;

import com.stone.common.hibernate.GenericDaoHibernate;
import com.stone.dao.RoleDao;
import com.stone.authentication.Role;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RoleDaoImpl extends GenericDaoHibernate<Role, Long> implements RoleDao {

    @Override
    public Role findRoleByName(String name) {

        DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);
        criteria.add(Restrictions.eq("name", name));

        List<Object> roles = getHibernateTemplate().findByCriteria(criteria);
        return (Role) getFirstOrNull(roles);
    }
}
