package br.ufc.jmetrix.extractor.normal.oo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.UtilTest;
import spoon.Launcher;
import spoon.SpoonAPI;

public class NoPRAExtractorTest {

	@Before
	public void setUp() throws Exception 
	{
		SpoonAPI spoon = new Launcher();
		
		spoon.addInputResource("../bank-sys/src/main/");
		spoon.getEnvironment().setNoClasspath(true);
		spoon.buildModel();
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoPRAExtractor");
		
		spoon.process();
		
	}
	
	@Test
	public void testProcessAbstractAccount() 
	{	
		UtilTest.test("banksys.account.AbstractAccount", Metric.NoPRA, 2, 0.0001);
	}
	
	@Test
	public void testProcessOrdinaryAccount() 
	{	
		UtilTest.test("banksys.account.OrdinaryAccount", Metric.NoPRA, 0, 0.0001);
	}
	
	@Test
	public void testProcessSavingsAccount() 
	{	
		UtilTest.test("banksys.account.SavingsAccount", Metric.NoPRA, 0, 0.0001);
	}
	
	@Test
	public void testProcessSpecialAccount() 
	{	
		UtilTest.test("banksys.account.SpecialAccount", Metric.NoPRA, 1, 0.0001);
	}
	
	@Test
	public void testProcessTaxAccount() 
	{	
		UtilTest.test("banksys.account.TaxAccount", Metric.NoPRA, 0, 0.0001);
	}

	@Test
	public void testProcessInsufficientFundsException() 
	{	
		UtilTest.test("banksys.account.exception.InsufficientFundsException", Metric.NoPRA, 1, 0.0001);
	}
	
	@Test
	public void testProcessNegativeAmountException() 
	{	
		UtilTest.test("banksys.account.exception.NegativeAmountException", Metric.NoPRA, 1, 0.0001);
	}
	
	@Ignore
	public void testProcessATM24H() 
	{	
		UtilTest.test("banksys.atm.ATM24H", Metric.NoPRA, 1, 0.0001);
	}
	
	@Test
	public void testProcessBankController() 
	{	
		UtilTest.test("banksys.control.BankController", Metric.NoPRA, 1, 0.0001);
	}
	
	@Test
	public void testProcessBankTransactionException() 
	{	
		UtilTest.test("banksys.control.exception.BankTransactionException", Metric.NoPRA, 1, 0.0001);
	}
	
	@Test
	public void testProcessIncompatibleAccountException() 
	{	
		UtilTest.test("banksys.control.exception.IncompatibleAccountException", Metric.NoPRA, 1, 0.0001);
	}
	
	@Test
	public void testProcessAccountVector() 
	{	
		UtilTest.test("banksys.persistence.AccountVector", Metric.NoPRA, 1, 0.0001);
	}
	
	@Ignore
	public void testProcessIAccountRepository() 
	{	
		UtilTest.test("banksys.persistence.IAccountRepository", Metric.NoPRA, 0, 0.0001);
	}
	
	@Test
	public void testProcessAccountCreationException() 
	{	
		UtilTest.test("banksys.persistence.exception.AccountCreationException", Metric.NoPRA, 0, 0.0001);
	}
	
	@Test
	public void testProcessAccountDeletionException() 
	{	
		UtilTest.test("banksys.persistence.exception.AccountDeletionException", Metric.NoPRA, 1, 0.0001);
	}
	
	@Test
	public void testProcessAccountNotFoundException() 
	{	
		UtilTest.test("banksys.persistence.exception.AccountNotFoundException", Metric.NoPRA, 1, 0.0001);
	}
	
	@Test
	public void testProcessExistingAccountException() 
	{	
		UtilTest.test("banksys.persistence.exception.ExistingAccountException", Metric.NoPRA, 1, 0.0001);
	}
	
	@Test
	public void testProcessPersistenceException() 
	{	
		UtilTest.test("banksys.persistence.exception.PersistenceException", Metric.NoPRA, 0, 0.0001);
	}
}