package fr.sc0rpi0.quantum.methodsfunctions;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class VerifFunction {
	public static String get(String url) throws IOException{
		String source = "";
		URL oracle = new URL(url);
		URLConnection yc = oracle.openConnection();
		yc.addRequestProperty("User-Agent", "Mozilla");
		yc.setReadTimeout(5000);
        yc.setConnectTimeout(5000);
		BufferedReader in = new BufferedReader(
		new InputStreamReader(
		yc.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			source += inputLine;
		}
		in.close();
		return source;
	}
}
