package org.meng.demo.model;

import javax.persistence.Table;

/**
 * 角色表
 *
 * @author Lu1FFy
 * @date 2018年09月19日13:59:15
 */
@Table(name = "sys_role")
public class SysRole {

    private Integer id;
    private String roleName;
    private String roleInfo;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleInfo() {
        return roleInfo;
    }

    public void setRoleInfo(String roleInfo) {
        this.roleInfo = roleInfo;
    }
}
