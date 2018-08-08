package com.wallet.platfrom.sdk;

import com.wallet.platfrom.util.RSACoder;

public class TestRSACoder {

	public static void main(String[] args) throws Exception {
		String privateKeyFile = "D:\\Work\\SoftwareRuntime\\datp-tzt\\keyfile\\private-tzt.key";
		String publicKeyFile = "D:\\Work\\SoftwareRuntime\\tzt-wallet\\keyfile\\public-tzt.key";
		
		String privateKey = RSACoder.getKey(privateKeyFile);
		String publicKey = RSACoder.getKey(publicKeyFile);
		String inputStr = "我爱中国";
		byte[] data = inputStr.getBytes();// 每次的得到的字节数组是不一样的。
		// 第二步 私钥加密
		byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);
		// 私钥进行数据签名
		String sign = RSACoder.sign(encodedData, privateKey);
		System.out.println(sign);

		// 第三步 公钥验证数字签名
		boolean flag = RSACoder.verify(encodedData, publicKey, sign);
		System.out.println("flag:" + flag);
		// 用公钥对数据解密
		byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);

		System.out.println("data:" + data + "加密数据：" + new String(encodedData) + "    解密数据：" + new String(decodedData));
		System.out.println("加密前数据-：" + new String(data) + "     解密后数据： " + new String(decodedData));

		// 第四步使用公钥加密数据
		encodedData = RSACoder.encryptByPublicKey(decodedData, publicKey);

		// 第五步 使用私钥解密数据
		decodedData = RSACoder.decryptPrivateKey(encodedData, privateKey);

		System.out.println("data:" + data + "加密数据：" + new String(encodedData) + "    解密数据：" + new String(decodedData));
		System.out.println("加密前数据：" + inputStr + "     解密后数据： " + new String(decodedData));
	}
}
