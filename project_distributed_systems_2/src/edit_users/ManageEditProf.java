package edit_users;

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

@WebServlet("/EditUser")
public class ManageEditProf extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageEditProf() {
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
		String userId = "";
				
		
		String name = "";
		String surname = "";
		String address = "";
		String email = "";
		String telephone = "";
		String language = "";
		String password = "";
		String nationality = "";
		String gender = "";
		
		
		
		if (session.getAttribute("idOtherUser")!=null) {
						
			String userCurr = (String) session.getAttribute("userId");
			String otherUser = (String)  session.getAttribute("idOtherUser");
			
			
			if (!userCurr.equals(otherUser)) {
				
				
				userId = (String) session.getAttribute("idOtherUser");
				
				System.out.println("cona1");
				
				name = request.getParameter("username1");
				 surname = request.getParameter("surname1");
				 address = request.getParameter("address1");
				 email = request.getParameter("email1");
				 telephone = request.getParameter("telephone1");
				 language = request.getParameter("language1");
				 password = request.getParameter("password1");
				 nationality = request.getParameter("country1");
				 gender = "";
				
				
				
				
				
				
				
			}
			else {
				System.out.println("cona2");

				userId = (String) session.getAttribute("userId");
				
				 name = request.getParameter("username");
				 surname = request.getParameter("surname");
				 address = request.getParameter("address");
				 email = request.getParameter("email");
				 telephone = request.getParameter("telephone");
				 language = request.getParameter("language");
				 password = request.getParameter("password");
				 nationality = request.getParameter("country");
				 gender = "";
				 
			}
		}
		else if (session.getAttribute("idOtherUser")==null) {
			
			System.out.println("cona3");

					
		userId = (String) session.getAttribute("userId");
		
		 name = request.getParameter("username");
		 surname = request.getParameter("surname");
		 address = request.getParameter("address");
		 email = request.getParameter("email");
		 telephone = request.getParameter("telephone");
		 language = request.getParameter("language");
		 password = request.getParameter("password");
		 nationality = request.getParameter("country");
		 gender = "";
		 
		}
		
	
		
		/*		
		if (name.equals("")) {
			
			name = ServerData.getArrUserInfo()[0];
		}
		
		if (surname.equals("")) {
			surname = ServerData.getArrUserInfo()[1];
		}
		
		if (address.equals("")) {
			address = ServerData.getArrUserInfo()[2];
		}
		
		if (email.equals("")) {
			email = ServerData.getArrUserInfo()[5];
		}
		
		if (telephone.equals("")) {
			telephone = ServerData.getArrUserInfo()[6];
		}
		
		if (language.equals("")) {
			language = ServerData.getArrUserInfo()[8];
		}
		
		if (password.equals("")) {
			
			password = ServerData.getPassword();
		}
		
		if (nationality == null) {
			
			nationality = ServerData.getArrUserInfo()[3];
		}
		*/
		
		String req = clientXml.createEditUserMessage(name, surname, address, email, telephone, language, password,
				nationality, gender, userId);
		System.out.println(req);
		
		
		ServerData.getClientByName(userName).sendXMLToServer(req);

		try {
			for (;;) {
				Thread.sleep(100);
				if (ServerData.getClientByName(userName).getResponseReceived() != null) {

					if (ServerData.getClientByName(userName).getResponseReceived()
							.startsWith(Expressions.userInfo)) {
						
						
						session.setAttribute("userInfo", ServerData.getClientByName(userName).getResponseReceived());
						
						
						ServerData.getClientByName(userName).resetResponseReceived();

						response.sendRedirect(request.getContextPath() + "/userProfile.jsp");

						break;
						

					}

				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
