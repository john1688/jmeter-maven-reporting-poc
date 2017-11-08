package com.mobility.objects;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TimeZone;

import com.mobility.utilities.Constants;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamImplicit;

public class Sample {
//	static String xml = "<Music><album><name>name1</name><author>author1</author></album></Music>";
//	 
//    public static void main(String[] args) throws FileNotFoundException {
//    	FileReader fileReader = new FileReader(Constants.userDir+ "/reports/jtl/Mobility_Dashboard.jtl");
//        XStream xStream = new XStream();
//        xStream.processAnnotations(TestResults.class);
//        xStream.alias("httpSample", String.class);
////        xStream.alias("author", String.class);
////        xStream.alias("album", Entry.class);
//        TestResults testObject = (TestResults) xStream.fromXML(fileReader);
//        System.out.println(testObject);
//    }
//}
//
//
//
//@XStreamAlias("testResults")
//class TestResults {
//	@XStreamImplicit
//	private List httpSample = new ArrayList();
// 
//    @Override
//    public String toString() {
//        return "testResults [testMap=" + httpSample + "]";
//    }
}
 