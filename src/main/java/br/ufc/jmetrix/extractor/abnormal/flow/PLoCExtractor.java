package br.ufc.jmetrix.extractor.abnormal.flow;

import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtTry;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class PLoCExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double protectedLoC = 0.0;
			for (CtTry tryClause : element.getElements(new TypeFilter<CtTry>(CtTry.class))) {
				int startLine = tryClause.getBody().getPosition().getLine();
				int endLine = tryClause.getBody().getPosition().getEndLine();
				protectedLoC += ((endLine - startLine) == 0) ? (1) : ((endLine - startLine) - 1);
			}
			Dataset.store(qualifiedName, new Measure(Metric.PLoC, protectedLoC));
		}
	}
}