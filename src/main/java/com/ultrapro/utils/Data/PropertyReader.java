package com.ultrapro.utils.Data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Properties;
import java.util.Set;

import org.ini4j.Ini;
import org.ini4j.Profile.Section;

public class PropertyReader {
	/**
	 * @param env
	 * @param fileName
	 * @param sectype
	 * @return
	 * @throws IOException
	 */
	public static Section iniLoader(String env, String fileName, String sectype) throws IOException {
		Ini ini = new Ini();
		FileInputStream iniConfigFile = null;
		iniConfigFile = new FileInputStream(System.getProperty("user.dir") + File.separator + "properties"
				+ File.separator + env + File.separator + fileName + ".properties");
		ini.load(iniConfigFile);
		Section section = ini.get(sectype);
		return section;
	}

	/**
	 * @param fileName
	 * @param sectype
	 * @return
	 * @throws IOException
	 */
	public static Section iniLoader(String fileName, String sectype) throws IOException {
		Ini ini = new Ini();
		FileInputStream iniConfigFile = null;
		iniConfigFile = new FileInputStream(System.getProperty("user.dir") + File.separator + "properties"
				+ File.separator + fileName + ".properties");
		ini.load(iniConfigFile);
		Section section = ini.get(sectype);
		return section;
	}

	public static String getUserProperties(String secntype, String val) {
		String value = null;
		try {
			Section section = iniLoader(System.getProperty("ENV"), "user", secntype.toUpperCase());
			value = section.get(val);
		} catch (Exception e) {
			System.out.println(e);
		}
		return value;
	}

	public static String getConfigProperties(String secntype, String val) {
		String value = null;
		try {
			Section section = iniLoader("config", secntype.toUpperCase());
			value = section.get(val);
		} catch (Exception e) {
			System.out.println(e);
		}
		return value;
	}

	public static String getSiteProperties(String secntype, String val) {
		String value = null;
		try {
			Section section = iniLoader("site", secntype.toUpperCase());
			value = section.get(val);
		} catch (Exception e) {
			System.out.println(e);
		}
		return value;
	}

	public static String getownershipsiteProperties(String secntype, String val) {
		String value = null;
		try {
			Section section = iniLoader("ownershipsite", secntype.toUpperCase());
			value = section.get(val);
		} catch (Exception e) {
			System.out.println(e);
		}
		return value;
	}

	public static HashMap<String, String> getPropValues(String file) {
		InputStream inputStream;
		HashMap<String, String> result = new HashMap<String, String>();

		try {
			Properties prop = new Properties();
			String propFileName = file;
			inputStream = PropertyReader.class.getClassLoader().getResourceAsStream(propFileName);
			// inputStream = new FileInputStream(propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			// get the property values
			Set<String> propNames = prop.stringPropertyNames();
			Iterator<String> iterator = propNames.iterator();
			while (iterator.hasNext()) {
				String key = iterator.next();
				result.put(key, prop.getProperty(key));
			}

			inputStream.close();

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		}
		return result;
	}

	public static void setPropsValue(String file, String path, String key, String value) {
		Properties prop = new Properties();
		InputStreamReader in = null;
		try {
			//prop.load(new InputStreamReader(new FileInputStream(file)));
			in = new InputStreamReader(PropertyReader.class.getClassLoader().getResourceAsStream(file));
			prop.load(in);
		} catch (FileNotFoundException e) {
			System.out.println("File Not Found");
		} catch (IOException e) {
			System.out.println("Failed to write to credentials.properties");
		}

		try {
			prop.remove(key);
			prop.setProperty(key, value);
			FileOutputStream out = new FileOutputStream(System.getProperty("user.dir") + path, false);
			prop.store(out, null);
			out.close();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
}
