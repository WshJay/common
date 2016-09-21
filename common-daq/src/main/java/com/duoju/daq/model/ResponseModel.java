package com.duoju.daq.model;

import com.duoju.daq.util.EMUtil;


/**
 * 返回模型
 * 创建人: LCN
 * 创建时间: Date： 2015年11月14日
 * 类说明:
 */
public class ResponseModel{
		//默认返回Code码
		public  int responseCode = EMUtil.RESPONS_STATUS.SUCCESS_CODE.KEY;
		
		//默认返回消息
		public  String responseMsg = EMUtil.RESPONS_STATUS.SUCCESS_CODE.VALUE;
		
		
		public ResponseModel() {}
		
		public ResponseModel(int responseCode, String responseMsg) {
			super();
			this.responseCode = responseCode;
			this.responseMsg = responseMsg;
		}


		public int getResponseCode() {
			return responseCode;
		}

		public void setResponseCode(int responseCode) {
			this.responseCode = responseCode;
		}

		public String getResponseMsg() {
			return responseMsg;
		}

		public void setResponseMsg(String responseMsg) {
			this.responseMsg = responseMsg;
		}

		
}




