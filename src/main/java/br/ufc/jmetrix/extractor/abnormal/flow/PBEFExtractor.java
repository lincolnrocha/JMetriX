package br.ufc.jmetrix.extractor.abnormal.flow;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtTry;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class PBEFExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {
		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double protectedBlocksOfexceptionalFlow = (double) element.getElements(new TypeFilter<CtTry>(CtTry.class)).size();
			Dataset.store(qualifiedName, new Measure(Metric.PBEF, protectedBlocksOfexceptionalFlow));
		}
	}
}
