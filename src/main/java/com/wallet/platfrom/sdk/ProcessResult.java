package com.wallet.platfrom.sdk;

/**
 * 处理结果封装
 * 
 * @author LiuQing
 */
public class ProcessResult {

	private Boolean success;

	private Integer code;

	private String message;

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
