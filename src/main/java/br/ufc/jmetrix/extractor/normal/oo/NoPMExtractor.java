package br.ufc.jmetrix.extractor.normal.oo;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

public class NoPMExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {
		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberPublicOfMethods = element.getMethods().stream().filter(m -> m.isPublic()).count();
			Dataset.store(qualifiedName, new Measure(Metric.NoPM, numberPublicOfMethods));
		}
	}
}
