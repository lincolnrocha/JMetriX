package br.ufc.jmetrix.extractor.abnormal.smell;

import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtTry;
import spoon.reflect.cu.SourcePosition;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class CLoCExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double cleanupLoC = 0.0;
			for (CtTry tryClause : element.getElements(new TypeFilter<CtTry>(CtTry.class))) {
				if (tryClause.getFinalizer() != null) {
					SourcePosition position = tryClause.getFinalizer().getPosition();
					int startLine = position.getLine();
					int endLine = position.getEndLine();
					cleanupLoC += ((endLine - startLine) == 0) ? (1) : ((endLine - startLine) - 1);
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.CLoC, cleanupLoC));
		}
	}
}
