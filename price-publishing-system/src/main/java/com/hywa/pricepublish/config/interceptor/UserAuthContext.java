package com.hywa.pricepublish.config.interceptor;

import com.hywa.pricepublish.common.enums.Module;
import com.hywa.pricepublish.common.utils.StringUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserAuthContext implements AuthStrategy {

    @Autowired
    private AuthService authService;

    @Override
    public boolean executeAuth(Module[] modules, String userId) throws Exception {
        //表示标注的方式属于所有用户可执行
        if (ArrayUtils.contains(modules, Module.ALL)) {
            return true;
        }
        //用户ID为空，不允许通过，直接返回false
        if (StringUtils.isEmpty(userId)) {
            return false;
        }

        List<Map<String, String>> permissionList = authService.getPermission(userId);

        List<String> moduleList = new ArrayList<>();

        for (Module module : modules) {
            moduleList.add(module.getModuleName());
        }

        List<String> hasList = new ArrayList<>();

        for (Map<String, String> map : permissionList) {
            hasList.add(map.get("priName"));
        }
        //如果用户拥有该接口所属模块的任何一个模块的权限则返回true,否则false
        return hasList.removeAll(moduleList);
    }

    public static void main(String[] args) {
        Module[] modules = {Module.COLLECT};
        List<Map<String, String>> permissionList = new ArrayList<>();
        HashMap<String, String> map1 = new HashMap<>();
        map1.put("priName", "采集模块");
        permissionList.add(map1);

        List<String> moduleList = new ArrayList<>();
        for (Module module : modules) {
            moduleList.add(module.getModuleName());
        }

        List<String> hasList = new ArrayList<>();
        for (Map<String, String> map : permissionList) {
            hasList.add(map.get("priName"));
        }
        //如果用户拥有该接口所属模块的任何一个模块的权限则返回true,否则false
        System.out.println(hasList.removeAll(moduleList));
    }
}
