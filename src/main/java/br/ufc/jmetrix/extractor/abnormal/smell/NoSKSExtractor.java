package br.ufc.jmetrix.extractor.abnormal.smell;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;

public class NoSKSExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberOfSignalingTheKitchenSink = 0.0;

			for (CtMethod<?> method : element.getMethods()) {
				if (method.getThrownTypes().size() > 1)
					numberOfSignalingTheKitchenSink++;
			}
			Dataset.store(qualifiedName, new Measure(Metric.NoSKS, numberOfSignalingTheKitchenSink));
		}
	}
}
