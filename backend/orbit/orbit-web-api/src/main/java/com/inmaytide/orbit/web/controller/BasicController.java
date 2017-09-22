package com.inmaytide.orbit.web.controller;

import com.inmaytide.orbit.exceptions.IllegalParameterException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.BindingResult;

public abstract class BasicController {

    protected void handleBindingResult(BindingResult binding) {
        if (binding.hasErrors()) {
            throw new IllegalParameterException();
        }
    }

    protected void requireNotBlank(String parameter) {
        if (StringUtils.isBlank(parameter)) {
            throw new IllegalParameterException();
        }
    }

}
