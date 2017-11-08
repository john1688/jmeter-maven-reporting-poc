package com.mobility.utilities;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import au.com.bytecode.opencsv.CSVReader;

public class Utilities {

	private static final Logger LOGGER = Logger.getLogger(Utilities.class);

	/*
	 * return random number of input size.
	 */
	public static String generateNumber(int length) {
		Random rng1 = new Random();
		String characters = "0123456789";
		char[] text = new char[length];
		for (int i = 0; i < length; i++) {
			text[i] = characters.charAt(rng1.nextInt(characters.length()));
		}
		return new String(text);
	}

	/*
	 * convert extension to csv
	 */
	public static void convertJtlToCsv(String dirLoc) throws Exception {
		try {
			File dir = new File(dirLoc);
			File[] directoryListing = dir.listFiles();
			if (directoryListing != null) {
				for (File child : directoryListing) {
					if (child.getName().contains("jtl")) {
						File file = new File(child.getParent() + "/" + child.getName().split("\\.")[0] + ".csv");
						child.renameTo(file);
						LOGGER.info(child.getAbsolutePath() + " :Successfully renamed");
					}
				}

			}

		} catch (Exception e) {
			throw e;
		}
	}

	/*
	 * return the pref samples into arraylist
	 */
	public static ArrayList<String[]> getSamplerResultsIntoList(String dirLoc) throws Exception {

		File dir = new File(dirLoc);
		ArrayList<String[]> testData = new ArrayList<>();
		String[] nextLine;
		File[] directoryListing = dir.listFiles();

		if (directoryListing != null) {
			for (File child : directoryListing) {
				System.out.println("Starting Reading file: " + child);

				try (CSVReader reader = new CSVReader(new FileReader(child))) {
					reader.readNext();
					while ((nextLine = reader.readNext()) != null) {
						if (nextLine[2].contains("Perf")) {
							testData.add(nextLine);
						}
					}
				} catch (Exception e) {
					throw e;
				}

			}
		}
		return testData;
	}

	/*
	 * Create file input: fileLocation, filename, fileText
	 */
	public static void createFile(String fileLocation, String fileName, String text) throws Exception {
		deleteExistingFile(fileLocation, fileName);

		File file = new File(fileLocation, fileName);
		file.getParentFile().mkdirs();
		file.createNewFile();

		FileWriter fw = new FileWriter(file.getAbsoluteFile());
		fw.write(text);
		fw.close();
	}

	private static void deleteExistingFile(String fileLocation, String fileName) throws Exception {

		File file = new File(fileLocation, fileName);

		if (file.exists()) {
			file.delete();
		}
	}

	// /Users/sumitsoman/Documents/workspace/mobility-performance/target/jmeter/bin/target/jmeter/results/
	public static String getCurrentWorkingDir() {
		return Constants.userDir.split("target")[0];
	}

	/*
	 * Copy files from source to destination
	 */
	public static boolean copyFile(File source, File dest) throws IOException {
		FileUtils.copyDirectory(source, dest);
		return true;
	}

	/**
	 * Gets the day.
	 *
	 * @return the day
	 */
	public static String getDay() {
		String ret = "";
		SimpleDateFormat sdf = new SimpleDateFormat("dd");
		ret = sdf.format(new Date());
		return ret;
	}

	/**
	 * Gets the today.
	 *
	 * @param format
	 *            the format
	 * @return the today
	 */
	public static String getToday(String format) {
		String ret = "";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		ret = sdf.format(new Date());
		return ret;
	}

	/**
	 * Gets the date and current time.
	 *
	 * @return the date and current time.
	 * 
	 */
	public static String getTodayDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * Gets the execution duration time.
	 *
	 * @return time in String.
	 * 
	 */
	public static String getRunDuration(String startTime, String endTime) throws Exception {

		SimpleDateFormat format = new SimpleDateFormat("YYYY/MM/dd HH:mm:ss");

		Date ExecutionStartTime = format.parse(startTime);
		Date ExecutionEndTime = format.parse(endTime);
		long diff = (ExecutionEndTime.getTime() - ExecutionStartTime.getTime());
		long diffSeconds = diff / 1000 % 60;
		long diffMinutes = diff / (60 * 1000) % 60;
		long diffHours = diff / (60 * 60 * 1000) % 24;
		long diffDays = diff / (24 * 60 * 60 * 1000);

		return String.valueOf(diffDays) + " Days " + String.valueOf(diffHours) + " Hours " + String.valueOf(diffMinutes)
				+ " Min " + String.valueOf(diffSeconds) + " Sec";

	}

}
