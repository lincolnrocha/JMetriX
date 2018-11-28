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

public class NOCExtractorTest {
	private static SpoonAPI spoonAPI;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		spoonAPI = new Launcher();
		
		spoonAPI.addInputResource("../bank-sys/src/main/");
		spoonAPI.getEnvironment().setNoClasspath(true);
		spoonAPI.buildModel();
		spoonAPI.addProcessor("br.ufc.jmetrix.extractor.normal.ck.NOCExtractor");
		
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
		test("banksys.account.AbstractAccount", Metric.NOC, 2.0, 0.001);
	}
	
	@Test
	public void testOrdinaryAccount() {
		test("banksys.account.OrdinaryAccount", Metric.NOC, 2.0, 0.001);
	}
	
	@Test
	public void testSavingsAccount() {
		test("banksys.account.SavingsAccount", Metric.NOC, 0.0, 0.001);
	}
	
	@Test
	public void testSpecialAccount() {
		test("banksys.account.SpecialAccount", Metric.NOC, 0.0, 0.001);
	}
	
	@Test
	public void testTaxAccount() {
		test("banksys.account.TaxAccount", Metric.NOC, 0.0, 0.001);
	}
	
	@Test
	public void testInsufficientFundsException() {
		test("banksys.account.exception.InsufficientFundsException", Metric.NOC, 0.0, 0.001);
	}

	@Test
	public void testNegativeAmountException() {
		test("banksys.account.exception.NegativeAmountException", Metric.NOC, 0.0, 0.001);
	}
	
	@Test
	public void testBankController() {
		test("banksys.control.BankController", Metric.NOC, 0.0, 0.001);
	}
	
	@Test
	public void testBankTransactionException() {
		test("banksys.control.exception.BankTransactionException", Metric.NOC, 1.0, 0.001);
	}
	
	@Test
	public void testIncompatibleAccountException() {
		test("banksys.control.exception.IncompatibleAccountException", Metric.NOC, 0.0, 0.001);
	}
	
	@Test
	public void testAccountVector() {
		test("banksys.persistence.AccountVector", Metric.NOC, 0.0, 0.001);
	}
	
	@Test
	public void testAccountXStream() {
		test("banksys.persistence.AccountXStream", Metric.NOC, 0.0, 0.001);
	}
	
	@Test
	public void testAccountCreationException() {
		test("banksys.persistence.exception.AccountCreationException", Metric.NOC, 0.0, 0.001);
	}
	
	@Test
	public void testAccountDeletionException() {
		test("banksys.persistence.exception.AccountDeletionException", Metric.NOC, 0.0, 0.001);
	}
	
	@Test
	public void testAccountNotFoundException() {
		test("banksys.persistence.exception.AccountNotFoundException", Metric.NOC, 0.0, 0.001);
	}
	
	@Test
	public void testExistingAccountException() {
		test("banksys.persistence.exception.ExistingAccountException", Metric.NOC, 0.0, 0.001);
	}

	@Test
	public void testFlushException() {
		test("banksys.persistence.exception.FlushException", Metric.NOC, 0.0, 0.001);
	}
	
	@Test
	public void testPersistenceException() {
		test("banksys.persistence.exception.PersistenceException", Metric.NOC, 4.0, 0.001);
	}
	
	@Test
	public void testATM24H() {
		test("banksys.view.ATM24H", Metric.NOC, 0.0, 0.001);
	}
}
