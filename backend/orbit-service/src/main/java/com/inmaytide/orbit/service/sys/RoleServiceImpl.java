package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.dao.sys.RoleRepository;
import com.inmaytide.orbit.model.basic.PageModel;
import com.inmaytide.orbit.model.sys.Role;
import com.inmaytide.orbit.utils.IdGenerator;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Moss
 * @since September 10, 2017
 */
@Service
@Transactional(readOnly = true)
public class RoleServiceImpl extends AbstractCrudService<RoleRepository, Role, Long> implements RoleService {

    @Resource
    private PermissionService permissionService;

    @Resource
    private UserService userService;

    @Resource
    private OrganizationService organizationService;


    public RoleServiceImpl(RoleRepository repository) {
        super(repository);
    }

    @Override
    public Set<String> findCodesByUsername(String username) {
        return new HashSet<>(getRepository().findCodesByUsername(username));
    }

    @Override
    public Page<Role> findList(PageModel pageModel) {
        Pageable pageable = pageModel.toPageable();
        if (StringUtils.isNotEmpty(pageModel.getKeywords(true))) {
            return getRepository().findByCodeLikeOrNameLike(pageModel.getKeywords(true), pageModel.getKeywords(true), pageable);
        } else {
            return getRepository().findAll(pageable);
        }
    }

    @Override
    public Role get(Long id) {
        Role role = super.get(id);
        Long[] ids = new Long[]{id};
        role.setPermissions(permissionService.findByRoleIds(ids));
        role.setOrganizations(organizationService.findByRoleIds(ids));
        role.setUsers(userService.findByRoleIds(ids));
        return role;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role add(Role role) {
        role.setId(IdGenerator.getInstance().nextId());
        return getRepository().insert(role);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void remove(String ids) {
        getRepository().deleteByIdIn(split(ids, Long::valueOf, Long[]::new));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Role modify(Role role) {
        Role original = super.get(role.getId());
        matchVersion(role, original);
        BeanUtils.copyProperties(role, original, FINAL_FIELDS);
        original.setVersion(original.getVersion() + 1);
        getRepository().update(original);
        return get(role.getId());
    }

    @Override
    public Boolean checkCode(String code, Long id) {
        return getRepository().countByCodeAndIdNot(code, id) == 0;
    }
}
