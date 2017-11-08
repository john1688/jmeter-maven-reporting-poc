package com.mobility.utilities;

public class Constants {

	// jmeter constants
	public static String userDir = System.getProperty("user.dir");
	public static String jmeterJtlDir = "target/jmeter/results/";
	public static String jmeterReportDir = "reports/";
	public static String jmeterReportJtlDir= userDir.substring(0, userDir.indexOf("/target"))+ "/reports/jtl/";
}