package br.ufc.jmetrix.extractor.normal.oo;

import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

public class LoCExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			int startLine = element.getPosition().getLine();
			int endLine = element.getPosition().getEndLine();
			double totalLoC = ((endLine - startLine) == 0) ? (1) : ((endLine - startLine) - 1);
			Dataset.store(qualifiedName, new Measure(Metric.LoC, totalLoC));
		}
	}
}
