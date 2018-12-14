package br.ufc.jmetrix.extractor.normal.oo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.UtilTest;
import spoon.Launcher;
import spoon.SpoonAPI;

public class NoPRMExtractorTest {

	@Before
	public void setUp() throws Exception 
	{
		SpoonAPI spoon = new Launcher();
		
		spoon.addInputResource("../bank-sys/src/main/");
		spoon.getEnvironment().setNoClasspath(true);
		spoon.buildModel();
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoPRMExtractor");
		
		spoon.process();
		
	}
	
	@Test
	public void testProcessAbstractAccount() 
	{	
		UtilTest.test("banksys.account.AbstractAccount", Metric.NoPRM, 0, 0.0001);
	}
	
	@Test
	public void testProcessOrdinaryAccount() 
	{	
		UtilTest.test("banksys.account.OrdinaryAccount", Metric.NoPRM, 0, 0.0001);
	}
	
	@Test
	public void testProcessSavingsAccount() 
	{	
		UtilTest.test("banksys.account.SavingsAccount", Metric.NoPRM, 0, 0.0001);
	}
	
	@Test
	public void testProcessSpecialAccount() 
	{	
		UtilTest.test("banksys.account.SpecialAccount", Metric.NoPRM, 0, 0.0001);
	}
	
	@Test
	public void testProcessTaxAccount() 
	{	
		UtilTest.test("banksys.account.TaxAccount", Metric.NoPRM, 0, 0.0001);
	}

	@Test
	public void testProcessInsufficientFundsException() 
	{	
		UtilTest.test("banksys.account.exception.InsufficientFundsException", Metric.NoPRM, 1, 0.0001);
	}
	
	@Test
	public void testProcessNegativeAmountException() 
	{	
		UtilTest.test("banksys.account.exception.NegativeAmountException", Metric.NoPRM, 0, 0.0001);
	}
	
	@Ignore
	public void testProcessATM24H() 
	{	
		UtilTest.test("banksys.atm.ATM24H", Metric.NoPRM, 1, 0.0001);
	}
	
	@Test
	public void testProcessBankController() 
	{	
		UtilTest.test("banksys.control.BankController", Metric.NoPRM, 1, 0.0001);
	}
	
	@Test
	public void testProcessBankTransactionException() 
	{	
		UtilTest.test("banksys.control.exception.BankTransactionException", Metric.NoPRM, 0, 0.0001);
	}
	
	@Test
	public void testProcessIncompatibleAccountException() 
	{	
		UtilTest.test("banksys.control.exception.IncompatibleAccountException", Metric.NoPRM, 0, 0.0001);
	}
	
	@Test
	public void testProcessAccountVector() 
	{	
		UtilTest.test("banksys.persistence.AccountVector", Metric.NoPRM, 1, 0.0001);
	}
	
	@Ignore
	public void testProcessIAccountRepository() 
	{	
		UtilTest.test("banksys.persistence.IAccountRepository", Metric.NoPRM, 0, 0.0001);
	}
	
	@Test
	public void testProcessAccountCreationException() 
	{	
		UtilTest.test("banksys.persistence.exception.AccountCreationException", Metric.NoPRM, 0, 0.0001);
	}
	
	@Test
	public void testProcessAccountDeletionException() 
	{	
		UtilTest.test("banksys.persistence.exception.AccountDeletionException", Metric.NoPRM, 0, 0.0001);
	}
	
	@Test
	public void testProcessAccountNotFoundException() 
	{	
		UtilTest.test("banksys.persistence.exception.AccountNotFoundException", Metric.NoPRM, 0, 0.0001);
	}
	
	@Test
	public void testProcessExistingAccountException() 
	{	
		UtilTest.test("banksys.persistence.exception.ExistingAccountException", Metric.NoPRM, 0, 0.0001);
	}
	
	@Test
	public void testProcessPersistenceException() 
	{	
		UtilTest.test("banksys.persistence.exception.PersistenceException", Metric.NoPRM, 1, 0.0001);
	}
}