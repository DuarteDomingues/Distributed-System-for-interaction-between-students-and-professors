package client.xml;

import java.util.ArrayList;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import util.Expressions;
import xml_read_write.XMLReadWrite;

public class ClientXml {

	public ClientXml() {

	}

	public String createLoginMessage(String user, String password) {

		String loginStart = Expressions.login;
		String loginEnd = Expressions.loginEnd;

		String requestLogin = loginStart + " <username>" + user + "</username>" + " <password>" + password
				+ "</password>" + " " + loginEnd;
		return requestLogin;
	}

	public String createRequestChatTurmaMessage(String ucId, String turmaId) {

		String requestChatStart = Expressions.requestChatTurma;
		String requestChatEnd = Expressions.requestChatTurmaEnd;

		String chatReq = requestChatStart + "<uc uc_id='" + ucId + "'> <turma turma_id='" + turmaId + "'/> </uc>"
				+ requestChatEnd;
		return chatReq;

	}

	public String createRequestChatUcMessage(String ucId) {

		String requestChatStart = Expressions.requestChatUc;
		String requestChatEnd = Expressions.requestChatUcEnd;

		String chatReq = requestChatStart + "<uc uc_id='" + ucId + "'>  </uc>" + requestChatEnd;
		return chatReq;

	}
	
	public String createRequestChatGroupUsersMessage(String ucId) {

		String requestChatStart = Expressions.requestChatGroup;
		String requestChatEnd = Expressions.requestChatGroupEnd;

		String chatReq = requestChatStart + "<chat id='" + ucId + "'>  </chat>" + requestChatEnd;
		return chatReq;

	}
	
	
	

	public String goBackFromChatMessage() {

		return "<back> </back>";

	}

	public String createEditUserMessage(String name, String surname, String address, String email, String telephone,
			String language, String password, String nationality, String gender) {

		return Expressions.updateUserInfo + "<name>" + name + "</name>" + "<surname>" + surname + "</surname>"
				+ "<address>" + address + "</address>" + "<email>" + email + "</email>" + "<telephone>" + telephone
				+ "</telephone>" + "<language>" + language + "</language> <password>" + password + "</password>"
				+ "<gender>" + gender + "</gender>" +"<nationality>" + nationality + "</nationality>"
				+ Expressions.updateUserInfoEnd;

	}

	public String createUserCreateGroup() {

		return Expressions.alunoCreateGrupo + Expressions.alunoCreateGrupoEnd;
	}
	
	public String createUcTurmaGroup(int type, String uc_id, String turma_id) {
		
		return Expressions.professorCreateGroup + "<type type = '"+type+"'/> <uc uc_id='"+uc_id+"'></uc> <turma turma_id ='"+turma_id+"'/> "+Expressions.professorCreateGroupEnd+"";
	}
	
	
	

	public String createAddStudentGroupMessage(ArrayList<String> students, String groupName) {

		System.out.println("GROUP NAME" + groupName);

		String strInicial = Expressions.sendStudentCreateGroup   + "<Groupname>" + groupName + "</Groupname>"
				+ "<students>";

		for (int i = 0; i < students.size(); i++) {

			strInicial = strInicial + "<student id='" + students.get(i) + "'/>";

		}
		return strInicial + "</students>" + Expressions.sendStudentCreateGroupEnd + "";

	}

}
