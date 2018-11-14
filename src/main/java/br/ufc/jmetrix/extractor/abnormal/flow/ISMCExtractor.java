package br.ufc.jmetrix.extractor.abnormal.flow;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class ISMCExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberOfInvocationOfSignalerMethodsPerClass = 0.0;
			TypeFilter<CtInvocation<?>> invocationFilter = new TypeFilter<CtInvocation<?>>(CtInvocation.class);

			for (CtInvocation<?> invocation : element.getElements(invocationFilter)) {
				if (invocation.getTarget() != null) {
					if (invocation.getExecutable() != null && invocation.getExecutable().getDeclaration() != null) {
						if (!invocation.getExecutable().getDeclaration().getThrownTypes().isEmpty()) {
							numberOfInvocationOfSignalerMethodsPerClass++;
						}
					}
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.ISMC, numberOfInvocationOfSignalerMethodsPerClass));
		}
	}
}
