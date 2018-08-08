package com.wallet.platfrom.sdk.beans;

import java.io.Serializable;
import java.util.List;

public class AddressBean implements Serializable {

	private static final long serialVersionUID = -5737851639049149517L;

	private String symbol;

	private List<String> address;

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public List<String> getAddress() {
		return address;
	}

	public void setAddress(List<String> address) {
		this.address = address;
	}
}
