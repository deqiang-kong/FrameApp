package com.kong.frameapp.net;

/**
 * 数据连接状态status
 */
public class ConnectStatus {


	/** 网络连接失败 **/
	public final static int FLAG_FAIL_CONNECT = 100;

	/** 手机有网络，但服务器连接不上 **/
	public static final int FLAG_FAIL_COMM = 101;

	/** 连接服务器成功，且服务器返回成功码，但加载到的数据为空 **/
	public final static int FLAG_NO_DATA = 250;

	/** 加载数据成功 **/
	public final static int FLAG_SUCESS = 200;

	/** 解析数据出错 **/
	public final static int FLAG_FAIL_PARSE = 253;

	/** 登录异常 **/
	public static final int FLAG_FAIL_LOGIN = 401;

	/** 获取验证码异常 **/
	public static final int FLAG_FAIL_AUTH = 400;
	/** 用户名密码错误 **/
	public static final int FLAG_FAIL_PASSWORD = 403;

	/** 注册异常 **/
	public static final int FLAG_FAIL_REGIST = 500;

	/** 返回相关信息 */
	public static final int FLAG_FAIL_ABOUT = 600;

	/** 重置手机号超过次数 */
	public static final int FLAG_MORE_RESET = 437;

	/** 认证被驳回 */
	public static final int FLAG_VERIFY_ERR = 429;

	/** 认证未通过 */
	public static final int FLAG_VERIFY_FAILURE = 430;
	/** 用户不存在 */
	public static final int FLAG_NO_USER = 444;



	/**************** FLAG_SERVER为服务器端返回的状态码 -start- ****************/
	/** 需要图片验证码 **/
	public final static int FLAG_SERVER_ERROR_NEED_IMGCODE = -1;
	/** 图片验证码错误 **/
	public final static int FLAG_SERVER_ERROR_IMGCODE = -2;
	/** 返回失败 **/
	public final static int FLAG_SERVER_COMMFAIL = 0;
	/** 服务器端-返回成功 */
	public final static int FLAG_SERVER_SUCESS = 1;
	/** ticket过期 **/
	public final static int FLAG_SERVER_TICKETOUTDATE = 2;
	/** 服务器出错 **/
	public final static int FLAG_SERVER_ERROR = 3;
	/** 服务器端-未知错误 */
	public final static int FLAG_SERVER_OTHER = 9;

	/** 用户被禁用 */
	public final static int FLAG_ERROR_BAN = 1000; //
	/** 手机号未注册 */
	public final static int FLAG_USER_NOT_FOUND = 404; //
	/** 验证码过期 */
	public final static int FLAG_ERR_IDENTIFY = 407; //
	/** 投票积分不足--仅在投票积分不足出现 */
	public final static int FLAG_GRADE_EMPTY = 440; //

	/**************** FLAG_SERVER为服务器端返回的状态码 -end- ****************/



	/**************** 未登录情况下 对应执行操作的状态码 -end- ****************/
	/**关闭当前界面*/
	public final static int FLAG_CLOSE_VIEW = 40701;
	/**关闭所有界面*/
	public final static int FLAG_CLOSE_ALL_VIEW = 40703;


	/**************** 未登录情况下 对应执行操作的状态码 -end- ****************/

}
