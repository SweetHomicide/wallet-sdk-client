package com.wallet.platfrom.sdk;

/**
 * 钱包管理客户端配置
 * 
 * @author LiuQing
 */
public class WalletPlatfromConfig {

	private String privateKeyFile;

	private String publicKeyFile;

	public String getPrivateKeyFile() {
		return privateKeyFile;
	}

	public void setPrivateKeyFile(String privateKeyFile) {
		this.privateKeyFile = privateKeyFile;
	}

	public String getPublicKeyFile() {
		return publicKeyFile;
	}

	public void setPublicKeyFile(String publicKeyFile) {
		this.publicKeyFile = publicKeyFile;
	}

}
