package br.ufc.jmetrix.extractor.abnormal.smell;

import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class NoEHExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberOfEmptyHandlers = element.getElements(new TypeFilter<CtCatch>(CtCatch.class)).stream()
					.filter(c -> c.getBody().getStatements().isEmpty()).count();
			Dataset.store(qualifiedName, new Measure(Metric.NoEH, numberOfEmptyHandlers));
		}
	}
}
