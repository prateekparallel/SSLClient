package com.bhaiti.kela.ssl.client.app;


import java.net.URL;

import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

import com.bhaiti.kela.common.config.ApplicationConfig;
import com.bhaiti.kela.ssl.client.SSLClient;


public class ClientApp {

	public static void main(String[] args) {
		System.out.println("In main");
		Configuration config = null;
		//Load (String)configuration
		try {
			    String currentPath = System.getProperty("user.dir");
				config = new PropertiesConfiguration(currentPath + "/" + "system.properties");
				
			}catch(Exception e) {
				System.out.println("Exception in reading properties file : system.properties");
				e.printStackTrace();
				System.exit(-1);
			}
		
		ApplicationConfig ac = ApplicationConfig.getInstance();
		ac.setKEYSTOREPATH((String)config.getProperty("KEYSTOREPATH"));
		ac.setTRUSTSTOREPATH((String)config.getProperty("TRUSTSTOREPATH"));
		ac.setKEYSTOREPW((String)config.getProperty("KEYSTOREPW"));
		ac.setTRUSTSTOREPW((String)config.getProperty("TRUSTSTOREPW"));
		ac.setKEYPASS((String)config.getProperty("KEYPASS"));
		ac.setKeystoreType((String)config.getProperty("keystoreType"));
		ac.setTrustAllCertificate((String)config.getProperty("trustAllCertificate"));
		ac.setKeymanageralgorithm((String)config.getProperty("keymanageralgorithm"));
		
		
		try {
			//A SOAP web service call
			SSLClient sslClient = SSLClient.getSSLClient();		
			String strurl ="https://localhost:23521/app/v1/myservice";//you can add all the urls in config file
			URL url = new URL(strurl);
			String method = "POST";
			String message = "your soap message body";//This must be a complete xml message
			String msgtype = "text/xml";
			String response = sslClient.sendRequest(url, method, message, msgtype);
			
			//A RESTFul GET web service call	
			strurl ="https://localhost:23521/app/v1/test/Student.json?studentId=9999";
			url = new URL(strurl);
			method = "GET";
			message = "";// can be json or xml
			msgtype = "text/xml";
			response = sslClient.sendRequest(url, method, message, msgtype);
			
			//A RESTFul POST web service call			
			strurl ="https://localhost:23521/app/v1/test/Student.json";
			url = new URL(strurl);
			method = "POST";
			message = "your json message body";//can be a json or xml message
			msgtype = "text/xml";
			response = sslClient.sendRequest(url, method, message, msgtype);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
