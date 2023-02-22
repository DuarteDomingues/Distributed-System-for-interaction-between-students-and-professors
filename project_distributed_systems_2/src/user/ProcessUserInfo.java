package user;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xml_read_write.XMLReadWrite;

public class ProcessUserInfo {

	private String[] arrInfo;
	private String userId;
	private ArrayList<String> chatIds;
	private ArrayList<String> chatNames;
	private HashMap<String, Boolean> activeUcChats;
	private HashMap<String, Boolean> activeTurmaChats;
	private String password;

	public void processUserInfoMessage(String userInfo) {

		arrInfo = new String[10];

		Document doc = XMLReadWrite.documentFromString(userInfo);

		NodeList userNodes = doc.getElementsByTagName("user");

		Element element = (Element) userNodes.item(0);

		String idName = element.getAttribute("id");

		NodeList chatsIds = (NodeList) doc.getElementsByTagName("chat");

		chatNames = new ArrayList<String>();
		chatIds = new ArrayList<String>();

		if (chatsIds.getLength() != 0 && chatsIds != null) {
			for (int i = 0; i < chatsIds.getLength(); i++) {

				Element elm = (Element) chatsIds.item(i);
				String chatI = elm.getAttribute("chat_id");
				chatIds.add(chatI);
				String chatName = null;
				if (!(chatI.equals("0"))) {
					chatName = elm.getElementsByTagName("group_name").item(0).getTextContent();
				}

				chatNames.add(chatName);
			}

		}

		this.userId = idName;
		password = element.getElementsByTagName("password").item(0).getTextContent();

		arrInfo[0] = element.getElementsByTagName("name").item(0).getTextContent();
		arrInfo[1] = element.getElementsByTagName("surname").item(0).getTextContent();
		arrInfo[2] = element.getElementsByTagName("address").item(0).getTextContent();
		arrInfo[3] = element.getElementsByTagName("nationality").item(0).getTextContent();
		arrInfo[4] = element.getElementsByTagName("number").item(0).getTextContent();
		arrInfo[5] = element.getElementsByTagName("email").item(0).getTextContent();
		arrInfo[6] = element.getElementsByTagName("telephone").item(0).getTextContent();
		arrInfo[7] = element.getElementsByTagName("type").item(0).getAttributes().item(0).getTextContent();
		arrInfo[8] = element.getElementsByTagName("language").item(0).getAttributes().item(0).getTextContent();
		arrInfo[9] = element.getElementsByTagName("gender").item(0).getAttributes().item(0).getTextContent();

	}
	
	
	
	public HashMap<String, String> processUcInfo(String info) {

		Document doc = XMLReadWrite.documentFromString(info);

		HashMap<String, String> ucDic = new HashMap<String, String>();

		NodeList ucInfo = doc.getElementsByTagName("uc");

		for (int i = 0; i < ucInfo.getLength(); i++) {

			Element element = (Element) ucInfo.item(i);

			String uc_id_i = element.getAttribute("id");
			String uc_name_i = element.getElementsByTagName("name").item(0).getTextContent();

			ucDic.put(uc_id_i, uc_name_i);

		}
		// this.userPanel.setUcInfo(ucDic);

		return ucDic;

	}

	public HashMap<String, String> processTurmaInfo(String info) {

		Document doc = XMLReadWrite.documentFromString(info);

		HashMap<String, String> turmaDic = new HashMap<String, String>();

		NodeList ucInfo = doc.getElementsByTagName("uc");

		for (int i = 0; i < ucInfo.getLength(); i++) {

			Element element = (Element) ucInfo.item(i);

			NodeList turmas = element.getElementsByTagName("turma");

			String uc_id_i = element.getAttribute("id");

			for (int j = 0; j < turmas.getLength(); j++) {

				Element turma_j = (Element) turmas.item(j);

				String turmaNome = turma_j.getElementsByTagName("nome").item(0).getTextContent();

				String turmaId = turma_j.getAttribute("turma_id");

				turmaDic.put(turmaId + "_" + uc_id_i, turmaNome);

			}

		}
		// this.userPanel.setTurmaInfo(turmaDic);
		return turmaDic;

	}

