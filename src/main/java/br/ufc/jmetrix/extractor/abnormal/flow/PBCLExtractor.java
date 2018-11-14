package br.ufc.jmetrix.extractor.abnormal.flow;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtDo;
import spoon.reflect.code.CtFor;
import spoon.reflect.code.CtForEach;
import spoon.reflect.code.CtTry;
import spoon.reflect.code.CtWhile;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class PBCLExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberOfProtectedBlocksInControllLoops = 0.0;

			for (CtTry tryBlock : element.getElements(new TypeFilter<CtTry>(CtTry.class))) {
				if (tryBlock.getParent(new TypeFilter<CtFor>(CtFor.class)) != null) {
					numberOfProtectedBlocksInControllLoops++;
				}
				if (tryBlock.getParent(new TypeFilter<CtForEach>(CtForEach.class)) != null) {
					numberOfProtectedBlocksInControllLoops++;
				}
				if (tryBlock.getParent(new TypeFilter<CtWhile>(CtWhile.class)) != null) {
					numberOfProtectedBlocksInControllLoops++;
				}
				if (tryBlock.getParent(new TypeFilter<CtDo>(CtDo.class)) != null) {
					numberOfProtectedBlocksInControllLoops++;
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.PBCL, numberOfProtectedBlocksInControllLoops));
		}
	}
}
