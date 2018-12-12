package br.ufc.jmetrix.extractor.normal.oo;

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

public class LCOM2ExtractorTest {
	private static SpoonAPI spoonAPI;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		spoonAPI = new Launcher();
		
		spoonAPI.addInputResource("../bank-sys/src/main/");
		spoonAPI.getEnvironment().setNoClasspath(true);
		spoonAPI.buildModel();
		spoonAPI.addProcessor("br.ufc.jmetrix.extractor.normal.oo.LCOM2Extractor");
		
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
		test("banksys.account.AbstractAccount", Metric.LCOM2, 1.0 - 3.0 / (4.0 * 2.0), 0.001);
	}
	
	@Test
	public void testOrdinaryAccount() {
		test("banksys.account.OrdinaryAccount", Metric.LCOM2, 1.0, 0.001);
	}
	
	@Test
	public void testSavingsAccount() {
		test("banksys.account.SavingsAccount", Metric.LCOM2, 1.0, 0.001);
	}
	
	@Test
	public void testSpecialAccount() {
		test("banksys.account.SpecialAccount", Metric.LCOM2, 1.0 - 3.0 / (3.0 * 1.0), 0.001);
	}
	
	@Test
	public void testTaxAccount() {
		test("banksys.account.TaxAccount", Metric.LCOM2, 1.0, 0.001);
	}
	
	@Test
	public void testInsufficientFundsException() {
		test("banksys.account.exception.InsufficientFundsException", Metric.LCOM2, 1.0 - 4.0 / (3.0 * 3.0), 0.001);
	}

	@Test
	public void testNegativeAmountException() {
		test("banksys.account.exception.NegativeAmountException", Metric.LCOM2, 1.0 - 2.0 / (2.0 * 2.0), 0.001);
	}
	
	@Test
	public void testBankController() {
		test("banksys.control.BankController", Metric.LCOM2, 1.0 - 9.0 / (9.0 * 1.0), 0.001);
	}
	
	@Test
	public void testBankTransactionException() {
		test("banksys.control.exception.BankTransactionException", Metric.LCOM2, 1.0 - 3.0 / (2.0 * 3.0), 0.001);
	}
	
	@Test
	public void testIncompatibleAccountException() {
		test("banksys.control.exception.IncompatibleAccountException", Metric.LCOM2, 1.0, 0.001);
	}
	
	@Test
	public void testAccountVector() {
		test("banksys.persistence.AccountVector", Metric.LCOM2, 1.0 - 5.0 / (7.0 * 1.0), 0.001);
	}
	
	@Test
	public void testAccountXStream() {
		test("banksys.persistence.AccountXStream", Metric.LCOM2, 1.0 - 9.0 / (9.0 * 2.0), 0.001);
	}
	
	@Test
	public void testAccountCreationException() {
		test("banksys.persistence.exception.AccountCreationException", Metric.LCOM2, 1.0, 0.001);
	}
	
	@Test
	public void testAccountDeletionException() {
		test("banksys.persistence.exception.AccountDeletionException", Metric.LCOM2, 1.0, 0.001);
	}
	
	@Test
	public void testAccountNotFoundException() {
		test("banksys.persistence.exception.AccountNotFoundException", Metric.LCOM2, 1.0, 0.001);
	}
	
	@Test
	public void testExistingAccountException() {
		test("banksys.persistence.exception.ExistingAccountException", Metric.LCOM2, 1.0 - 2.0 / (2.0 * 2.0), 0.001);
	}

	@Test
	public void testFlushException() {
		test("banksys.persistence.exception.FlushException", Metric.LCOM2, 1.0, 0.001);
	}
	
	@Test
	public void testPersistenceException() {
		test("banksys.persistence.exception.PersistenceException", Metric.LCOM2, 1.0 - 3.0 / (2.0 * 3.0), 0.001);
	}
	
	@Test
	public void testATM24H() {
		test("banksys.view.ATM24H", Metric.LCOM2, 1.0 - 3.0 / (3.0 * 1.0), 0.001);
	}
}