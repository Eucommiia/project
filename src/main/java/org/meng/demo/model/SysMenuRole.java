package org.meng.demo.model;


import javax.persistence.Table;

/**
 * 角色菜单关联
 *
 * @date 2018-09-19 14:09:32
 * @author Lu1FFy
 */
@Table(name = "sys_menu_role")
public class SysMenuRole {

    private Integer id;
    private Integer roleId;
    private Integer menuId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}
