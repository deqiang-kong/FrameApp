package com.kong.frameapp.net;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.lzy.okgo.model.HttpHeaders;

/**
 * 网络链接相关
 */
public class NetUtils {


	private static volatile NetUtils instance = null;

	public static NetUtils getInstance() {
		if (instance == null) {
			synchronized (NetUtils.class) {
				if (instance == null) {
					instance = new NetUtils();
				}
			}
		}
		return instance;
	}


	/**
	 * 获取网络请求头信息
	 * @return
	 */
	public synchronized HttpHeaders getRequestHead() {
		HttpHeaders headers = new HttpHeaders();
		headers.put("_kpusrId", "1000143");
		headers.put("_kptkt", "8F9699A2718DE8B3463F6F6FEBA01EC5");
		headers.put("client_ver", "1.8.0");
		headers.put("platform", "android");
//        TelephonyManager tm = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
//        String a = tm.getDeviceId();
//        headers.put("device_id", a);
		headers.put("app_type", "XF");

		return headers;
	}



	public static boolean isNetworkConnected(Context context) {

		ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
		if (mNetworkInfo != null) {
			return mNetworkInfo.isAvailable();
		}
		return false;
	}

	public static boolean isWifiConnected(Context context) {

		ConnectivityManager mConnectivityManager = (ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mWiFiNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (mWiFiNetworkInfo != null) {
			return mWiFiNetworkInfo.isAvailable();
		}
		return false;
	}

	public static boolean isMobileConnected(Context context) {

		ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mMobileNetworkInfo = mConnectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mMobileNetworkInfo != null) {
			return mMobileNetworkInfo.isAvailable();
		}

		return false;
	}

	public static int getConnectedType(Context context) {

		ConnectivityManager mConnectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo mNetworkInfo = mConnectivityManager.getActiveNetworkInfo();
		if (mNetworkInfo != null && mNetworkInfo.isAvailable()) {
			return mNetworkInfo.getType();
		}

		return -1;
	}


}
