package util;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.net.DatagramSocket;
import java.net.Socket;
import java.net.SocketException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

public class teste_exame_2 {

	public boolean registarTCP(int DEFAULT_PORT, String DEFAULT_HOST, String num, String data, String resultado)
			throws ParserConfigurationException, IOException, ClassNotFoundException {

		// Cria socket - TCP 
		// (anonymous port)
		//Socket socket = new Socket(DEFAULT_HOST, DEFAULT_PORT);

		//Criar um teste
		
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = dbf.newDocumentBuilder();
		Document doc_teste = builder.newDocument();
		
		
		
		//CONVERTER DATA PARA STRING
	    Date date = Calendar.getInstance().getTime();  
	    DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	    String strDate = dateFormat.format(date);  
		
	    //CONVERTER INT PARA STRING e boolean
	    String kappa = String.valueOf(false);
	    String kappa2 = String.valueOf(2);
		

		
		Element element = doc_teste.createElement("teste");
		doc_teste.appendChild(element);

		Element numUtente = doc_teste.createElement("numUtente");
		numUtente.appendChild(doc_teste.createTextNode(num));

		Element dateTeste = doc_teste.createElement("dataTest");
		dateTeste.appendChild(doc_teste.createTextNode(data));

		Element result = doc_teste.createElement("result");
		result.appendChild(doc_teste.createTextNode(resultado));

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
		
		Element proto = doc.createElement("protocol");
		proto.appendChild(register);
		doc.appendChild(proto);
		
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
		System.out.println( out.toString());
		
		
		//resposta
	
		/*
		try {
		
		//enviar request
		ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
		oos.writeObject(doc);
		
		//receber resposta
		Document replyDoc = builder.newDocument();
		ObjectInputStream iis = new ObjectInputStream(socket.getInputStream());
		replyDoc =  (Document) iis.readObject();
		} catch (ClassNotFoundException e) {
			return false;
		} catch (IOException e) {
			return false;
		}

		
		NodeList S = reply.getElementsByTagName("sucesso");
		return S.getLength()==1;
		
		
	*/
		return false;

		
	}

	public static void main(String[] args) throws ParserConfigurationException, ClassNotFoundException, IOException {
		Date d = new Date();

		teste_exame_2 t = new teste_exame_2();

		boolean fds = t.registarTCP(1000, "xd", "2", "4", "3");
	}

}