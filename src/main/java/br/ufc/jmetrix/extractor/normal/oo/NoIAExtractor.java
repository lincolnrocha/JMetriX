package br.ufc.jmetrix.extractor.normal.oo;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

public class NoIAExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberOfInheritedAttributes = 0.0;
			if (element.getSuperclass() != null && !element.getSuperclass().isShadow()) {
				CtTypeReference<?> superClass = element.getSuperclass();
				numberOfInheritedAttributes += superClass.getElements(new TypeFilter<CtField<?>>(CtField.class)).size();
			}
			Dataset.store(qualifiedName, new Measure(Metric.NoIA, numberOfInheritedAttributes));
		}
	}
}
