package gui;

import java.util.HashMap;

import javax.swing.JFrame;

import gui.class_chat.ClassChatPanel;
import gui.create_chat.CreateUserChatPanel;
import gui.login.LoginPanel;
import gui.user.ManageUserInfo;
import gui.user.UserEditPanel;
import gui.user.UserPanel;

public class GuiFrame extends JFrame{
	
	public GuiFrame(LoginPanel loginPanel) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 650);

		createLoginPanel(loginPanel);
	}
	
	
	public void createLoginPanel(LoginPanel loginPanel) {
		
		setContentPane(loginPanel);
			
	}
	
	public void updatePanel(UserPanel userPanel) {
		
		this.getContentPane().removeAll();
		this.repaint();
		setBounds(100, 100, 1200, 651);
		setContentPane(userPanel);

	}
	
	public void updateChatPanel(ClassChatPanel classChatPanel) {
		
		this.getContentPane().removeAll();
		this.repaint();
		setBounds(100, 100, 900, 652);
		setContentPane(classChatPanel);
		
	}
	
	public void updateEditUserPanel(UserEditPanel editPanel) {
		
		this.getContentPane().removeAll();
		this.repaint();
		setBounds(100, 100, 900, 652);
		setContentPane(editPanel);
		
		
	}
	
	public void updateCreateUserGroupChatPanel(CreateUserChatPanel createChat) {
		
		this.getContentPane().removeAll();
		this.repaint();
		setBounds(100, 100, 900, 653);
		setContentPane(createChat);
		
	}
	
	
	
	
	
	public static void main(String[] args) {
		LoginPanel l = new LoginPanel();
		UserPanel u = new UserPanel();
		GuiFrame g = new GuiFrame(l);
		ClassChatPanel c = new ClassChatPanel();
		g.updateChatPanel(c);
		g.setVisible(true);

		
	}
	
	
}
