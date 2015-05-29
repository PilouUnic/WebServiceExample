package fr.web.service.example.basic;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

import org.json.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class BasicWebServiceClientExample {

	public static final String URI_SIMPLE_WS_GET = "http://localhost:8080/WebServiceExample/rest/hello";
	public static final String URI_SIMPLE_WS_GET_WITH_PARAM = "http://localhost:8080/WebServiceExample/rest/hello";
	public static final String URI_SIMPLE_WS_GET_WITH_EXCEPTION = "http://localhost:8080/WebServiceExample/rest/hello/testid";

	public static void main(String[] args) {

		// Appel a un webservice simple renvoyant une chaine de caractere en GET
		try {
			Client client = Client.create();

			WebResource webResource = client
					.resource(URI_SIMPLE_WS_GET);

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);
			System.out.println("Returned by Webservice: " + output);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Appel a un webservice simple renvoyant une chaine de caractere en GET
		// avec un parametre passe en entre.
		try {
			Client client = Client.create();

			WebResource webResource = client
					.resource(URI_SIMPLE_WS_GET_WITH_PARAM + "/aurelien");

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			String output = response.getEntity(String.class);
			System.out.println("Returned by Webservice: " + output);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Appel a un webservice simple renvoyant une chaine de caractere en GET avec gestion d'exception
		try {
			Client client = Client.create();

			WebResource webResource = client
					.resource(URI_SIMPLE_WS_GET_WITH_EXCEPTION + "/1s");

			ClientResponse response = webResource.accept("application/json")
					.get(ClientResponse.class);

			//			if (response.getStatus() != 200) {
			//				throw new RuntimeException("Failed : HTTP error code : "
			//						+ response.getStatus());
			//			}

			String output = response.getEntity(String.class);
			System.out.println("Returned by Webservice: " + output);

		} catch (Exception e) {
			e.printStackTrace();
		}


		String string = "";
		try {

			// Lecture d'un fichier de donnees au format JSON afin de le 
			// transmettre a un webservice
			InputStream crunchifyInputStream = new FileInputStream(
					"C:\\DEV\\DATA\\JSON\\data.txt");
			InputStreamReader crunchifyReader = new InputStreamReader(crunchifyInputStream);
			BufferedReader br = new BufferedReader(crunchifyReader);
			String line;
			while ((line = br.readLine()) != null) {
				string += line + "\n";
			}

			// Transformation du contenu du fichier txt precedemment lu en objet JSON
			JSONObject jsonObject = new JSONObject(string);
			System.out.println(jsonObject);

			// Appel au webservice, en lui passant en parametre l'objet JSON converti
			try {
				URL url = new URL("http://localhost:8080/WebServiceExample/rest/hello/json");
				URLConnection connection = url.openConnection();
				connection.setDoOutput(true);
				connection.setRequestProperty("Content-Type", "application/json");
				connection.setConnectTimeout(5000);
				connection.setReadTimeout(5000);
				OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream());
				out.write(jsonObject.toString());
				out.close();

				BufferedReader in = new BufferedReader(new InputStreamReader(
						connection.getInputStream()));

				while (in.readLine() != null) {
				}
				System.out.println("\nREST Service Invoked Successfully..");
				in.close();
			} catch (Exception e) {
				System.out.println("\nError while calling REST Service");
				System.out.println(e);
			}

			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
