package io.jyotirmay.digitaltwin.cloudapi.v1.util;

public class CommonUtil {

    public static boolean isNull(String value){
        return null == value;
    }

    public static boolean isNotNullNotEmpty(String value){
        return null != value && !value.trim().isEmpty();
    }

    public static boolean isNotNull(Object value){
        return null != value;
    }
}
