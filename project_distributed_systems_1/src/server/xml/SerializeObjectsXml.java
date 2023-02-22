package server.xml;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import util.Paths;
import util.Vars;
import xml_read_write.XMLReadWrite;

public class SerializeObjectsXml {

	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document docUsers;
	private Document docGroups;
	private Document chatsTurma;
	private Document chatsUc;
	private Document chatsUsers;
	private Document chatsGroupUsers;

	public SerializeObjectsXml() throws ParserConfigurationException, SAXException, IOException {
		
		try {

		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
		this.docUsers = builder.parse(Paths.users_xml_path);
		
		this.docGroups = builder.parse(Paths.grupos_xml_path);
		this.chatsTurma = builder.parse(Paths.chats_turma_xml_path);
		this.chatsUsers = builder.parse(Paths.chats_alunos_xml_path);
		this.chatsUc = builder.parse(Paths.chats_uc_xml_path);
		this.chatsGroupUsers = builder.parse(Paths.chats_grupos_turma_path);
		
		/*if (ValidatorXML.validDoc(docUsers, Paths.users_xml_path , XMLConstants.W3C_XML_SCHEMA_NS_URI)) {
			System.out.println("Validação do XML feita com sucesso");// Validação com XSD realizada com sucesso!

			
		}
		else {
			System.out.println("O documento XML não é válido para o documento XSD");// Falhou a validação com XSD

		} */
		}
		
		catch (Exception e) {
			e.printStackTrace(System.out);
			System.out.print("Erro ao analisar o documento XML.");
		}
	}

