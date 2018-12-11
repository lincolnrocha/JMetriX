package br.ufc.jmetrix.util;

import static org.junit.Assert.fail;

import org.junit.Assert;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.model.Suite;

public class UtilTest {
	
	public static void test(String classQualifiedName, Metric metric, double expected, double delta) 
	{
		boolean classFound = false; 
		boolean metricFound = false;
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals(classQualifiedName)) {
				classFound = true;
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == metric) {
						metricFound = true;
						Assert.assertEquals(expected, measure.getValue(), delta);
					}
				}
				
				if(!metricFound) {
					fail("A classe " + classQualifiedName + " não pôde ser avaliada na métrica " + metric + ".");
				}
				
			}
		}
		
		if(!classFound) {
			fail("Não foi possível encontrar a classe " + classQualifiedName + ".");
		}
	}

}
