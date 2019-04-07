package com.rest;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Iterator;

import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonWriter;

public class ObjectToJSON {

	private static final String outputJSONfilePath = "D://feedback.json";

	/*@method To test appendJson
	 * */
	public static void main(String[] a) {

		Feedback fd = new Feedback();

		// Insert the data into the object
		fd = getObjectData(fd);

		// Creating Object of ObjectMapper define in Jakson Api
		ObjectToJSON obj = new ObjectToJSON();
		// obj.writeFeedbackToJsonFile(fd);
		obj.appendObjToJSON(fd);
	}

	
	/**
	 * @return Object after reading it from source location and converting JSON to Obj.
	 */
	public Object readFeedbackFromJsonFile() {
		Object obj = new Object();
		try {

			FileReader reader = new FileReader(outputJSONfilePath);
			JSONParser jsonParser = new JSONParser();
			try {
				obj = jsonParser.parse(reader);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}

		} catch (IOException e) {
			System.out.println("JSON failed to write!!! ");
			e.printStackTrace();
			return null;
		}

		System.out.println("JSON Read successfuly");

		return obj;
	}

	// Get the data to be inserted into the object
	public static Feedback getObjectData(Feedback fd) {

		// Insert the data
		fd.setEmail("sharmagauravs08@gmail.com");
		fd.setMessage("This is serioulsy awesome");
		fd.setTime(LocalDateTime.now().toString());
		fd.setBrowser("Chrome");

		// Return the object
		return fd;
	}

	 /**
     * Load the json file if its present or create the new empty json file.
     * and append the new feedback to json file 
     * @param   Feedback new feedback to write 
     */
	public void appendObjToJSON(Feedback fd) {
		try {

			File file = new File(outputJSONfilePath);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
			
            JsonParser jsonParser = new JsonParser();
            JsonElement jsonElement = jsonParser.parse(new FileReader(outputJSONfilePath));
            Gson gson = new GsonBuilder().create();
            String newfd = gson.toJson(fd);

            JsonObject jsonObject = new JsonObject();
            Feedbacks fds = new Feedbacks();

            if(!jsonElement.isJsonNull()){
				jsonObject = (JsonObject) jsonElement;
				System.out.println(jsonObject);
				System.out.println("Element to Write ==>" + newfd);
				
				//Iterate over all the elements of feedbacks
				JsonArray msg = (JsonArray) jsonObject.get("feedbacks");
				Iterator<JsonElement> iterator = msg.iterator();
				
				iterator = msg.iterator();
				while (iterator.hasNext()) {
					fds.addFeedback(gson.fromJson(iterator.next().toString(), Feedback.class));
				}
			}
            //Adding the new feedback at last
            fds.addFeedback(fd);
            FileWriter fw = new FileWriter(outputJSONfilePath, false);
            JsonWriter jw = new JsonWriter(fw);
            
            gson.toJson(fds, Feedbacks.class, jw);
            fw.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	/*
	 * public boolean writeFeedbackToJsonFile(Feedback fd) { ObjectMapper Obj = new
	 * ObjectMapper(); try { Obj.writeValue(new FileWriter(outputJSONfilePath,true),
	 * fd); } catch (IOException e) {
	 * System.out.println("JSON failed to write!!! "); e.printStackTrace(); return
	 * false; }
	 * 
	 * System.out.println("JSON Written successfuly"); // return true;
	 * 
	 * 
	 * JSONObject obj = new JSONObject(); JSONParser parser = new JSONParser();
	 * 
	 * Gson gsonBuilder = new GsonBuilder().create(); String jsonFromPojo =
	 * gsonBuilder.toJson(fd);
	 * 
	 * JSONObject jsonObj = (JSONObject)parser.parse(new
	 * FileReader(outputJSONfilePath));
	 * 
	 * 
	 * return true; }
	 */
}
