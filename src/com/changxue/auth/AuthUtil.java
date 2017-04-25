package com.changxue.auth;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.client.ClientProtocolException;

import net.sf.json.JSONObject;



public class AuthUtil {
	
	public static final String APPID="wx12f4bc74645eafea";
	public static final String APPSECRET="b601981ab63c0bef672dddec9210cf14";
	
	public static JSONObject doGetJson(String URL) throws IOException{
		
		URL url = new URL(URL);
		HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
	    //urlConnection.setRequestMethod("GET");
	
		// 将返回的输入流转换成字符串 
		InputStream inputStream = urlConnection.getInputStream();
	
		// 指定编码格式
		InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8"); 
		BufferedReader in = new BufferedReader(inputStreamReader); 
		String jsonUserStr =in.readLine(); 
		System.out.println("----------"+jsonUserStr);
		jsonUserStr =new String(jsonUserStr.getBytes(),"UTF-8");
		System.out.println("=========="+jsonUserStr);
	
	
		// 释放资源 
		inputStream.close(); 
		inputStream = null; 
		urlConnection.disconnect();
		
		JSONObject jsonUserObject = JSONObject.fromObject(jsonUserStr);
		return jsonUserObject;
	}
}
