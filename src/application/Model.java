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
import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.StringJoiner;

import javax.net.ssl.HttpsURLConnection;

import java.util.Base64;

public class Model {

	private String IPaddress;
	private final String RESET_MISSED = "/index.htm?misseddel=0";
	private final String RESET_DIALED = "/index.htm?dialeddel=0";
	private final String REST_RECIEVED = "/index.htm?receiveddel=0";
	private final String REBOOT = "/advanced_update.htm?reboot=Reboot";
	private final String PROTOCOL = "http://";
	private HashMap<Integer, String> table;

	public Model() {
		// initialize data structure here
		initializeTable();

	}

	private void setIPAddress() {
		InetAddress ip = null;
		try {
			// read the ip addresses from a file maybe??
			ip = InetAddress.getByName("192.168.2.1");
		} 
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		byte[] bytes = ip.getAddress();
		for (byte b : bytes) {
			System.out.println(b & 0xFF);
		}
	}

	private void initializeTable() {
		this.table = new HashMap<Integer, String>();
	}

	public void executePost() throws IOException {
		// reset missed calls
		// URL url = new URL("http://10.90.1.134/index.htm?misseddel=0");

		// reset missed calls
		// http://10.90.1.134/index.htm?misseddel=0
		// URL url = new URL("http://10.90.1.134/index.htm?misseddel=0");
		// reset dialed calls
		// http://10.90.1.134/index.htm?dialeddel=0
		// URL url = new URL("http://10.90.1.134/index.htm?dialeddel=0");
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
		// might not need this connect.
		// or I might not need to create an InputStream because I'm just sending
		connection.connect();
		InputStream content = (InputStream) connection.getInputStream();
		BufferedReader in = new BufferedReader(new InputStreamReader(content));
		String line;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}
	}

	public void resetMissedCalls(String roomExtension) {
		// execute post to rest the missed calls

	}

	public void restDialedCalls(String roomExtension) {
		// TODO Auto-generated method stub

	}

	public void resetRecievedCalls(String roomExtension) {
		// TODO Auto-generated method stub

	}

	public void rebootNode(String roomExtension) {
		// TODO Auto-generated method stub

	}
}
