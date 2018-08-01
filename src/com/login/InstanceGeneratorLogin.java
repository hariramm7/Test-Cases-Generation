package com.login;

import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.design.Homepage;
import com.utils.Database;


public class InstanceGeneratorLogin extends JFrame implements ActionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/*Old User Declaration*/
	JTextField txtUser;
	JPasswordField pwdUser;
	JTextField txtNoofjobs;
	JButton btnOk;
	JButton btnQuit;
	JTabbedPane tabbedPane;
	
	/*New User Declaration*/
	
	JTextField txtNewUser;
	JPasswordField pwdNewUser;
	JPasswordField pwdNewConUser;
	JButton btnNewOk;
	JButton btnNewQuit;
	
	Database database;
	
	public InstanceGeneratorLogin() {
		database=new Database();
		tabbedPane=new JTabbedPane();
		tabbedPane.addTab("Login", getDesign());
		tabbedPane.addTab("New user", getNewDesign());
		add(tabbedPane);
		setTitle("Instance Generator::");
		setSize(400,350);
		setVisible(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	private JPanel getNewDesign() {

		JPanel panel=new JPanel(null);
		
		
		Rectangle r=new Rectangle();
		r.x=50;
		r.y=75;
		r.width=100;
		r.height=20;
		int inc=30;
		JLabel lblUser=new JLabel("User Name");
		lblUser.setBounds(r);
		panel.add(lblUser);
	
		r.width=150;
		r.x=r.x+120;
		txtNewUser=new JTextField();
		txtNewUser.setBounds(r);
		panel.add(txtNewUser);
		r.x=50;
		r.y=r.y+inc;
		r.width=100;
		
		JLabel lblPwd=new JLabel("Password");
		lblPwd.setBounds(r);
		panel.add(lblPwd);
		
		r.width=150;
		r.x=r.x+120;
		pwdNewUser=new JPasswordField();
		pwdNewUser.setBounds(r);
		panel.add(pwdNewUser);

		r.x=50;
		r.y=r.y+inc;
		r.width=150;
		
		JLabel lblNewConPwd=new JLabel("Confirm Password");
		lblNewConPwd.setBounds(r);
		panel.add(lblNewConPwd);
		
		r.width=150;
		r.x=r.x+120;
		pwdNewConUser=new JPasswordField();
		pwdNewConUser.setBounds(r);
		panel.add(pwdNewConUser);

		r.x=50;
		r.y=r.y+inc;
		r.width=100;
		
		btnNewOk=new JButton("Create");
		btnNewOk.setBounds(r);
		panel.add(btnNewOk);
		btnNewOk.addActionListener(this);
		
		r.x=r.x+120;
		btnNewQuit=new JButton("Quit");
		btnNewQuit.setBounds(r);
		panel.add(btnNewQuit);
		btnNewQuit.addActionListener(this);
		
		return panel;

	}

	private JPanel getDesign() {
		JPanel panel=new JPanel(null);
		
		
		Rectangle r=new Rectangle();
		r.x=50;
		r.y=75;
		r.width=100;
		r.height=20;
		int inc=30;
		JLabel lblUser=new JLabel("User Name");
		lblUser.setBounds(r);
		panel.add(lblUser);
	
		r.width=150;
		r.x=r.x+120;
		txtUser=new JTextField();
		txtUser.setBounds(r);
		panel.add(txtUser);
		r.x=50;
		r.y=r.y+inc;
		r.width=100;
		
		JLabel lblPwd=new JLabel("Password");
		lblPwd.setBounds(r);
		panel.add(lblPwd);
		
		r.width=150;
		r.x=r.x+120;
		pwdUser=new JPasswordField();
		pwdUser.setBounds(r);
		panel.add(pwdUser);
	
		r.x=50;
		r.y=r.y+inc;
		r.width=100;
		btnOk=new JButton("OK");
		btnOk.setBounds(r);
		panel.add(btnOk);
		btnOk.addActionListener(this);
		
		r.x=r.x+120;
		btnQuit=new JButton("Quit");
		btnQuit.setBounds(r);
		panel.add(btnQuit);
		btnQuit.addActionListener(this);
		
		return panel;
	}

	public static void main(String[] args) {
		try {
			String inf = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";

			UIManager.setLookAndFeel(inf);

		} catch (Exception e) {
			e.printStackTrace();
		}
		new InstanceGeneratorLogin();
		

	}

	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==btnOk)
		{
			if(database.check(txtUser.getText(), new String(pwdUser.getPassword())).equals("Valid"))
			{
				new Homepage();
				this.setVisible(false);
			}
			else{
				JOptionPane.showMessageDialog(null, "Enter the Correct User Name and Password");
				txtUser.setText("");pwdUser.setText("");
			}
		}
		if(e.getSource()==btnNewOk)
		{
			if(database.chkUserNme(txtNewUser.getText()))
			{
				if(new String(pwdNewUser.getPassword()).equals(new String(pwdNewConUser.getPassword())))
				{
					Vector<String> vec=new Vector<String>();
					vec.add(new String(txtNewUser.getText()));
					vec.add(new String(pwdNewConUser.getPassword()));
					database.insertToLogin(vec);
					JOptionPane.showMessageDialog(null, "New User has been registered successfully.");
					new Homepage();
					this.setVisible(false);
				}
				else{
					JOptionPane.showMessageDialog(null, "Mismatch in Password & Conform password.");
				}
				
			}
			else{
				JOptionPane.showMessageDialog(null, "Username not available.");
			}
			
		}
		if(e.getSource()==btnQuit)
		{
			System.exit(0);
		}
		if(e.getSource()==btnNewQuit)
		{
			System.exit(0);
		}
	}

}
