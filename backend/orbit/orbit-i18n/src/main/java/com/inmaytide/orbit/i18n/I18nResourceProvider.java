package com.inmaytide.orbit.i18n;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Frontend i18n resource provider
 *
 * @author Moss
 * @since September 11, 2017
 */
@RestController
public class I18nResourceProvider {

    @Autowired
    private I18nResourceHolder resourceHolder;

    @GetMapping("lang/{lang}")
    public Map<String, String> lang(@PathVariable String lang) {
        return StringUtils.isEmpty(lang) ? resourceHolder.getResources() : resourceHolder.getResources(lang);
    }

}
