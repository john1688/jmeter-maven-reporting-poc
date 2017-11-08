package com.mobility.performance;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.junit.Assert;

import com.mobility.reporting.ProcessReport;
import com.mobility.reporting.ProcessSummary;
import com.mobility.utilities.*;

public class JmeterReportExecutor {

	final static Logger LOGGER = Logger.getLogger(JmeterReportExecutor.class);

	public static void main(String[] args) throws Exception {
		copyJtlToReportFolder();
		HashMap<String, Map<String, String>> httpSamplerAttribute = XmlParser
				.getHttpSamplers(Constants.jmeterReportJtlDir);
		createHTMLReportsFromJTL(httpSamplerAttribute);
		generateSummaryReport(httpSamplerAttribute);
	}

	/*
	 * copy jtl from target to Report folder for compilation
	 * */
	private static void copyJtlToReportFolder() throws Exception {

		Boolean status = Utilities.copyFile(new File(Constants.jmeterJtlDir),
				new File(Constants.jmeterReportDir + "/jtl/"));
		Assert.assertEquals(true, status);
		LOGGER.info("JTLs files copied successfully into Report directory");

	}
	
	
	/*
	 * create HTML report for each jtl report
	 * */
	private static void createHTMLReportsFromJTL(HashMap<String, Map<String, String>> httpSamplerAttribute)
			throws Exception {
		ProcessReport processReportObj = new ProcessReport();

		// Generate separate html reports for every executions
		processReportObj.createReport(httpSamplerAttribute);
	}

	
	/*
	 * create Summary report for all overall execution
	 * */
	public static void generateSummaryReport(HashMap<String, Map<String, String>> httpSamplerAttribute)
			throws Exception {

		ProcessSummary processSummaryObj = new ProcessSummary();
		// Generate Summary execution report
		processSummaryObj.createSummaryReport(httpSamplerAttribute);
	}

}