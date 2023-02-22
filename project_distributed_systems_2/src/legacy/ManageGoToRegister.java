package legacy;

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

@WebServlet("/ManageGoToRegister")
public class ManageGoToRegister extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public ManageGoToRegister() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession(false);

		if (session == null) {
			session = request.getSession();

		}

		ClientXml clientXml = new ClientXml();

		String req = clientXml.requestTurmas();

		String username = "default";
		if (session.getAttribute("isConnected") == null) {

			System.out.println("HELLO1");

			Client client = new Client(username);
			ServerData.addClient(client);
			ServerData.getClientByName(username).sendXMLToServer(req);

			session.setAttribute("isConnected", username);

		}

		else if (session.getAttribute("isConnected") != null) {

			System.out.println("HELLO2");

			ServerData.getClientByName((String) session.getAttribute("isConnected")).setClientName(username);

			ServerData.getClientByName(username).sendXMLToServer(req);
			session.setAttribute("isConnected", username);

		}

		try {
			for (;;) {
				Thread.sleep(100);

				if (ServerData.getClientByName(username).getResponseReceived() != null) {

					if (ServerData.getClientByName(username).getResponseReceived().startsWith(Expressions.registerResponse)) {

						
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