package groups;

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

@WebServlet("/ChangeTurmas")
public class ManageTurmas extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageTurmas() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession();
		}

		String userName = (String) session.getAttribute("username");

		ClientXml clientXml = new ClientXml();
		String inputHidden = request.getParameter("id");

		String[] arr = inputHidden.split("_", 2);

		String uc = arr[0];
		String turma = arr[1];
		
		session.setAttribute("editTurma", inputHidden);
		
		
		String sendReq = clientXml.requestChangeTurmaMessage(uc,turma);

		ServerData.getClientByName(userName).sendXMLToServer(sendReq);
		
		

		try {
			for (;;) {
				Thread.sleep(100);
				
				if (ServerData.getClientByName(userName).getResponseReceived() != null) {
					
					if (ServerData.getClientByName(userName).getResponseReceived().startsWith(Expressions.responseChangeTurma)){
						
						
						System.out.println(ServerData.getClientByName(userName).getResponseReceived());
						
						session.setAttribute("userInfo", ServerData.getClientByName(userName).getResponseReceived());
						
						ServerData.getClientByName(userName).resetResponseReceived();
						
						request.getRequestDispatcher( "/changeTurma.jsp").forward(request, response);

						//process input response and redirect page
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
