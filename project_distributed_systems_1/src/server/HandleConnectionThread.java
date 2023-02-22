package server;

import java.io.BufferedReader;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;

import org.xml.sax.SAXException;

import server.data_info.ClassChat;
import server.data_info.Message;
import server.data_info.User;
import server.xml.SerializeObjectsXml;
import server.xml.XmlDocument;
import util.Expressions;

class HandleConnectionThread extends Thread {

	private Socket connection;
	private ServerData data;
	private XmlDocument xmlDoc;
	private int userId;
	private User user;
	private SerializeObjectsXml serObjectsXml;

	public HandleConnectionThread(Socket connection, ServerData data, XmlDocument doc) {
		this.connection = connection;
		this.data = data;
		this.xmlDoc = doc;
		try {
			this.serObjectsXml = new SerializeObjectsXml();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void run() {

		BufferedReader is = null;
		PrintWriter os = null;

		try {
			// circuito virtual estabelecido: socket cliente na variavel newSock
			System.out.println("Thread " + this.getId() + ", " + connection.getRemoteSocketAddress());

			is = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			os = new PrintWriter(connection.getOutputStream(), true);

			String inputLine;
			for (;;) {
				if ((inputLine = is.readLine()) != null) {

					System.out.println(inputLine);

					if (inputLine.startsWith(Expressions.login)) {
						
						

						int userId = xmlDoc.getUserId(inputLine);
						this.userId = userId;

						if (userId > 0) {

							os.println(xmlDoc.getUserInfo(userId));

							user = new User(userId, connection.getOutputStream(), os);

							data.getUsers().add(user);

							os.println(xmlDoc.getTurmasUcsWithChat(userId));
							Thread.sleep(10);
							os.println(xmlDoc.getTurmasUser(userId));
							Thread.sleep(10);
							// System.out.println(xmlDoc.getTurmasUser(userId));

						}

					}

					else if (inputLine.startsWith(Expressions.requestChatTurma)) {

						os.println("ChatTurmaPanel");

						// 0 -> uc id 1 -> turma id

						String[] turmaInfo = xmlDoc.processChatTurmaRequest(inputLine);
						ClassChat classChat = data.getClassChatByIds(turmaInfo[0], turmaInfo[1]);

						if (data.checkUserExistsInChat(userId, classChat) == false) {
							data.getClassChatByIds(turmaInfo[0], turmaInfo[1]).getUsersConnected().add(user);
						}
						os.println(xmlDoc.createClassChatSendMessage(classChat, 0));

					}

					else if (inputLine.startsWith(Expressions.requestChatUc)) {

						String ucInfo = xmlDoc.processChatUcRequest(inputLine);
						ClassChat ucChat = data.getUcChatByIds(ucInfo);
						if (data.checkUserExistsInChat(userId, ucChat) == false) {
							data.getUcChatByIds(ucInfo).getUsersConnected().add(user);
						}
						os.println(xmlDoc.createClassChatSendMessage(ucChat, 1));

					}

					else if (inputLine.startsWith(Expressions.requestChatGroup)) {

						// do stuff
						String id = xmlDoc.processChatGroupRequest(inputLine);

						ClassChat groupChat = data.getGroupStudentsChatByIds(id);
						if (data.checkUserExistsInChat(userId, groupChat) == false) {
							data.getGroupStudentsChatByIds(id).getUsersConnected().add(user);
						}
						os.println(xmlDoc.createClassChatSendMessage(groupChat, 2));

					}

					else if (inputLine.startsWith(Expressions.sendUcMessage)) {

						String result[] = xmlDoc.processSendMessageString(inputLine);

						String uc_id = result[0];
						String user_id = result[2];
						String texto = result[3];
						String date = result[4];

						ClassChat chat = data.getUcChatByIds(uc_id);

						String sendMessage = xmlDoc.sendMessageUcToChat(uc_id, user_id, texto, date);

						Message msg = new Message(texto, date, user_id);

						data.updateChatMessages(uc_id, "0", msg, 1);

						for (User u : chat.getUsersConnected()) {

							u.getOs().println(sendMessage);

						}

						try {
							serObjectsXml.addMessageToXmlChatTurma(uc_id, "", user_id, texto, date, 1);
						} catch (TransformerConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TransformerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					else if (inputLine.startsWith(Expressions.sendTurmaMessage)) {

						// 0 uc_id 1 turma_id 2 user_id 3 texto 4 data
						String result[] = xmlDoc.processSendMessageString(inputLine);

						String uc_id = result[0];
						String turma_id = result[1];
						String user_id = result[2];
						String texto = result[3];
						String date = result[4];

						ClassChat chat = data.getClassChatByIds(uc_id, turma_id);

						String sendMessage = xmlDoc.sendMessageToChat(uc_id, turma_id, user_id, texto, date);

						Message msg = new Message(texto, date, user_id);

						// data.getClassChatByIds(result[0],result[1]).getMessages().add(msg);

						data.updateChatMessages(uc_id, turma_id, msg, 0);

						for (User u : chat.getUsersConnected()) {

							u.getOs().println(sendMessage);

						}

						try {
							serObjectsXml.addMessageToXmlChatTurma(uc_id, turma_id, user_id, texto, date, 0);
						} catch (TransformerConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TransformerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					else if (inputLine.startsWith(Expressions.sendGroupChatMessage)) {

						String result[] = xmlDoc.processGroupStudentsMessage(inputLine);

						String group_id = result[0];
						String user_id = result[1];
						String texto = result[2];
						String date = result[3];

						ClassChat chat = data.getGroupStudentsChatByIds(group_id);

						String sendMessage = xmlDoc.sendMessageGroupChat(group_id, user_id, texto, date);

						// ADICIONAR NOVA MENSAGEM
						Message msg = new Message(texto, date, user_id);
						data.updateChatMessages(group_id, "0", msg, 2);

						for (User u : chat.getUsersConnected()) {

							u.getOs().println(sendMessage);

						}

						try {
							serObjectsXml.addMessageToXmlChatTurma(group_id, "0", user_id, texto, date, 2);
						} catch (TransformerConfigurationException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (TransformerException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}

					else if (inputLine.startsWith(Expressions.back)) {
						this.xmlDoc = null;
						this.xmlDoc = new XmlDocument();
						os.println(xmlDoc.getUserInfo(userId));
						os.println(xmlDoc.getTurmasUcsWithChat(userId));
						Thread.sleep(10);
						os.println(xmlDoc.getTurmasUser(userId));
						Thread.sleep(10);

					}

					else if (inputLine.startsWith(Expressions.updateUserInfo)) {

						serObjectsXml.updateUserInfo(inputLine, userId);

						Thread.sleep(100);
						this.xmlDoc = null;
						this.xmlDoc = new XmlDocument();
						os.println(xmlDoc.getUserInfo(userId));

						os.println(xmlDoc.getTurmasUcsWithChat(userId));
						os.println(xmlDoc.getTurmasUser(userId));

					}

					else if (inputLine.startsWith(Expressions.alunoCreateGrupo)) {

						os.println(xmlDoc.getListofAllStudents());

					} else if (inputLine.startsWith(Expressions.professorCreateGroup)) {

						System.out.println("PROFESSOR CREATE GROUP");
						System.out.println(inputLine);

						String[] turmaInfo = serObjectsXml.createChat(inputLine, String.valueOf(userId));

						if (turmaInfo[2].equals("0")) {
							data.getRecentChatsTurma();

							ClassChat classChat = data.getClassChatByIds(turmaInfo[0], turmaInfo[1]);

							if (data.checkUserExistsInChat(userId, classChat) == false) {
								data.getClassChatByIds(turmaInfo[0], turmaInfo[1]).getUsersConnected().add(user);
							}
							os.println(xmlDoc.createClassChatSendMessage(classChat, 0));

						}

						else if (turmaInfo[2].equals("1")) {
							this.data = null;
							data = new ServerData();
							//data.getRecentChatsUc();
							Thread.sleep(100);
							System.out.println("uc"+ turmaInfo[0]);
							ClassChat ucChat = data.getUcChatByIds(turmaInfo[0]);
							System.out.println("KAPPA"+ucChat.getUcId());
							
							
							
							if (data.checkUserExistsInChat(userId, ucChat) == false) {
								data.getUcChatByIds(turmaInfo[0]).getUsersConnected().add(user);
							}
							
							os.println(xmlDoc.createClassChatSendMessage(ucChat, 1));

						}

					}

					else if (inputLine.startsWith(Expressions.sendStudentCreateGroup)) {

						serObjectsXml.addGroupToGroupUsers(inputLine, userId);

					}

				}
			}

			// System.out.println("Recebi -> " + inputLine);

			// os.println("@" + inputLine.toUpperCase()); // converte para
			// maiusculas
		} catch (IOException e) {
			System.err.println("erro na ligaçao " + connection + ": " + e.getMessage());
		} catch (XPathExpressionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SAXException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

} // end HandleConnectionThread
