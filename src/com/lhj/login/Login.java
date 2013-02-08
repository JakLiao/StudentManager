package com.lhj.login;

import javax.swing.*;
import com.lhj.SMFrame;
import java.io.*;
import java.awt.event.*;

public class Login extends JFrame implements ActionListener {
	private static final long serialVersionUID = 1L;
	private JLabel userLabel;
	private JLabel passLabel;
	private JTextField userName;
	private JPasswordField userPassword;
	private JButton exit;
	private JButton login;
	private File file = new File("res/login.swing");

	public Login() {
		setTitle("登录学生成绩管理系统");
		final JPanel panel = new LoginPanel();
		panel.setLayout(null);
		getContentPane().add(panel);
		setBounds(300, 200, panel.getWidth(), panel.getHeight());
		userLabel = new JLabel("用户名:");
		userLabel.setBounds(100, 135, 200, 18);
		panel.add(userLabel);
		userName = new JTextField();
		userName.setBounds(150, 135, 200, 18);
		panel.add(userName);
		passLabel = new JLabel("密  码:");
		passLabel.setBounds(100, 165, 200, 18);
		panel.add(passLabel);
		userPassword = new JPasswordField();
		userPassword.setBounds(150, 165, 200, 18);
		panel.add(userPassword);

		login = new JButton("登录");
		login.setBounds(180, 195, 60, 18);
		panel.add(login);
		exit = new JButton("退出");
		exit.setBounds(260, 195, 60, 18);
		panel.add(exit);

		login.addActionListener(this);
		exit.addActionListener(this);
		
		userName.setText("tomochin");
		userPassword.setText("123");
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource().equals(login)) {
			String username = userName.getText();
			String password = String.valueOf(userPassword.getPassword());
			BufferedReader br = null;
			try {
				FileReader fr = new FileReader(file);
				br = new BufferedReader(fr);
				String line = "", str = "";
				while ((line = br.readLine()) != null) {
					str += line;
				}
				String[] users = str.split("&&");
				for (String user : users) {
					String[] userInfo = user.split("##");
					if (userInfo[0].equals(username)
							&& userInfo[1].equals(password)) {
						setVisible(false);
						new SMFrame();
						return;
					}
				}
				JOptionPane.showMessageDialog(null, "用户名或密码错误!");
				return;
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		} else {
			System.exit(0);
		}
	}
}