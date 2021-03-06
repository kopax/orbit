package com.inmaytide.orbit.service.sys.impl;

import com.inmaytide.orbit.consts.Constants;
import com.inmaytide.orbit.consts.PermissionCategory;
import com.inmaytide.orbit.dao.sys.PermissionRepository;
import com.inmaytide.orbit.exception.IllegalParameterException;
import com.inmaytide.orbit.domain.sys.Permission;
import com.inmaytide.orbit.service.sys.PermissionService;
import com.inmaytide.orbit.utils.IdGenerator;
import org.springframework.beans.BeanUtils;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Sort;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class PermissionServiceImpl extends AbstractCrudService<PermissionRepository, Permission, Long> implements PermissionService {

    public PermissionServiceImpl(PermissionRepository repository) {
        super(repository);
    }

    @Override
    public Set<String> findCodesByUsername(String username) {
        return new HashSet<>(getRepository().findCodesByUsername(username));
    }

    @Override
    public List<Permission> findByRoleIds(final Long[] roleIds) {
        Assert.notEmpty(roleIds, "The parameter \"roleIds\" must not be empty: it must contain at least 1 element");
        return getRepository().findByRoleIds(roleIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Permission add(Permission inst) {
        inst.setId(IdGenerator.getInstance().nextId());
        inst.setVersion(START_VERSION);
        inst.setSort(getRepository().getSort());
        return getRepository().insert(inst);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Permission modify(Permission inst) {
        Permission origin = this.get(inst.getId()).orElseThrow(IllegalParameterException::new);
        BeanUtils.copyProperties(inst, origin, FINAL_FIELDS);
        origin.setVersion(origin.getVersion() + 1);
        return getRepository().update(origin);
    }

    @Override
    @Cacheable(cacheNames = "user_menus", key = "#username + '_menus'")
    public List<Permission> findByUsername(String username) {
        List<Permission> list = getRepository().findByUsername(username, String.valueOf(PermissionCategory.MENU.getCode()));
        return listToTree(list);
    }

    @Override
    public List<Permission> findAllListToTree() {
        List<Permission> list = getRepository().findAll(new Sort(Sort.Direction.ASC, "sort"));
        return listToTree(list);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(String ids) {
        getRepository().deleteByIdIn(split(ids, Long::valueOf, Long[]::new));
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
        updateIgnore(new Permission(id, sort));
    }

    private List<Permission> listToTree(List<Permission> list) {
        Permission root = Permission.of(Constants.MENU_ROOT_ID);
        setChildren(root, list);
        return new ArrayList<>(root.getChildren());
    }

    private static void setChildren(Permission permission, List<Permission> list) {
        List<Permission> children = list.stream()
                .filter(p -> p.getParent().equals(permission.getId()))
                .peek(p -> setChildren(p, list))
                .collect(Collectors.toList());
        permission.setChildren(children);
    }
}
