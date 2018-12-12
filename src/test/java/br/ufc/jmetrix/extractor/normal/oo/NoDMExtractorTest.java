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

public class NoDMExtractorTest {
	private static SpoonAPI spoonAPI;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		spoonAPI = new Launcher();
		
		spoonAPI.addInputResource("../bank-sys/src/main/");
		spoonAPI.getEnvironment().setNoClasspath(true);
		spoonAPI.buildModel();
		spoonAPI.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoDMExtractor");
		
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
		test("banksys.account.AbstractAccount", Metric.NoDM, 4.0, 0.001);
	}
	
	@Test
	public void testOrdinaryAccount() {
		test("banksys.account.OrdinaryAccount", Metric.NoDM, 1.0, 0.001);
	}
	
	@Test
	public void testSavingsAccount() {
		test("banksys.account.SavingsAccount", Metric.NoDM, 1.0, 0.001);
	}
	
	@Test
	public void testSpecialAccount() {
		test("banksys.account.SpecialAccount", Metric.NoDM, 3.0, 0.001);
	}
	
	@Test
	public void testTaxAccount() {
		test("banksys.account.TaxAccount", Metric.NoDM, 1.0, 0.001);
	}
	
	@Test
	public void testInsufficientFundsException() {
		test("banksys.account.exception.InsufficientFundsException", Metric.NoDM, 3.0, 0.001);
	}

	@Test
	public void testNegativeAmountException() {
		test("banksys.account.exception.NegativeAmountException", Metric.NoDM, 2.0, 0.001);
	}
	
	@Test
	public void testBankController() {
		test("banksys.control.BankController", Metric.NoDM, 9.0, 0.001);
	}
	
	@Test
	public void testBankTransactionException() {
		test("banksys.control.exception.BankTransactionException", Metric.NoDM, 2.0, 0.001);
	}
	
	@Test
	public void testIncompatibleAccountException() {
		test("banksys.control.exception.IncompatibleAccountException", Metric.NoDM, 0.0, 0.001);
	}
	
	@Test
	public void testAccountVector() {
		test("banksys.persistence.AccountVector", Metric.NoDM, 7.0, 0.001);
	}
	
	@Test
	public void testAccountXStream() {
		test("banksys.persistence.AccountXStream", Metric.NoDM, 9.0, 0.001);
	}
	
	@Test
	public void testAccountCreationException() {
		test("banksys.persistence.exception.AccountCreationException", Metric.NoDM, 0.0, 0.001);
	}
	
	@Test
	public void testAccountDeletionException() {
		test("banksys.persistence.exception.AccountDeletionException", Metric.NoDM, 0.0, 0.001);
	}
	
	@Test
	public void testAccountNotFoundException() {
		test("banksys.persistence.exception.AccountNotFoundException", Metric.NoDM, 0.0, 0.001);
	}
	
	@Test
	public void testExistingAccountException() {
		test("banksys.persistence.exception.ExistingAccountException", Metric.NoDM, 2.0, 0.001);
	}

	@Test
	public void testFlushException() {
		test("banksys.persistence.exception.FlushException", Metric.NoDM, 1.0, 0.001);
	}
	
	@Test
	public void testPersistenceException() {
		test("banksys.persistence.exception.PersistenceException", Metric.NoDM, 2.0, 0.001);
	}
	
	@Test
	public void testATM24H() {
		test("banksys.view.ATM24H", Metric.NoDM, 3.0, 0.001);
	}
}
