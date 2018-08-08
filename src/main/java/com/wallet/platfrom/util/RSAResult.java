package com.wallet.platfrom.util;

public class RSAResult {

	private boolean isSignRight;

	private String sign;

	private String data;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public boolean isSignRight() {
		return isSignRight;
	}

	public void setSignRight(boolean isSignRight) {
		this.isSignRight = isSignRight;
	}

}
