package br.ufc.jmetrix.extractor.abnormal.smell;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class NoOCExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberOfOverCatches = 0.0;

			for (CtCatch catchBlock : element.getElements(new TypeFilter<CtCatch>(CtCatch.class))) {
				if (catchBlock.getParameter().getMultiTypes() != null && catchBlock.getParameter().getMultiTypes().size() > 1) {
					numberOfOverCatches++;
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.NoOC, numberOfOverCatches));
		}
	}
}
