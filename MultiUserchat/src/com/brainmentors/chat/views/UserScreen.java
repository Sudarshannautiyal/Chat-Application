package com.brainmentors.chat.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.brainmentors.chat.dao.UserDAO;
import com.brainmentors.chat.dto.UserDTO;
import com.brainmentors.chat.utils.UserInfo;

public class UserScreen extends JFrame {
	private JTextField Useridtxt;
	private JPasswordField PasswordField;
	

	
	public static void main(String[] args) {
		
					UserScreen window = new UserScreen();
					
	}
	UserDAO userDAO = new UserDAO();
	private void doLogin() {
         String userid = Useridtxt.getText();
		
		char []password = PasswordField.getPassword();
		
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			String message ="";
			if(userDAO.isLogin(userDTO)) {
				message = "Welcome "+userid;
				UserInfo.USER_NAME = userid;
				JOptionPane.showMessageDialog(this, message);
				setVisible(false);
				dispose();
				DashBoard dashBoard = new DashBoard(message);
				dashBoard.setVisible(true);
			}
			else {
				message = "Invalid UserId and Password ";
				JOptionPane.showMessageDialog(this, message);
				
			}
			//JOptionPane.showMessageDialog(this, message);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	private void register() {
		String userid = Useridtxt.getText();
		
		char []password = PasswordField.getPassword();
		UserDAO userDAO = new UserDAO();
		UserDTO userDTO = new UserDTO(userid, password);
		try {
			
		int result = userDAO.add(userDTO);
		if(result>0) {
			JOptionPane.showMessageDialog(this, " Registered Successfully ");
		}
		else {
			JOptionPane.showMessageDialog(this, " Registered Fail ");
		}
		}
		catch(ClassNotFoundException |SQLException ex) {
			System.out.println("DB Isssue...");
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("some generic exception raised....");
			ex.printStackTrace();//where is the exception
			
		}
		System.out.println(" UserId "+userid+" Password "+password);//will print ClassName@Hashcode(Hexa) as char array does not have a tostring method so it uses object's tostring method to print .
		
		
		}
	

	
	public UserScreen() {
		setResizable(false);
		setTitle("Login");
		getContentPane().setBackground(Color.WHITE);
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.BOLD, 40));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(282, 35, 165, 74);
		getContentPane().add(lblNewLabel);
		
		Useridtxt = new JTextField();
		Useridtxt.setBounds(401, 136, 296, 40);
		getContentPane().add(Useridtxt);
		Useridtxt.setColumns(10);
		
		JLabel Useridlbl = new JLabel("Userid");
		Useridlbl.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		Useridlbl.setBounds(241, 148, 113, 28);
		getContentPane().add(Useridlbl);
		
		JLabel pswlbl = new JLabel("Password");
		pswlbl.setFont(new Font("Lucida Grande", Font.PLAIN, 18));
		pswlbl.setBounds(241, 188, 113, 28);
		getContentPane().add(pswlbl);
		
		PasswordField = new JPasswordField();
		PasswordField.setBounds(401, 188, 296, 33);
		getContentPane().add(PasswordField);
		
		JButton Loginbt = new JButton("Login");
		Loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
			
		});
		Loginbt.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		Loginbt.setBounds(302, 257, 132, 45);
		getContentPane().add(Loginbt);
		
		JButton Registerbt = new JButton("Register");
		Registerbt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				register();
				
			}
		});
		Registerbt.setFont(new Font("Lucida Grande", Font.PLAIN, 16));
		Registerbt.setBounds(495, 257, 123, 45);
		getContentPane().add(Registerbt);
		setBackground(Color.WHITE);
		setSize( 750, 390);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setVisible(true);
		
	}
}
