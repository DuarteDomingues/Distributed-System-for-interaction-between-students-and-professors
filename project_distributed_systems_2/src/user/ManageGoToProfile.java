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
import user.ClientXml;
import util.Expressions;

@WebServlet("/ManageGoToProfile")
public class ManageGoToProfile extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageGoToProfile() {
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
		
		String req = "<reqInfoUser></reqInfoUser>";
		
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
