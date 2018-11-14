package br.ufc.jmetrix.extractor.abnormal.flow;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtTry;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class CAHFExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberOfCleanupActionsOfHandlingFlow = 0.0;

			for (CtTry tryClause : element.getElements(new TypeFilter<CtTry>(CtTry.class))) {
				if (tryClause.getFinalizer() != null) {
					numberOfCleanupActionsOfHandlingFlow++;
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.CAHF, numberOfCleanupActionsOfHandlingFlow));
		}
	}
}
