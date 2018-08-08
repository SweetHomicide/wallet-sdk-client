package com.wallet.platfrom.sdk.beans;

import java.io.Serializable;
import java.math.BigDecimal;

public class WithdrawDataBean implements Serializable {

	private static final long serialVersionUID = 1968576354694708837L;

	private String serno;

	private String symbol;

	private String address;

	private BigDecimal amount;
	
	public String getSerno() {
		return serno;
	}

	public void setSerno(String serno) {
		this.serno = serno;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("{");
		buf.append("\\\"serno\\\":").append("\\\"").append(null == serno ? "" : serno).append("\\\",");
		buf.append("\\\"symbol\\\":").append("\\\"").append(null == symbol ? "" : symbol).append("\\\",");
		buf.append("\\\"address\\\":").append("\\\"").append(null == address ? "" : address).append("\\\",");
		buf.append("\\\"amount\\\":").append(String.format("%.8f", null == amount ? BigDecimal.ZERO : amount));
		buf.append("}");
		return buf.toString();
	}
}
