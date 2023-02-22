package gui.class_chat;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import server.data_info.Message;
import util.Expressions;
import xml_read_write.XMLReadWrite;

public class ManageChatPanel {

	private String uc_id;
	private String turma_id;
	private String admin_id;
	private String group_chat_id;

	private String nome_turma;
	private String nome_admin;
	private String nome_uc;

	private ArrayList<Message> msgs;

	private ClassChatPanel chatPanel;

	private ArrayList<String> usersChat;

	public ManageChatPanel(ClassChatPanel chatPanel) {

		this.msgs = new ArrayList<Message>();
		this.usersChat = new ArrayList<String>();
		this.chatPanel = chatPanel;

	}

	// TODO DIFFERENT ONE FOR CLASS AND UC CHAT
	public void processInputString(String input) {

		Document doc = XMLReadWrite.documentFromString(input);

		NodeList chatInfo = doc.getElementsByTagName("sendChatTurma");

		Element chat = (Element) chatInfo.item(0);
		uc_id = (chat.getAttribute("id_uc"));
		turma_id = (chat.getAttribute("id_turma"));
		admin_id = (chat.getAttribute("id_admin"));

		nome_turma = (chat.getAttribute("nome_turma"));
		nome_admin = (chat.getAttribute("nome_admin"));
		nome_uc = (chat.getAttribute("nome_uc"));

		NodeList messageInfo = doc.getElementsByTagName("message");

		NodeList users = doc.getElementsByTagName("user");

		for (int j = 0; j < users.getLength(); j++) {

			Element user = (Element) users.item(j);
			String texto = user.getAttribute("name");

			this.usersChat.add(texto);

		}

		for (int i = 0; i < messageInfo.getLength(); i++) {

			Element message = (Element) messageInfo.item(i);
			String id = message.getElementsByTagName("membro").item(0).getAttributes().item(0).getTextContent();
			String texto = message.getElementsByTagName("texto").item(0).getTextContent();
			String date = message.getElementsByTagName("date").item(0).getTextContent();

			Message m_i = new Message(texto, date, id);

			msgs.add(m_i);

			String sendToTextArea = id + " " + texto + " " + date + "\n";

			this.chatPanel.getTextArea().append(sendToTextArea);


		}

		this.chatPanel.setLabels(nome_uc, nome_turma);
		this.chatPanel.setUsersPanel(usersChat, nome_admin);
	}

	public void processInputUcString(String input) {

		Document doc = XMLReadWrite.documentFromString(input);

		NodeList chatInfo = doc.getElementsByTagName("sendChatUc");

		Element chat = (Element) chatInfo.item(0);
		uc_id = (chat.getAttribute("id_uc"));
		admin_id = (chat.getAttribute("id_admin"));

		nome_admin = (chat.getAttribute("nome_admin"));
		nome_uc = (chat.getAttribute("nome_uc"));

		NodeList messageInfo = doc.getElementsByTagName("message");

		NodeList users = doc.getElementsByTagName("user");

		for (int j = 0; j < users.getLength(); j++) {

			Element user = (Element) users.item(j);
			String texto = user.getAttribute("name");

			this.usersChat.add(texto);

		}


		for (int i = 0; i < messageInfo.getLength(); i++) {

			Element message = (Element) messageInfo.item(i);
			String id = message.getElementsByTagName("membro").item(0).getAttributes().item(0).getTextContent();
			String texto = message.getElementsByTagName("texto").item(0).getTextContent();
			String date = message.getElementsByTagName("date").item(0).getTextContent();

			Message m_i = new Message(texto, date, id);

			msgs.add(m_i);

			String sendToTextArea = id + " " + texto + " " + date + "\n";

			this.chatPanel.getTextArea().append(sendToTextArea);

		}

		this.chatPanel.setLabels(nome_uc, " ");
		this.chatPanel.setUsersPanel(usersChat, nome_admin);
	}

