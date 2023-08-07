package com.brainmentors.chat.utils;

import java.util.ResourceBundle;

public class ConfigReader {
	ConfigReader(){}
	private static ResourceBundle rb = ResourceBundle.getBundle("config");
	public static String getValue(String Key) {
		return rb.getString(Key);
	}
	

}
