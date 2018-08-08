package com.wallet.platfrom.rsa;

import java.awt.EventQueue;

import javax.swing.JFrame;

import java.awt.GridBagLayout;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import com.wallet.platfrom.util.RSAUtil;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;


public class CreateRSAKeyFile {

	private JFrame frame;
	private JTextField textFieldFolder;
	private JTextField textFieldPrivateKey;
	private JTextField textFieldPublicKey;
	private JButton btnSelect;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {

			public void run() {
				try {
					CreateRSAKeyFile window = new CreateRSAKeyFile();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public CreateRSAKeyFile() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("太一钱包管理平台 - RSA秘钥生成");
		frame.getContentPane().setFont(new Font("微软雅黑", Font.BOLD, 14));
		frame.setSize(400, 300);
		frame.setResizable(false);
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[] {30, 30, 0, 0, 30};
		gridBagLayout.rowHeights = new int[] {30, 30, 30, 30, 30, 30, 30, 30, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		frame.getContentPane().setLayout(gridBagLayout);
		
		JLabel lblFolder = new JLabel("密钥保存目录：");
		lblFolder.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFolder.setFont(new Font("微软雅黑", Font.BOLD, 14));
		GridBagConstraints gbc_lblFolder = new GridBagConstraints();
		gbc_lblFolder.anchor = GridBagConstraints.EAST;
		gbc_lblFolder.insets = new Insets(0, 0, 5, 5);
		gbc_lblFolder.gridx = 1;
		gbc_lblFolder.gridy = 1;
		frame.getContentPane().add(lblFolder, gbc_lblFolder);
		
		textFieldFolder = new JTextField();
		textFieldFolder.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GridBagConstraints gbc_textFieldFolder = new GridBagConstraints();
		gbc_textFieldFolder.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldFolder.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldFolder.gridx = 2;
		gbc_textFieldFolder.gridy = 1;
		frame.getContentPane().add(textFieldFolder, gbc_textFieldFolder);
		textFieldFolder.setColumns(10);
		
		btnSelect = new JButton("浏览");
		btnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				if (JFileChooser.APPROVE_OPTION == fileChooser.showDialog(frame, "选择文件夹")) {
					textFieldFolder.setText(fileChooser.getSelectedFile().getPath());
				}
			}
		});
		btnSelect.setFont(new Font("微软雅黑", Font.PLAIN, 12));
		GridBagConstraints gbc_btnSelect = new GridBagConstraints();
		gbc_btnSelect.fill = GridBagConstraints.BOTH;
		gbc_btnSelect.insets = new Insets(0, 0, 5, 5);
		gbc_btnSelect.gridx = 3;
		gbc_btnSelect.gridy = 1;
		frame.getContentPane().add(btnSelect, gbc_btnSelect);
		
		JLabel lblPrivateKey = new JLabel("私钥文件名称：");
		lblPrivateKey.setFont(new Font("微软雅黑", Font.BOLD, 14));
		GridBagConstraints gbc_lblPrivateKey = new GridBagConstraints();
		gbc_lblPrivateKey.anchor = GridBagConstraints.EAST;
		gbc_lblPrivateKey.insets = new Insets(0, 0, 5, 5);
		gbc_lblPrivateKey.gridx = 1;
		gbc_lblPrivateKey.gridy = 3;
		frame.getContentPane().add(lblPrivateKey, gbc_lblPrivateKey);
		
		textFieldPrivateKey = new JTextField();
		textFieldPrivateKey.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GridBagConstraints gbc_textFieldPrivateKey = new GridBagConstraints();
		gbc_textFieldPrivateKey.gridwidth = 2;
		gbc_textFieldPrivateKey.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPrivateKey.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPrivateKey.gridx = 2;
		gbc_textFieldPrivateKey.gridy = 3;
		frame.getContentPane().add(textFieldPrivateKey, gbc_textFieldPrivateKey);
		textFieldPrivateKey.setColumns(100);
		
		JLabel lblPublicKey = new JLabel("公钥文件名称：");
		lblPublicKey.setFont(new Font("微软雅黑", Font.BOLD, 14));
		GridBagConstraints gbc_lblPublicKey = new GridBagConstraints();
		gbc_lblPublicKey.anchor = GridBagConstraints.EAST;
		gbc_lblPublicKey.insets = new Insets(0, 0, 5, 5);
		gbc_lblPublicKey.gridx = 1;
		gbc_lblPublicKey.gridy = 5;
		frame.getContentPane().add(lblPublicKey, gbc_lblPublicKey);
		
		textFieldPublicKey = new JTextField();
		textFieldPublicKey.setFont(new Font("微软雅黑", Font.PLAIN, 14));
		GridBagConstraints gbc_textFieldPublicKey = new GridBagConstraints();
		gbc_textFieldPublicKey.gridwidth = 2;
		gbc_textFieldPublicKey.insets = new Insets(0, 0, 5, 5);
		gbc_textFieldPublicKey.fill = GridBagConstraints.HORIZONTAL;
		gbc_textFieldPublicKey.gridx = 2;
		gbc_textFieldPublicKey.gridy = 5;
		frame.getContentPane().add(textFieldPublicKey, gbc_textFieldPublicKey);
		textFieldPublicKey.setColumns(100);
		
		JButton btnCreate = new JButton("生成");
		btnCreate.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				String folderPath = textFieldFolder.getText();
				if (null == folderPath || folderPath.trim().length() == 0) {
					JOptionPane.showMessageDialog(frame, "请选择密钥保存目录", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				folderPath = folderPath.trim();
				File folder = new File(folderPath);
				if (!folder.isDirectory()) {
					JOptionPane.showMessageDialog(frame, "密钥保存目录不是文件夹", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				String privateKeyFile = textFieldPrivateKey.getText();
				if (null == privateKeyFile || privateKeyFile.trim().length() == 0) {
					JOptionPane.showMessageDialog(frame, "请填写私钥文件名称", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				String publicKeyFile = textFieldPublicKey.getText();
				if (null == publicKeyFile || publicKeyFile.trim().length() == 0) {
					JOptionPane.showMessageDialog(frame, "请填写公钥文件名称", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				
				RSAUtil.makeKeyFile(folderPath + File.separator + publicKeyFile, folderPath + File.separator + privateKeyFile);
				JOptionPane.showMessageDialog(frame, "生成成功", "成功", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		btnCreate.setFont(new Font("微软雅黑", Font.BOLD, 14));
		GridBagConstraints gbc_btnCreate = new GridBagConstraints();
		gbc_btnCreate.insets = new Insets(0, 0, 0, 5);
		gbc_btnCreate.gridheight = 1;
		gbc_btnCreate.gridwidth = 3;
		gbc_btnCreate.fill = GridBagConstraints.BOTH;
		gbc_btnCreate.gridx = 1;
		gbc_btnCreate.gridy = 7;
		frame.getContentPane().add(btnCreate, gbc_btnCreate);
	}

}
