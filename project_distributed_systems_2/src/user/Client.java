package user;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import server_info.ServerInfo;

public class Client {

	private Socket socket;
	private BufferedReader is = null;
	private PrintWriter os = null;
	private String ClientName;
	public static String sendXmlString = "";
	
	
	private String responseReceived;


	public Client(String name) {
		createSocket();
		this.ClientName = name;
		
	}

	private void createSocket() {

		try {
			socket = new Socket(ServerInfo.DEFAULT_HOST, ServerInfo.DEFAULT_PORT);

			// Mostrar os parametros da ligação
			// System.out.println("Ligação: " + socket);

			// Stream para escrita no socket
			os = new PrintWriter(socket.getOutputStream(), true);

			// Stream para leitura do socket
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			
			
			createSocketWithThread() ;
			new Thread(new Runnable() {
				@Override
				public void run() {
					String inputLine;
					for (;;) {
						try {

							if ((inputLine = is.readLine()) != null) {
								System.out.println("Recebi P -> " + inputLine);
								
								
								responseReceived = inputLine;
								
							}
						
						} 
							catch (IOException e) {
							e.printStackTrace();
						}
					}
					
				
				}
			}).start();


		} catch (IOException e) {
			System.err.println("Erro na ligação " + e.getMessage());
		}

	}
	
	private void createSocketWithThread() {
		new Thread(new Runnable() {
			@Override
			public void run() {

				for (;;) {
					try {

						Thread.sleep(10);
						if (sendXmlString != "") {
							os.println(sendXmlString);
							sendXmlString = "";
						}
					} 
					
						catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				}
			
		}).start();
	}
	
	

	
	
	public String getClientName() {
		return this.ClientName;
	}
	
	
	public void setClientName(String name) {
		this.ClientName = name;
	}
	
	public void sendXMLToServer(String messageXML) {
		sendXmlString = messageXML;
	}
	
	public String getResponseReceived() {
		return this.responseReceived;
	}
	
	public void resetResponseReceived() {
		this.responseReceived = null;
	}

}
