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

	/* Métodos nessa classe:
	 *   AbstractAccount.credit(double);
	 *   AbstractAccount.debit(double);
	 *   AbstractAccount.getNumber();
	 *   AbstractAccount.getBalance();
	 */
	@Test
	public void testAbstractAccount() {
		test("banksys.account.AbstractAccount", Metric.RFC, 4.0, 0.001);
	}
	
	/* Métodos nessa classe:
     *   OrdinaryAccount.debit(double);
	 */
	@Test
	public void testOrdinaryAccount() {
		test("banksys.account.OrdinaryAccount", Metric.RFC, 1.0, 0.001);
	}
	
	/* Métodos nessa classe:
	 *   SavingsAccount.earnInterest();
	 *   AbstractAccount.credit(double);
	 *   AbstractAccount.getBalance(double);
	 */
	@Test
	public void testSavingsAccount() {
		test("banksys.account.SavingsAccount", Metric.RFC, 3.0, 0.001);
	}
	
	/* Métodos nessa classe:
	 *   SpecialAccount.earnBonus();
	 *   SpecialAccount.getBonus();
	 *   SpecialAccount.credit(double);
	 *   AbstractAccount.credit(double);
	 */
	@Test
	public void testSpecialAccount() {
		test("banksys.account.SpecialAccount", Metric.RFC, 4.0, 0.001);
	}
	
	/* Métodos nessa classe:
	 *   TaxAccount.debit(double);
	 */
	@Test
	public void testTaxAccount() {
		test("banksys.account.TaxAccount", Metric.RFC, 1.0, 0.001);
	}
	
	/* Métodos nessa classe:
	 *   InsufficientFundsException.getMessage();
	 *   InsufficientFundsException.getNumber();
	 *   InsufficientFundsException.getBalance();
	 */
	@Test
	public void testInsufficientFundsException() {
		test("banksys.account.exception.InsufficientFundsException", Metric.RFC, 3.0, 0.001);
	}
	
	/* Métodos nessa classe:
	 *   NegativeAmountException.getMessage();
	 *   NegativeAmountException.getAmount();
	 */
	@Test
	public void testNegativeAmountException() {
		test("banksys.account.exception.NegativeAmountException", Metric.RFC, 2.0, 0.001);
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
		test("banksys.control.BankController", Metric.RFC, 18.0, 0.001);
	}
	
	/* Métodos nessa classe:
	 *   BankTransactionException.getMessage();
	 *   BankTransactionException.getCause();
	 *   Exception.getMessage();
	 */
	@Test
	public void testBankTransactionException() {
		test("banksys.control.exception.BankTransactionException", Metric.RFC, 3.0, 0.001);
	}
	
	/* Métodos nessa classe:
	 */
	@Test
	public void testIncompatibleAccountException() {
		test("banksys.control.exception.IncompatibleAccountException", Metric.RFC, 0.0, 0.001);
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
		test("banksys.persistence.AccountVector", Metric.RFC, 13.0, 0.001);
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
		test("banksys.persistence.AccountXStream", Metric.RFC, 19.0, 0.001);
	}
	
	/* Métodos nessa classe:
	 */
	@Test
	public void testAccountCreationException() {
		test("banksys.persistence.exception.AccountCreationException", Metric.RFC, 0.0, 0.001);
	}
	
	/* Métodos nessa classe:
	 */
	@Test
	public void testAccountDeletionException() {
		test("banksys.persistence.exception.AccountDeletionException", Metric.RFC, 0.0, 0.001);
	}
	
	/* Métodos nessa classe:
	 */
	@Test
	public void testAccountNotFoundException() {
		test("banksys.persistence.exception.AccountNotFoundException", Metric.RFC, 0.0, 0.001);
	}
	
	/* Métodos nessa classe:
	 *   ExistingAccountException.getMessage();
	 *   ExistingAccountException.getNumber();
	 */
	@Test
	public void testExistingAccountException() {
		test("banksys.persistence.exception.ExistingAccountException", Metric.RFC, 2.0, 0.001);
	}
	
	/* Métodos nessa classe:
	 *   FlushException.getMessage();
	 */
	@Test
	public void testFlushException() {
		test("banksys.persistence.exception.FlushException", Metric.RFC, 1.0, 0.001);
	}
	
	/* Métodos nessa classe:
	 *   PersistenceException.getMessage();
	 *   PersistenceException.getNumber();
	 */
	@Test
	public void testPersistenceException() {
		test("banksys.persistence.exception.PersistenceException", Metric.RFC, 2.0, 0.001);
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
		test("banksys.view.ATM24H", Metric.RFC, 17.0, 0.001);
	}
}
