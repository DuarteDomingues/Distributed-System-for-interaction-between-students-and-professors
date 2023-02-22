package chats;

import java.io.IOException;

import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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

@WebServlet("/ManageSendMessage")
public class ManageSendMessage extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageSendMessage() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.sendRedirect(request.getContextPath() + "/userProfile.jsp");

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession();
		}
		
		ClientXml clientXml = new ClientXml();
		String userName = (String) session.getAttribute("username");
		String userId = (String) session.getAttribute("userId");

		String inputHidden = request.getParameter("id");
		String chatType = request.getParameter("type");
		String texto = request.getParameter("commentText");
		String uc_id = request.getParameter("uc_id");
		String turma_id = "";
		String group_chat_id ="";
		
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();

		String time = dtf.format(now);
		
		
		int type = Integer.parseInt(chatType);
		
		if (type ==0) {
			
			turma_id = request.getParameter("turma_id");
		}
		else if (type ==2) {
			
			group_chat_id = request.getParameter("group_chat_id");
		}

		System.out.println(texto);
		System.out.println(type);
		System.out.println(time);
		System.out.println(uc_id);
		System.out.println(userId);
		System.out.println(turma_id);
		
		String req =null;
		if (type!=2) {
		
		 req = clientXml.createTextMessage( userId,  texto, time,  type,  uc_id,
				 turma_id);
		}
		else {
			 req = clientXml.createTextMessage( userId,  texto, time,  type,  group_chat_id,
					 "");
			
		}
		
		
		
		ServerData.getClientByName(userName).sendXMLToServer(req);
		
		
		int getChatType = (int) session.getAttribute("chatType");
		
		String send = "";
		
		
		if (getChatType ==0) {
			
			send =  clientXml.createRequestChatTurmaMessage(uc_id, turma_id);
			
		}
		
		else if (getChatType ==1) {
			
			send = clientXml.createRequestChatUcMessage(uc_id);

		}
		
		else if (getChatType ==2) {
			
			send = clientXml.createRequestChatGroupUsersMessage(group_chat_id);
		}
		
		try {
			Thread.sleep(800);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		ServerData.getClientByName(userName).sendXMLToServer(send);

		
		
		
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
