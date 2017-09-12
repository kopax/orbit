package com.inmaytide.orbit.web.controller;

import com.inmaytide.orbit.utils.I18nUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Frontend i18n resource provider
 *
 * @author Moss
 * @since September 11, 2017
 */
@RestController
public class I18nController {

    @Resource
    private I18nUtils i18n;

    @GetMapping("lang/{lang}")
    public Map<String, String> lang(@PathVariable String lang) {
        return StringUtils.isEmpty(lang) ? i18n.getI18nResources() : i18n.getI18nResources(lang);
    }

}