	public void addGroupToGroupUsers(String input, int idUser) {

		Vars.count++;
		String chatId = String.valueOf(Vars.count);

		Document doc = XMLReadWrite.documentFromString(input);
		NodeList createStudentChat = doc.getElementsByTagName("createStudentChat");

		Element elementUpdate = (Element) createStudentChat.item(0);

		String userNameNewUpdate = elementUpdate.getElementsByTagName("Groupname").item(0).getTextContent();

		NodeList students = (NodeList) elementUpdate.getElementsByTagName("student");
		ArrayList<String> ids = new ArrayList<String>();
		for (int i = 0; i < students.getLength(); i++) {

			Element elm = (Element) students.item(i);
			String xd = elm.getAttribute("id");
			ids.add(xd);
		}

		NodeList chats = (NodeList) chatsGroupUsers.getElementsByTagName("chats");
		Element chat = chatsGroupUsers.createElement("chat");
		chats.item(0).appendChild(chat);
		chat.setAttribute("id", chatId);

		Element admin = chatsGroupUsers.createElement("admin");
		admin.setAttribute("id_admin", String.valueOf(idUser));
		chat.appendChild(admin);

		Element nome = chatsGroupUsers.createElement("group_name");
		nome.appendChild(chatsGroupUsers.createTextNode(userNameNewUpdate));
		chat.appendChild(nome);

		Element studentsAdd = chatsGroupUsers.createElement("students");

		for (String id : ids) {

			Element student_i = chatsGroupUsers.createElement("student");
			student_i.setAttribute("id", id);
			studentsAdd.appendChild(student_i);

		}

		chat.appendChild(studentsAdd);

		DOMSource source = new DOMSource(chatsGroupUsers);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		StreamResult result = new StreamResult(Paths.chats_grupos_turma_path);
		try {
			transformerFactory.newTransformer().transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void addMessageToXmlChatTurma(String id_uc, String id_turma, String id_membro, String textoMsg,
			String dateMsg, int chatType) throws TransformerConfigurationException, TransformerException {

		// users/user[@id="1"]
		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = null;
		if (chatType == 0) {
			expression = "//chat[@id_uc=\"" + id_uc + "\" and @id_turma=\"" + id_turma + "\"]/mensagens";
		} else if (chatType == 1) {

			expression = "//chat[@id_uc=\"" + id_uc + "\"]/mensagens";

		} else if (chatType == 2) {

			expression = "//chat[@id=\"" + id_uc + "\"]/mensagens";
		}

		Node mensagens = null;

		try {
			if (chatType == 0) {
				mensagens = (Node) xpath.evaluate(expression, chatsTurma, XPathConstants.NODE);
			} else if (chatType == 1) {
				mensagens = (Node) xpath.evaluate(expression, chatsUc, XPathConstants.NODE);
			} else if (chatType == 2) {
				mensagens = (Node) xpath.evaluate(expression, chatsGroupUsers, XPathConstants.NODE);
			}
		}

		catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Node mensagens = (Node)
		// this.chatsTurma.getElementsByTagName("mensagens").item(0);

		Element membro = null;
		Element texto = null;
		Element date = null;
		Node mensagem = null;

		if (chatType == 0) {
			
			mensagem = chatsTurma.createElement("mensagem");
			mensagens.appendChild(mensagem);

			membro = chatsTurma.createElement("membro");
			membro.setAttribute("id", id_membro);

			texto = chatsTurma.createElement("texto");
			texto.appendChild(chatsTurma.createTextNode(textoMsg));

			date = chatsTurma.createElement("date");
			date.appendChild(chatsTurma.createTextNode(dateMsg));
		}

		else if (chatType == 1) {

			mensagem = chatsUc.createElement("mensagem");
			mensagens.appendChild(mensagem);

			membro = chatsUc.createElement("membro");
			membro.setAttribute("id", id_membro);

			texto = chatsUc.createElement("texto");
			texto.appendChild(chatsUc.createTextNode(textoMsg));

			date = chatsUc.createElement("date");
			date.appendChild(chatsUc.createTextNode(dateMsg));

		}

		else if (chatType == 2) {

			mensagem = chatsGroupUsers.createElement("mensagem");
			mensagens.appendChild(mensagem);

			membro = chatsGroupUsers.createElement("membro");
			membro.setAttribute("id", id_membro);

			texto = chatsGroupUsers.createElement("texto");
			texto.appendChild(chatsGroupUsers.createTextNode(textoMsg));

			date = chatsGroupUsers.createElement("date");
			date.appendChild(chatsGroupUsers.createTextNode(dateMsg));

		}

		mensagem.appendChild(membro);
		mensagem.appendChild(texto);
		mensagem.appendChild(date);

		DOMSource source = null;

		if (chatType == 0) {
			source = new DOMSource(chatsTurma);

		}

		else if (chatType == 1) {
			source = new DOMSource(chatsUc);

		} else if (chatType == 2) {
			source = new DOMSource(chatsGroupUsers);

		}

		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		StreamResult result = null;

		if (chatType == 0) {

			result = new StreamResult(Paths.chats_turma_xml_path);
		}

		if (chatType == 1) {

			result = new StreamResult(Paths.chats_uc_xml_path);
		}

		if (chatType == 2) {

			result = new StreamResult(Paths.chats_grupos_turma_path);
		}
		transformerFactory.newTransformer().transform(source, result);

	}

	public void updateUserInfo(String input, int id) throws TransformerConfigurationException, TransformerException {

		Document doc = XMLReadWrite.documentFromString(input);
		NodeList updateUser = doc.getElementsByTagName("UpdateUser");
		Element elementUpdate = (Element) updateUser.item(0);

		String userNameNewUpdate = elementUpdate.getElementsByTagName("name").item(0).getTextContent();
		String surnameNewUpdate = elementUpdate.getElementsByTagName("surname").item(0).getTextContent();
		String addressNewUpdate = elementUpdate.getElementsByTagName("address").item(0).getTextContent();
		String emailNewUpdate = elementUpdate.getElementsByTagName("email").item(0).getTextContent();
		String telephoneNewUpdate = elementUpdate.getElementsByTagName("telephone").item(0).getTextContent();
		String languagesNewUpdate = elementUpdate.getElementsByTagName("language").item(0).getTextContent();
		String passwordNewUpdate = elementUpdate.getElementsByTagName("password").item(0).getTextContent();
		String genderNewUpdate = elementUpdate.getElementsByTagName("gender").item(0).getTextContent();
		String nationalityNewUpdate = elementUpdate.getElementsByTagName("nationality").item(0).getTextContent();

		XPath xpath = XPathFactory.newInstance().newXPath();

		String expression = "//users/user[@id=\"" + id + "\"]";

		NodeList userInfo = null;
		try {
			userInfo = (NodeList) xpath.evaluate(expression, docUsers, XPathConstants.NODESET);
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		Element element = (Element) userInfo.item(0);

		// TEXT THAT WILL BE SET, ON DOC USERS DOCUMENT

		element.getElementsByTagName("name").item(0).setTextContent(userNameNewUpdate);
		element.getElementsByTagName("surname").item(0).setTextContent(surnameNewUpdate);
		element.getElementsByTagName("address").item(0).setTextContent(addressNewUpdate);
		element.getElementsByTagName("email").item(0).setTextContent(emailNewUpdate);
		element.getElementsByTagName("telephone").item(0).setTextContent(telephoneNewUpdate);
		element.getElementsByTagName("language").item(0).setTextContent(languagesNewUpdate);
		element.getElementsByTagName("password").item(0).setTextContent(passwordNewUpdate);
		if (genderNewUpdate != "" && nationalityNewUpdate != "") {
			element.getElementsByTagName("gender").item(0).getAttributes().item(0).setTextContent(genderNewUpdate);
			element.getElementsByTagName("nationality").item(0).setTextContent(nationalityNewUpdate);
		}

		DOMSource source = new DOMSource(docUsers);
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		StreamResult result = new StreamResult(Paths.users_xml_path);
		transformerFactory.newTransformer().transform(source, result);

	}

	public String[] createChat(String input, String idUser) throws XPathExpressionException {

		Document doc = XMLReadWrite.documentFromString(input);
		NodeList createProfessorChat = doc.getElementsByTagName("createProfessorChat");

		Element element = (Element) createProfessorChat.item(0);

		String type = element.getElementsByTagName("type").item(0).getAttributes().item(0).getTextContent();
		String uc_id = element.getElementsByTagName("uc").item(0).getAttributes().item(0).getTextContent();
		String turma_id = element.getElementsByTagName("turma").item(0).getAttributes().item(0).getTextContent();

		XPath xpath = XPathFactory.newInstance().newXPath();
		String expression = null;
		
		String[] arr  = new String[3];
		
		arr[0] = uc_id;
		arr[1] = turma_id;
		arr[2] = type;
		
		

		if (type.equals("0")) {

			expression = "//ucs/uc[@id='" + uc_id + "']/turma[@turma_id='" + turma_id + "']/membros/membro";
		}
		
		if (type.equals("1")) {
			
			expression = "//ucs/uc[@id='"+uc_id+"']/turma/membros/membro";

		}
		

		NodeList nodes = (NodeList) xpath.evaluate(expression, docGroups, XPathConstants.NODESET);
		ArrayList<String>userIds = new ArrayList<String>();
		for (int i = 0; i < nodes.getLength(); i++) {

			Element membroS = (Element) nodes.item(i);
			String membroId = membroS.getAttribute("membro_id");
			userIds.add(membroId);
			// System.out.println("XD"+membroId);

		}
		
		
		if (type.equals("0")) {
 

		NodeList chats = (NodeList) chatsTurma.getElementsByTagName("chats");
		Element chat = chatsTurma.createElement("chat");
		chats.item(0).appendChild(chat);
		chat.setAttribute("id_turma", turma_id);
		chat.setAttribute("id_uc", uc_id);

		 Element admin = chatsTurma.createElement("admin");
		 admin.setAttribute("id_admin", String.valueOf(idUser));
		 chat.appendChild(admin);
		
		
		  Element studentsAdd = chatsTurma.createElement("users");
		 
		  for (String id : userIds) {
		  
			  Element student_i = chatsTurma.createElement("user");
			  student_i.setAttribute("id", id);
			  studentsAdd.appendChild(student_i);
		  
		  }
		  
		  Element mensagens = chatsTurma.createElement("mensagens");
		  chat.appendChild(mensagens);
		  
		chat.appendChild(studentsAdd);
		
		
		}
		
		if (type.equals("1")) {
			 

		NodeList chats = (NodeList) chatsUc.getElementsByTagName("chats");
		Element chat = chatsUc.createElement("chat");
		chats.item(0).appendChild(chat);
		chat.setAttribute("id_uc", uc_id);

		 Element admin = chatsUc.createElement("admin");
		 admin.setAttribute("id", String.valueOf(idUser));
		 chat.appendChild(admin);
		
		
		  Element studentsAdd = chatsUc.createElement("users");
		 
		  for (String id : userIds) {
		  
			  Element student_i = chatsUc.createElement("user");
			  student_i.setAttribute("id", id);
			  studentsAdd.appendChild(student_i);
		  
		  }
		  Element mensagens = chatsUc.createElement("mensagens");
		  chat.appendChild(mensagens);
		chat.appendChild(studentsAdd);
		}
		

		
		
		DOMSource source = null;
		StreamResult result = null;
		TransformerFactory transformerFactory = null;
		
		if (type.equals("0")) {
			
			source = new DOMSource(chatsTurma);
			 transformerFactory = TransformerFactory.newInstance();
			 result = new StreamResult(Paths.chats_turma_xml_path);
		}
		
		if (type.equals("1")) {
			
			source = new DOMSource(chatsUc);
			 transformerFactory = TransformerFactory.newInstance();
			 result = new StreamResult(Paths.chats_uc_xml_path);
		}


		
		try {
			transformerFactory.newTransformer().transform(source, result);
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr;

		// String m1 =
		// element.getElementsByTagName("membro_id").item(0).getAttributes().item(0).getTextContent();

		// System.out.println("id"+m1);

	}

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException,
			TransformerConfigurationException, TransformerException, XPathExpressionException {
		SerializeObjectsXml d = new SerializeObjectsXml();

		// String xd =
		// "<createStudentChat><Groupname>caralhos</Groupname><students><student
		// id='2'/><student id='3'/></students></createStudentChat>";
		// d.addGroupToGroupUsers(xd,1);
		String xd = "<createProfessorChat><type type = '0'/> <uc uc_id='2'></uc> <turma turma_id ='1'/> </createProfessorChat>";
		d.createChat(xd,"1");

	}

}
