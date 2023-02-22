package search;

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
import user.Client;
import user.ClientXml;
import util.Expressions;

@WebServlet("/ManageOtherUser")
public class ManageOtherUserProfile extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageOtherUserProfile() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession();

		}

		ClientXml clientXml = new ClientXml();
		String userName = (String) session.getAttribute("username");
		String userId = (String) session.getAttribute("userId");

		String otherId = request.getParameter("id");

		String req = clientXml.requestOtherUser(otherId);

		System.out.println(req);

		ServerData.getClientByName(userName).sendXMLToServer(req);

		try {
			for (;;) {
				Thread.sleep(100);

				if (ServerData.getClientByName(userName).getResponseReceived() != null) {
					
					if (ServerData.getClientByName(userName).getResponseReceived().startsWith(Expressions.replyOtherUser)){
						
					

						
						session.setAttribute("userInfo", ServerData.getClientByName(userName).getResponseReceived());
						ServerData.getClientByName(userName).resetResponseReceived();

						request.getRequestDispatcher( "/otherUserProfile.jsp").forward(request, response);
						
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