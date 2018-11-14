package br.ufc.jmetrix.extractor.abnormal.smell;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtBlock;
import spoon.reflect.code.CtThrow;
import spoon.reflect.code.CtTry;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class NoRCBExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberOfRaisingsInCleanupBlocks = 0.0;

			for (CtTry tryBlock : element.getElements(new TypeFilter<CtTry>(CtTry.class))) {
				if (tryBlock.getFinalizer() != null) {
					CtBlock<?> finallyBlock = tryBlock.getFinalizer();
					numberOfRaisingsInCleanupBlocks += finallyBlock.getElements(new TypeFilter<CtThrow>(CtThrow.class)).size();
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.NoRCB, numberOfRaisingsInCleanupBlocks));
		}
	}
}
