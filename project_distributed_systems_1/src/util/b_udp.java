package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import xml_read_write.XMLReadWrite;

public class b_udp {

	public void registarUdp(int DEFAULT_PORT, String DEFAULT_HOST, String num, String data, String resultado)
			throws ParserConfigurationException, IOException, ClassNotFoundException {

		 
	    // Obtém endereço do servidor 
        InetAddress hostAddress = InetAddress.getByName(DEFAULT_HOST);
		
        // Cria socket - UDP com um porto atribuído dinamicamente pelo sistema (anonymous port)
		 DatagramSocket socket = new DatagramSocket();
		 
		

		//Criar um teste
		
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc_teste = builder.newDocument();

		
		Element element = doc_teste.createElement("teste");
		doc_teste.appendChild(element);

		Element numUtente = doc_teste.createElement("numUtente");
		numUtente.appendChild(doc_teste.createTextNode("12"));

		Element dateTeste = doc_teste.createElement("dataTeste");
		dateTeste.appendChild(doc_teste.createTextNode("21-10-1999"));

		Element result = doc_teste.createElement("result");
		result.appendChild(doc_teste.createTextNode("0"));

		element.appendChild(numUtente);
		element.appendChild(dateTeste);
		element.appendChild(result);

			

		//criar mensagem do protocolo
		
		Document doc = builder.newDocument();

		Element register = doc.createElement("register");
		doc.appendChild(register);
		Element request = doc.createElement("request");
		Element reply = doc.createElement("reply");
		register.appendChild(request);
		register.appendChild(reply);
		// Element protocol = (Element) doc.getElementsByTagName("protocol").item(0);
		// protocol.appendChild(register);
		
		
		Element teste = doc_teste.getDocumentElement();
		Element clone = (Element) doc.importNode(teste, true);

		
	
		request.appendChild(clone);
		
		//doc.appendChild(register);
		
		
		//converter documento para String
		Writer out = new StringWriter();
		try {
			Transformer tf = TransformerFactory.newInstance().newTransformer();
			tf.setOutputProperty(OutputKeys.ENCODING, "ISO-8859-1"); // "UTF-8"
			tf.setOutputProperty(OutputKeys.INDENT, "yes");
			tf.transform(new DOMSource(doc), new StreamResult(out));
		} catch (Exception e) {
			System.out.println("Error: Unable to write XML to string!\n\t" + e);
			e.printStackTrace();
		}
		String docString =  out.toString();
		
		
		
		// Cria um datagrama
	
		DatagramPacket outputPacket = new DatagramPacket(docString.getBytes(), 
				docString.length(),
                hostAddress, DEFAULT_PORT);
		
		
		socket.send(outputPacket);
		

        // --- Recebe resposta ---

        // Criar datagrama de recepção
        byte[] buf = new byte[1024];
        DatagramPacket inputPacket = new DatagramPacket(buf, buf.length);

        // Espera pela recepção da resposta
        socket.receive(inputPacket);

        // Mostra Resposta
        String received = new String(inputPacket.getData(), 0, inputPacket.getLength());
        System.out.println("Dados recebidos: " + received);
        
   

        if (socket != null) socket.close();

	
	}

	public static void main(String[] args) throws ParserConfigurationException, ClassNotFoundException, IOException {
		Date d = new Date();

		teste_exame_2 t = new teste_exame_2();

		//t.registarUdp(1000, "xd", 2, 4, 3);
	}

}