package gui.user;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xml_read_write.XMLReadWrite;

public class ManageUserInfo {

	private UserPanel userPanel;
	private HashMap<String,Boolean> activeUcChats;
	private HashMap<String,Boolean> activeTurmaChats;
	private String userId;
	private String[] arrInfo ;
	private String password;
	private ArrayList<String> chatIds ;
	private ArrayList<String> chatNames;
	public ManageUserInfo( UserPanel panel) {

		this.userPanel = panel;
		this.activeTurmaChats = new HashMap<String,Boolean>();
		this.activeUcChats = new HashMap<String,Boolean>();

	}
	
	public String getUserId() {
		return this.userId;
	}
	
	public HashMap<String,Boolean> getActiveUcChats(){
		
		return this.activeUcChats;
	}
	
public HashMap<String,Boolean> getActiveTurmaChats(){
		
		return this.activeTurmaChats;
	}

	public void processUserInfoMessage(String userInfo) {

		 arrInfo = new String[10];

		Document doc = XMLReadWrite.documentFromString(userInfo);

		NodeList userNodes = doc.getElementsByTagName("user");

		Element element = (Element) userNodes.item(0);
		
		String idName = element.getAttribute("id");
		
		NodeList chatsIds = (NodeList) doc.getElementsByTagName("chat");
		
		chatNames = new ArrayList<String>();
	    chatIds = new ArrayList<String>();

		if (chatsIds.getLength()!= 0 && chatsIds!= null) {
			for (int i =0;i< chatsIds.getLength(); i++) {
				

				Element elm= (Element) chatsIds.item(i);
				String chatI = elm.getAttribute("chat_id");
				chatIds.add(chatI);
				String chatName=null;
				if (!(chatI.equals("0"))){
				 chatName = elm.getElementsByTagName("group_name").item(0).getTextContent();
				}
				
				chatNames.add(chatName);
			}
			
			
			
		}

		
		this.userId = idName;

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
		password = element.getElementsByTagName("password").item(0).getTextContent();

		this.userPanel.setUserInfo(arrInfo,chatIds,chatNames);
		this.userPanel.isProfessor(arrInfo[7]);
		

	}

	
	public ArrayList<String> getUserInfo() {
		
		ArrayList<String> arrLabels = new ArrayList<String>();
		
		arrLabels.add(0, arrInfo[0]);
		arrLabels.add(1, arrInfo[1]);
		arrLabels.add(2, arrInfo[2]);
		arrLabels.add(3 , arrInfo[5]);
		arrLabels.add(4, arrInfo[6]);
		arrLabels.add(5, arrInfo[8]);
		arrLabels.add(6, password);
				
		if (arrInfo[7].equals("prof")) {

			arrLabels.add(7, arrInfo[3]);
			arrLabels.add(8, arrInfo[9]);
			
		}

		return arrLabels;
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

		NodeList turmaInfo = doc.getElementsByTagName("turma");

		for (int i = 0; i < turmaInfo.getLength(); i++) {

			Element elementTurma = (Element) turmaInfo.item(i);

			String turma_id_i = elementTurma.getAttribute("turma_id");
			String turma_name_i = elementTurma.getElementsByTagName("nome").item(0).getTextContent();

			String uc_id = (String) processUcInfo(info).keySet().toArray()[i];

			turmaDic.put( turma_id_i+"_"+uc_id, turma_name_i);

			// System.out.println(uc_id+"_"+turma_id_i+"XDDDD");

		}
		// this.userPanel.setTurmaInfo(turmaDic);
		return turmaDic;

	}

	public void setUcAndTurmaInfo(HashMap<String, String> ucInfo, HashMap<String, String> turmaInfo, HashMap<String, Boolean> turmaChatInfo, HashMap<String, Boolean> ucChatInfo) {

		this.userPanel.setTurmaInfoUcInfo(ucInfo, turmaInfo, activeTurmaChats,activeUcChats);

	}

	

	public void checkTurmasUcsWithChats(String info) {

		Document doc = XMLReadWrite.documentFromString(info);

		NodeList userNodes = doc.getElementsByTagName("checkChat");

		NodeList nodes = (NodeList) userNodes.item(0);

		for (int i = 0; i < nodes.getLength(); i++) {

			Element element = (Element) nodes.item(i);
			String hasChat = (element.getTextContent());
			boolean chat = Boolean.parseBoolean(hasChat);
			
			String chatUcId = element.getAttribute("id");
			String chatTurmaId = element.getAttribute("turma_id");
			
			if (chatUcId!= null && chatUcId!= "" ) {
				
				this.activeUcChats.put(chatUcId, chat);
			}
			
			if (chatTurmaId!= null && chatTurmaId!= "") {
				
				this.activeTurmaChats.put(chatTurmaId+"", chat);

			}	

	

		}

	}
	

	public static void main(String[] args) {

		UserPanel u = new UserPanel();
		//ManageUserInfo m = new ManageUserInfo("XD", u);
		
		
		String xd = "131_2";
		
		String xd1[] = xd.split("_");
		
	
		
		//Recebi -> <sendChatTurma id_turma ='2' id_uc='1' id_admin=' 2'><users><user name='jones'/><user name='tavora'/></users><message> <membro id ='tavora'/> <texto>Mequie bro</texto> <date>14/05/2021</date></message></sendChatTurma>

	}

}
