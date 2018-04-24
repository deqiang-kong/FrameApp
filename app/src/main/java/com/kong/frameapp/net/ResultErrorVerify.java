package com.kong.frameapp.net;



/**
 * 网络请求结果错误信息
 */
public class ResultErrorVerify {


	private static volatile ResultErrorVerify instance = null;

	public static ResultErrorVerify getInstance() {
		if (instance == null) {
			synchronized (ResultErrorVerify.class) {
				if (instance == null) {
					instance = new ResultErrorVerify();
				}
			}
		}
		return instance;
	}


	public  synchronized void resultVerify(ResultBaseBean resultData) {

		if (resultData.isSuccess()) {

			switch(resultData.getFlg()){
//				//数据正确
//				case ConnectSts.FLAG_SERVER_SUCESS:
//
//					break;
//				//投票积分不足
//				case ConnectSts.FLAG_GRADE_EMPTY:
//					//callBack.onFail(resultData.getFailCode(), resultData.getFailInfo());
//					break;
				//

				default:
					break;
			}
		} else {
			//callBack.onFail(resultData.getFailCode(), resultData.getFailInfo());
		}


	}


}
