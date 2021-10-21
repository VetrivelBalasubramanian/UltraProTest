package com.ultrapro.Helpers;

import java.io.File;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LoggerHelper {
	private static boolean root = false;
	public static Logger getLogger(Class<?> clas) {
		if (root)
			return Logger.getLogger(clas);

		PropertyConfigurator
				.configure(System.getProperty("user.dir") + File.separator + ("properties/log4j.properties"));
		root = true;
		return Logger.getLogger(clas);
	}
}
