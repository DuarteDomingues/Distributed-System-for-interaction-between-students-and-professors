package create_groups;

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

@WebServlet("/ManageCreateButton")
public class ManageCreateSubmit extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageCreateSubmit() {
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
		String userId = (String) session.getAttribute("userId");
		String chatName = request.getParameter("chatname");

		HashMap<String, String> users = ServerData.getUsers();
		
		ClientXml clientXml = new ClientXml();

		ArrayList<String> usersId = new ArrayList<String>();
		
		usersId.add(userId);

		Iterator hmIterator = users.entrySet().iterator();

		while (hmIterator.hasNext()) {
			Map.Entry mapElement = (Map.Entry) hmIterator.next();
			String ind = (String) mapElement.getKey();
			String key = request.getParameter(ind);

			if (key != null) {

				usersId.add(key);
			}

		}

		// String
		String req = clientXml.createAddStudentGroupMessage(usersId,chatName);
		
		ServerData.getClientByName(userName).sendXMLToServer(req);
		
		//request.getRequestDispatcher( "/userProfile.jsp").forward(request, response);
		
		
		try {
			for (;;) {
				Thread.sleep(100);
				
				if (ServerData.getClientByName(userName).getResponseReceived() != null) {
					
					if (ServerData.getClientByName(userName).getResponseReceived().startsWith("<user")) {

						session.setAttribute("username", userName);
						session.setAttribute("userInfo", ServerData.getClientByName(userName).getResponseReceived());

						ServerData.getClientByName(userName).resetResponseReceived();

						//response.sendRedirect(request.getContextPath() + "/userProfile.jsp");
						
						request.getRequestDispatcher( "/userProfile.jsp").forward(request, response);

						// getServletContext().getRequestDispatcher(request.getContextPath() +
						// "/userProfile.jsp").forward(request, response);

						break;

					}

					
					
				}
				
			}
		}
		
		catch(Exception e) {
			  e.printStackTrace();
			}
		

		
		
		

	}

}
