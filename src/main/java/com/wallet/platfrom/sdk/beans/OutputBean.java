package com.wallet.platfrom.sdk.beans;

import java.io.Serializable;

public class OutputBean implements Serializable {

	private static final long serialVersionUID = 5323233676553799850L;

	private Boolean success;

	private Integer code;

	private String message;

	private String data;

	private String sign;

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

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		buf.append("\"success\"").append(":").append(null == success ? false : success).append(",");
		buf.append("\"code\"").append(":").append(null == code ? 0 : code).append(",");
		buf.append("\"data\"").append(":").append("\"" + (null == data ? "" : data) + "\"").append(",");
		buf.append("\"sign\"").append(":").append("\"" + (null == sign ? "" : sign) + "\"").append(",");
		buf.append("\"message\"").append(":").append("\"" + (null == message ? "" : message) + "\"");
		buf.append("}");
		return buf.toString();
	}
}
