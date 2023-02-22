package groups;

import java.io.IOException;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

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

@WebServlet("/ManageChangeClass")
public class ManageEditTurmas extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageEditTurmas() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession();
		}

		String userName = (String) session.getAttribute("username");
		String turmaId =  (String) session.getAttribute("editTurma");


		ClientXml clientXml = new ClientXml();
		
		
		ArrayList<String> members = ServerData.getDataMembers();
		HashMap<String,String> students = ServerData.getDataStudents();
		
		
		ArrayList<String> addStudents = new ArrayList<String>();
		ArrayList<String> removeStudents = new ArrayList<String>();
		
		 for (String i : students.keySet()) {
			 

			 String val = "a"+i;
			 
			 System.out.println(val);
			 String key = request.getParameter(val);
			 
			 if (key!=null) {
				 
				 addStudents.add(i);
			 }
	 
			 
		 }
		 
		 for (String i : students.keySet()) {
			 
			 
			 String val = "r"+i;
			 System.out.println(val);

			 String key = request.getParameter(val);
			 
			 if (key!=null) {

				 removeStudents.add(i);
			 }
			 
 
		 }

		String req = clientXml.requestChangeTurmaEdit(addStudents,removeStudents,turmaId);
				
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
