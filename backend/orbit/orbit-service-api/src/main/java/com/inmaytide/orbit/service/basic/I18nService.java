package com.inmaytide.orbit.service.basic;

import java.util.Locale;
import java.util.Map;

public interface I18nService {

    Map<String, String> getI18nResources();

    Map<String, String> getI18nResources(String lang);

    String getValue(String key, Object... args);

    String getValue(String key, Locale locale, Object... args);
}
