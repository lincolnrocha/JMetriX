package br.ufc.jmetrix.extractor.normal.oo;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.UtilTest;
import spoon.Launcher;
import spoon.SpoonAPI;

public class LoCExtractorTest {

	@Before
	public void setUp() throws Exception 
	{
		SpoonAPI spoon = new Launcher();
		
		spoon.addInputResource("../bank-sys/src/main/");
		spoon.getEnvironment().setNoClasspath(true);
		spoon.buildModel();
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.LoCExtractor");
		
		spoon.process();
		
	}
	
	@Test
	public void testProcessAbstractAccount() 
	{	
		UtilTest.test("banksys.account.AbstractAccount", Metric.LoC, 33, 0.0001);
	}
	
	@Test
	public void testProcessOrdinaryAccount() 
	{	
		UtilTest.test("banksys.account.OrdinaryAccount", Metric.LoC, 23, 0.0001);
	}
	
	@Test
	public void testProcessSavingsAccount() 
	{	
		UtilTest.test("banksys.account.SavingsAccount", Metric.LoC, 23, 0.0001);
	}
	
	@Test
	public void testProcessSpecialAccount() 
	{	
		UtilTest.test("banksys.account.SpecialAccount", Metric.LoC, 33, 0.0001);
	}
	
	@Test
	public void testProcessTaxAccount() 
	{	
		UtilTest.test("banksys.account.TaxAccount", Metric.LoC, 24, 0.0001);
	}

	@Test
	public void testProcessInsufficientFundsException() 
	{	
		UtilTest.test("banksys.account.exception.InsufficientFundsException", Metric.LoC, 31, 0.0001);
	}
	
	@Test
	public void testProcessNegativeAmountException() 
	{	
		UtilTest.test("banksys.account.exception.NegativeAmountException", Metric.LoC, 23, 0.0001);
	}
	
	@Ignore
	public void testProcessATM24H() 
	{	
		UtilTest.test("banksys.atm.ATM24H", Metric.LoC, 186, 0.0001);
	}
	
	@Test
	public void testProcessBankController() 
	{	
		UtilTest.test("banksys.control.BankController", Metric.LoC, 136, 1);
	}
	
	@Test
	public void testProcessBankTransactionException() 
	{	
		UtilTest.test("banksys.control.exception.BankTransactionException", Metric.LoC, 34, 1);
	}
	
	@Test
	public void testProcessIncompatibleAccountException() 
	{	
		UtilTest.test("banksys.control.exception.IncompatibleAccountException", Metric.LoC, 10, 1);
	}
	
	@Test
	public void testProcessAccountVector() 
	{	
		UtilTest.test("banksys.persistence.AccountVector", Metric.LoC, 70, 1);
	}
	
	@Ignore
	public void testProcessIAccountRepository() 
	{	
		UtilTest.test("banksys.persistence.IAccountRepository", Metric.LoC, 19, 1);
	}
	
	@Test
	public void testProcessAccountCreationException() 
	{	
		UtilTest.test("banksys.persistence.exception.AccountCreationException", Metric.LoC, 7, 0.0001);
	}
	
	@Test
	public void testProcessAccountDeletionException() 
	{	
		UtilTest.test("banksys.persistence.exception.AccountDeletionException", Metric.LoC, 9, 0.0001);
	}
	
	@Test
	public void testProcessAccountNotFoundException() 
	{	
		UtilTest.test("banksys.persistence.exception.AccountNotFoundException", Metric.LoC, 10, 1);
	}
	
	@Test
	public void testProcessExistingAccountException() 
	{	
		UtilTest.test("banksys.persistence.exception.ExistingAccountException", Metric.LoC, 21, 1);
	}
	
	@Test
	public void testProcessPersistenceException() 
	{	
		UtilTest.test("banksys.persistence.exception.PersistenceException", Metric.LoC, 23, 1);
	}
}