	public void processInputGroupChatString(String input) {

		Document doc = XMLReadWrite.documentFromString(input);

		NodeList chatInfo = doc.getElementsByTagName("sendGroupChat");

		Element chat = (Element) chatInfo.item(0);

		group_chat_id = (chat.getAttribute("id_group"));

		nome_admin = (chat.getAttribute("nome_admin"));
		nome_uc = (chat.getAttribute("nome_uc"));

		NodeList messageInfo = doc.getElementsByTagName("message");

		NodeList users = doc.getElementsByTagName("user");

		for (int j = 0; j < users.getLength(); j++) {

			Element user = (Element) users.item(j);
			String texto = user.getAttribute("name");

			this.usersChat.add(texto);

		}

		for (int i = 0; i < messageInfo.getLength(); i++) {

			Element message = (Element) messageInfo.item(i);
			String id = message.getElementsByTagName("membro").item(0).getAttributes().item(0).getTextContent();
			String texto = message.getElementsByTagName("texto").item(0).getTextContent();
			String date = message.getElementsByTagName("date").item(0).getTextContent();

			Message m_i = new Message(texto, date, id);

			msgs.add(m_i);

			String sendToTextArea = id + " " + texto + " " + date + "\n";

			this.chatPanel.getTextArea().append(sendToTextArea);

		}

		this.chatPanel.setLabels(nome_uc, " ");
		this.chatPanel.setUsersPanel(usersChat, nome_admin);
	}

	public void processUserMessage(String input) {

		Document doc = XMLReadWrite.documentFromString(input);

		NodeList messageInfo = doc.getElementsByTagName("content");

		Element content = (Element) messageInfo.item(0);

		String name = content.getElementsByTagName("user").item(0).getAttributes().item(0).getTextContent();
		String texto = content.getElementsByTagName("texto").item(0).getTextContent();
		String date = content.getElementsByTagName("data").item(0).getTextContent();

		String msg = name + " " + date + " " + texto + "\n";

		this.chatPanel.getTextArea().append(msg);
	}

	public String createTextMessage(String userId, String texto, String date, int chatType) {

		String sendMessage = null;
		if (chatType == 0) {
			sendMessage = "" + Expressions.sendTurmaMessage + " <chat id_uc='" + uc_id + "' id_turma='" + turma_id
					+ "'/> <content> <user id_user= '" + userId + "'/><texto>" + texto + "</texto><data>" + date
					+ "</data></content> " + Expressions.sendTurmaMessageEnd + "";

		}

		if (chatType == 1) {
			sendMessage = "" + Expressions.sendUcMessage + " <chat id_uc='" + uc_id + "' id_turma='" + turma_id
					+ "'/> <content> <user id_user= '" + userId + "'/><texto>" + texto + "</texto><data>" + date
					+ "</data></content> " + Expressions.sendUcMessageEnd + "";

		}

		if (chatType == 2) {
			sendMessage = "" + Expressions.sendGroupChatMessage + " <chat id_group='" + group_chat_id
					+ "' /> <content> <user id_user= '" + userId + "'/><texto>" + texto + "</texto><data>" + date
					+ "</data></content> " + Expressions.sendGroupChatMessageEnd + "";


		}

		return sendMessage;
		// String turma_id = data.ge

	}

	// TODO CREATE UCMESSAGE

	public static void main(String[] args) {
		ClassChatPanel c = new ClassChatPanel();
		ManageChatPanel m = new ManageChatPanel(c);

		String input = "<sendChatTurma id_turma ='2' id_uc='1' id_admin='2'><message> <membro id ='2'/> <texto>Mequie bro</texto> <date>14/05/2021</date></message></sendChatTurma>";

		String xd = m.createTextMessage("2", "banana", "32/32/12", 0);

		String msgUser = "<sendTurmaMessageUser> <chat id_uc='1' id_turma='2'/> <content> <user name_user= 'tavora'/><texto>21/05/2021 </texto><data>hrsfd</data></content> </sendTurmaMessageUser>\r\n"
				+ "";
		m.processUserMessage(msgUser);
		// m.processInputString(input);

	}

// <sendChatTurma id_turma ='2' id_uc='1' id_admin=' 2'><message> <membro id ='2'/> <texto>Mequie bro</texto> <date>14/05/2021</date></message></sendChatTurma>

}