	public void checkTurmasUcsWithChats(String info) {

		this.activeUcChats = new HashMap<String, Boolean>();
		this.activeTurmaChats = new HashMap<String, Boolean>();

		Document doc = XMLReadWrite.documentFromString(info);

		NodeList ucInfo = doc.getElementsByTagName("uc");

		for (int i = 0; i < ucInfo.getLength(); i++) {

			Element element = (Element) ucInfo.item(i);

			String hasChat = element.getElementsByTagName("hasChat").item(0).getTextContent();
			NodeList turmas = element.getElementsByTagName("turma");
			String uc_id_i = element.getAttribute("id");

			if (hasChat.equals("true")) {

				this.activeUcChats.put(uc_id_i, true);
			} else {

				this.activeUcChats.put(uc_id_i, false);
			}

			for (int j = 0; j < turmas.getLength(); j++) {

				Element turma_j = (Element) turmas.item(j);
				String hasChatTurma = turma_j.getElementsByTagName("hasChat").item(0).getTextContent();

				String turmaId = turma_j.getAttribute("turma_id");

				if (hasChatTurma.equals("true")) {

					this.activeTurmaChats.put(turmaId, true);
				} else {

					this.activeTurmaChats.put(turmaId, false);
				}

			}

		}

	}

	public HashMap<String, Boolean> getActiveUcChats() {
		return activeUcChats;
	}

	public void setActiveUcChats(HashMap<String, Boolean> activeUcChats) {
		this.activeUcChats = activeUcChats;
	}

	public HashMap<String, Boolean> getActiveTurmaChats() {
		return activeTurmaChats;
	}

	public void setActiveTurmaChats(HashMap<String, Boolean> activeTurmaChats) {
		this.activeTurmaChats = activeTurmaChats;
	}

	public String[] getArrInfo() {
		return this.arrInfo;
	}

	public String getUserId() {
		return userId;
	}

	public ArrayList<String> getChatIds() {
		return chatIds;
	}

	public ArrayList<String> getChatNames() {
		return chatNames;
	}

	public String[] splitInfo(String info) {

		String[] arr = info.split("</checkChat>", 2);

		String str1 = arr[0];

		String str2 = arr[1];

		String str1Out = str1 + "</checkChat>" + " </messageGroups>";

		String str2Out = "<messageGroups>" + str2;

		String[] arrOut = new String[2];

		arrOut[0] = str1Out;
		arrOut[1] = str2Out;

		return arrOut;

	}
	
	public String getPassword() {
		return password;
	}

	public static void main(String[] args) {

		ProcessUserInfo p = new ProcessUserInfo();
		String info = "<messageGroups><checkChat><uc id = '1'><hasChat>true</hasChat><turma turma_id='2_1'><hasChat>false</hasChat></turma><turma turma_id='1_1'><hasChat>true</hasChat></turma></uc><uc id = '2'><hasChat>true</hasChat><turma turma_id='1_2'><hasChat>true</hasChat></turma></uc></checkChat><ucInfo><uc id='1'><name>Engenharia de XML</name><turma turma_id='2'><nome>21D</nome></turma><turma turma_id='1'><nome>23D</nome></turma></uc><uc id='2'><name>Interação Pessoa Maquina</name><turma turma_id='1'><nome>22D</nome></turma></uc></ucInfo></messageGroups>";

		String[] arr = info.split("</checkChat>", 2);

		String str1 = arr[0];

		String str2 = arr[1];

		String str1Out = str1 + "</checkChat>" + " </messageGroups>";

		String str2Out = "<messageGroups>" + str2;

		System.out.println(str2Out);

		p.checkTurmasUcsWithChats(str1Out);

		HashMap<String, String> turmaMap = p.processTurmaInfo(str2Out);
		HashMap<String, String> ucMap = p.processUcInfo(str2Out);

		for (String i : ucMap.keySet()) {

			if (p.getActiveUcChats().get(i)) {
				System.out.println("true");
			} else {
				System.out.println("false");
			}

		}

	}

}
