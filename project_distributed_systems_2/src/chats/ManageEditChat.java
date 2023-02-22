package chats;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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

@WebServlet("/ManageEditUserChat")
public class ManageEditChat extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageEditChat() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.sendRedirect(request.getContextPath() + "/userProfile.jsp");

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession();
		}

		String userName = (String) session.getAttribute("username");

		String inputHidden = request.getParameter("id");
		String chatType = request.getParameter("type");

		ClientXml clientXml = new ClientXml();

		String chatName = request.getParameter("chatname");
		String idChat = request.getParameter("id_chat");

		ArrayList<String> users = ServerData.getUsersInChat();
		
		ArrayList<String> usersNames = new ArrayList<String>();

		
		for (int i =0; i <users.size();i++) {
			
			String val = users.get(i);
			String key = request.getParameter(val);

			if (key != null) {
				
				System.out.println(key);
				usersNames.add(key);
			}

		}
		
		String req = clientXml.requestEditChatUser(idChat,chatName, usersNames);
		
		ServerData.getClientByName(userName).sendXMLToServer(req);
		
		try {
			for (;;) {
				Thread.sleep(100);
				
				if (ServerData.getClientByName(userName).getResponseReceived() != null) {
					
					if (ServerData.getClientByName(userName).getResponseReceived().startsWith("<user")){
						
						
						session.setAttribute("userInfo", ServerData.getClientByName(userName).getResponseReceived());

						ServerData.getClientByName(userName).resetResponseReceived();

						//response.sendRedirect(request.getContextPath() + "/userProfile.jsp");
						
						request.getRequestDispatcher( "/userProfile.jsp").forward(request, response);
						
						break;
						
					}
					

					
				}

			}
		} 
		
		catch (Exception e) {
			e.printStackTrace();
		}

		
		
		
		
		

	}

}
