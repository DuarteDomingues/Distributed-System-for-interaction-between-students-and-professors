package util;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import xml_read_write.XMLReadWrite;

public class testes_exame {
	
	
	
	
	
	
	
	public static void main(String[] args) throws ParserConfigurationException {
		
		
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
        
        String xd =  XMLReadWrite.documentToString(doc_teste);
        
        System.out.println(xd);
        
       
	}

}
