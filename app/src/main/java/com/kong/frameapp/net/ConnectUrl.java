package com.kong.frameapp.net;

/**
 * URL地址库
 * Created by kongdq on 2016/6/2.
 */
public class ConnectUrl {
    /**
     * 是否是测试服务器
     */
    public final static boolean IS_TEST_SERVER = false;


    ////////---------UAT------------////////
    public final static String UAT_HOST = "http://test.kaipai.net";
    /**
     * UAT环境服务器地址
     */
    public final static String UAT_BASE_URL = UAT_HOST + "/kp-web";
    /**
     * UAT环境:星主页相关
     */
    public final static String UAT_BASE_URL_START = UAT_HOST + "/kp-fd";
    /**
     * UAT环境:交易系统服务器地址
     */
    public final static String UAT_BASE_URL_TRADE = UAT_HOST + "/xf-trade-web";
    /**
     * UAT环境:交易查询系统服务器地址
     */
    public final static String UAT_BASE_URL_QUERY = UAT_HOST + "/xf-ch-web";


    ////////---------生产------------////////
    public final static String PRODUCT_HOST = "http://www.kaipai.net";
    ////////---------项目分享------------////////
    public final static String SHARE_HOST = "http://wap.kaipai.net";
    /**
     * 生产环境服务器地址
     */
    public final static String PRODUCT_BASE_URL = PRODUCT_HOST + "/kp-web";
    /**
     * 生产环境:星主页相关
     */
    public final static String PRODUCT_BASE_URL_START = PRODUCT_HOST + "/kp-fd";
    /**
     * 生产环境交易系统服务器地址
     */
    public final static String PRODUCT_BASE_URL_TRADE = PRODUCT_HOST + "/xf-trade-web";
    /**
     * 生产环境交易查询系统服务器地址
     */
    public final static String PRODUCT_BASE_URL_QUERY = PRODUCT_HOST + "/xf-ch-web";


    /**
     * 当前服务器地址
     */
    public final static String BASE_HOST = IS_TEST_SERVER ? UAT_HOST : PRODUCT_HOST;
    /**
     * 当前服务器地址
     */
    public final static String BASE_URL = IS_TEST_SERVER ? UAT_BASE_URL : PRODUCT_BASE_URL;

    /**
     * 推送key
     */
    public final static String PUSH_KEY = IS_TEST_SERVER ? "bb1b7bb58f5c9001f7f26e2b" : "ab7c2f12a74876ae15bd40d9";
    /**
     * 当前服务器:星主页相关
     */
    public final static String BASE_URL_START = IS_TEST_SERVER ? UAT_BASE_URL_START : PRODUCT_BASE_URL_START;
    /**
     * 当前交易系统服务器地址
     */
    public final static String BASE_URL_TRADE = IS_TEST_SERVER ? UAT_BASE_URL_TRADE : PRODUCT_BASE_URL_TRADE;
    /**
     * 当前交易查询系统服务器地址
     */
    public final static String BASE_URL_QUERY = IS_TEST_SERVER ? UAT_BASE_URL_QUERY : PRODUCT_BASE_URL_QUERY;


    /**
     * 登陆接口
     */
    public final static String URL_LOGIN = BASE_URL + "/service/base/appLogin";
    /**
     * 注册接口
     */
    public final static String URL_REGIST = BASE_URL + "/service/base/appRegister";
    /**
     * 获取短信验证码接口
     */
    public final static String URL_REGIST_VERIFY_CODE = BASE_URL + "/service/base/appGetMsgCode";
    /**
     * 验证短信验证码接口
     */
    public final static String URL_REGIST_DO_VERIFY_CODE = BASE_URL + "/service/base/app/validateMsgCode";
    /**
     * 重置密码接口
     */
    public final static String URL_RESET_PW = BASE_URL + "/service/base/app/restUserPassWord";
    /**
     * 重置密码接口
     */
    public final static String URL_RESET_PAY = BASE_URL + "/service/user/app/tdpwd/reset";
    /**
     * 获取公钥接口
     */
    public final static String URL_PUBLIC_KEY = BASE_URL + "/service/system/pubkey";


    /**
     * modules：星广场
     * name：粉圈首页
     * v：1.9.0
     */
    public final static String URL_STAR_CIRCLE_BASE = BASE_URL_START + "/service/pink/getFdChPinkData";

    /**
     * modules：星广场
     * name：粉圈加载更多
     * v：1.9.0
     */
    public final static String URL_STAR_MORE_DATA = BASE_URL_START + "/service/pink/getFdChPinkMsgPage";

    /**
     * modules：星广场
     * name：粉圈点赞
     * v：1.9.0
     */
    public final static String URL_STAR_CIRCLE_PRAISE = BASE_URL_START + "/service/pink/addFdChPinkPraise";




}
