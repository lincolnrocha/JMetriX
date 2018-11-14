package br.ufc.jmetrix;

import java.io.IOException;
import java.util.List;

import br.ufc.jmetrix.api.InputInfo;
import br.ufc.jmetrix.api.JMetriXAPI;
import br.ufc.jmetrix.util.Util;

public class Runner {
	private static String inputFile = null;

	public static void main(String[] args) throws IOException {
		String value = null;
		for (int i = 0; i < args.length; i++) {
			if (i + 1 < args.length) {
				value = args[i + 1];
			} else {
				value = null;
			}

			if (args[i].equals("-h")) {
				help();
			} else if (args[i].equals("-f")) {
				inputFile = value;
			}
		}

		if (inputFile == null) {
			help();
		}

		StringBuffer message = new StringBuffer();
		List<InputInfo> inputInfos = Util.loadInputInfoFile(inputFile);
		message.append("\n=====================================================================================");
		message.append("\nHello, JMetriX just started! ");
		message.append(inputInfos.size());
		message.append(" project(s) will be processed.");
		message.append("\nThis may take a while. Please, be patient! ;-)");
		message.append("\n=====================================================================================");
		int count = 1;
		for (InputInfo info : inputInfos) {
			message.append("\n\n=====================================================================================");
			message.append("\n (" + count++ + ") Processing Project + " + info.getLabel());
			message.append("\n\n - Extracting metrics from ");
			message.append(info.getProjectSourcePath());
			message.append(";");

			if (info.getDefectGroundTruthFile() != null) {
				message.append("\n - Reading defect ground truth info from ");
				message.append(info.getDefectGroundTruthFile());
				message.append(";");
			}
			message.append(" \n - Generating a result dataset ");
			message.append("labeled as ");
			message.append(info.getLabel());
			message.append(" at ./datasets directory.");
			System.out.println(message.toString());

			if (info.getDefectGroundTruthFile() != null) {
				JMetriXAPI.extract(info.getLabel(), info.getProjectSourcePath(), info.getDefectGroundTruthFile());
			} else {
				JMetriXAPI.extract(info.getLabel(), info.getProjectSourcePath());
			}
		}
	}

	private static void help() {
		System.out.println(".::JMetrix Usage::.");
		System.out.println(
				"$ java -jar jmetrix-x.y.z.jar -f <input-info-file>.csv (for metric and defect ground truth extraction)");
		System.out.println();
		System.out.println("<input-info-file> internal line formats:");
		System.out.println("- for metric extraction only: <dataset-name>,<project-pom-path>");
		System.out.println(" or ");
		System.out.println("- for metric extraction defect labeling: <dataset-name>,<project-pom-path>,<ground-truth-file-path>");
		System.out.println();
		System.out.println("$ java -jar jmetrix-x.y.z.jar -h (for help)");
		System.exit(0);
	}
}
