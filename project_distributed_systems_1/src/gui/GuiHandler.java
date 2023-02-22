package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import client.ClientData;
import client.ClientTCP;
import client.xml.ClientXml;
import gui.class_chat.ClassChatPanel;
import gui.class_chat.ManageChatPanel;
import gui.create_chat.CreateUserChatPanel;
import gui.create_chat.ManageCreateUserChat;
import gui.login.LoginPanel;
import gui.user.ManageUserInfo;
import gui.user.UserEditPanel;
import gui.user.UserPanel;
import util.Expressions;

public class GuiHandler {

	private GuiFrame frame;
	private ClientData data;

	private UserEditPanel editPanel;
	// panels
	private LoginPanel loginPanel;
	private UserPanel userPanel;
	private ClassChatPanel classChatPanel;

	private ArrayList<String> arrInfo;
	private ClientXml clientXml;

	private String username;
	private String password;
	private String inputline;
	private HashMap<String, String> mapUc;
	private HashMap<String, String> mapTurma;

	private ManageUserInfo manageUserInfo;
	private ManageChatPanel manageChatPanel;

	private CreateUserChatPanel createUserChatPanel;

	private ManageCreateUserChat manageCreateUserChat;

	public GuiHandler(ClientData data) {

		this.clientXml = new ClientXml();
		this.loginPanel = new LoginPanel();
		frame = new GuiFrame(loginPanel);
		this.data = data;
		// checkForUpdates();
		notifyServerLogin();

	}

	public void showFrame() {

		frame.setVisible(true);
	}

