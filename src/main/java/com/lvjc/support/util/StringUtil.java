package com.lvjc.support.util;

import java.io.UnsupportedEncodingException;

/**
 * Created by lvjc on 2017/7/19.
 */
public class StringUtil {

    private static final String UTF8 = "UTF-8";

    public static byte[] stringToByteArray(String str){
        try {
            return str.getBytes(UTF8);
        } catch (UnsupportedEncodingException e) {
            return str.getBytes();
        }
    }

    public static String byteArrayToString(byte[] bytes){
        try {
            return new String(bytes, UTF8);
        } catch (UnsupportedEncodingException e) {
            return new String(bytes);
        }
    }
}
