package com.ultrapro.utils.Data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ReadJsonData {
	/**
	 * @param filePath
	 * @return
	 * @throws IOException
	 */
	public Map<Object, Map<String, Map<String, String>>> jsonInit(String filePath) {
		File file = new File(filePath);
		Map<Object, Map<String, Map<String, String>>> map = new HashMap<Object, Map<String, Map<String, String>>>();
		ObjectMapper mapper = new ObjectMapper();
		try {
			map = mapper.readValue(file, new TypeReference<HashMap<Object, Map<String, Map<String, String>>>>() {
			});
		} catch (IOException e) {
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * readJsonData
	 * 
	 * @param fileName
	 * @param header
	 * @param key
	 * @return data from Json file
	 */
	public String readJsonData(String fileName, String header, String key) {
		String Environment = System.getProperty("ENV").toLowerCase();
		String value = null;
		String filePath = System.getProperty("user.dir") + File.separator + "src/test/resources/test_data"
				+ File.separator + getJsonFileName(fileName) + ".json";
		Map<Object, Map<String, Map<String, String>>> map = new HashMap<Object, Map<String, Map<String, String>>>();
		map = jsonInit(filePath);
		value = map.get(Environment).get(header.toLowerCase()).get(key.toLowerCase()).toString();
		return value;
	}

	/**
	 * readJsonMapData
	 *  
	 * @param fileName
	 * @param header
	 * @return map data from json header
	 */
	public Map<String, String> readJsonMapData(String fileName, String header) {
		String Environment = System.getProperty("ENV").toLowerCase();
		String filePath = System.getProperty("user.dir") + File.separator + "src/test/resources/test_data"
				+ File.separator + getJsonFileName(fileName) + ".json";
		Map<Object, Map<String, Map<String, String>>> map = new HashMap<Object, Map<String, Map<String, String>>>();
		map = jsonInit(filePath);
		Map<String, String> mapData = map.get(Environment).get(header.toLowerCase());
		return mapData;
	}

	/**
	 * read file name and add language parameter if any
	 *  
	 * @param fileName
	 * @return
	 */
	public String getJsonFileName(String fileName) {
		if (System.getProperty("LANG") != null) {
			fileName += "_" + System.getProperty("LANG");
		}
		return fileName;
	}
}
