package com.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class NetClientGet {

	public static void main(String[] args) {

		try {

		
			URL url = new URL("http://localhost:8080/FeedBack-api/api/json/feedback/get");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			//URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			conn.setRequestProperty("Content-Type", "application/json");
			//conn.setConnectTimeout(5000);
			//conn.setReadTimeout(5000);
			
			if (conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
				throw new RuntimeException("Failed To read: HTTP error code : " + conn.getResponseCode());
			}
			 

			BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));

			String output;
			System.out.println("Output from Server  all feedbacks so far \n");
			while ((output = br.readLine()) != null) {

				System.out.println(output);
			}
			br.close();
			
			//conn.disconnect();

		} catch (MalformedURLException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
			
		}

	}

}