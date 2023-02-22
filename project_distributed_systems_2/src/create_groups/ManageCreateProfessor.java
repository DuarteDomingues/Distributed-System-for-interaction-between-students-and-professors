package create_groups;

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

@WebServlet("/ManageCreateProfessor")
public class ManageCreateProfessor extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageCreateProfessor() {
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
		ClientXml clientXml = new ClientXml();

		System.out.println("cona");

		String inputHidden = request.getParameter("id");
		String chatType = request.getParameter("type");

		String req = null;

		if (chatType.equals("uc_chat")) {

			// req = clientXml.createRequestChatUcMessage(inputHidden);

			// criar uc chat

			System.out.println("cona uc");

			req = clientXml.createUcTurmaGroup(1, inputHidden, "");
		}

		else if (chatType.equals("turma_chat")) {

			String[] arr = inputHidden.split("_", 2);

			String uc = arr[0];
			String turma = arr[1];

			// req = clientXml.createRequestChatTurmaMessage(uc, turma);

			// criar turma chat

			System.out.println("cona turma");

			req = clientXml.createUcTurmaGroup(0, uc, turma);

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

				}

			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}

	}

}
