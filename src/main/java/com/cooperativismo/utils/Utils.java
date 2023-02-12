package com.cooperativismo.utils;

import org.springframework.util.StringUtils;

public class Utils {
    public static Boolean isNotNullOrEmpty(String string){
        return StringUtils.hasLength(string);
    }
    public static Boolean isNullOrEmpty(String string){
        return !StringUtils.hasLength(string);
    }
}

