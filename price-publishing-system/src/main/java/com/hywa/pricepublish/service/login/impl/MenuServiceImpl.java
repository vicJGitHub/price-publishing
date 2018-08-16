package com.hywa.pricepublish.service.login.impl;

import com.hywa.pricepublish.common.ConstantPool;
import com.hywa.pricepublish.common.utils.UUIDUtils;
import com.hywa.pricepublish.dao.entity.Menu;
import com.hywa.pricepublish.dao.entity.MenuExample;
import com.hywa.pricepublish.dao.entity.RoleMenuKey;
import com.hywa.pricepublish.dao.mapper.MenuMapper;
import com.hywa.pricepublish.dao.mapper.RoleMenuMapper;
import com.hywa.pricepublish.representation.MenuRep;
import com.hywa.pricepublish.representation.MenuReps;
import com.hywa.pricepublish.service.login.MenuService;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;
    @Autowired
    private RoleMenuMapper roleMenuMapper;

    private static final String PARENT_ID = "#";

    @Override
    public int save(MenuRep menuRep) {
        Menu menu = new Menu();
        menu.setId(UUIDUtils.randomUUID());
        menu.setCreateTime(new Date());
        menu.setCreateUser(menuRep.getCreateUser());
        menu.setName(menuRep.getName());
        menu.setSort(menuRep.getSort());
        menu.setStatus(menuRep.getStatus());
        menu.setParentId(menuRep.getParentId());
        menu.setDescription(menuRep.getDescription());
        menu.setIcon(menuRep.getIcon());
        menu.setType(menuRep.getType());
        return menuMapper.insertSelective(menu);
    }

    @Override
    public int update(MenuRep menuRep) {
        Menu menu = new Menu();
        BeanUtils.copyProperties(menuRep,menu,"createTime","createUser");
        menu.setUpdateTime(new Date());
        return menuMapper.updateByPrimaryKeySelective(menu);
    }

    @Override
    public int delete(String id) {
        return menuMapper.deleteByPrimaryKey(id);
    }

    @Override
    public List<MenuRep> getChildren(String userId, String parentMenuId) {
        List<Menu> menuList = menuMapper.selectByUserIdAndParentId(userId, parentMenuId);
        List<MenuRep> menuReps = new ArrayList<>();
        menuList.forEach(menu -> menuReps.add(new MenuRep(menu)));
        return menuReps;
    }

    @Override
    public List<MenuRep> getMenuTreeByUserId(String userId, String requestMode) {
        List<Menu> menuList = menuMapper.selectMenusByUserId(userId);
        return getAllMenuRep(menuList, PARENT_ID, requestMode);
    }

    @Override
    public List<MenuRep> selectMenusByRoleId(String roleId) {
        List<Menu> menuList = menuMapper.selectByRoleId(roleId);

        List<MenuRep> menuChildReps = new ArrayList<>();
        for (Menu menuChild : menuList) {
            menuChildReps.add(new MenuRep(menuChild));
        }
        return menuChildReps;
    }

    @Override
    public List<MenuRep> getMenuAllTree() {
        MenuExample example = new MenuExample();
        example.createCriteria().andIsDelEqualTo(ConstantPool.NOT_DEL);
        example.setOrderByClause("sort DESC");
        List<Menu> menus = menuMapper.selectByExample(example);

        return getAllMenuRep(menus, PARENT_ID, ConstantPool.REUEST_MODE_NOT_ALL);
    }


    private List<MenuRep> getAllMenuRep(List<Menu> menuList, String parentId, String requestMode) {
        List<MenuRep> menuReps = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu == null) {
                continue;
            }

            if (menu.getParentId().equals(parentId)) {
                MenuRep menuRep = new MenuRep(menu);
                List<MenuRep> allMenuRep;
                if (ConstantPool.REUEST_MODE_ALL.equals(requestMode)) {
                    allMenuRep = getAllMenuRep(menuList, menu.getId(), requestMode);
                } else {
                    allMenuRep = findChildrenMenus(menuList, menu.getId());
                }
                menuRep.setChildMenus(allMenuRep);
                menuReps.add(menuRep);
            }
        }
        return menuReps;
    }

    private List<MenuRep> findChildrenMenus(List<Menu> menuList, String parentId) {
        List<MenuRep> menuRepList = new ArrayList<>();
        for (Menu menu : menuList) {
            if (menu == null) {
                continue;
            }
            if (menu.getParentId().equals(parentId)) {
                MenuRep menuRep1 = new MenuRep(menu);
                menuRepList.add(menuRep1);
            }
        }
        return menuRepList;
    }
}
