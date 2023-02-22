package data_info;

import java.io.OutputStream;
import java.io.PrintWriter;

public class User {
	
	private int idUser;
	private String name;
	private int type;
	private OutputStream userOs;
	private PrintWriter os;
	
	public User(int idUser, OutputStream userOs, PrintWriter os) {
		
		this.idUser= idUser;
		this.userOs = userOs;
		this.os =os;
		
		
	}
	
	public PrintWriter getOs() {
		return this.os;
	}
	
	public int getIdUser() {
		return idUser;
	}

	

	public void setName(String name) {
		this.name = name;
	}

	

	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
		
	}
	
	public OutputStream getOutPutStream() {
		return userOs;
	}
	
	
	

	

}
