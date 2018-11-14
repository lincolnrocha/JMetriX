package br.ufc.jmetrix.extractor.abnormal.flow;

import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

public class SBHFExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {
		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberOfSignalingBranchesOfHandlingFlow = 0.0;

			for (CtMethod<?> method : element.getMethods()) {
				numberOfSignalingBranchesOfHandlingFlow += method.getThrownTypes().size();
			}
			Dataset.store(qualifiedName, new Measure(Metric.SBHF, numberOfSignalingBranchesOfHandlingFlow));
		}
	}
}
