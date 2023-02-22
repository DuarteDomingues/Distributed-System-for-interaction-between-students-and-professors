package user;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xml_read_write.XMLReadWrite;

public class ProcessOtherUser {

	private String[] arrInfo;
	private String userId;

	public void processUserInfoMessage(String userInfo) {

		arrInfo = new String[10];

		Document doc = XMLReadWrite.documentFromString(userInfo);

		NodeList userNodes = doc.getElementsByTagName("otherUser");

		Element element = (Element) userNodes.item(0);

		String idName = element.getAttribute("id");

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

	
	}

	public String[] getArrInfo() {
		return arrInfo;
	}

	public void setArrInfo(String[] arrInfo) {
		this.arrInfo = arrInfo;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

}
