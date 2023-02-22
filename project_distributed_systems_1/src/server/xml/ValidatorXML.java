package server.xml;

import java.io.File;

import java.io.IOException;

import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class ValidatorXML {
	
	
	
	public static final boolean validDoc(Document document, String xsdFileName, String type) throws SAXException {

		// create a SchemaFactory capable of understanding WXS schemas
		SchemaFactory factory = SchemaFactory.newInstance(type);

		// load a WXS schema, represented by a Schema instance
		Source schemaFile = new StreamSource(new File(xsdFileName));
		Schema schema = factory.newSchema(schemaFile);

		// create a Validator instance, which can be used to validate an instance document
		Validator validator = schema.newValidator();
		

		// validate the DOM tree
		try {
		    validator.validate(new DOMSource(document));
		    return true;
		} catch (IOException e) {
		    // instance document is invalid!
			return false;
		}
	}


}
