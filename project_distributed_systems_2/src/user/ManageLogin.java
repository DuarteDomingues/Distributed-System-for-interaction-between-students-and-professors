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

@WebServlet("/ManageLogin")
public class ManageLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageLogin() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// response.sendRedirect(request.getContextPath() + "/userProfile.jsp");

		// getServletContext().getRequestDispatcher("/userProfile.jsp").forward(request,
		// response);

		// ler username e password do form
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		HttpSession session = request.getSession(false);
		
		if (session == null) {
			session = request.getSession();

		}

		// VALIDAR USER E PASSWORD

		System.out.println(username);
		System.out.println(password);
		ClientXml clientXml = new ClientXml();

		
		if (session.getAttribute("isConnected") ==null) {
		
		Client client = new Client(username);
		ServerData.addClient(client);
		String loginMessage = clientXml.createLoginMessage(username, password);
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		client.sendXMLToServer(loginMessage);
		
		session.setAttribute("isConnected", username);
		
		}
		
		
		else if (session.getAttribute("isConnected") !=null){
			

			
			ServerData.getClientByName((String) session.getAttribute("isConnected")).setClientName(username);
			String loginMessage = clientXml.createLoginMessage(username, password);
			ServerData.getClientByName(username).sendXMLToServer(loginMessage);
			
			session.setAttribute("isConnected", username);

			
		}
		
		

		try {
			for (;;) {
				Thread.sleep(100);

				if (ServerData.getClientByName(username).getResponseReceived() != null) {
					

					if (ServerData.getClientByName(username).getResponseReceived().startsWith("<user")) {
						
						session.setAttribute("username", username);
						session.setAttribute("userInfo", ServerData.getClientByName(username).getResponseReceived());

						ServerData.getClientByName(username).resetResponseReceived();

						//response.sendRedirect(request.getContextPath() + "/userProfile.jsp");
						
						request.getRequestDispatcher( "/userProfile.jsp").forward(request, response);

						// getServletContext().getRequestDispatcher(request.getContextPath() +
						// "/userProfile.jsp").forward(request, response);

						break;

					}

					else if (ServerData.getClientByName(username).getResponseReceived()
							.startsWith(Expressions.incorrect)) {
						
						ServerData.getClientByName(username).resetResponseReceived();

			
						//response.sendRedirect(request.getContextPath() + "/Login.jsp");
						session.setAttribute("loginAttempt", true);
						request.getRequestDispatcher("/Login.jsp").forward(request, response);

						break;
					}

				}

			}

		} 
		
		catch(Exception e) {
			  e.printStackTrace();
			}
		
	
		
		//catch (InterruptedException e) {
		//	e.printStackTrace();
	//	}

		
	}

}
