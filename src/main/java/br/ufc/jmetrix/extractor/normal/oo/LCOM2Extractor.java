package br.ufc.jmetrix.extractor.normal.oo;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtFieldAccess;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.declaration.CtField;
import spoon.reflect.declaration.CtMethod;
import spoon.reflect.visitor.filter.TypeFilter;

public class LCOM2Extractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {

			double lackOfCohesionOfMethods = 1.0;
			double numberOfMethods = element.getMethods().size();
			double numberOfAttributes = element.getFields().size();
			double summation = 0.0;
			String qualifiedName = element.getQualifiedName();

			if (numberOfMethods > 0 && numberOfAttributes > 0) {
				for (CtField<?> field : element.getFields()) {
					summation += numberOfMethodsAccessingField(element, field.getReference().getSimpleName());
				}
				lackOfCohesionOfMethods = 1.0 - (summation / (numberOfMethods * numberOfAttributes));
			}
			Dataset.store(qualifiedName, new Measure(Metric.LCOM2, lackOfCohesionOfMethods));
		}

	}

	private int numberOfMethodsAccessingField(CtClass<?> element, String fieldName) {
		int numberOfMethods = 0;
		for (CtMethod<?> method : element.getMethods()) {
			for (CtFieldAccess<?> access : method.getElements(new TypeFilter<CtFieldAccess<?>>(CtFieldAccess.class))) {
				if (fieldName.equals(access.getVariable().getSimpleName())) {
					numberOfMethods++;
					break;
				}
			}
		}
		return numberOfMethods;
	}
}
