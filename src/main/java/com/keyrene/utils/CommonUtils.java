package com.keyrene.utils;

import java.util.UUID;

/**
 * Created by DELL on 2017/7/18.
 */
public class CommonUtils {

    //生成uid的
    public static String getUUid(){
        return UUID.randomUUID().toString();
    }
}
