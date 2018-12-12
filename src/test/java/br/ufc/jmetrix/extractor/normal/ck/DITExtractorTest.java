package br.ufc.jmetrix.extractor.normal.ck;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.model.Suite;
import spoon.Launcher;
import spoon.SpoonAPI;

public class DITExtractorTest {
	private static SpoonAPI spoonAPI;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		spoonAPI = new Launcher();
		
		spoonAPI.addInputResource("../bank-sys/src/main/");
		spoonAPI.getEnvironment().setNoClasspath(true);
		spoonAPI.buildModel();
		spoonAPI.addProcessor("br.ufc.jmetrix.extractor.normal.ck.DITExtractor");
		
		spoonAPI.process();
	}
	
	@Ignore
	public void test(String classQualifiedName, Metric metric, double expected, double delta) {
		boolean classFound = false, metricFound = false;
		
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals(classQualifiedName)) {
				classFound = true;
				
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == metric) {
						metricFound = true;
						
						assertEquals(expected, measure.getValue(), delta);
					}
				}
				
				if(!metricFound) {
					fail("A classe '" + classQualifiedName + "' não pôde ser avaliada na métrica '" + metric + "'.");
				}
			}
		}
		
		if(!classFound) {
			fail("Não foi possível encontrar a classe '" + classQualifiedName + "'.");
		}
	}

	@Test
	public void testAbstractAccount() {
		test("banksys.account.AbstractAccount", Metric.DIT, 1.0, 0.001);
	}
	
	@Test
	public void testOrdinaryAccount() {
		test("banksys.account.OrdinaryAccount", Metric.DIT, 2.0, 0.001);
	}
	
	@Test
	public void testSavingsAccount() {
		test("banksys.account.SavingsAccount", Metric.DIT, 3.0, 0.001);
	}
	
	@Test
	public void testSpecialAccount() {
		test("banksys.account.SpecialAccount", Metric.DIT, 3.0, 0.001);
	}
	
	@Test
	public void testTaxAccount() {
		test("banksys.account.TaxAccount", Metric.DIT, 2.0, 0.001);
	}
	
	@Test
	public void testInsufficientFundsException() {
		test("banksys.account.exception.InsufficientFundsException", Metric.DIT, 3.0, 0.001);
	}

	@Test
	public void testNegativeAmountException() {
		test("banksys.account.exception.NegativeAmountException", Metric.DIT, 3.0, 0.001);
	}
	
	@Test
	public void testBankController() {
		test("banksys.control.BankController", Metric.DIT, 1.0, 0.001);
	}
	
	@Test
	public void testBankTransactionException() {
		test("banksys.control.exception.BankTransactionException", Metric.DIT, 3.0, 0.001);
	}
	
	@Test
	public void testIncompatibleAccountException() {
		test("banksys.control.exception.IncompatibleAccountException", Metric.DIT, 4.0, 0.001);
	}
	
	@Test
	public void testAccountVector() {
		test("banksys.persistence.AccountVector", Metric.DIT, 1.0, 0.001);
	}
	
	@Test
	public void testAccountXStream() {
		test("banksys.persistence.AccountXStream", Metric.DIT, 1.0, 0.001);
	}
	
	@Test
	public void testAccountCreationException() {
		test("banksys.persistence.exception.AccountCreationException", Metric.DIT, 4.0, 0.001);
	}
	
	@Test
	public void testAccountDeletionException() {
		test("banksys.persistence.exception.AccountDeletionException", Metric.DIT, 4.0, 0.001);
	}
	
	@Test
	public void testAccountNotFoundException() {
		test("banksys.persistence.exception.AccountNotFoundException", Metric.DIT, 4.0, 0.001);
	}
	
	@Test
	public void testExistingAccountException() {
		test("banksys.persistence.exception.ExistingAccountException", Metric.DIT, 3.0, 0.001);
	}

	@Test
	public void testFlushException() {
		test("banksys.persistence.exception.FlushException", Metric.DIT, 4.0, 0.001);
	}
	
	@Test
	public void testPersistenceException() {
		test("banksys.persistence.exception.PersistenceException", Metric.DIT, 3.0, 0.001);
	}
	
	@Test
	public void testATM24H() {
		test("banksys.view.ATM24H", Metric.DIT, 1.0, 0.001);
	}
}
