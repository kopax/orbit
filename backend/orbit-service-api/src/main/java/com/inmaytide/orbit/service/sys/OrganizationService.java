package com.inmaytide.orbit.service.sys;

import com.inmaytide.orbit.model.sys.Organization;
import com.inmaytide.orbit.service.basic.BasicService;

import java.util.List;

public interface OrganizationService extends BasicService {

    List<Organization> findByRoleIds(Long[] ids);

}
