package com.inmaytide.orbit.web.controller;

import com.inmaytide.orbit.exceptions.InvalidParameterException;
import org.springframework.validation.BindingResult;

public abstract class BasicController {

    protected void handleBindingResult(BindingResult binding) {
        if (binding.hasErrors()) {
            throw new InvalidParameterException(binding.getAllErrors());
        }
    }

}
