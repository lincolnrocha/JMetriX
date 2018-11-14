package br.ufc.jmetrix.model;

import java.util.Collection;
import java.util.Vector;

public class Suite {

	private String classQualifiedName;

	private Collection<Measure> measures;

	public Suite(String classQualifiedName) {
		this.classQualifiedName = classQualifiedName;
		this.measures = new Vector<>();
	}

	public String getClassQualifiedName() {
		return classQualifiedName;
	}

	public void setClassQualifiedName(String classQualifiedName) {
		this.classQualifiedName = classQualifiedName;
	}

	public Collection<Measure> getMeasures() {
		return measures;
	}

	public void addMeasure(Measure measure) {
		if (measures.stream().filter(m -> m.getMetric().getShortName().equals(measure.getMetric().getShortName())).count() == 0) {
			this.measures.add(measure);
		}
	}

}
