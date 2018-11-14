package br.ufc.jmetrix.extractor.normal.ck;

import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.reference.CtTypeReference;

public class CBOExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double couplingBetweenObjects = 0.0;
			for (CtTypeReference<?> referredType : element.getReferencedTypes()) {
				if (referredType != null && !referredType.isShadow()) {
					couplingBetweenObjects++;
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.CBO, couplingBetweenObjects));
		}
	}
}
