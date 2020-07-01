package com.syntel.diwaligift.util;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class RespBuilder {
	
	
	
//
	
	/**
	Method Name :addResponse
	Description :Method used for Sending Response when data is correct
	@param :String code, String msg,JSONArray data
	@return :it returns value of type i:e JSONObject.
	*/

	public static JSONObject buildSuccessResponse(String code, String msg,JSONArray data) {

		JSONObject statusJsonObject = new JSONObject();
		JSONObject finalJsonObject=new JSONObject();
		try {
			
			statusJsonObject.put(GlobalConstants.STATUS_CODE,code);
			statusJsonObject.put(GlobalConstants.STATUS_MESSAGE,msg);
			finalJsonObject.put(GlobalConstants.STATUS, statusJsonObject);
			finalJsonObject.put(GlobalConstants.DATA, data);
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalJsonObject;

	}// End of Method

	public static JSONObject buildFailedResponse(String code, String msg) {

		JSONObject statusJsonObject = new JSONObject();
		JSONObject finalJsonObject=new JSONObject();
		try {
			statusJsonObject.put(GlobalConstants.STATUS_CODE,code);
			statusJsonObject.put(GlobalConstants.STATUS_MESSAGE,msg);
			finalJsonObject.put(GlobalConstants.STATUS, statusJsonObject);
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		return finalJsonObject;

	}// End of Method

	public static JSONObject buildFailedJSONParsingResponse(String code, String msg) {

		JSONObject statusJsonObject = new JSONObject();
		JSONObject finalJsonObject=new JSONObject();
		try {
			statusJsonObject.put(GlobalConstants.STATUS_CODE,code);
			statusJsonObject.put(GlobalConstants.STATUS_MESSAGE,msg);
			finalJsonObject.put(GlobalConstants.STATUS, statusJsonObject);
		} catch (JSONException e) {
			
			e.printStackTrace();
		}
		return finalJsonObject;

	}// End of Method

	
	
	
	//for R5
	
	public static JSONObject buildSuccessResponseR5(String code, String msg,JSONObject data) {

		JSONObject statusJsonObject = new JSONObject();
		JSONObject finalJsonObject=new JSONObject();
		try {
			
			statusJsonObject.put(GlobalConstants.STATUS_CODE,code);
			statusJsonObject.put(GlobalConstants.STATUS_MESSAGE,msg);
			finalJsonObject.put(GlobalConstants.STATUS, statusJsonObject);
			finalJsonObject.put(GlobalConstants.DATA, data);
		
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return finalJsonObject;

	}// End of Method
}