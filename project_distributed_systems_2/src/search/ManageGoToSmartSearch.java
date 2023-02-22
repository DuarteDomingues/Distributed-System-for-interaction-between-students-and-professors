package search;

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
import user.Client;
import user.ClientXml;
import util.Expressions;

@WebServlet("/Smart_search")
public class ManageGoToSmartSearch extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageGoToSmartSearch() {
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
		
		HashMap<String, String> users = ServerData.getUsers();

		
		String input = request.getParameter("nameInput");
		
		session.setAttribute("userInput", input);
		
		response.sendRedirect(request.getContextPath() + "/searches.jsp");

		
		//redirect
		

	}
}