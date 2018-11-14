package br.ufc.jmetrix.extractor.normal.oo;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

public class NoPAExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberOfPublicAttributes = element.getFields().stream().filter(p -> p.isPublic()).count();
			Dataset.store(qualifiedName, new Measure(Metric.NoPA, numberOfPublicAttributes));
		}
	}
}
