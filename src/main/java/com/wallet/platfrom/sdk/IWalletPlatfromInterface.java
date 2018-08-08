package com.wallet.platfrom.sdk;

import java.math.BigDecimal;
import java.util.List;

import com.wallet.platfrom.sdk.beans.WithdrawDataBean;

/**
 * 钱包管理客户端接口定义
 * 
 * @author LiuQing
 */
public interface IWalletPlatfromInterface {

	/**
	 * 获取钱包管理客户端配置
	 * 
	 * @return
	 */
	WalletPlatfromConfig getConfig();

	/**
	 * 处理新获取地址的接口
	 * 
	 * @param addresses
	 *            新地址
	 * @return 还需要生成地址的数量
	 */
	Integer processAddress(String symbol, List<String> addresses);

	/**
	 * 充币交易数据处理
	 * 
	 * @param txid
	 *            交易ID
	 * @param address
	 *            充币地址
	 * @param amount
	 *            充币金额
	 * @return 处理结果
	 */
	ProcessResult charge(String txid, String address, BigDecimal amount);

	/**
	 * 确认充币交易达到确认数交易
	 * 
	 * @param txid
	 *            交易ID
	 * @param confirms
	 *            确认数
	 * @return 处理结果
	 */
	ProcessResult confirmCharge(String txid, Integer confirms);

	/**
	 * 获取提币数据信息
	 * 
	 * @return 提币数据信息
	 */
	List<WithdrawDataBean> getWithdrawDatas();

	/**
	 * 提币数据钱包客户端结果处理
	 * 
	 * @param success
	 *            处理是否成功
	 * @param txid
	 *            交易ID，在success = true时有效
	 * @param message
	 *            错误信息，在success = false时有效
	 * @param withdrawData
	 *            提币数据信息
	 * @return 处理结果
	 */
	ProcessResult processWithdrawResult(Boolean success, String txid, String message, WithdrawDataBean withdrawData);
}
