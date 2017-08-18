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
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

import javax.net.ssl.HttpsURLConnection;

import java.util.Base64;

public class Model {

	private final String RESET_MISSED = "/index.htm?misseddel=0";
	private final String RESET_DIALED = "/index.htm?dialeddel=0";
	private final String REST_RECIEVED = "/index.htm?receiveddel=0";
	private final String REBOOT = "/advanced_update.htm?reboot=Reboot";
	private final String PROTOCOL = "http://";
	private final String CREDENTIALS = "admin:mKHyJQkDWu2hQ9Ng";
	private final String VOLUME_UP = "/command.htm?key=VOLUME_UP";
	private final String VOLUME_DOWN = "/command.htm?key=VOLUME_DOWN";

	private HashMap<Integer, InetAddress> rooms;
	private List<InetAddress> suitesList100;
	private List<InetAddress> suitesList200;
	private List<InetAddress> suitesList300;

	private URL url;
	private HttpURLConnection conn;
	private InputStream contento;
	private BufferedReader inBuff;
	private String linee;
	private String encodinge;

	public Model() {
		// initialize data structure here
		initializeVars();

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

	public void resetMissedCalls(int roomExtension) {
		// execute post to rest the missed calls
		// Integer roomNumber = rooms.get(key)
		String IPaddress = "";
		IPaddress = rooms.get(roomExtension).getHostAddress();
		System.out.println(IPaddress);

		try {

			// DEBUG MODE == using static IP instead of IPaddress
			URL urlTest = new URL(PROTOCOL + "10.90.1.134" + VOLUME_DOWN);

			// this.url = new URL(PROTOCOL + IPaddress + RESET_MISSED);
			String encoding = Base64.getEncoder().encodeToString(new String(CREDENTIALS).getBytes());
			HttpURLConnection connection = (HttpURLConnection) urlTest.openConnection();
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

		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException--");
			System.out.println(e.getMessage());
		} catch (ProtocolException e) {
			System.out.println("ProtocolException--");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException--");
			System.out.println(e.getMessage());
		}

	}

	public void testingCallSequence() {

		// String encoding = Base64.getEncoder().encodeToString(new
		// String(CREDENTIALS).getBytes());

		String serviceURL = PROTOCOL + "10.90.1.134" + VOLUME_UP;
		try {

			// DEBUG MODE
			URL myURL = new URL(serviceURL);
			HttpURLConnection myURLConnection = (HttpURLConnection) myURL.openConnection();
			String userCredentials = CREDENTIALS;
			String basicAuth = "Basic " + new String(Base64.getEncoder().encode(userCredentials.getBytes()));
			myURLConnection.setRequestProperty("Authorization", basicAuth);

			myURLConnection.setRequestMethod("POST");
			myURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			myURLConnection.setRequestProperty("Content-Length", "" + serviceURL.getBytes().length);
			myURLConnection.setRequestProperty("Content-Language", "en-US");
			myURLConnection.setUseCaches(false);
			myURLConnection.setDoInput(true);
			myURLConnection.setDoOutput(true);
			myURLConnection.connect();
			System.out.println("Everything good here");

			InputStream content = (InputStream) myURLConnection.getInputStream();
			BufferedReader in = new BufferedReader(new InputStreamReader(content));
			String line;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}

		} catch (MalformedURLException e) {
			System.out.println("MalformedURLException--");
			System.out.println(e.getMessage());
		} catch (ProtocolException e) {
			System.out.println("ProtocolException--");
			System.out.println(e.getMessage());
		} catch (IOException e) {
			System.out.println("IOException--");
			System.out.println(e.getMessage());
		}
	}

	public void restDialedCalls(int roomExtension) {
		
		this.encodinge = "Basic " + new String(Base64.getEncoder().encode(CREDENTIALS.getBytes()));
		// user roomExtension instead of static ip string
		String serviceURL = PROTOCOL + "10.90.1.134" + VOLUME_UP;
		try {
			this.url = new URL(serviceURL);
			this.conn = (HttpURLConnection) this.url.openConnection();
			this.conn.setRequestProperty("Authorization", this.encodinge);
			this.conn.setRequestMethod("POST");
			this.conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			this.conn.setRequestProperty("Content-Length", "" + serviceURL.getBytes().length);
			this.conn.setRequestProperty("Content-Language", "en-US");
			this.conn.setUseCaches(false);
			this.conn.setDoInput(true);
			this.conn.setDoOutput(true);
			this.conn.connect();
			
			this.contento = (InputStream) conn.getInputStream();
			this.inBuff = new BufferedReader(new InputStreamReader(contento));
			String line;
			while ((line = inBuff.readLine()) != null) {
				System.out.println(line);
			}

		} 
		catch (MalformedURLException e) {
			System.out.println("MalformedURLException--");
			System.out.println(e.getMessage());
		} 
		catch (ProtocolException e) {
			System.out.println("ProtocolException--");
			System.out.println(e.getMessage());
		} 
		catch (IOException e) {
			System.out.println("IOException--");
			System.out.println(e.getMessage());
		}
	}

