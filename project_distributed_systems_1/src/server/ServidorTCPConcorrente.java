package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import server.xml.XmlDocument;

public class ServidorTCPConcorrente {
	
	public final static int DEFAULT_PORT = 5025; // porto onde vai ficar á espera
	private ServerSocket  serverSocket;
	private ServerData data;
	private XmlDocument doc;
	
	public ServidorTCPConcorrente() {
		
		this.serverSocket = null;
	
		this.data = new ServerData();
		try {
			this.doc = new XmlDocument();
		} catch (ParserConfigurationException | SAXException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		createSocketAndThread();
		
	}
	
	
	private void createSocketAndThread() {
		
		try {
			
			serverSocket = new ServerSocket(DEFAULT_PORT);
			Socket newSocket = null;

			for (;;) {
				System.out
						.println("Servidor TCP concorrente aguarda ligacao no porto "
								+ DEFAULT_PORT + "...");

				// Espera connect do cliente
				newSocket = serverSocket.accept();

				Thread th = new HandleConnectionThread(newSocket,this.data, this.doc);
				th.start();
			}
		} catch (IOException e) {
			System.err.println("Excepção no servidor: " + e);
		}
	}
}