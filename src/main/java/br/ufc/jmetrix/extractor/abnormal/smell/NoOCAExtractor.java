package br.ufc.jmetrix.extractor.abnormal.smell;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.code.CtInvocation;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class NoOCAExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double numberOfOverCatchesAndAbort = 0.0;
			TypeFilter<CtInvocation<?>> invocationFilter = new TypeFilter<CtInvocation<?>>(CtInvocation.class);

			for (CtCatch catchBlock : element.getElements(new TypeFilter<CtCatch>(CtCatch.class))) {
				if (catchBlock.getParameter().getMultiTypes() != null && catchBlock.getParameter().getMultiTypes().size() > 1) {
					for (CtInvocation<?> invocation : catchBlock.getElements(invocationFilter)) {
						if (invocation.getTarget() != null && invocation.getExecutable() != null) {
							String targetClass = invocation.getTarget().toString();
							String calledMethodSignature = invocation.getExecutable().getSignature();
							if (targetClass.equals("java.lang.System") && calledMethodSignature.equals("exit(int)")) {
								numberOfOverCatchesAndAbort++;
							}
						}
					}
				}
			}

			Dataset.store(qualifiedName, new Measure(Metric.NoOCA, numberOfOverCatchesAndAbort));
		}
	}
}
