package br.ufc.jmetrix.api;

public class InputInfo {

	private String label;

	private String projectSourcePath;

	private String defectGroundTruthFile;

	public InputInfo(String label, String projectSourcePath, String defectGroundTruthFile) {
		super();
		this.label = label;
		this.projectSourcePath = projectSourcePath;
		this.defectGroundTruthFile = defectGroundTruthFile;
	}

	public InputInfo(String label, String projectSourcePath) {
		this(label, projectSourcePath, null);
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getProjectSourcePath() {
		return projectSourcePath;
	}

	public void setProjectSourcePath(String projectSourcePath) {
		this.projectSourcePath = projectSourcePath;
	}

	public String getDefectGroundTruthFile() {
		return defectGroundTruthFile;
	}

	public void setDefectGroundTruthFile(String defectGroundTruthFile) {
		this.defectGroundTruthFile = defectGroundTruthFile;
	}

}
