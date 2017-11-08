package com.mobility.reporting;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.mobility.utilities.*;

public class ProcessReport {

	final static Logger LOGGER = Logger.getLogger(ProcessReport.class);

	/*
	 * create Sample result Html Report
	 */
	public void createReport(HashMap<String, Map<String, String>> testData) throws Exception {
		String Html_Content_String = "";
		String testNM = "";
		String Html_Header_String = "<html xmlns='http://www.w3.org/1999/xhtml'> <head> <meta http-equiv='Content-type' content='text/html; charset=utf-8' /> <title>Mobility Performance Report</title> "
				+ "<link rel='stylesheet' href='css/style.css' type='text/css' media='all' /> </head> <body> <!-- Header --> <div id='header'> "
				+ "<div class='shell'> <!-- Logo + Top Nav --> <div class='box-label'><h2 class='left'>Mobility Performance Report</h2></div> <!-- End Logo + Top Nav --> </div> </div> <!-- End Header --> <!-- Container --> "
				// + "<div id='container'> <div class='shell'> <!-- Main -->
				// <div id='main'> <div class='cl'>"
				// + "<p><strong>Test Case Name :</strong> "
				// + "$TESTNAME</p><p>"
				// + "<strong>Test Case Status :</strong>"
				// + "&nbsp;$TESTSTATUS &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>"
				// + "<p><strong>Environment:</strong> "
				// + "$ENVIRONMENT</p></div>"
				// +"<div class='fp'>"
				// + "<p><strong>Execution Started at:</strong>"
				// + "$STARTTIME &nbsp;&nbsp;&nbsp;</p>"
				// + "<p><strong>Execution Finished at:</strong> "
				// + "$FINISHTIME </p>"
				// + "<p><strong>Total Run Duration:</strong> "
				// + "$RUNDURATION </p></div>"
				// + "</div><!-- Content --> <div id='content'> <!-- Box --> "
				+ "<div class='box'> <!-- Box Head --> <div class='box-head'> <h2 class='left'>Report</h2> </div> <!-- End Box Head --> <!-- Table --> "
				+ "<div class='table'> " + "<table width='100%' border='1' cellspacing='0' cellpadding='0'> "
				+ "<tr> <th>API Name</th><th>Threads</th><th>Response Code</th> <th>Duration</th><th>Latency</th><th>Bytes</th><th>Failure</th></tr> ";
		String duration, responseCode, status, responseMsg, bytes, ng, na,
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

			Html_Content_String = Html_Content_String + "<tr>"
			// testNM
					+ "<td>" + testNM + "</td>"

					// threadCount
					+ "<td>" + ng + "</td>"

					// responseCode
					+ "<td>" + responseCode + "</td>"

					// duration
					+ "<td>" + duration + "</td>"

					// latency
					+ "<td>" + latency + "</td>"

					// bytes
					+ "<td>" + bytes + "</td>"
					// failMsg
					+ "<td>" + responseMsg + "</td></tr>";
			Html_Content_String = Html_Content_String + "</table></body></html>";

			Utilities.createFile(Utilities.getCurrentWorkingDir() + "/" + Constants.jmeterReportDir, testNM + ".html",
					Html_Header_String + Html_Content_String);
			Html_Content_String = "";
			LOGGER.info("HTML Report created successfull : " + testNM);
		}
	}

}
