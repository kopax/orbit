package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.dao.sys.RoleRepository;
import com.inmaytide.orbit.model.basic.PageModel;
import com.inmaytide.orbit.model.sys.Role;
import org.apache.commons.lang3.StringUtils;
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
    private RoleRepository roleRepository;

    /**
     * 构造函数.
     *
     * @param repository 注入的Repository
     */
    public RoleServiceImpl(RoleRepository repository) {
        super(repository);
    }

    @Override
    public Set<String> findCodesByUsername(String username) {
        return new HashSet<>(roleRepository.findCodesByUsername(username));
    }

    @Override
    public Page<Role> findList(PageModel pageModel) {
        Pageable pageable = pageModel.toPageable();
        if (StringUtils.isNotEmpty(pageModel.getKeywords())) {
            return getRepository().findByCodeLikeOrNameLike(pageModel.getKeywords(), pageModel.getKeywords(), pageable);
        } else {
            return getRepository().findAll(pageable);
        }
    }
}
