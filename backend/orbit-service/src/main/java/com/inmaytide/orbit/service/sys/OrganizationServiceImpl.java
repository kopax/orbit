package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.dao.sys.OrganizationRepository;
import com.inmaytide.orbit.model.sys.Organization;
import org.springframework.data.support.AbstractCrudService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationServiceImpl extends AbstractCrudService<OrganizationRepository, Organization, Long> implements OrganizationService {

    public OrganizationServiceImpl(OrganizationRepository repository) {
        super(repository);
    }

    @Override
    public List<Organization> findByRoleIds(Long[] ids) {
        return null;
    }
}
