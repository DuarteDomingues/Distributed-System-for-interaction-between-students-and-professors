package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import gui.GuiHandler;
import util.Expressions;

public class ClientTCP {

	private final static String DEFAULT_HOST = "localhost"; // Máquina onde reside o servidor
	private final static int DEFAULT_PORT = 5025; // porto onde o servidor está á espera
	private Socket socket;
	private BufferedReader is = null;
	private PrintWriter os = null;
	private ClientData data;
	public static String sendXmlString = "";
	private GuiHandler guiHandler;

	public ClientTCP() {

		data = new ClientData();
		createFrame();
		createSocket();
	}

	private void createSocket() {

		try {
			socket = new Socket(DEFAULT_HOST, DEFAULT_PORT);

			// Mostrar os parametros da ligação
			// System.out.println("Ligação: " + socket);

			// Stream para escrita no socket
			os = new PrintWriter(socket.getOutputStream(), true);

			// Stream para leitura do socket
			is = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			// Escreve no socket
			// os.println("Olá mundo!!!");

			// Mostrar o que se recebe do socket
			// System.out.println("Recebi -> " + is.readLine());
			createSocketWithThread();
			String inputServer;

			for (;;) {

				if ((inputServer = is.readLine()) != null) {
					System.out.println("Recebi -> " + inputServer);

					if (inputServer.startsWith(Expressions.userInfo)) {
						guiHandler.updateFrameToUserInfo(inputServer);
					}

					else if (inputServer.startsWith(Expressions.checkChats)) {

						guiHandler.updateActiveChats(inputServer);
					}

					else if (inputServer.startsWith(Expressions.turmaInfo)) {

						guiHandler.updateFrameTurmas(inputServer);

					}

					else if (inputServer.startsWith(Expressions.sendClassChat)) {

						guiHandler.updateFrameChatTurma(inputServer, 0);
						System.out.println("here");
					}
					
					else if (inputServer.startsWith(Expressions.sendUcChat)) {

						guiHandler.updateFrameChatTurma(inputServer, 1);
					}
					else if (inputServer.startsWith(Expressions.sendGroupChat)) {
						guiHandler.updateFrameChatTurma(inputServer, 2);

						
					}
					
					
					else if (inputServer.startsWith(Expressions.sendTurmaMessageUser)) {

						guiHandler.addMessageToChat(inputServer);
					}
					
					else if (inputServer.startsWith(Expressions.sendUcMessageUser)) {
						
						
						guiHandler.addMessageToChat(inputServer);
					}
					
					else if (inputServer.startsWith(Expressions.sendGroupMessageUser)) {
						
						guiHandler.addMessageToChat(inputServer);
						
						
					}
	
					else if (inputServer.startsWith(Expressions.sendAlunos)) {
						
						guiHandler.showAllStudents(inputServer);
					}
				}

			}

		}

		catch (IOException e) {
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
							System.out.println(sendXmlString);
							os.println(sendXmlString);
							sendXmlString = "";
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

	private void createFrame() {

		this.guiHandler = new GuiHandler(data);
		this.guiHandler.showFrame();

	}

}
