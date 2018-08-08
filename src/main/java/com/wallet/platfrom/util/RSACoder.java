package com.wallet.platfrom.util;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;

public abstract class RSACoder extends Coder {

	private static final String KEY_ALGORITHM = "RSA";

	private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

	/** */
	/**
	 * RSA最大加密明文大小
	 */
	private static final int MAX_ENCRYPT_BLOCK = 117;

	/** */
	/**
	 * RSA最大解密密文大小
	 */
	private static final int MAX_DECRYPT_BLOCK = 128;

	/**
	 * 用私钥对信息生成数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param privateKey
	 *            私钥
	 * @return
	 * @throws Exception
	 */
	public static String sign(byte[] data, String privateKey) throws Exception {
		// 解密由base64编码的私钥
		byte[] keyBytes = decryptBASE64(privateKey);
		PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 取私钥对象
		PrivateKey pKey = keyFactory.generatePrivate(pkcs8EncodedKeySpec);

		// 用私钥生成数字签名
		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initSign(pKey);
		signature.update(data);

		return bytes2Hex(signature.sign());
	}

	/**
	 * 校验数字签名
	 * 
	 * @param data
	 *            加密数据
	 * @param publicKey
	 *            公钥
	 * @param sign
	 *            数字签名
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, String publicKey, String sign) throws Exception {
		// 解密有base64编码的公钥
		byte[] keyBytes = decryptBASE64(publicKey);

		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);

		// 取公钥对象
		PublicKey pKey = keyFactory.generatePublic(keySpec);

		Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
		signature.initVerify(pKey);
		signature.update(data);
		// 验证签名是否正常
		return signature.verify(hex2Bytes(sign));
	}

	/**
	 * 解密 用私钥解密
	 * 
	 * @param data
	 *            加密数据
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptPrivateKey(byte[] data, String key) throws Exception {
		byte[] keyBytes = decryptBASE64(key);
		// 取得私钥
		PKCS8EncodedKeySpec encodedKeySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory factory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key pKey = factory.generatePrivate(encodedKeySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, pKey);
		return doFinal(cipher, Cipher.DECRYPT_MODE, data);
	}

	/**
	 * 用公钥解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, String key) throws Exception {
		byte[] keyBytes = decryptBASE64(key); // 解密
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes); // 取得公钥
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key pKey = keyFactory.generatePublic(keySpec);
		// 对数据解密
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, pKey);
		return doFinal(cipher, Cipher.DECRYPT_MODE, data);
	}

	/**
	 * 用公钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, String key) throws Exception {
		byte[] keyBytes = decryptBASE64(key);
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key pKey = keyFactory.generatePublic(keySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, pKey);
		return doFinal(cipher, Cipher.ENCRYPT_MODE, data);
	}

	/**
	 * 用私钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, String key) throws Exception {
		byte[] keyBytes = decryptBASE64(key);
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		Key privateKey = keyFactory.generatePrivate(keySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return doFinal(cipher, Cipher.ENCRYPT_MODE, data);
	}

	/**
	 * 取得私钥
	 * 
	 * @param keyMap
	 * @return
	 * @throws Exception
	 */
	public static String getKey(String keyFile) throws Exception {
		return encryptBASE64(getFromKeyFile(keyFile).getEncoded());
	}

	public static String bytes2Hex(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (bytes == null || bytes.length <= 0) {
			return null;
		}
		for (int i = 0; i < bytes.length; i++) {
			int v = bytes[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	public static byte[] hex2Bytes(String hex) {
		if (hex == null || hex.equals("")) {
			return null;
		}
		hex = hex.toUpperCase();
		int length = hex.length() / 2;
		char[] hexChars = hex.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	private static Key getFromKeyFile(String keyFile) throws Exception {
		ObjectInputStream ois = null;
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(keyFile);
			ois = new ObjectInputStream(fis);
			return (Key) ois.readObject();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != ois) {
				try {
					ois.close();
				} catch (Exception e) {

				}
				ois = null;
			}
			if (null != fis) {
				try {
					fis.close();
				} catch (Exception e) {

				}
				fis = null;
			}
		}
		return null;
	}
	
	private static byte[] doFinal(Cipher cipher, int inmode, byte[] data) throws Exception {
		int inputLen = data.length;
		ByteArrayOutputStream out = null;

		try {
			out = new ByteArrayOutputStream();
			int offSet = 0;
			byte[] cache;
			int i = 0;
			int maxSize = Cipher.ENCRYPT_MODE == inmode ? MAX_ENCRYPT_BLOCK : MAX_DECRYPT_BLOCK;
			// 对数据分段解密
			while (inputLen - offSet > 0) {
				if (inputLen - offSet > maxSize) {
					cache = cipher.doFinal(data, offSet, maxSize);
				} else {
					cache = cipher.doFinal(data, offSet, inputLen - offSet);
				}
				out.write(cache, 0, cache.length);
				i++;
				offSet = i * maxSize;
			}
			return out.toByteArray();
		} catch (Exception e) {
			throw e;
		} finally {
			if (null != out) {
				try {
					out.close();
				} catch (Exception e) {
				}
			}
		}
	}
	
}
