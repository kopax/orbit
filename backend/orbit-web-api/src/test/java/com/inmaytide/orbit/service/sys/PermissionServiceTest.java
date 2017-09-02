package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.consts.Constants;
import com.inmaytide.orbit.consts.PermissionCategory;
import com.inmaytide.orbit.model.sys.Permission;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PermissionServiceTest {

    @Resource
    private PermissionService permissionService;

    @Test
    public void add() {
        Permission permission = new Permission();
        permission.setCode("workflow");
        permission.setCategory(Long.toString(PermissionCategory.MENU.getCode()));
        permission.setAction(null);
        permission.setParent(-1L);
        permission.setName("Workflow");
        permission.setCreator(9999L);
        permission = permissionService.add(permission);
        Assert.assertNotNull(permission.getId());
    }

    @Test
    public void findAll() {
        List<Permission> list = permissionService.findAll(new Permission());
        Assert.assertEquals(list.size(), 2);
    }

}

