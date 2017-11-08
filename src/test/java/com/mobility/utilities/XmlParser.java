package com.mobility.utilities;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class XmlParser {
	private static final Logger LOGGER = Logger.getLogger(XmlParser.class);
	static HashMap<String,Map<String, String>> httpSampleattributeMap = new HashMap<>();
	
	/*
	 * process jtl file into hashMap
	 * */
	public static HashMap<String, Map<String, String>> getHttpSamplers(String filePath) throws Exception {
		
		File dir = new File(filePath);
		File[] directoryListing = dir.listFiles();
		if (directoryListing != null) {
			for (File child : directoryListing) {
				if (child.getName().contains("jtl")) {
					jtlParser(filePath+child.getName());
					LOGGER.info(child.getAbsolutePath()+" :Successfully parsed");
				}
			}
		}
		LOGGER.info("All jtl report processed to hashmap: " +httpSampleattributeMap.size());
		
		return httpSampleattributeMap;
		
	}

	
	/*
	 * parses the xml and updates the key pair for the httpsample with pref label
	 * */
	private static void jtlParser(String filePath) throws Exception{
		String xml = inputStreamToString(new FileInputStream(new File(filePath)));
		System.out.println("String:" + xml);
		String testLable="";
		HashMap<String, String> httpSampleattribute = new HashMap<String, String>();
		
		Pattern httpSamplerPattern = Pattern.compile("(<httpSample).*?(<\\/httpSample>|\\/>)");
		Pattern httpSamplerAtrributePattern = Pattern.compile("\\s.*?=.*?\".*?\"");
		Matcher httpSamplerMatcher = httpSamplerPattern.matcher(xml);
		Matcher httpSamplerAtrributeMatcher;
		
		while (httpSamplerMatcher.find()) {
			if (httpSamplerMatcher.group(0).contains("Perf")) {
				System.out.println(httpSamplerMatcher.group(0));
				
				httpSamplerAtrributeMatcher = httpSamplerAtrributePattern.matcher(httpSamplerMatcher.group(0));
				
				while(httpSamplerAtrributeMatcher.find()){
					String[] attriKeyPair = httpSamplerAtrributeMatcher.group(0).split("=");
					httpSampleattribute.put(attriKeyPair[0], attriKeyPair[1]);
					if(attriKeyPair[0].contains("lb"))
						testLable=attriKeyPair[1].trim();
					System.out.println(attriKeyPair[0]+" : "+ attriKeyPair[1]);
				}
				
			}
		}
		httpSampleattributeMap.put(testLable, httpSampleattribute);
	}

	public static String inputStreamToString(InputStream is) throws IOException {
		StringBuilder sb = new StringBuilder();
		String line;
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) {
			sb.append(line);
		}
		br.close();
		return sb.toString();
	}
	
	
}
