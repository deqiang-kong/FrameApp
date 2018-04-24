package com.kong.frameapp.util;

import android.content.Context;

import com.kong.fmklibrary.base.BaseApplication;
import com.kong.fmklibrary.controls.slideback.ActivityHelper;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.cache.CacheEntity;
import com.lzy.okgo.cache.CacheMode;
import com.lzy.okgo.cookie.CookieJarImpl;
import com.lzy.okgo.cookie.store.DBCookieStore;
import com.lzy.okgo.https.HttpsUtils;
import com.lzy.okgo.interceptor.HttpLoggingInterceptor;
import com.lzy.okgo.model.HttpHeaders;
import com.lzy.okgo.model.HttpParams;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;

import okhttp3.OkHttpClient;


/**
 * 应用入口
 */
public class MyApplication extends BaseApplication {


    public static Context AppContext;

    public static MyApplication get(Context context) {
        return (MyApplication) context.getApplicationContext();
    }


    //在自己的Application中添加如下代码
    public static RefWatcher getRefWatcher(Context context) {
        MyApplication application = (MyApplication) context
                .getApplicationContext();
        return application.refWatcher;
    }

    //在自己的Application中添加如下代码
    private RefWatcher refWatcher;

    private ActivityHelper mActivityHelper;

    @Override
    public void onCreate() {
        super.onCreate();

        AppContext = this;
        refWatcher = LeakCanary.install(this);

        mActivityHelper = new ActivityHelper();
        registerActivityLifecycleCallbacks(mActivityHelper);

//        initImageLoader();
//        initJPush();
//        initBugtags();
//        initDB();

        initOkGo();
    }


    public static ActivityHelper getActivityHelper() {
        return ((MyApplication) AppContext).mActivityHelper;
    }

    private void initOkGo() {
        //---------这里给出的是示例代码,告诉你可以这么传,实际使用的时候,根据需要传,不需要就不传-------------//
        HttpHeaders headers = new HttpHeaders();
        headers.put("commonHeaderKey1", "commonHeaderValue1");    //header不支持中文，不允许有特殊字符
        headers.put("commonHeaderKey2", "commonHeaderValue2");
        HttpParams params = new HttpParams();
        params.put("commonParamsKey1", "commonParamsValue1");     //param支持中文,直接传,不要自己编码
        params.put("commonParamsKey2", "这里支持中文参数");
        //----------------------------------------------------------------------------------------//

        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        //log相关
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor("OkGo");
        loggingInterceptor.setPrintLevel(HttpLoggingInterceptor.Level.BODY);        //log打印级别，决定了log显示的详细程度
        loggingInterceptor.setColorLevel(Level.INFO);                               //log颜色级别，决定了log在控制台显示的颜色
        builder.addInterceptor(loggingInterceptor);
        //builder.addInterceptor(new ChuckInterceptor(this));                       //第三方的开源库，使用通知显示当前请求的log

        //超时时间设置，默认60秒
        builder.readTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);      //全局的读取超时时间
        builder.writeTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);     //全局的写入超时时间
        builder.connectTimeout(OkGo.DEFAULT_MILLISECONDS, TimeUnit.MILLISECONDS);   //全局的连接超时时间

        //自动管理cookie（或者叫session的保持），以下几种任选其一就行
        //builder.cookieJar(new CookieJarImpl(new SPCookieStore(this)));            //使用sp保持cookie，如果cookie不过期，则一直有效
        builder.cookieJar(new CookieJarImpl(new DBCookieStore(this)));              //使用数据库保持cookie，如果cookie不过期，则一直有效
        //builder.cookieJar(new CookieJarImpl(new MemoryCookieStore()));            //使用内存保持cookie，app退出后，cookie消失

        //https相关设置，以下几种方案根据需要自己设置
        //方法一：信任所有证书,不安全有风险
        HttpsUtils.SSLParams sslParams1 = HttpsUtils.getSslSocketFactory();
        //方法二：自定义信任规则，校验服务端证书
        HttpsUtils.SSLParams sslParams2 = HttpsUtils.getSslSocketFactory(new SafeTrustManager());
        //方法三：使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams3 = HttpsUtils.getSslSocketFactory(getAssets().open("srca.cer"));
        //方法四：使用bks证书和密码管理客户端证书（双向认证），使用预埋证书，校验服务端证书（自签名证书）
        //HttpsUtils.SSLParams sslParams4 = HttpsUtils.getSslSocketFactory(getAssets().open("xxx.bks"), "123456", getAssets().open("yyy.cer"));
        builder.sslSocketFactory(sslParams1.sSLSocketFactory, sslParams1.trustManager);
        //配置https的域名匹配规则，详细看demo的初始化介绍，不需要就不要加入，使用不当会导致https握手失败
        builder.hostnameVerifier(new SafeHostnameVerifier());

        // 其他统一的配置
        // 详细说明看GitHub文档：https://github.com/jeasonlzy/
        OkGo.getInstance().init(this)                           //必须调用初始化
                .setOkHttpClient(builder.build())               //设置OkHttpClient
                .setCacheMode(CacheMode.NO_CACHE)               //全局统一缓存模式，默认不使用缓存，可以不传
                .setCacheTime(CacheEntity.CACHE_NEVER_EXPIRE)   //全局统一缓存时间，默认永不过期，可以不传
                .setRetryCount(3)                               //全局统一超时重连次数，默认为三次，那么最差的情况会请求4次(一次原始请求，三次重连请求)，不需要可以设置为0
                .addCommonHeaders(headers)                      //全局公共头
                .addCommonParams(params);                       //全局公共参数
    }

    /**
     * 这里只是我谁便写的认证规则，具体每个业务是否需要验证，以及验证规则是什么，请与服务端或者leader确定
     * 这里只是我谁便写的认证规则，具体每个业务是否需要验证，以及验证规则是什么，请与服务端或者leader确定
     * 这里只是我谁便写的认证规则，具体每个业务是否需要验证，以及验证规则是什么，请与服务端或者leader确定
     * 重要的事情说三遍，以下代码不要直接使用
     */
    private class SafeTrustManager implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
            try {
                for (X509Certificate certificate : chain) {
                    certificate.checkValidity(); //检查证书是否过期，签名是否通过等
                }
            } catch (Exception e) {
                throw new CertificateException(e);
            }
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    /**
     * 这里只是我谁便写的认证规则，具体每个业务是否需要验证，以及验证规则是什么，请与服务端或者leader确定
     * 这里只是我谁便写的认证规则，具体每个业务是否需要验证，以及验证规则是什么，请与服务端或者leader确定
     * 这里只是我谁便写的认证规则，具体每个业务是否需要验证，以及验证规则是什么，请与服务端或者leader确定
     * 重要的事情说三遍，以下代码不要直接使用
     */
    private class SafeHostnameVerifier implements HostnameVerifier {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            //验证主机名是否匹配
            //return hostname.equals("server.jeasonlzy.com");
            return true;
        }
    }


