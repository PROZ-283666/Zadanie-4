package jaxb;

import java.io.StringReader;
import java.net.URI;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

public class InfoGetter {
	private Client client;
	private URI uri;
	private WebTarget webTarget;
	
	InfoGetter() {
		client = ClientBuilder.newClient();
	}
	
	private void setURI(String firstPart, String table, String code, String topCount) {
		uri = UriBuilder.fromUri(firstPart + table + "/" + code + "/last/" + topCount).build();
	}
	
	private String getXMLAnswer() {
		String xmlAnswer = webTarget.request()
				.accept(MediaType.TEXT_XML)
				.get(String.class);
		return xmlAnswer;
	}
	
	public static String getMeanFromNBPUsingUnmarshalling(String table, String code, String topCount) {
		InfoGetter t = new InfoGetter();
		t.setURI("http://api.nbp.pl/api/exchangerates/rates/", table, code, topCount);
		t.webTarget = t.client.target(t.uri);
		String ans = t.getXMLAnswer();
		
		double sum = 0.0;
		double n = 0.0;
		
		try {
	        JAXBContext context = JAXBContext.newInstance(ExchangeRatesSeries.class);
	        Unmarshaller unmarshaller = context.createUnmarshaller();
	        StringReader reader = new StringReader(ans);
	        ExchangeRatesSeries example = (ExchangeRatesSeries) unmarshaller.unmarshal(reader);
		    String w = "Mid";
		    if(table.equals("C")) w = "Ask";
		    sum = example.getRates().getSum(w);
		    n = example.getRates().getRate().size();
		}
		catch (JAXBException e) {
	        e.printStackTrace();
	    }
		
		Double mean = sum/n;
		mean = (double)Math.round(mean * 10000)/10000;
		return mean.toString();	       	        
	}
	
}