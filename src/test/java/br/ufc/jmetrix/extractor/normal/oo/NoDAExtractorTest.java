package br.ufc.jmetrix.extractor.normal.oo;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.model.Suite;

import spoon.Launcher;
import spoon.SpoonAPI;

public class NoDAExtractorTest {
	private static SpoonAPI spoonAPI;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		spoonAPI = new Launcher();
		
		spoonAPI.addInputResource("../bank-sys/src/main/");
		spoonAPI.getEnvironment().setNoClasspath(true);
		spoonAPI.buildModel();
		spoonAPI.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoDAExtractor");
		
		spoonAPI.process();
	}

	@Test
	public void testAbstractAccount() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.AbstractAccount")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(2.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testOrdinaryAccount() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.OrdinaryAccount")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(0.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testSavingsAccount() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.SavingsAccount")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(0.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testSpecialAccount() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.SpecialAccount")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testTaxAccount() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.TaxAccount")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(0.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testInsufficientFundsException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.exception.InsufficientFundsException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(3.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testNegativeAmountException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.exception.NegativeAmountException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(2.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testBankController() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.control.BankController")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testBankTransactionException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.control.exception.BankTransactionException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(3.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testIncompatibleAccountException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.control.exception.IncompatibleAccountException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testAccountVector() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.AccountVector")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testAccountXStream() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.AccountXStream")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(2.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testIAccountRepository() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.IAccountRepository")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(0.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testAccountCreationException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.exception.AccountCreationException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testAccountDeletionException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.exception.AccountDeletionException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testAccountNotFoundException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.exception.AccountNotFoundException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testExistingAccountException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.exception.ExistingAccountException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(2.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testFlushException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.exception.FlushException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	@Test
	public void testPersistenceException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.exception.PersistenceException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(3.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}

	@Test
	public void testATM24H() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.view.ATM24H")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.NoDA) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
}
