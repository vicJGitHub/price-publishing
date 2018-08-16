package com.hywa.pricepublish.dao.entity;

public class RoleMenuKey {

    private String roleId;

    private String menuId;

    public RoleMenuKey() {
    }

    public RoleMenuKey(String roleId, String menuId) {
        this.setRoleId(roleId);
        this.setMenuId(menuId);
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId == null ? null : menuId.trim();
    }
}