	public void resetRecievedCalls(int roomExtension) {
		// TODO Auto-generated method stub

	}

	public void rebootNode(int roomExtension) {
		// TODO Auto-generated method stub

	}

	// private void setIPAddress() {
	// InetAddress ip = null;
	// try {
	// // read the ip addresses from a file maybe??
	// ip = InetAddress.getByName("192.168.2.1");
	// } catch (UnknownHostException e) {
	// // TODO Auto-generated catch block
	// e.printStackTrace();
	// }
	// byte[] bytes = ip.getAddress();
	// for (byte b : bytes) {
	// System.out.println(b & 0xFF);
	// }
	// }

	private void initializeVars() {
		this.rooms = new HashMap<Integer, InetAddress>();
		this.suitesList100 = new LinkedList<InetAddress>();
		this.suitesList200 = new LinkedList<InetAddress>();
		this.suitesList300 = new LinkedList<InetAddress>();

		try {
			// data for room 100x
			this.rooms.put(101, InetAddress.getByName("172.28.3.10"));
			this.rooms.put(102, InetAddress.getByName("172.28.3.11"));
			this.rooms.put(103, InetAddress.getByName("172.28.3.12"));
			this.rooms.put(104, InetAddress.getByName("172.28.3.13"));
			this.rooms.put(105, InetAddress.getByName("172.28.3.14"));
			this.suitesList100.add(InetAddress.getByName("172.28.3.15"));
			this.suitesList100.add(InetAddress.getByName("172.28.3.16"));
			this.rooms.put(108, InetAddress.getByName("172.28.3.17"));

			// data for room 200x
			this.rooms.put(201, InetAddress.getByName("172.28.3.18"));
			this.rooms.put(202, InetAddress.getByName("172.28.3.19"));
			this.rooms.put(203, InetAddress.getByName("172.28.3.20"));
			this.rooms.put(204, InetAddress.getByName("172.28.3.21"));
			this.rooms.put(205, InetAddress.getByName("172.28.3.22"));
			this.suitesList200.add(InetAddress.getByName("172.28.3.23"));
			this.suitesList200.add(InetAddress.getByName("172.28.3.24"));
			this.rooms.put(208, InetAddress.getByName("172.28.3.25"));

			// data for room 300x
			this.rooms.put(301, InetAddress.getByName("172.28.3.26"));
			this.rooms.put(302, InetAddress.getByName("172.28.3.27"));
			this.rooms.put(303, InetAddress.getByName("172.28.3.28"));
			this.rooms.put(304, InetAddress.getByName("172.28.3.29"));
			this.rooms.put(305, InetAddress.getByName("172.28.3.30"));
			this.suitesList300.add(InetAddress.getByName("172.28.3.31"));
			this.suitesList300.add(InetAddress.getByName("172.28.3.32"));

			// data for rooms 400x
			this.rooms.put(401, InetAddress.getByName("172.28.3.34"));
			this.rooms.put(402, InetAddress.getByName("172.28.3.35"));
			this.rooms.put(403, InetAddress.getByName("172.28.3.36"));
			this.rooms.put(404, InetAddress.getByName("172.28.3.48"));
			this.rooms.put(405, InetAddress.getByName("172.28.3.49"));
			this.rooms.put(406, InetAddress.getByName("172.28.3.47"));
			this.rooms.put(407, InetAddress.getByName("172.28.3.46"));
			this.rooms.put(408, InetAddress.getByName("172.28.3.45"));

			// data for rooms 500x
			this.rooms.put(501, InetAddress.getByName("172.28.3.56"));
			this.rooms.put(502, InetAddress.getByName("172.28.3.55"));
			this.rooms.put(503, InetAddress.getByName("172.28.3.53"));
			this.rooms.put(504, InetAddress.getByName("172.28.3.54"));
			this.rooms.put(505, InetAddress.getByName("172.28.3.50"));
			this.rooms.put(506, InetAddress.getByName("172.28.3.51"));
			this.rooms.put(507, InetAddress.getByName("172.28.3.52"));
			this.rooms.put(508, InetAddress.getByName("172.28.3.57"));

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void handleSuite(int roomExtension) {
		if (roomExtension == 106) {
			removeSuite106();
		} else if (roomExtension == 206) {
			removeSuite206();
		} else if (roomExtension == 306) {
			removeSuite306();
		}

	}

	private void removeSuite306() {

	}

	private void removeSuite206() {
		// TODO Auto-generated method stub

	}

	private void removeSuite106() {
		// TODO Auto-generated method stub

	}
}
