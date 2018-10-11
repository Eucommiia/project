package org.meng.demo.model;

import javax.persistence.Table;

/**
 * 用户角色关联
 *
 * @date 2018年09月19日14:01:14
 * @author Lu1FFy
 */
@Table(name = "sys_user_role")
public class SysUserRole {

    private  Integer id;
    private   Integer userId;
    private Integer roleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
}
