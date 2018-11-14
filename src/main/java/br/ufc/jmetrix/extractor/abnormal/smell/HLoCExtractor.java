package br.ufc.jmetrix.extractor.abnormal.smell;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.Util;
import spoon.processing.AbstractProcessor;
import spoon.reflect.code.CtCatch;
import spoon.reflect.declaration.CtClass;
import spoon.reflect.visitor.filter.TypeFilter;

public class HLoCExtractor extends AbstractProcessor<CtClass<?>> {

	@Override
	public void process(CtClass<?> element) {

		if (Util.isValid(element)) {
			String qualifiedName = element.getQualifiedName();
			double handlingLoC = 0.0;

			for (CtCatch catchClause : element.getElements(new TypeFilter<CtCatch>(CtCatch.class))) {
				int numberOfStatments = catchClause.getBody().getStatements().size();
				if (numberOfStatments > 0) {
					int startLine = catchClause.getBody().getPosition().getLine();
					int endLine = catchClause.getBody().getPosition().getEndLine();
					handlingLoC += ((endLine - startLine) == 0) ? (1) : ((endLine - startLine) - 1);
				}
			}
			Dataset.store(qualifiedName, new Measure(Metric.HLoC, handlingLoC));
		}
	}
}
