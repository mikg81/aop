package com.aop.performance;

public class RequestUuid {
    private static ThreadLocal<String> contextHolder = new ThreadLocal();

    public RequestUuid() {
    }

    public static void setUuid(String uuid) {
        contextHolder.set(uuid);
    }

    public static String getUuid() {
        String headers = (String)contextHolder.get();
        return headers;
    }

    public static void reset() {
        setUuid((String)null);
    }
}