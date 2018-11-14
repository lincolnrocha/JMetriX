package br.ufc.jmetrix.extractor.normal.oo;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

public class NoMExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {
		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberOfMethods = element.getMethods().size();
			if (element.getSuperclass() != null && !element.getSuperclass().isShadow()) {
				CtTypeReference<?> superClass = element.getSuperclass();
				numberOfMethods += superClass.getElements(new TypeFilter<CtMethod<?>>(CtMethod.class)).size();
			}
			Dataset.store(qualifiedName, new Measure(Metric.NoM, numberOfMethods));
		}
	}
}
