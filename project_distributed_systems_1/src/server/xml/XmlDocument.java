package server.xml;

import java.io.IOException;

import java.util.ArrayList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import server.data_info.ClassChat;
import server.data_info.Message;
import util.Expressions;
import util.Paths;
import util.Vars;
import xml_read_write.XMLReadWrite;

public class XmlDocument {

	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document docUsers;
	private Document docGroups;
	private Document chatsTurma;
	private Document chatsUc;
	private Document chatsUsers;
	private Document chatsGroupsAlunos;

	public XmlDocument() throws ParserConfigurationException, SAXException, IOException {
		
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		this.docUsers = builder.parse(Paths.users_xml_path);
		this.docGroups = builder.parse(Paths.grupos_xml_path);
		this.chatsTurma = builder.parse(Paths.chats_turma_xml_path);
		this.chatsUsers = builder.parse(Paths.chats_alunos_xml_path);
		this.chatsUc = builder.parse(Paths.chats_uc_xml_path);
		this.chatsGroupsAlunos = builder.parse(Paths.chats_grupos_turma_path);
		
			
		 //TODO VALIDAR XML COM XSD
		
		if (ValidatorXML.validDoc(docGroups, Paths.grupos_xsd , XMLConstants.W3C_XML_SCHEMA_NS_URI) )
			System.out.println("Validação do XML feita com sucesso");// Validação com XSD realizada com sucesso!
		else {
			System.out.println("O documento XML não é válido para o documento XSD");// Falhou a validação com XSD

		}
	

	}

	// gets Cleared XML String from document
	public String getXmlDocString(Document doc) {

		return getXMLStringNoHeader(XMLReadWrite.documentToString(doc));
	}

	// clear header and removes \n, to put the content of the string, all in one
	// string
	private String getXMLStringNoHeader(String strXML) {
		strXML = strXML.substring(61, strXML.length());
		return strXML.replace("\n", "").replace("\r", "");
	}

	public Document getDocUsers() {
		return this.docUsers;
	}

