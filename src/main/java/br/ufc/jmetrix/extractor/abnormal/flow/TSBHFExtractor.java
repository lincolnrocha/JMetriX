package br.ufc.jmetrix.extractor.abnormal.flow;

import java.util.HashSet;
import java.util.Set;

import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.reference.CtTypeReference;

public class TSBHFExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {
		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			Set<String> exceptions = new HashSet<>();
			for (CtMethod<?> method : element.getMethods()) {
				for (CtTypeReference<?> exceptionType : method.getThrownTypes()) {
					if (exceptionType != null)
						exceptions.add(exceptionType.getQualifiedName());
				}
			}
			double numberOfTypesOfSignalingBranchesOfHandlingFlow = exceptions.size();

			Dataset.store(qualifiedName, new Measure(Metric.TSBHF, numberOfTypesOfSignalingBranchesOfHandlingFlow));
		}
	}
}