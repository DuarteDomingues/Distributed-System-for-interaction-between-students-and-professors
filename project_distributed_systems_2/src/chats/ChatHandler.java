package chats;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import data_info.Message;
import util.Expressions;
import xml_read_write.XMLReadWrite;

public class ChatHandler {
	
	
	private String uc_id;
	private String turma_id;
	private String admin_id;
	private String group_chat_id;

	private String nome_turma;
	private String nome_admin;
	private String nome_uc;

	private ArrayList<Message> msgs;

	private ArrayList<String> usersChat;
	
	
	public ChatHandler() {

		this.msgs = new ArrayList<Message>();
		this.usersChat = new ArrayList<String>();

	}
	
	
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

			


		}
	
	
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



		}
		
	}
	
	
	public void processInputGroupChatString(String input) {

		Document doc = XMLReadWrite.documentFromString(input);

		NodeList chatInfo = doc.getElementsByTagName("sendGroupChat");

		Element chat = (Element) chatInfo.item(0);

		group_chat_id = (chat.getAttribute("id_group"));

		nome_admin = (chat.getAttribute("nome_admin"));
		nome_uc = (chat.getAttribute("nome_uc"));
		admin_id =  (chat.getAttribute("id_admin"));

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


		}

	
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
	




	public String getUc_id() {
		return uc_id;
	}





	public String getTurma_id() {
		return turma_id;
	}





	public String getAdmin_id() {
		return admin_id;
	}





	public String getGroup_chat_id() {
		return group_chat_id;
	}





	public String getNome_turma() {
		return nome_turma;
	}





	public String getNome_admin() {
		return nome_admin;
	}





	public String getNome_uc() {
		return nome_uc;
	}





	public ArrayList<Message> getMsgs() {
		return msgs;
	}





	public ArrayList<String> getUsersChat() {
		return usersChat;
	}

	
	
	

}
