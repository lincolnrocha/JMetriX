package br.ufc.jmetrix.extractor.abnormal.flow;

import java.util.HashSet;
import java.util.Set;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtThrow;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.reference.CtTypeReference;
import spoon.reflect.visitor.filter.TypeFilter;

public class TSPEFExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			double numberOfTypesOfStartPointsOfExceptionalFlow = 0.0;
			String qualifiedName = element.getQualifiedName();
			Set<String> exceptions = new HashSet<>();
			for (CtThrow throwClause : element.getElements(new TypeFilter<CtThrow>(CtThrow.class))) {
				CtTypeReference<?> exceptionType = throwClause.getThrownExpression().getType();
				if (exceptionType != null)
					exceptions.add(exceptionType.getQualifiedName());
			}
			numberOfTypesOfStartPointsOfExceptionalFlow = exceptions.size();

			Dataset.store(qualifiedName, new Measure(Metric.TSPEF, numberOfTypesOfStartPointsOfExceptionalFlow));
		}
	}

}
