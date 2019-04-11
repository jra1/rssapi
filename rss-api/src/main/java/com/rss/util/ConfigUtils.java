package com.rss.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class ConfigUtils {

    @Autowired
    private Environment env;

    public String getProperty(String property) {
        return env.getProperty(property);
    }
} 
