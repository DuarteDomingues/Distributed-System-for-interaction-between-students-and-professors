package groups;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xml_read_write.XMLReadWrite;

public class ProcessChangeTurma {
	
	
	private ArrayList<String> members;
	private HashMap<String,String> students;
	
	public ProcessChangeTurma() {
		
		members = new ArrayList<String>();
		students = new HashMap<String,String>();
	}
	
	
	public void processInput(String input) {
		
		Document docInput = XMLReadWrite.documentFromString(input);
		NodeList membros = docInput.getElementsByTagName("membro");
		
		for (int i=0; i < membros.getLength(); i++) {
			
			
			Element membro_i = (Element) membros.item(i);
			String membroId = membro_i.getTextContent();
			
			members.add(membroId);
			
		}
		
		NodeList users = docInput.getElementsByTagName("user");
		
		for ( int i =0; i < users.getLength(); i++) {
			
			
			Element user_i = (Element) users.item(i);
			
			String userId = user_i.getAttribute("id");
			
			String name_i =  user_i.getElementsByTagName("name").item(0).getTextContent();
			
			students.put(userId, name_i);
		}
		
		
		
	}
	
	
	
	
	
	
	
	
	public ArrayList<String> getMembers() {
		return members;
	}


	public void setMembers(ArrayList<String> members) {
		this.members = members;
	}


	public HashMap<String, String> getStudents() {
		return students;
	}


	public void setStudents(HashMap<String, String> students) {
		this.students = students;
	}


	public static void main(String[] args) {
		
		ProcessChangeTurma p = new ProcessChangeTurma();
		String xd = "<responseChangeTurma><membros><membro>1</membro><membro>2</membro></membros><sendAlunos><user id='2'> <name>tavora</name></user><user id='3'> <name>Boi</name></user></sendAlunos></responseChangeTurma>\r\n"
				+ "";
		
		p.processInput(xd);
		
		
		
		
	}
	
	
	
	
	
}
