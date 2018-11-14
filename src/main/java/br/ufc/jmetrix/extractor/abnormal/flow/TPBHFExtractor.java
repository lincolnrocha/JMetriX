package br.ufc.jmetrix.extractor.abnormal.flow;

import java.util.HashSet;
import java.util.Set;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

public class TPBHFExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			Set<String> exceptions = new HashSet<>();

			for (CtCatch catchClause : element.getElements(new TypeFilter<CtCatch>(CtCatch.class))) {
				for (CtTypeReference<?> exceptionType : catchClause.getParameter().getMultiTypes()) {
					if (exceptionType != null) {
						exceptions.add(exceptionType.getQualifiedName());
					}
				}
			}
			double numberOfTypesOfProtectedBranchesOfHandlingFlow = exceptions.size();

			Dataset.store(qualifiedName, new Measure(Metric.TPBHF, numberOfTypesOfProtectedBranchesOfHandlingFlow));
		}
	}
}