	// handle login attempts
	public void notifyServerLogin() {

		loginPanel.getContinueButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				// get string values from username and password text fields
				username = loginPanel.getTextFields()[0].getText();
				password = loginPanel.getTextFields()[1].getText();

				ClientTCP.sendXmlString = clientXml.createLoginMessage(username, password);

			}
		});

	}

	public void notifyServerSendMessage(int chatType) {

		classChatPanel.getWriteButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (classChatPanel.getTextFromInputTextArea() != null
						&& !(classChatPanel.getTextFromInputTextArea().isEmpty())
						&& !(classChatPanel.getTextFromInputTextArea().trim().isEmpty())) {
					// manageChatPanel create sendMessage
					String userId = String.valueOf(data.getIdUser());
					String mensagemTexto = classChatPanel.getTextFromInputTextArea();
					DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
					LocalDateTime now = LocalDateTime.now();

					String time = dtf.format(now);

					ClientTCP.sendXmlString = manageChatPanel.createTextMessage(userId, mensagemTexto, time, chatType);

				}

			}
		});

	}

	// UPDATE NAS FRAMES

	public void updateFrameToUserInfo(String info) {

		userPanel = new UserPanel();
		manageUserInfo = new ManageUserInfo(userPanel);

		manageUserInfo.processUserInfoMessage(info);
		data.setIdUser(Integer.parseInt(manageUserInfo.getUserId()));

		arrInfo = manageUserInfo.getUserInfo();

	}

	public void updateFrameToEditUser(boolean isProfessor) {

		// editPanel = new EditPanel();
		this.editPanel = new UserEditPanel(arrInfo, isProfessor);

		frame.updateEditUserPanel(editPanel);

		notifySubmitButton();
		notifyCreateButton();

	}

	public void updateFrameTurmas(String info) {

		mapUc = manageUserInfo.processUcInfo(info);
		mapTurma = manageUserInfo.processTurmaInfo(info);
		manageUserInfo.setUcAndTurmaInfo(mapUc, mapTurma, manageUserInfo.getActiveTurmaChats(),
				manageUserInfo.getActiveUcChats());
		frame.updatePanel(userPanel);
		notifyServerChatButton();
		notifyEditButton();

	}

	public void updateFrameChatTurma(String info, int chatType) {

		userPanel = null;
		manageUserInfo = null;

		classChatPanel = new ClassChatPanel();
		manageChatPanel = new ManageChatPanel(classChatPanel);

		if (chatType == 0) {
			manageChatPanel.processInputString(info);
		} else if (chatType == 1) {
			manageChatPanel.processInputUcString(info);
		} else if (chatType == 2) {
			manageChatPanel.processInputGroupChatString(info);
		}

		frame.updateChatPanel(classChatPanel);
		notifyServerSendMessage(chatType);
		notifyBackButton();
	}

	public void addMessageToChat(String input) {

		manageChatPanel.processUserMessage(input);
	}

	public void updateActiveChats(String info) {

		manageUserInfo.checkTurmasUcsWithChats(info);

	}

	public void showAllStudents(String info) {

		createUserChatPanel = new CreateUserChatPanel();
		manageCreateUserChat = new ManageCreateUserChat(createUserChatPanel);
		manageCreateUserChat.processStudentMessage(info);
		frame.updateCreateUserGroupChatPanel(createUserChatPanel);
		this.editPanel = null;
		manageUserInfo = null;


		notifyCreateStudentButton();

	}

	public void notifyCreateStudentButton() {

		createUserChatPanel.getCreateButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				HashMap<String, Boolean> checkAddedUsers = createUserChatPanel.getCheckAddedUsers();
				String textField = createUserChatPanel.getTextField().getText();
				int size = checkAddedUsers.size();
				ArrayList<String> idsArr = new ArrayList<String>();

				for (String i : checkAddedUsers.keySet()) {

					if (checkAddedUsers.get(i)) {

						idsArr.add(i);

					}

				}

				ClientTCP.sendXmlString = clientXml.createAddStudentGroupMessage(idsArr, textField);
				editPanel = new UserEditPanel(arrInfo, userPanel.getIsProfessor());
				updateFrameToEditUser(userPanel.getIsProfessor());

			}
		});

	}

	// notifyCreateButton
	public void notifyCreateButton() {

		editPanel.getCreateButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (userPanel.getIsProfessor() == false) {
					ClientTCP.sendXmlString = clientXml.createUserCreateGroup();

				}

				// processar xml

			}
		});
	}

	public void notifySubmitButton() {

		editPanel.getSubmitButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// manageUserInfo.processhUserInfoMessage();

				ArrayList<String> arr = new ArrayList<String>();

				for (int i = 0; i < editPanel.getTextFields().size(); i++) {

					String text_i = editPanel.getTextFields().get(i).getText();
					arr.add(i, text_i);

				}
				if (userPanel.getIsProfessor() == false) {
					ClientTCP.sendXmlString = clientXml.createEditUserMessage(arr.get(0), arr.get(1), arr.get(2),
							arr.get(3), arr.get(4), arr.get(5), arr.get(6), "", "");
				} else {
					ClientTCP.sendXmlString = clientXml.createEditUserMessage(arr.get(0), arr.get(1), arr.get(2),
							arr.get(3), arr.get(4), arr.get(5), arr.get(6), arr.get(7), arr.get(8));

				}
			}
		});

	}

	public void notifyEditButton() {

		userPanel.getEditButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// manageUserInfo.processhUserInfoMessage();

				// if (userPanel.getIsProfessor()) {

				// System.out.println("IS PROFESSOR");

				// }
				// else {
				updateFrameToEditUser(userPanel.getIsProfessor());
				// }

			}
		});

	}

	public void notifyBackButton() {

		classChatPanel.getBackButton().addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// manageUserInfo.processhUserInfoMessage();
				classChatPanel = null;
				manageChatPanel = null;
				userPanel = null;
				
				ClientTCP.sendXmlString = clientXml.goBackFromChatMessage();

				
			}
		});

	}

	// handle chat buttons, on user panel
	public void notifyServerChatButton() {

		if (userPanel.getturmaChatButtons() != null && userPanel.getturmaChatButtons().size() != 0) {
			for (String i : userPanel.getturmaChatButtons().keySet()) {

				userPanel.getturmaChatButtons().get(i).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// get string values from username and password text fields

						String[] ids = i.split("_");
						ClientTCP.sendXmlString = clientXml.createRequestChatTurmaMessage(ids[1], ids[0]);

					}
				});

			}
		}

		if (userPanel.getucChatButtons() != null && userPanel.getucChatButtons().size() != 0) {
			for (String i : userPanel.getucChatButtons().keySet()) {

				userPanel.getucChatButtons().get(i).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						ClientTCP.sendXmlString = clientXml.createRequestChatUcMessage(i);

					}
				});

			}
		}
		if (userPanel.getTurmaCreateButtons() != null && userPanel.getTurmaCreateButtons().size() != 0) {
			for (String i : userPanel.getTurmaCreateButtons().keySet()) {

				userPanel.getTurmaCreateButtons().get(i).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						// get string values from username and password text fields

						System.out.println("CRATE TURMA" + i);
						String[] ids = i.split("_");
						//System.out.println(clientXml.createUcTurmaGroup(0, ids[1], ids[0]));
						ClientTCP.sendXmlString = clientXml.createUcTurmaGroup(0, ids[1], ids[0]);

					}
				});

			}
		}

		if (userPanel.getucCreateButtons() != null && userPanel.getucCreateButtons().size() != 0) {

			for (String i : userPanel.getucCreateButtons().keySet()) {

				userPanel.getucCreateButtons().get(i).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						System.out.println("CRATE UC" + i);
						ClientTCP.sendXmlString = clientXml.createUcTurmaGroup(1, i, "");
						
						

					}
				});
			}
		}

		if (userPanel.getStudentsChatButtons() != null && userPanel.getStudentsChatButtons().size() != 0) {
			for (String i : userPanel.getStudentsChatButtons().keySet()) {

				userPanel.getStudentsChatButtons().get(i).addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						ClientTCP.sendXmlString = clientXml.createRequestChatGroupUsersMessage(i);

					}
				});

			}
		}
	}

	public void setInputLine(String input) {

		this.inputline = input;

	}

	public String getInputLine() {
		return this.inputline;
	}

}
