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

@WebServlet("/ManageChat")
public class ManageChats extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageChats() {
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

		String req = null;

		if (chatType.equals("uc_chat")) {

			req = clientXml.createRequestChatUcMessage(inputHidden);

		}

		else if (chatType.equals("turma_chat")) {

			String[] arr = inputHidden.split("_", 2);

			String uc = arr[0];
			String turma = arr[1];

			req = clientXml.createRequestChatTurmaMessage(uc, turma);
		}
		
		
		else if (chatType.equals("group_chat")) {
			
			
			req = clientXml.createRequestChatGroupUsersMessage(inputHidden);
			
		}
		
		
		
		
		ServerData.getClientByName(userName).sendXMLToServer(req);

		
		try {
			for (;;) {
				Thread.sleep(100);
				
				if (ServerData.getClientByName(userName).getResponseReceived() != null) {

					if (ServerData.getClientByName(userName).getResponseReceived().startsWith(Expressions.sendClassChat)){
						
						
						System.out.println(ServerData.getClientByName(userName).getResponseReceived());
						
						
						session.setAttribute("userInfo", ServerData.getClientByName(userName).getResponseReceived());
						session.setAttribute("chatType", 0);
						
						//
						ServerData.getClientByName(userName).resetResponseReceived();
						
						request.getRequestDispatcher( "/chat.jsp").forward(request, response);
						
						break;
	
					}
					
				
					
					
					else if (ServerData.getClientByName(userName).getResponseReceived().startsWith(Expressions.sendUcChat)){
						
						System.out.println(ServerData.getClientByName(userName).getResponseReceived());
						
						//
						session.setAttribute("userInfo", ServerData.getClientByName(userName).getResponseReceived());
						session.setAttribute("chatType", 1);
						
						
						ServerData.getClientByName(userName).resetResponseReceived();
						
						request.getRequestDispatcher( "/chat.jsp").forward(request, response);

						break;
						
						
					}
					
					
					else if (ServerData.getClientByName(userName).getResponseReceived().startsWith(Expressions.sendGroupChat)){
						
						System.out.println(ServerData.getClientByName(userName).getResponseReceived());
						
						//
						session.setAttribute("userInfo", ServerData.getClientByName(userName).getResponseReceived());
						session.setAttribute("chatType", 2);
						
						
						ServerData.getClientByName(userName).resetResponseReceived();
						
						request.getRequestDispatcher( "/chat.jsp").forward(request, response);

						break;
						
						
					}
					
					
					
					
					
					

					
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
