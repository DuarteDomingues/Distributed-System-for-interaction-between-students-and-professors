package chats;

import java.io.IOException;

import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import server_data.ServerData;
import user.ClientXml;
import util.Expressions;

@WebServlet("/ManageChatInd")
public class ManageChatInd extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageChatInd() {
		super();
	
							
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
	            throws IOException, ServletException {
	        
		  System.out.println("cona e boa");

	}
}
