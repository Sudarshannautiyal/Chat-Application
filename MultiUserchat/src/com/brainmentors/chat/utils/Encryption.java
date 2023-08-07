package com.brainmentors.chat.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public interface Encryption {
  public static String passwordEncrypt(String PlainPassword) throws NoSuchAlgorithmException {
	  String encryptedPassword = null;
	  MessageDigest messageDigest = MessageDigest.getInstance("MD5");
	  messageDigest.update(PlainPassword.getBytes());
	  byte [] encrypt = messageDigest.digest();
	  StringBuffer sb = new StringBuffer();
	  for(byte b:encrypt) {
		  sb.append(b);
	  }
	  encryptedPassword = sb.toString();
	  return encryptedPassword;
 }
}
