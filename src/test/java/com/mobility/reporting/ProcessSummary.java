package com.mobility.reporting;

import java.util.Map;

import org.apache.log4j.Logger;

import com.mobility.utilities.*;;

public class ProcessSummary {
	
	final static Logger LOGGER = Logger.getLogger(ProcessSummary.class);
	String Main_Frame_String="";
	String Automation_Report_String="";
	String Html_Execution_String= "";
	String Html_Summary_String="";
	
	/*
	 * Create Summary Html report
	 */
	public void createSummaryReport(Map<String, Map<String, String>> testData) throws Exception {

		Main_Frame_String = "<html><head>" + "<title>Results for Execution</title>"
				+ "<link rel='stylesheet' href='css/style.css' type='text/css' media='all' />" + "</head>" + "<body>"
				+ "<div id='header'> <div class='shell'> <!-- Logo + Top Nav -->  <div class='box-label'><h2 class='left'>Mobility Performance Report</h2></div> "
				+ "<!-- End Logo + Top Nav --> </div> " + "</div> <!-- End Header -->"
				+ "<div class ='cl'>Date/Time: $DATETIME" + "</br></br></div>"
				+ "Select a Result on the Left-Hand Pane." + "</body></html>";

		Automation_Report_String = "<html><head>" + "<title>Execution Report</title>"
				+ "</head> <frameset cols='40%,60%'>" + "<frame src='ExecutionList.html' name='navFrame'>" + ""
				+ "<frame src='main.html' name='mainFrame'>" + "</frameset>" + "</html>";

		Html_Summary_String = "<html xmlns='http://www.w3.org/1999/xhtml'> "
				+ "<head> <meta http-equiv='Content-type' content='text/html; charset=utf-8' /> <title>Mobility Automation</title> "
				+ "<link rel='stylesheet' href='css/style.css' type='text/css' media='all' /> </head> <body> <!-- Header --> "
				+ "<div id='header'> <div class='shell'> <!-- Logo + Top Nav --> <div class='box-label'><h2 class='left'>Mobility Performance Report</h2></div> <!-- End Logo + Top Nav --> </div> "
				+ "</div> <!-- End Header --> <!-- Container --> <div id='container'> <div class='shell'> <!-- Main --> "
				+ "<div id='main'> <div class='cl'>&nbsp;</div> <!-- Content --> <div id='content'> <!-- Box --> <div class='box'> <!-- Box Head --> "
				+ "<div class='box-head'> <h2 class='left'>Summary Report</h2> </div> <!-- End Box Head --> <!-- Table --> <div class='table'> "
				+ "<table width='100%' border='0' cellspacing='0' cellpadding='0'> "
				+ "<tr> <th>Total</th> <th>Passed</th> <th>Failed</th> <th>Execution Time</th> </tr> " + "<tr> <td>"
				+ testData.size() + "</td> " + "<td>$PASSCOUNTER</td>" + "<td>$FAILCOUNTER</td>" + "<td>$EXECUTION_TIME</td>"
				+ "</tr> </table> </div> <!-- Table --> </div>";

		Html_Execution_String = "<div class='box'> <!-- Box Head --> <div class='box-head'> "
				+ "<h2>Execution Summary</h2> </div> <!-- End Box Head --> <!-- Table --> "
				+ "<div class='table'> <table width='100%' border='0' cellspacing='0' cellpadding='0'> "
				+ "<tr> <th>S.No</th> <th>Test Name</th> <th>Status</th> <th>Failed Msg</th> <th>Result</th> </tr>";

		int testCount = 1, passCounter = 0, failCounter = 0;
		String testNM = "", status = "fail";
		String duration, responseCode, statusMsg, responseMsg, bytes, ng, na,
				latency = duration = responseCode = status = responseMsg = bytes = ng = na = "";

		for (Map.Entry<String, Map<String, String>> keyEntry : testData.entrySet()) {
			for (Map.Entry<String, String> k : keyEntry.getValue().entrySet()) {

				String value = k.getValue().replace("\"", "");
				switch (k.getKey().trim()) {
				case "t":
					duration = value;
					break;
				case "lb":
					testNM = value;
					break;
				case "rc":
					responseCode = value;
					break;
				case "s":
					status = value;
					break;
				case "rm":
					responseMsg = value;
					break;
				case "by":
					bytes = value;
					break;
				case "ng":
					ng = value;
					break;
				case "lt":
					latency = value;
					break;
				case "na":
					na = value;
					break;
				}
			}

			if (responseCode == "200") {
				status = "pass";
				passCounter++;
			} else {
				status = "fail";
				failCounter++;
			}
			Html_Execution_String = Html_Execution_String + "<tr>" 
					//test Count
					+ "<td>" + testCount + "</td>"
					// Test Name
					+ "<td>" + testNM + "</td>"
					// Status
					+ "<td><span class='" + status + "'>" + status + "</span></td>"
					// Failed msg
					+ "<td>" + responseMsg + "</td>"
					// Result Link
					+ "<td>" + "<a href='"+ testNM+".html' target='mainFrame' >Result</a>" + "</td></tr>";
			testCount++;
		}
		
		Html_Execution_String = Html_Execution_String + "</table></body></html>";
		
		Main_Frame_String = Main_Frame_String.replace("$DATETIME", Utilities.getTodayDateTime());
		Html_Summary_String = Html_Summary_String.replace("$PASSCOUNTER", String.valueOf(passCounter))
				.replace("$FAILCOUNTER", String.valueOf(failCounter));
//		Html_Summary_String = Html_Summary_String.replaceAll("$EXECUTION_TIME",
//				Utilities.getRunDuration(System.getProperty("timestamp"), Utilities.getTodayDateTime()));
		
		Utilities.createFile(Utilities.getCurrentWorkingDir() + "/"+ Constants.jmeterReportDir, "automation_report.html",
				Automation_Report_String);
		Utilities.createFile(Utilities.getCurrentWorkingDir() + "/"+ Constants.jmeterReportDir, "main.html",
				Main_Frame_String);
		Utilities.createFile(Utilities.getCurrentWorkingDir() + "/"+ Constants.jmeterReportDir, "ExecutionList.html",
				Html_Summary_String + Html_Execution_String);
	}
}
