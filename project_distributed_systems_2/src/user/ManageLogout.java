package user;

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
import util.Expressions;

@WebServlet("/ManageLogout")
public class ManageLogout extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageLogout() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession();

		}

		String userName = (String) session.getAttribute("username");

		ServerData.getClientByName(userName).sendXMLToServer("<logout></logout>");

		// TODO
		// NAO ESQUECER DE REMOVER OS OBJETOS ASSOCIADOS A DATA GUARDADA NO SERVIDOR QND
		// LOGOUT
		// remover cenas do serverData sobre o users
		// ServerData.logout(userName);

		session.removeAttribute("username");
		response.sendRedirect(request.getContextPath() + "/Login.jsp");

	}

}
