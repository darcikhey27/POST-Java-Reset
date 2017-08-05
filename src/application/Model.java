package application;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
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

public class Model {

	// do all the processing here
	// create a hash table that will conenct my button.number to
	// ip address

	public void executePost(String ipAddress) throws Exception {
		URL url = new URL("http://httpbin.org/ip");
		URLConnection conn = url.openConnection();
		conn.setDoOutput(true);
		OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());

		//writer.write("name=darci&password=pass");
		//writer.flush();
		String line;
		BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
		while ((line = reader.readLine()) != null) {
			System.out.println(line);
		}
		writer.close();
		reader.close();

	}

	public void excutePost2() throws Exception {
		URL url = new URL("http://localhost:4567/form");
		URLConnection con = url.openConnection();
		HttpURLConnection http = (HttpURLConnection) con;
		http.setRequestMethod("POST"); // PUT is another valid option
		http.setDoOutput(true);

		Map<String, String> arguments = new HashMap<>();
		arguments.put("username", "darci");
		arguments.put("password", "pass"); // This is a fake password obviously
		StringJoiner sj = new StringJoiner("&");
		
		for (Map.Entry<String, String> entry : arguments.entrySet())
			sj.add(URLEncoder.encode(entry.getKey(), "UTF-8") + "=" + URLEncoder.encode(entry.getValue(), "UTF-8"));
		
		byte[] out = sj.toString().getBytes(StandardCharsets.UTF_8);
		int length = out.length;

		http.setFixedLengthStreamingMode(length);
		http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
		http.connect();
		try (OutputStream os = http.getOutputStream()) {
			os.write(out);
		}

	}

	public void executePost3() {
		try {
			URL u = new URL("http://httpbin.org/ip");
			
			URLConnection uc = u.openConnection();
			uc.setAllowUserInteraction(true);
			uc.connect();
			InputStream in = uc.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(in));
			System.out.println(uc.getContentType());
			
			String line = "";
			while( (line = reader.readLine()) != null) {
				System.out.println(line);
			}
			
			/*
			 InputStream in = new FileInputStream(new File("C:/temp/test.txt"));
		        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
		        StringBuilder out = new StringBuilder();
		        String line;
		        while ((line = reader.readLine()) != null) {
		            out.append(line);
		        }
		        System.out.println(out.toString());   //Prints the string content read from input stream
		        reader.close();
			*/
		} 
		catch (IOException ex) {
			System.err.println(ex);
		}
	}
	public void executePost4() {
		try {
			URL u = new URL("http://httpbin.org/ip");
			URLConnection uc = u.openConnection();
			uc.setDoOutput(true);
			
			OutputStream raw = uc.getOutputStream();
			
			OutputStream buffered = new BufferedOutputStream(raw);
			OutputStreamWriter out = new OutputStreamWriter(buffered, "8859_1");
			out.write("name=darci&password=pass");
			out.write("\r\n");
			out.flush();
			out.close();
			
		}
		catch(Exception e) {
			System.err.println(e);
		}
		
	}

}