	// return user Id based on string received from client
	public int getUserId(String loginNotify) {

		// get password and username from input string
		Document doc = XMLReadWrite.documentFromString(loginNotify);
		
		try {
			if (ValidatorXML.validDoc(doc, Paths.login_xsd,XMLConstants.W3C_XML_SCHEMA_NS_URI )) {
				System.out.println("validacao login com sucesso");
			}
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		NodeList login = doc.getElementsByTagName("login");
		Element element = (Element) login.item(0);

		NodeList user = element.getElementsByTagName("username");
		String username = (user.item(0).getTextContent());

		NodeList passList = element.getElementsByTagName("password");
		String password = (passList.item(0).getTextContent());

		// check in users xml if there is a user with that name and password

		NodeList users = docUsers.getElementsByTagName("user");

		for (int i = 0; i < users.getLength(); i++) {

			Element elmnt = (Element) users.item(i);

			String userName = elmnt.getElementsByTagName("name").item(0).getTextContent();
			String pass = elmnt.getElementsByTagName("password").item(0).getTextContent();
				
			if (username.equals(userName) && password.equals(pass)) {

				String id = elmnt.getAttribute("id");

				return Integer.parseInt(id);
			}

		}

		return -1;

	}

	private String getUsersGroupChatName(String id) {

		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression = "//chat[@id='" + id + "']/group_name";
		NodeList elementx = null;
		try {
			elementx = (NodeList) xpath.evaluate(expression, chatsGroupsAlunos, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element element = (Element) elementx.item(0);
		String name = element.getTextContent();

		return name;

	}

	// returns a String with the info of a specific user based on his id
	public String getUserInfo(int id) throws SAXException, IOException {
		this.chatsUsers = null;
		this.chatsUsers = builder.parse(Paths.chats_alunos_xml_path);

		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression = "//users/user[@id=\"" + id + "\"]";
		NodeList elementx = null;
		try {
			elementx = (NodeList) xpath.evaluate(expression, docUsers, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Element element = (Element) elementx.item(0);

		// Element element = (Element) users.item(id - 1);
		// chats.item(0).appendChild(chat);
		// chat.setAttribute("id", chatId);

		NodeList chatsIds = (NodeList) element.getElementsByTagName("chat");
		
		
		

		ArrayList<String> ids = new ArrayList<String>();
		if (chatsIds.getLength() != 0 && chatsIds != null) {
			for (int i = 0; i < chatsIds.getLength(); i++) {

				Element elm = (Element) chatsIds.item(i);
				String xd = elm.getAttribute("id");
				ids.add(xd);
			}

		}

		String userName = element.getElementsByTagName("name").item(0).getTextContent();
		String number = element.getElementsByTagName("number").item(0).getTextContent();
		String surname = element.getElementsByTagName("surname").item(0).getTextContent();
		String type = element.getElementsByTagName("type").item(0).getAttributes().item(0).getTextContent();
		String gender = element.getElementsByTagName("gender").item(0).getAttributes().item(0).getTextContent();
		String date = element.getElementsByTagName("birth_date").item(0).getTextContent();
		String nat = element.getElementsByTagName("nationality").item(0).getTextContent();
		String lang = element.getElementsByTagName("language").item(0).getAttributes().item(0).getTextContent();
		String address = element.getElementsByTagName("address").item(0).getTextContent();
		String email = element.getElementsByTagName("email").item(0).getTextContent();
		String telephone = element.getElementsByTagName("telephone").item(0).getTextContent();
		String password = element.getElementsByTagName("password").item(0).getTextContent();

		Document docXml = builder.newDocument();
		Element user = docXml.createElement("user");
		docXml.appendChild(user);

		user.setAttribute("id", String.valueOf(id));

		// criar <chats> </chat>
		Element chats = docXml.createElement("chats");

		for (String i_id : ids) {

			Element chatI = docXml.createElement("chat");
			chatI.setAttribute("chat_id", i_id);
			Element nome = docXml.createElement("group_name");
			if (!(i_id.equals("0"))) {
			nome.appendChild(docXml.createTextNode(getUsersGroupChatName(i_id)));
			chatI.appendChild(nome);
			}
			chats.appendChild(chatI);

		}
		user.appendChild(chats);

		// Element chat = docXml.createElement("chat");
		// chat.setAttribute("chat_id", chatId);

		user.appendChild(XMLReadWrite.createTextNode(docXml, "name", userName));
		user.appendChild(XMLReadWrite.createTextNode(docXml, "number", number));
		user.appendChild(XMLReadWrite.createTextNode(docXml, "surname", surname));
		user.appendChild(XMLReadWrite.createTextNode(docXml, "date", date));
		user.appendChild(XMLReadWrite.createTextNode(docXml, "nationality", nat));
		user.appendChild(XMLReadWrite.createTextNode(docXml, "address", address));
		user.appendChild(XMLReadWrite.createTextNode(docXml, "email", email));
		user.appendChild(XMLReadWrite.createTextNode(docXml, "telephone", telephone));
		user.appendChild(XMLReadWrite.createTextNode(docXml, "password", password));

		// attributes
		user.appendChild(XMLReadWrite.createAttributeNode(docXml, "language", "lang", lang));
		user.appendChild(XMLReadWrite.createAttributeNode(docXml, "type", "user_type", type));
		user.appendChild(XMLReadWrite.createAttributeNode(docXml, "gender", "gender", gender));

		return getXmlDocString(docXml);
		// return XMLReadWrite.documentToString(docXml);

	}

	public static final NodeList getXPath(final String expression, final Document doc) {
		XPath xpath = XPathFactory.newInstance().newXPath();
		NodeList nodes;
		try {
			nodes = (NodeList) xpath.evaluate(expression, doc, XPathConstants.NODESET);
			return nodes;
		} catch (XPathExpressionException e) {
			e.printStackTrace();
			return null;
		}
	}

	public String getTurmasUser(int id) throws XPathExpressionException {
		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression_turma_id = "//turma/@turma_id[//turma/membros/membro[@membro_id=\"" + id + "\"]]";
		String expression_turma_nome = "//nome[//turma/membros/membro[@membro_id=\"" + id + "\"]]";

		String expression_uc_id = "//uc/@id[//turma/membros/membro[@membro_id=\"" + id + "\"]]";
		String expression_uc_nome = "//uc/name[//turma/membros/membro[@membro_id=\"" + id + "\"]]";

		NodeList turma_id = (NodeList) xpath.evaluate(expression_turma_id, docGroups, XPathConstants.NODESET);
		NodeList turma_nome = (NodeList) xpath.evaluate(expression_turma_nome, docGroups, XPathConstants.NODESET);

		NodeList uc_id = (NodeList) xpath.evaluate(expression_uc_id, docGroups, XPathConstants.NODESET);
		NodeList uc_nome = (NodeList) xpath.evaluate(expression_uc_nome, docGroups, XPathConstants.NODESET);

		String xd = "<ucInfo>";

		for (int i = 0; i < uc_id.getLength(); i++) {

			xd = xd + "<uc id = '" + uc_id.item(i).getTextContent() + "'><name>" + uc_nome.item(i).getTextContent()
					+ "</name><turma turma_id = '" + turma_id.item(i).getTextContent() + "'> <nome>"
					+ turma_nome.item(i).getTextContent() + "</nome> </turma> </uc> ";
		}

		xd = xd + "</ucInfo>";

		return xd;
	}

	public String getTurmasUcsWithChat(int id) throws XPathExpressionException {
		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression_turma_id = "//turma/@turma_id[//turma/membros/membro[@membro_id=\"" + id + "\"]]";
		String expression_turma_nome = "//nome[//turma/membros/membro[@membro_id=\"" + id + "\"]]";

		String expression_uc_id = "//uc/@id[//turma/membros/membro[@membro_id=\"" + id + "\"]]";
		String expression_uc_nome = "//uc/name[//turma/membros/membro[@membro_id=\"" + id + "\"]]";

		NodeList turma_id = (NodeList) xpath.evaluate(expression_turma_id, docGroups, XPathConstants.NODESET);

		NodeList uc_id = (NodeList) xpath.evaluate(expression_uc_id, docGroups, XPathConstants.NODESET);

		String xd = Expressions.checkChats;

		for (int i = 0; i < uc_id.getLength(); i++) {

			xd = xd + "<uc id = '" + uc_id.item(i).getTextContent() + "'><hasChat>"
					+ checkIfUcChatExists(uc_id.item(i).getTextContent()) + "</hasChat>" + "</uc>"
					+ "<turma turma_id = '" + turma_id.item(i).getTextContent() + "_" + uc_id.item(i).getTextContent()
					+ "'><hasChat>"
					+ checkIfTurmaChatExists(uc_id.item(i).getTextContent(), turma_id.item(i).getTextContent())
					+ "</hasChat>" + "</turma>";

		}

		xd = xd + Expressions.checkChatsEnd;

		return xd;
	}

	private boolean checkIfTurmaChatExists(String idUc, String idTurma) {

		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression = "count(//chat[@id_uc='" + idUc + "' and @id_turma='" + idTurma + "'])";
		double c = 0;
		try {
			c = (double) xpath.evaluate(expression, chatsTurma, XPathConstants.NUMBER);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (c == 1) {
			return true;
		}

		return false;

	}

	private boolean checkIfUcChatExists(String idUc) {

		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression = "count(//chat[@id_uc='" + idUc + "'])";
		double c = 0;
		try {
			c = (double) xpath.evaluate(expression, chatsUc, XPathConstants.NUMBER);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (c == 1) {
			return true;
		}

		return false;

	}

	public String getNameUserById(String id) {

		// users/user[@id="1"]
		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression = "//users/user[@id=\"" + id + "\"]/name";
		NodeList userInfo = null;
		try {
			userInfo = (NodeList) xpath.evaluate(expression, docUsers, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String xmlUsers = this.getXmlDocString(docUsers);
		// Document docUsers = XMLReadWrite.documentFromString(xmlUsers);
		// turma_id = docUsers.getElementsByTagName("user");

		// Element element = (Element) turma_id.item(Integer.parseInt(id));

		String userName = userInfo.item(0).getTextContent();
		return userName;

	}

	public String getNameTurmaById(String id) {

		// users/user[@id="1"]
		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression = "//ucs/uc/turma[@turma_id=\"" + id + "\"]/nome";
		NodeList userInfo = null;
		try {
			userInfo = (NodeList) xpath.evaluate(expression, docGroups, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String xmlUsers = this.getXmlDocString(docUsers);
		// Document docUsers = XMLReadWrite.documentFromString(xmlUsers);
		// turma_id = docUsers.getElementsByTagName("user");

		// Element element = (Element) turma_id.item(Integer.parseInt(id));

		String name = userInfo.item(0).getTextContent();
		return name;

	}

	public String getNameUcById(String id) {

		// users/user[@id="1"]
		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression = "//ucs/uc[@id =\"" + id + "\"]/name";
		NodeList userInfo = null;
		try {
			userInfo = (NodeList) xpath.evaluate(expression, docGroups, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// String xmlUsers = this.getXmlDocString(docUsers);
		// Document docUsers = XMLReadWrite.documentFromString(xmlUsers);
		// turma_id = docUsers.getElementsByTagName("user");

		// Element element = (Element) turma_id.item(Integer.parseInt(id));

		String name = userInfo.item(0).getTextContent();
		return name;

	}

	public ArrayList<ClassChat> getRecentChatsTurma() throws SAXException, IOException {
		this.chatsTurma = builder.parse(Paths.chats_turma_xml_path);
		ArrayList<ClassChat> chatsTurmaArr = new ArrayList<ClassChat>();

		// String chat = this.getXmlDocString(chatsTurma);
		// Document docChatsTurma = XMLReadWrite.documentFromString(chat);
		NodeList chats = chatsTurma.getElementsByTagName("chat");

		// ArrayList<Message> msgs = new ArrayList<Message>();
		// System.out.println(chats.getLength());
		int chatsLen = chats.getLength();

		for (int i = 0; i < chatsLen; i++) {

			Element chat_i = (Element) chats.item(i);

			String id_uc = chat_i.getAttribute("id_uc");
			String id_turma = chat_i.getAttribute("id_turma");
			String admin = chat_i.getElementsByTagName("admin").item(0).getAttributes().item(0).getTextContent();

			ArrayList<String> usersId = new ArrayList<String>();
			NodeList users = chat_i.getElementsByTagName("user");

			for (int x = 0; x < users.getLength(); x++) {

				Element user = (Element) users.item(x);

				String id_x = user.getAttribute("id");

				usersId.add(id_x);

			}

			// String membro_name =
			// users.getElementsByTagName("user").item(0).getAttributes().item(0).getTextContent();

			int mensagensLen = chat_i.getElementsByTagName("mensagem").getLength();

			ArrayList<Message> msgs = new ArrayList<Message>();

			int num_idx = 0;
			if (mensagensLen > Vars.numberOfMessagesInHistory) {

				num_idx = mensagensLen - Vars.numberOfMessagesInHistory;

			}
			
			if (mensagensLen == 0) {
				
				ClassChat c_i = new ClassChat(msgs, id_uc, id_turma, admin, usersId, "0");
				chatsTurmaArr.add(c_i);
		}

			for (int j = mensagensLen - 1; j > num_idx - 1; j--) {

				Element mensagem = (Element) chat_i.getElementsByTagName("mensagem").item(j);

				String membro_name = mensagem.getElementsByTagName("membro").item(0).getAttributes().item(0)
						.getTextContent();
				String texto = mensagem.getElementsByTagName("texto").item(0).getTextContent();
				String date = mensagem.getElementsByTagName("date").item(0).getTextContent();

				Message msg_j = new Message(texto, date, membro_name);

				msgs.add(msg_j);

				if (j == num_idx) {
					ClassChat c_i = new ClassChat(msgs, id_uc, id_turma, admin, usersId, "0");
					chatsTurmaArr.add(c_i);

				}

			}
		}
		return chatsTurmaArr;

	}

	public ArrayList<ClassChat> getRecentChatsUc() {

		ArrayList<ClassChat> chatsUcArr = new ArrayList<ClassChat>();

		// String chat = this.getXmlDocString(chatsUc);
		// Document docChatsUc = XMLReadWrite.documentFromString(chat);
		NodeList chats = chatsUc.getElementsByTagName("chat");

		// ArrayList<Message> msgs = new ArrayList<Message>();
		// System.out.println(chats.getLength());
		int chatsLen = chats.getLength();

		if (chatsLen > Vars.numberOfMessagesInHistory) {
			chatsLen = Vars.numberOfMessagesInHistory;
		}

		for (int i = 0; i < chatsLen; i++) {

			Element chat_i = (Element) chats.item(i);

			String id_uc = chat_i.getAttribute("id_uc");
			String admin = chat_i.getElementsByTagName("admin").item(0).getAttributes().item(0).getTextContent();

			ArrayList<String> usersId = new ArrayList<String>();
			NodeList users = chat_i.getElementsByTagName("user");

			for (int x = 0; x < users.getLength(); x++) {

				Element user = (Element) users.item(x);

				String id_x = user.getAttribute("id");

				usersId.add(id_x);

			}

			int mensagensLen = chat_i.getElementsByTagName("mensagem").getLength();

			ArrayList<Message> msgs = new ArrayList<Message>();

			int num_idx = 0;
			if (mensagensLen > Vars.numberOfMessagesInHistory) {

				num_idx = mensagensLen - Vars.numberOfMessagesInHistory;

			}
				if (mensagensLen == 0) {
				
					ClassChat c_i = new ClassChat(msgs, id_uc, "0", admin, usersId, "0");
					chatsUcArr.add(c_i);
			}
				
				else {
			// ClassChat c_i = new ClassChat(msgs,id_uc,id_turma,admin);

			for (int j = mensagensLen - 1; j > num_idx - 1; j--) {

				Element mensagem = (Element) chat_i.getElementsByTagName("mensagem").item(j);

				String membro_name = mensagem.getElementsByTagName("membro").item(0).getAttributes().item(0)
						.getTextContent();
				String texto = mensagem.getElementsByTagName("texto").item(0).getTextContent();
				String date = mensagem.getElementsByTagName("date").item(0).getTextContent();

				Message msg_j = new Message(texto, date, membro_name);

				msgs.add(msg_j);

				if (j == num_idx) {

					ClassChat c_i = new ClassChat(msgs, id_uc, "0", admin, usersId, "0");
					chatsUcArr.add(c_i);

				}

			}
				}
		}
		return chatsUcArr;

	}

	public ArrayList<ClassChat> getRecentChatsGroupUsers() {

		ArrayList<ClassChat> chatsUcArr = new ArrayList<ClassChat>();

		// String chat = this.getXmlDocString(chatsUc);
		// Document docChatsUc = XMLReadWrite.documentFromString(chat);
		NodeList chats = chatsGroupsAlunos.getElementsByTagName("chat");

		// ArrayList<Message> msgs = new ArrayList<Message>();
		// System.out.println(chats.getLength());
		int chatsLen = chats.getLength();
		
		if (chatsLen > Vars.numberOfMessagesInHistory) {
			chatsLen = Vars.numberOfMessagesInHistory;
		}

		for (int i = 0; i < chatsLen; i++) {
			
			

			Element chat_i = (Element) chats.item(i);

			String id_chat = chat_i.getAttribute("id");
			String admin = chat_i.getElementsByTagName("admin").item(0).getAttributes().item(0).getTextContent();

			ArrayList<String> usersId = new ArrayList<String>();
			NodeList users = chat_i.getElementsByTagName("student");
			

			for (int x = 0; x < users.getLength(); x++) {

				Element user = (Element) users.item(x);

				String id_x = user.getAttribute("id");

				usersId.add(id_x);

			}

			int mensagensLen = chat_i.getElementsByTagName("mensagem").getLength();
			

			ArrayList<Message> msgs = new ArrayList<Message>();

			int num_idx = 0;
			if (mensagensLen > Vars.numberOfMessagesInHistory) {

				num_idx = mensagensLen - Vars.numberOfMessagesInHistory;

			}

			// ClassChat c_i = new ClassChat(msgs,id_uc,id_turma,admin);
			if (mensagensLen == 0) {
				
				ClassChat c_i = new ClassChat(msgs, "0", "0", admin, usersId, id_chat);
				chatsUcArr.add(c_i);
			}
			else {
			for (int j = mensagensLen - 1; j > num_idx - 1; j--) {
				

				Element mensagem = (Element) chat_i.getElementsByTagName("mensagem").item(j);

				String membro_name = mensagem.getElementsByTagName("membro").item(0).getAttributes().item(0)
						.getTextContent();
				String texto = mensagem.getElementsByTagName("texto").item(0).getTextContent();
				String date = mensagem.getElementsByTagName("date").item(0).getTextContent();

				Message msg_j = new Message(texto, date, membro_name);

				msgs.add(msg_j);

				if (j == num_idx) {

					ClassChat c_i = new ClassChat(msgs, "0", "0", admin, usersId, id_chat);
					chatsUcArr.add(c_i);

				}
			}
			}
		}
		return chatsUcArr;

	}

	public String[] processChatTurmaRequest(String input) {

		Document docInput = XMLReadWrite.documentFromString(input);
		NodeList ucInfo = docInput.getElementsByTagName("uc");

		Element uc = (Element) ucInfo.item(0);
		String uc_id = (uc.getAttribute("uc_id"));

		NodeList turma_info = docInput.getElementsByTagName("turma");

		Element turma = (Element) turma_info.item(0);
		String turma_id = (turma.getAttribute("turma_id"));

		String[] results = new String[2];

		results[0] = uc_id;
		results[1] = turma_id;

		return results;

	}

	public String processChatUcRequest(String input) {

		Document docInput = XMLReadWrite.documentFromString(input);
		NodeList ucInfo = docInput.getElementsByTagName("uc");

		Element uc = (Element) ucInfo.item(0);
		String uc_id = (uc.getAttribute("uc_id"));

		return uc_id;

	}
	
	public String processChatGroupRequest(String input) {
		
		Document docInput = XMLReadWrite.documentFromString(input);
		NodeList ucInfo = docInput.getElementsByTagName("chat");

		Element uc = (Element) ucInfo.item(0);
		String id = (uc.getAttribute("id"));

		return id;
	}
	
	

	public String createClassChatSendMessage(ClassChat classChat, int isClass) {

		String id_turma = classChat.getTurmaId();
		String id_uc = classChat.getUcId();
		String id_admin = classChat.getAdminId();
		
		String id_group = classChat.getId_studentGroup();

		String nome_admin = getNameUserById(id_admin);
		String nome_turma = null;
		String nome_uc = null;
		if (isClass ==0) {
			nome_turma = getNameTurmaById(id_turma);
			nome_uc = getNameUcById(id_uc);
		}
		String str_inicial = null;
		String str_final = null;

		if (isClass==0) {
			str_inicial = Expressions.sendClassChat;
			str_final = Expressions.sendClassChatEnd;
		} 
		else if (isClass==1) {
			nome_uc = getNameUcById(id_uc);
			str_inicial = Expressions.sendUcChat;
			str_final = Expressions.sendUcChatEnd;

		}
		else if (isClass==2) {
			nome_uc = getUsersGroupChatName(id_group);
			str_inicial = Expressions.sendGroupChat;
			str_final = Expressions.sendGroupChatEnd;
			
			
		}
		str_inicial = str_inicial + " id_turma ='" + id_turma + "' id_uc='" + id_uc + "' id_admin='" + id_group+ "' id_group='"+ id_group
				+ "' nome_turma ='" + nome_turma + "' nome_uc='" + nome_uc + "' nome_admin='" + nome_admin + "'>";

		String str_aux = "";

		str_inicial = str_inicial + "<users>";

		for (int x = 0; x < classChat.getUsersId().size(); x++) {

			str_inicial = str_inicial + "<user name='" + getNameUserById(classChat.getUsersId().get(x)) + "'/>";

		}
		str_inicial = str_inicial + "</users>";
		for (int i = classChat.getMessages().size() - 1; i > -1; i--) {

			String strIdUser = classChat.getMessages().get(i).getUserId();

			String userName = getNameUserById(strIdUser);

			String date = classChat.getMessages().get(i).getDate();
			String text = classChat.getMessages().get(i).getText();

			str_aux = str_aux + "<message> <membro id ='" + userName + "'/> <texto>" + text + "</texto> <date>" + date
					+ "</date></message>";

		}

		String result_str = str_inicial + str_aux + str_final;
		return result_str;

	}

	public String[] processSendMessageString(String input) {

		Document docInput = XMLReadWrite.documentFromString(input);

		NodeList chat = (NodeList) docInput.getElementsByTagName("chat");

		Element chat_0 = (Element) chat.item(0);

		// NodeList ucInfo = docInput.getElementsByTagName("uc");

		// Element uc = (Element) ucInfo.item(0);
		// String uc_id = chat.item(0).getAttributes().item(0).getTextContent();

		NodeList content = (NodeList) docInput.getElementsByTagName("content");

		Element content_0 = (Element) content.item(0);

		String id_uc = chat_0.getAttribute("id_uc");
		String id_turma = chat_0.getAttribute("id_turma");

		String id_user = content_0.getElementsByTagName("user").item(0).getAttributes().item(0).getTextContent();
		String date = content_0.getElementsByTagName("data").item(0).getTextContent();
		String texto = content_0.getElementsByTagName("texto").item(0).getTextContent();

		String[] result = new String[5];

		result[0] = id_uc;
		result[1] = id_turma;
		result[2] = id_user;
		result[3] = texto;
		result[4] = date;

		return result;

	}
	
	public String[] processGroupStudentsMessage(String input) {
		
		Document docInput = XMLReadWrite.documentFromString(input);

		NodeList chat = (NodeList) docInput.getElementsByTagName("chat");

		Element chat_0 = (Element) chat.item(0);

		// NodeList ucInfo = docInput.getElementsByTagName("uc");

		// Element uc = (Element) ucInfo.item(0);
		// String uc_id = chat.item(0).getAttributes().item(0).getTextContent();

		NodeList content = (NodeList) docInput.getElementsByTagName("content");

		Element content_0 = (Element) content.item(0);

		String id_group = chat_0.getAttribute("id_group");

		String id_user = content_0.getElementsByTagName("user").item(0).getAttributes().item(0).getTextContent();
		String date = content_0.getElementsByTagName("data").item(0).getTextContent();
		String texto = content_0.getElementsByTagName("texto").item(0).getTextContent();

		String[] result = new String[4];

		result[0] = id_group;
		result[1] = id_user;
		result[2] = texto;
		result[3] = date;

		return result;
		
	}
	
	
	
	
	

	public String[] processUcSendMessageString(String input) {

		Document docInput = XMLReadWrite.documentFromString(input);

		NodeList chat = (NodeList) docInput.getElementsByTagName("chat");

		Element chat_0 = (Element) chat.item(0);

		// NodeList ucInfo = docInput.getElementsByTagName("uc");

		// Element uc = (Element) ucInfo.item(0);
		// String uc_id = chat.item(0).getAttributes().item(0).getTextContent();

		NodeList content = (NodeList) docInput.getElementsByTagName("content");

		Element content_0 = (Element) content.item(0);

		String id_uc = chat_0.getAttribute("id_uc");
		String id_turma = chat_0.getAttribute("id_turma");

		String id_user = content_0.getElementsByTagName("user").item(0).getAttributes().item(0).getTextContent();
		String date = content_0.getElementsByTagName("data").item(0).getTextContent();
		String texto = content_0.getElementsByTagName("texto").item(0).getTextContent();

		String[] result = new String[5];

		result[0] = id_uc;
		result[1] = id_turma;
		result[2] = id_user;
		result[3] = texto;
		result[4] = date;

		return result;

	}

	public String sendMessageToChat(String id_uc, String id_turma, String id_user, String texto, String data) {

		String sendString = "<sendTurmaMessageUser> <chat id_uc='" + id_uc + "' id_turma='" + id_turma
				+ "'/> <content> <user name_user= '" + getNameUserById(id_user) + "'/><texto>" + texto
				+ "</texto><data>" + data + "</data></content> </sendTurmaMessageUser>";
		return sendString;
	}

	public String sendMessageUcToChat(String id_uc, String id_user, String texto, String data) {

		String sendString = "<sendUcMessageUser> <chat id_uc='" + id_uc + "' />" + " <content> <user name_user= '"
				+ getNameUserById(id_user) + "'/><texto>" + texto + "</texto><data>" + data
				+ "</data></content> </sendUcMessageUser>";
		return sendString;
	}
	
	public String sendMessageGroupChat(String id_group, String id_user,String texto, String data) {

		String sendString = "<sendGroupMessageUser> <chat id_group='" + id_group + "' />" + " <content> <user name_user= '"
				+ getNameUserById(id_user) + "'/><texto>" + texto + "</texto><data>" + data
				+ "</data></content> </sendGroupMessageUser>";
		return sendString;
	}
	
	
	
	
	

	public String getListofAllStudents() {

		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression = "users/user[type/@user_type='student']";

		NodeList users = null;

		try {
			users = (NodeList) xpath.evaluate(expression, docUsers, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String strInicial = "";
		strInicial = strInicial + Expressions.sendAlunos;

		for (int i = 0; i < users.getLength(); i++) {

			Element user_i = (Element) users.item(i);
			String name = user_i.getElementsByTagName("name").item(0).getTextContent();
			String id = user_i.getAttribute("id");
			strInicial = strInicial + "<user id='" + id + "'> <name>" + name + "</name>" + "</user>";

		}

		return strInicial + Expressions.sendAlunosEnd;

	}

	public static void main(String[] args)
			throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {

		XmlDocument x = new XmlDocument();
		
	}

}
