package reaDefine;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class HttpUtility {
	
	private final String API_ENDPOINT = "https://www.dictionaryapi.com/api/v1/references/collegiate/xml/";
	private final String API_KEY = "?key=5da1d31c-d5d1-4958-98e1-bea87cdb7909";
	
	// Makes a GET request to the dictionaryapi.com endpoint
	public String get(String word) throws IOException {
		StringBuilder result = new StringBuilder();
		URL url = new URL(API_ENDPOINT + word + API_KEY);
		HttpURLConnection conn = (HttpURLConnection) url.openConnection();
		conn.setRequestMethod("GET");
		int response = conn.getResponseCode();
		if (response != HttpURLConnection.HTTP_OK) {
			return "Error: " + response;
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		String line;
		while ((line = reader.readLine()) != null) {
			result.append(line);
		}
		reader.close();
		return result.toString();
	}
	
	// Parses the Stringified xml for the "def" tag and retrieves the text
	public String xmlDef(String xml) throws ParserConfigurationException, SAXException, IOException {
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputSource input = new InputSource();
		input.setCharacterStream(new StringReader(xml));
		
		try {
			Document doc = db.parse(input);
			NodeList nodes = doc.getElementsByTagName("def");
			for (int i = 0; i < nodes.getLength(); i++) {
				if (nodes.item(i).getNodeType() == Node.ELEMENT_NODE) {
					Element el = (Element) nodes.item(i);
					String def = el.getElementsByTagName("dt").item(0).getTextContent();
					return def.replaceAll("[:()\"]", "").trim(); 
				}
			}
		} catch (NullPointerException | SAXException | IOException e) {
			return "no definition";
		}
		return "no definition";
	}
}