//    /**
//     * 集成第三方bug搜集包
//     */
//    private void initBugtags() {
//        if (ConnectUrl.IS_TEST_SERVER) {
//            CrashHandlerException exception = CrashHandlerException.getInstance();
//            exception.initCrashHandlerException(getApplicationContext());
//            return;
//        } else {
//            BugtagsOptions options = new BugtagsOptions.Builder().
//                    trackingLocation(false).//是否获取位置
//                    trackingCrashLog(true).//是否收集crash
//                    trackingConsoleLog(true).//是否收集console log
//                    trackingUserSteps(true).//是否收集用户操作步骤
//                    crashWithScreenshot(false).//crash附带图
//                    build();
//            Bugtags.start("8f49e041ae318bc4bc724d971bdcc89b", this, Bugtags.BTGInvocationEventBubble, options);
//            Bugtags.setInvocationEvent(Bugtags.BTGInvocationEventNone);
//            Bugtags.setBeforeSendingCallback(new BugtagsCallback() {
//                @Override
//                public void run() {
//                    Bugtags.log("before");
//                }
//            });
//            Bugtags.setAfterSendingCallback(new BugtagsCallback() {
//                @Override
//                public void run() {
//                    Bugtags.log("after");
//                }
//            });
//        }
//    }
//
//    /**
//     * 初始化jpush
//     */
//    private void initJPush() {
//        //极光推送实例化
//        JPushInterface.setDebugMode(false);
//        JPushInterface.init(getApplicationContext());
//        JPushInterface.setLatestNotificationNumber(getApplicationContext(), 1);
//        String registrationId = JPushInterface.getRegistrationID(this);
//        if (JPushInterface.isPushStopped(getApplicationContext())) {
//            JPushInterface.resumePush(getApplicationContext());
//        }
//        if (!TextUtils.isEmpty(registrationId)) {
//            getSharedPreferences(JPushReceiver.PUSH_NAEM, Activity.MODE_PRIVATE).edit().putString("channelId", registrationId).apply();
//        }
//    }
//
//    /**
//     * 初始化本地数据库
//     */
//    public void initDB() {
//        SQLUtil.dataBasePath = getDatabasePath(DBInfoConfig.DATABASE_NAME).getPath();
//        initTable();
//
////		SQLiteDatabase db = MySQLiteHelper.getInstance(this).getReadableDatabase();
////		Cursor cursor = SQLUtil.query(db, this, DBInfoConfig.DbVersionTable.TABLE_NAME, null, null);
////
////		String time = CommUtil.getMetaValue(this, "nativedb_update_time");
////		time = time.substring(time.indexOf(":") + 1, time.length());
//
////		if (cursor != null && cursor.getCount() > 0) {
////			cursor.moveToFirst();
////			String create_time = cursor.getString(cursor.getColumnIndex(DbVersionTable.CREATE_TIME));
////			String id = cursor.getString(cursor.getColumnIndex(DbVersionTable.ID));
////			if (cursor != null) {
////				cursor.close();
////				cursor = null;
////				db.close();
////			}
////			Log.v("test", "MyApplication ...create_time =" + create_time);
////			if (Integer.valueOf(create_time) < Integer.valueOf(time)) {
////				Log.v("test", "MyApplication ...copy db......");
////				ServiceUtil.copyDataBase(this, R.raw.nmyc, DBInfoConfig.DATABASE_NAME, true);
////				initTable();
////				ContentValues values = new ContentValues();
////				values.put(DbVersionTable.CREATE_TIME, time);
////				values.put(DbVersionTable.VERSION, DBInfoConfig.DATABASE_VERSION);
////				String[] whereArgs = { id };
////				SQLUtil.update(this, DbVersionTable.TABLE_NAME, values, "_id=?", whereArgs);
////			}
////		}
//
//    }
//
//    /**
//     * 构建数据表
//     */
//    private void initTable() {
//        MySQLiteHelper mySQLiteHelper = MySQLiteHelper.getInstance(this);
//        SQLiteDatabase sqLiteDatabase = mySQLiteHelper.getWritableDatabase();
//
//        mySQLiteHelper.createTables(sqLiteDatabase);
//
//        sqLiteDatabase.close();
//    }

}
