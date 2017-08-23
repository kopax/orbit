package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.consts.Constants;
import com.inmaytide.orbit.consts.PermissionCategory;
import com.inmaytide.orbit.dao.sys.PermissionRepository;
import com.inmaytide.orbit.model.sys.Permission;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PermissionServiceImpl extends AbstractCrudService<PermissionRepository, Permission, String> implements PermissionService {
    /**
     * 构造函数.
     *
     * @param repository 注入的Repository
     */
    public PermissionServiceImpl(PermissionRepository repository) {
        super(repository);
    }

    @Override
    public Set<String> findCodesByUsername(String username) {
        return new HashSet<>(getRepository().findCodesByUsername(username));
    }

    @Override
    public Permission add(Permission inst) {
        inst.setVersion(0);
        inst.setCreateTime(LocalDateTime.now());
        return getRepository().save(inst);
    }

    @Override
    public List<Permission> findMenusByUsername(String username) {
        List<Permission> list = getRepository().findByUsername(username, String.valueOf(PermissionCategory.MENU.getCode()));
        Permission root = Permission.of(Constants.MENU_ROOT_ID);
        setChildren(root, list);
        return new ArrayList<>(root.getChildren());
    }

    private static void setChildren(Permission permission, List<Permission> list) {
        List<Permission> children = list.stream()
                .filter(p -> p.getParent().equals(permission.getId()))
                .collect(Collectors.toList());
        children.forEach(p -> setChildren(p, list));
        permission.setChildren(children);
    }


}
