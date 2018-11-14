package br.ufc.jmetrix.api;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.util.Util;
import spoon.MavenLauncher;
import spoon.MavenLauncher.SOURCE_TYPE;
import spoon.SpoonAPI;
import spoon.reflect.CtModel;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtType;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

public class JMetriXAPI {

	private static SpoonAPI spoon;

	private static Map<String, String> superTypeMap = new HashMap<>();

	private static Map<String, Set<String>> typeDependecyMap = new HashMap<>();

	public static void extract(String path) {
		buildModel(path, true);
	}

	public static void extract(String label, String path, String defectGroundTruthFilePath) {
		try {
			System.out.print("- [RUNNING] Loading Defect Ground Truth...");
			Util.loadDefectGroundTruth(defectGroundTruthFilePath);
			System.out.println("[DONE!]");
		} catch (IOException e) {
			e.printStackTrace();
		}
		extract(label, path);
	}

	public static void extract(String label, String path) {
		Dataset.setLabel(label);
		buildModel(path, true);
	}

	public static void buildModel(String path, boolean withoutDependencies) {
		spoon = new MavenLauncher(path, SOURCE_TYPE.APP_SOURCE);
		spoon.getEnvironment().setNoClasspath(withoutDependencies);
		System.out.print("- [RUNNING] Code Scanning and Model Building...");
		spoon.buildModel();
		System.out.println("[DONE!]");
		System.out.print("- [RUNNING] Total number of classes... ");
		System.out.print(getAllValidTypes().size() + " [DONE!]");
		System.out.println();
		System.out.print("- [RUNNING] Model Configuration...");
		configure();
		fillSuperTypeMapping();
		fillTypeDependencyMapping();
		System.out.println("[DONE!]");
		System.out.println("- [RUNNING] Metric Extraction...");
		process();
		System.out.println("[DONE!]");
		System.out.print("- [RUNNING] Dataset CSV File Generation...");
		Dataset.generateCSVFile();
		System.out.println("[DONE!]");
	}

	public static SpoonAPI getSpoonAPI() {
		return spoon;
	}

	public static CtModel getModel() {
		return spoon.getModel();
	}

	public static Collection<CtClass<?>> getAllClasses() {
		return spoon.getModel().getElements(new TypeFilter<>(CtClass.class));
	}

	public static Collection<CtClass<?>> getAllValidClasses() {
		return spoon.getModel().getElements(new TypeFilter<CtClass<?>>(CtClass.class)).stream()
				.filter(t -> Util.isValid(t)).collect(Collectors.<CtClass<?>> toList());
	}

	public static Collection<CtType<?>> getAllValidTypes() {
		return spoon.getModel().getAllTypes().stream().filter(t -> Util.isValid(t))
				.collect(Collectors.<CtType<?>> toList());
	}

	public static Collection<CtType<?>> getAllTypes() {
		return spoon.getModel().getAllTypes();
	}

	private static void fillSuperTypeMapping() {
		for (CtType<?> type : getAllValidTypes()) {
			CtTypeReference<?> parent = null;
			if (type.isClass()) {
				parent = type.getSuperclass();
			}
			if (parent != null && parent.getQualifiedName() != null) {
				superTypeMap.put(type.getQualifiedName(), parent.getQualifiedName());
			} else {
				superTypeMap.put(type.getQualifiedName(), "java.lang.Object");
			}
		}
	}

	public static long getNumberOfChildren(String parentCandidateName) {
		return superTypeMap.values().stream().filter(name -> name.equals(parentCandidateName)).count();
	}

	public static long getEfferentCoupling(String typeQualifiedName) {
		if (typeDependecyMap.containsKey(typeQualifiedName)) {
			return typeDependecyMap.get(typeQualifiedName).size();
		} else {
			return 0;
		}
	}

	public static long getAfferentCoupling(String typeQualifiedName) {
		return typeDependecyMap.values().stream().filter(set -> set.contains(typeQualifiedName)).count();
	}

	private static void fillTypeDependencyMapping() {
		for (CtType<?> type : getAllValidTypes()) {
			typeDependecyMap.put(type.getQualifiedName(), new HashSet<>());
			for (CtTypeReference<?> referredType : type.getReferencedTypes()) {
				if (referredType != null && !referredType.isShadow() && referredType.getQualifiedName() != null) {
					typeDependecyMap.get(type.getQualifiedName()).add(referredType.getQualifiedName());
				}
			}
		}
	}

	private static void configure() {
		addCKExtractors();
		addOOExtractors();
		addEHFlowExtractors();
		addEHSmellsExtractors();
		spoon.addProcessor("br.ufc.jmetrix.extractor.EHMUExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.BugInfoExtractor");

		// http://www.cs.sjsu.edu/~pearce/cs251b/DesignMetrics.htm
		// https://www.leepoint.net/principles_and_practices/complexity/complexity-java-method.html
	}

	private static void addCKExtractors() {
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.ck.NOCExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.ck.CBOExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.ck.DITExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.ck.RFCExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.ck.WMCExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.ck.LCOMExtractor");
	}

	private static void addOOExtractors() {
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.LoCExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.LCOM2Extractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.LCOM3Extractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.CeExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.CaExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoAExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoDAExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoIAExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoPAExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoPRAExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoMExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoDMExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoIMExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoPMExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoPRMExtractor");
	}

	private static void addEHFlowExtractors() {
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.PLoCExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.PBCLExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.SPEFExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.PBEFExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.PBHFExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.SBHFExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.CAHFExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.TSPEFExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.TPBHFExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.TSBHFExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.PBCCExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.ISMCExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.IMPBExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.flow.IMHBExtractor");
	}


	private static void addEHSmellsExtractors() {
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.HLoCExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.CLoCExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.NoRCBExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.NoSKSExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.NoOCExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.NoOCAExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.NoCRNExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.NoCAExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.NoCIExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.NoDRExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.NoEHExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.NoGHExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.NoGSExtractor");
		spoon.addProcessor("br.ufc.jmetrix.extractor.abnormal.smell.NoNPBExtractor");
	}

	private static void process() {
		spoon.process();
	}

}
