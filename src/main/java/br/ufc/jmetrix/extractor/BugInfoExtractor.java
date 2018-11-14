package br.ufc.jmetrix.extractor;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

public class BugInfoExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberOfBugs = Util.getNumberOfDefects(qualifiedName);

			Dataset.store(qualifiedName, new Measure(Metric.BUG, (numberOfBugs > 0) ? (1.0) : (0.0)));
			Dataset.store(qualifiedName, new Measure(Metric.NoBUG, numberOfBugs));
		}
	}

}
