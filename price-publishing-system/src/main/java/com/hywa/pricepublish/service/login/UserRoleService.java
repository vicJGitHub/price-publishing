package com.hywa.pricepublish.service.login;

import com.hywa.pricepublish.dao.entity.UserRoleKey;
import java.util.List;


public interface UserRoleService {

    void insertBatch(List<UserRoleKey> userRoleKeys);
}
