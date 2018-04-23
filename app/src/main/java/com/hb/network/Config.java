package com.hb.network;

public class Config {

    /**
     * 环境——动态配置项
     */
    public static ProgramMode   mode                 = ProgramMode.PROGRAM_TEST_MODE;

    /**
     * 是否调试
     */
    public static final boolean DEBUG                = !(mode == ProgramMode.PRGRAM_PRODUCT_MODE);

    /** 接口信息 */
    /**
     * 域名
     */
    public static String        API_DEVICE           = "";
    public static String        API_API           = "";
    public static String        VERSION              = "api/v1/";

    static {
        switch (mode) {
            case PRGRAM_PRODUCT_MODE:
                API_DEVICE = "";
                break;
            case PRGRAM_PREPRODUCT_MODE:
                API_DEVICE = "http://47.74.250.76:9091/";
                API_API = "http://192.168.10.58/";
                break;
            case PROGRAM_TEST_MODE:
                API_DEVICE = "http://47.74.250.76:9091/";
                API_API = "http://192.168.10.58/";
                break;
            case PROGRAM_DEV_MODE:
                API_DEVICE = "";
                break;
        }
    }

    /**
     * 系统运行环境参数
     */
    public enum ProgramMode {
        /**
         * 线上参数
         */
        PRGRAM_PRODUCT_MODE,
        /**
         * 预发参数
         */
        PRGRAM_PREPRODUCT_MODE,
        /**
         * 测试参数
         */
        PROGRAM_TEST_MODE,
        /**
         * 开发参数
         */
        PROGRAM_DEV_MODE
    }

}
