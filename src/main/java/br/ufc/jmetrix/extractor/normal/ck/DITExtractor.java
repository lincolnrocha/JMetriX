package br.ufc.jmetrix.extractor.normal.ck;

import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

public class DITExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double depthOfInheritanceTree = 1.0;
			depthOfInheritanceTree += Util.getDepthOfInheritanceTree(element.getReference());
			Dataset.store(qualifiedName, new Measure(Metric.DIT, depthOfInheritanceTree));
		}
	}
}
