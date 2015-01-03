package com.stone.authentication.dao;

import com.stone.common.hibernate.GenericDao;
import com.stone.authentication.Role;

public interface RoleDao extends GenericDao<Role, Long> {

    public Role findRoleByName(String name);
}
