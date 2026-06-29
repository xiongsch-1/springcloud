package com.zhaowa.galaxy.springcloud.customer.loadbalance;

public class TrafficContext {

    public static final InheritableThreadLocal<String> TRAFFIC_VERSION = new InheritableThreadLocal<>();

    public static void setTrafficVersion(String version) {
        TRAFFIC_VERSION.set(version);
    }

    public static String getTrafficVersion(){
        return TRAFFIC_VERSION.get();
    }

    public static void clear() {
        TRAFFIC_VERSION.remove();
    }
}
