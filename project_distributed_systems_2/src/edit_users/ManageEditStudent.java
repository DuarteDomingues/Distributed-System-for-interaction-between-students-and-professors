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

@WebServlet("/EditStudent")
public class ManageEditStudent extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageEditStudent() {
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

		String name = request.getParameter("username");
		String address = request.getParameter("address");
		String telephone = request.getParameter("telephone");
		
				
		if (name.equals("")) {
			
			name = ServerData.getArrUserInfo()[0];
		}
		
	
		
		if (address.equals("")) {
			address = ServerData.getArrUserInfo()[2];
		}
		
		
		
		if (telephone.equals("")) {
			telephone = ServerData.getArrUserInfo()[6];
		}
		

		String req = clientXml.createStudentEditUserMessage(name, address, telephone);

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
