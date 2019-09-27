package com.tingbob.fastform.utils;

import java.util.Date;

public class Utils {
    public static String generateKey() {
        String key = (97 + Math.ceil(Math.random() * 25)) + "" + new Date().getTime() + "" + Math.ceil(Math.random() * 99999);
        return key;
    }
}
