package br.ufc.jmetrix.extractor.normal.oo;

import br.ufc.jmetrix.api.JMetriXAPI;
import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;

public class CeExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double efferentCoupling = JMetriXAPI.getEfferentCoupling(qualifiedName);
			//for (CtTypeReference<?> type : element.getReferencedTypes()) {
			//	if (type != null && !type.isShadow() && !type.isAnonymous() && !type.isLocalType()) {
			//		efferentCoupling++;
			//	}
			//}
			Dataset.store(qualifiedName, new Measure(Metric.Ce, efferentCoupling));
		}
	}
}
