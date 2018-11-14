package br.ufc.jmetrix.extractor;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.code.CtThrow;
import spoon.reflect.code.CtTry;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

public class EHMUExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			boolean ehmu = false;

			ehmu = ehmu || !element.getElements(new TypeFilter<>(CtThrow.class)).isEmpty();

			ehmu = ehmu || !element.getElements(new TypeFilter<>(CtTry.class)).isEmpty();

			ehmu = ehmu || !element.getElements(new TypeFilter<CtCatch>(CtCatch.class)).isEmpty();

			if (!ehmu) {
				for (CtMethod<?> method : element.getMethods()) {
					ehmu = ehmu || !method.getThrownTypes().isEmpty();
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.EHMU, ((ehmu) ? (1.0) : (0.0))));
		}
	}

}
