package com.keyrene.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 得到properties文件的值
 * Created by DELL on 2017/7/23.
 */
public class PropertiesUtil {

    public static String getValue(String key){
        Properties prop = new Properties();
        InputStream in = PropertiesUtil.class.getResourceAsStream( "/properties/email.properties" );
        try {
            prop.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }
}
