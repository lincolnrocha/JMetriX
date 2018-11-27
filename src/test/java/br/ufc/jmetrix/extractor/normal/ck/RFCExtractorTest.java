package br.ufc.jmetrix.extractor.normal.ck;

import static org.junit.Assert.*;

import org.junit.BeforeClass;
import org.junit.Test;

import br.ufc.jmetrix.model.Dataset;
import br.ufc.jmetrix.model.Measure;
import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.model.Suite;
import spoon.Launcher;
import spoon.SpoonAPI;

public class RFCExtractorTest {
	private static SpoonAPI spoonAPI;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		spoonAPI = new Launcher();
		
		spoonAPI.addInputResource("../bank-sys/src/main/");
		spoonAPI.getEnvironment().setNoClasspath(true);
		spoonAPI.buildModel();
		spoonAPI.addProcessor("br.ufc.jmetrix.extractor.normal.ck.RFCExtractor");
		
		spoonAPI.process();
	}

	/* Métodos nessa classe:
	 *   AbstractAccount.credit(double);
	 *   AbstractAccount.debit(double);
	 *   AbstractAccount.getNumber();
	 *   AbstractAccount.getBalance();
	 */
	@Test
	public void testAbstractAccount() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.AbstractAccount")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(4.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
     *   OrdinaryAccount.debit(double);
	 */
	@Test
	public void testOrdinaryAccount() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.OrdinaryAccount")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   SavingsAccount.earnInterest();
	 *   AbstractAccount.credit(double);
	 *   AbstractAccount.getBalance(double);
	 */
	@Test
	public void testSavingsAccount() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.SavingsAccount")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(3.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   SpecialAccount.earnBonus();
	 *   SpecialAccount.getBonus();
	 *   SpecialAccount.credit(double);
	 *   AbstractAccount.credit(double);
	 */
	@Test
	public void testSpecialAccount() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.SpecialAccount")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(4.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   TaxAccount.debit(double);
	 */
	@Test
	public void testTaxAccount() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.TaxAccount")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   InsufficientFundsException.getMessage();
	 *   InsufficientFundsException.getNumber();
	 *   InsufficientFundsException.getBalance();
	 */
	@Test
	public void testInsufficientFundsException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.exception.InsufficientFundsException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(3.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   NegativeAmountException.getMessage();
	 *   NegativeAmountException.getAmount();
	 */
	@Test
	public void testNegativeAmountException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.account.exception.NegativeAmountException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(2.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   BankController.addAccount(AbstractAccount);
	 *   BankController.removeAccount(String);
	 *   BankController.doCredit(String, double);
	 *   BankController.doDebit(String, double);
	 *   BankController.getBalance(String);
	 *   BankController.doTransfer(String, String, double);
	 *   BankController.doEarnInterest(String);
	 *   BankController.doEarnBonus(String);
	 *   BankController.commit();
	 *   IAccountRepository.create(AbstractAccount);
	 *   IAccountRepository.delete(String);
	 *   IAccountRepository.retrieve(String);
	 *   IAccountRepository.flush();
	 *   AbstractAccount.credit(double);
	 *   AbstractAccount.debit(double);
	 *   AbstractAccount.getBalance(double);
	 *   SavingsAccount.earnInterest();
	 *   SpecialAccount.earnBonus();
	 */
	@Test
	public void testBankController() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.control.BankController")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(18.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   BankTransactionException.getMessage();
	 *   BankTransactionException.getCause();
	 *   Exception.getMessage();
	 */
	@Test
	public void testBankTransactionException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.control.exception.BankTransactionException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(3.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 */
	@Test
	public void testIncompatibleAccountException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.control.exception.IncompatibleAccountException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(0.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   AccountVector.delete(String);
	 *   AccountVector.create(AbstractAccount);
	 *   AccountVector.retrieve(String);
	 *   AccountVector.mumberOfAccounts();
	 *   AccountVector.list();
	 *   AccountVector.flush();
	 *   AccountVector.findAccount(String);
	 *   Vector<>.addElement(AbstractAccount);
	 *   Vector<>.remove(AbstractAccount);
	 *   Vector<>.elementAt(int);
	 *   Vector<>.size();
	 *   AbstractAccount.getNumber();
	 *   String.equals(Object);
	 */
	@Test
	public void testAccountVector() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.AccountVector")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(13.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   AccountXStream.delete(String);
	 *   AccountXStream.create(AbstractAccount);
	 *   AccountXStream.list();
	 *   AccountXStream.mumberOfAccounts();
	 *   AccountXStream.retrieve(String);
	 *   AccountXStream.findAccount(String);
	 *   AccountXStream.loadData();
	 *   AccountXStream.saveData();
	 *   AccountXStream.flush();
	 *   Vector<>.addElement(AbstractAccount);
	 *   Vector<>.remove(AbstractAccount);
	 *   Vector<>.elementAt(int);
	 *   Vector<>.size();
	 *   XStream.alias(String, Class<>);
	 *   XStream.fromXML(Reader, Object);
	 *   XStream.toXML(Object, Writer);
	 *   AbstractAccount.getNumber();
	 *   IOException.getMessage();
	 *   String.equals(Object);
	 */
	@Test
	public void testAccountXStream() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.AccountXStream")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(19.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   IAccountRepository.create(AbstractAccount);
	 *   IAccountRepository.delete(String);
	 *   IAccountRepository.retrieve(String);
	 *   IAccountRepository.list();
	 *   IAccountRepository.mumberOfAccounts();
	 *   IAccountRepository.flush();
	 */
	@Test
	public void testIAccountRepository() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.IAccountRepository")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(6.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 */
	@Test
	public void testAccountCreationException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.exception.AccountCreationException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(0.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 */
	@Test
	public void testAccountDeletionException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.exception.AccountDeletionException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 */
	@Test
	public void testAccountNotFoundException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.exception.AccountNotFoundException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   ExistingAccountException.getMessage();
	 *   ExistingAccountException.getNumber();
	 */
	@Test
	public void testExistingAccountException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.exception.ExistingAccountException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(2.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   FlushException.getMessage();
	 */
	@Test
	public void testFlushException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.exception.FlushException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   PersistenceException.getMessage();
	 *   PersistenceException.getNumber();
	 */
	@Test
	public void testPersistenceException() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.persistence.exception.PersistenceException")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(1.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
	
	/* Métodos nessa classe:
	 *   ATM24H.main(String[]);
	 *   ATM24H.mainMenu();
	 *   ATM24H.addAccountMenu();
	 *   BankController.addAccount(AbstractAccount);
	 *   BankController.removeAccount(String);
	 *   BankController.doCredit(String, double);
	 *   BankController.doDebit(String, double);
	 *   BankController.doTransfer(String, String, double);
	 *   BankController.getBalance(String);
	 *   BankController.doEarnInterest(String);
	 *   BankController.doEarnBonus(String);
	 *   BankTransactionException.getMessage();
	 *   PrintStream.println(String);
	 *   PrintStream.print(String);
	 *   Scanner.next();
	 *   Scanner.nextInt();
	 *   Scanner.nextDouble();
	 */
	@Test
	public void testATM24H() {
		for(Suite suite : Dataset.list()) {
			if(suite.getClassQualifiedName().equals("banksys.view.ATM24H")) {
				for(Measure measure : suite.getMeasures()) {
					if(measure.getMetric() == Metric.RFC) {
						assertEquals(17.0, measure.getValue(), 0.001);
					}
				}
			}
		}
	}
}
