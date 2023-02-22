package manage_groups;

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

@WebServlet("/ChangeGroups")
public class ManageGroups extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageGroups() {
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
		
		String req = clientXml.createUserCreateGroup();
		
		//ServerData.getClientByName(userName).sendXMLToServer(req);
		
		request.getRequestDispatcher( "/manage_groups.jsp").forward(request, response);

				

	
	}

}
