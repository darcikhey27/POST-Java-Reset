package application;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.net.ssl.HttpsURLConnection;

import java.util.Base64;

public class Model {

	public void executePost() throws IOException {
		// reset missed calls
		// URL url = new URL("http://10.90.1.134/index.htm?misseddel=0");

		// reset missed calls
		// http://10.90.1.134/index.htm?misseddel=0
		// URL url = new URL("http://10.90.1.134/index.htm?misseddel=0");
		// reset dialed calls
		// http://10.90.1.134/index.htm?dialeddel=0
		//URL url = new URL("http://10.90.1.134/index.htm?dialeddel=0");
		// reset recieved calls
		// http://10.90.1.134/index.htm?receiveddel=0
		// URL url = new URL("http://10.90.1.134/index.htm?receiveddel=0");
		// reboot
		// http://10.90.1.134/advanced_update.htm?reboot=Reboot
		URL url = new URL("http://10.90.1.134/advanced_update.htm?reboot=Reboot");
		
		String encoding = Base64.getEncoder().encodeToString(new String("admin:mKHyJQkDWu2hQ9Ng").getBytes());

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		connection.setRequestProperty("Authorization", "Basic " + encoding);
		connection.connect();
		InputStream content = (InputStream) connection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(content));
		String line;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
	}
}
