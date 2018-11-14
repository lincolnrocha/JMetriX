package br.ufc.jmetrix.model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Dataset {

	private final static String DATASET_PATH = "./datasets";

	private static String label = null;

	private static Map<String, Suite> dataset = new HashMap<>();

	public static void setLabel(String label) {
		Dataset.label = label;
	}

	public static void store(String classQualifiedName, Measure measure) {
		Suite suite = null;
		if (dataset.containsKey(classQualifiedName)) {
			suite = dataset.get(classQualifiedName);
		} else {
			suite = new Suite(classQualifiedName);
			dataset.put(classQualifiedName, suite);
		}
		System.out.println("- Class Name: " + classQualifiedName + " [" + measure.getMetric().getShortName() + " = " + measure.getValue() + "]");
		suite.addMeasure(measure);
	}

	public static Collection<Suite> list() {
		return dataset.values();
	}

	public static String toCSV() {
		StringBuffer buffer = new StringBuffer("CLASS_QNAME");
		for (Suite suite : list()) {
			for (Measure measure : suite.getMeasures()) {
				buffer.append(",");
				buffer.append(measure.getMetric().getShortName());

			}
			break;
		}

		for (Suite suite : list()) {
			buffer.append("\n");
			buffer.append(suite.getClassQualifiedName());
			for (Measure measure : suite.getMeasures()) {
				buffer.append(",");
				buffer.append(measure.getValue());
			}
		}
		return buffer.toString();
	}

	public static void generateCSVFile() {
		File directory = new File(DATASET_PATH);
		if (!directory.exists()) {
			directory.mkdir();
		}

		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss");
		dateFormat.setTimeZone(calendar.getTimeZone());
		StringBuffer reportName = new StringBuffer();
		if (label != null && !label.isEmpty()) {
			reportName.append(label);
			reportName.append("-");
		}
		reportName.append("dataset-");
		reportName.append(dateFormat.format(calendar.getTime()));
		reportName.append(".csv");
		String fullReportName = reportName.toString();

		File reportFile = new File(directory, fullReportName);
		try {
			FileWriter fileWriter = new FileWriter(reportFile, true);
			PrintWriter printWriter = new PrintWriter(fileWriter);
			printWriter.append(toCSV());
			printWriter.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		dataset.clear();
	}
}
