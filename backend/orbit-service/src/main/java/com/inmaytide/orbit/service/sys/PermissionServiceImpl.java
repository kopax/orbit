package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.consts.Constants;
import com.inmaytide.orbit.consts.PermissionCategory;
import com.inmaytide.orbit.dao.sys.PermissionRepository;
import com.inmaytide.orbit.exceptions.VersionMatchedException;
import com.inmaytide.orbit.model.sys.Permission;
import io.jsonwebtoken.lang.Assert;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl extends AbstractCrudService<PermissionRepository, Permission, Long> implements PermissionService {

    @Resource
    private UserService userService;

    public PermissionServiceImpl(PermissionRepository repository) {
        super(repository);
    }

    @Override
    public Set<String> findCodesByUsername(String username) {
        return new HashSet<>(getRepository().findCodesByUsername(username));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Permission add(Permission inst) {
        inst.setVersion(0);
        inst.setCreateTime(LocalDateTime.now());
        inst.setCreator(userService.getCurrent().getId());
        inst.setSort(getRepository().getSort());
        return getRepository().save(inst);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Permission modify(Permission inst) {
        Permission origin = this.get(inst.getId());
        if (null == origin || Objects.equals(origin.getVersion(), inst.getVersion())) {
            throw new VersionMatchedException(origin);
        }
        BeanUtils.copyProperties(inst, origin, FINAL_FIELDS);
        setUpdateInformation(origin);
        update(origin);
        return origin;
    }

    @Override
    public List<Permission> findMenusByUsername(String username) {
        List<Permission> list = getRepository().findByUsername(username, String.valueOf(PermissionCategory.MENU.getCode()));
        return listToTree(list);
    }

    @Override
    public List<Permission> findList() {
        List<Permission> list = getRepository().findAll(new Sort(Sort.Direction.ASC, "sort"));
        return listToTree(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteBatch(Long[] ids) {
        Assert.notEmpty(ids);
        getRepository().deleteByIdIn(ids);
    }

    @Override
    public Boolean checkCode(String code, Long id) {
        return getRepository().countByCodeAndIdNot(code, id) == 0;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void exchangeSort(Permission[] permissions) {
        modifySort(permissions[0].getId(), permissions[1].getSort());
        modifySort(permissions[1].getId(), permissions[0].getSort());
    }


    private void modifySort(Long id, Integer sort) {
        updateIgnore(setUpdateInformation(new Permission(id, sort)));
    }

    private Permission setUpdateInformation(Permission permission) {
        permission.setUpdateTime(LocalDateTime.now());
        permission.setUpdater(userService.getCurrent().getId());
        return permission;
    }

    private List<Permission> listToTree(List<Permission> list) {
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
