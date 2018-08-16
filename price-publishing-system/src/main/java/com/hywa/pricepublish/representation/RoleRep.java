package com.hywa.pricepublish.representation;

import com.hywa.pricepublish.dao.entity.Role;
import java.util.ArrayList;
import java.util.List;
import javax.validation.constraints.NotNull;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RoleRep {

    private String id;

    @NotNull(message = "角色名字不能为空")
    private String name;
    @NotNull(message = "角色描述不能为空")
    private String description;
    private String code;
    private List<MenuRep> menuReps;

    public RoleRep(Role role) {
        this.setId(role.getId());
        this.setName(role.getName());
        this.setDescription(role.getDescription());
        this.setCode(role.getCode());
    }

    private void setCode(String code) {
        this.code = code;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setName(String name) {
        this.name = name;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCode() {
        return code;
    }

    public List<MenuRep> getMenuReps() {
        return menuReps;
    }

    public void setMenuReps(List<MenuRep> menuReps) {
        this.menuReps = new ArrayList<>(menuReps);
    }
}
