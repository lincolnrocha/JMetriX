package br.ufc.jmetrix.util;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;

import br.ufc.jmetrix.api.InputInfo;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeReference;

public class Util {

	private static Map<String, Double> defectGroundTruth = new HashMap<>();

	public static boolean isValid(CtType<?> element) {
		return element != null && element.getQualifiedName() != null && (element.isClass() || element.isInterface())
				&& !element.isAnonymous() && !element.isLocalType() && !element.isShadow();
	}

	public static int getDepthOfInheritanceTree(CtTypeReference<?> type) {
		if (type.isShadow() || type.getSuperclass() == null) {
			return 0;
		} else {
			return 1 + getDepthOfInheritanceTree(type.getSuperclass());
		}
	}

	public static boolean inherits(CtTypeReference<?> type, CtTypeReference<?> parentCandidate) {
		if (!type.isShadow()) {
			CtTypeReference<?> parent = type.getSuperclass();
			if (parent != null) {
				String parentName = parent.getQualifiedName();
				String parentCandidateName = parentCandidate.getQualifiedName();
				if (parentName != null && parentCandidateName.equals(parentName)) {
					return true;
				} else {
					return inherits(parent, parentCandidate);
				}
			}
		}
		return false;
	}

	public static void loadDefectGroundTruth(String filePath) throws IOException {
		Reader in = new FileReader(filePath);
		Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
		defectGroundTruth.clear();
		for (CSVRecord record : records) {
			defectGroundTruth.put(record.get(0), Double.parseDouble(record.get(1)));
		}
	}

	public static List<InputInfo> loadInputInfoFile(String filePath) throws IOException {
		List<InputInfo> inputInfos = new ArrayList<>();
		Reader in = new FileReader(filePath);
		Iterable<CSVRecord> records = CSVFormat.RFC4180.parse(in);
		InputInfo inputInfo;
		for (CSVRecord record : records) {
			if (record.size() > 2) {
				inputInfo = new InputInfo(record.get(0), record.get(1), record.get(2));
			} else {
				inputInfo = new InputInfo(record.get(0), record.get(1));
			}

			inputInfos.add(inputInfo);
		}
		return inputInfos;
	}

	public static Double getNumberOfDefects(String classQualifiedName) {
		if (defectGroundTruth.containsKey(classQualifiedName)) {
			return defectGroundTruth.get(classQualifiedName);
		} else {
			return 0.0;
		}
	}
}