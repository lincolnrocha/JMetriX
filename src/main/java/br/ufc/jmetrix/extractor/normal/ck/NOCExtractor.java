package br.ufc.jmetrix.extractor.normal.ck;

import br.ufc.jmetrix.api.JMetriXAPI;
import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

public class NOCExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();

			double numberOfChildren = JMetriXAPI.getNumberOfChildren(qualifiedName);

			Dataset.store(qualifiedName, new Measure(Metric.NOC, numberOfChildren));
		}
	}
}
