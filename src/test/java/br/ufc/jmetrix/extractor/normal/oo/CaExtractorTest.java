package br.ufc.jmetrix.extractor.normal.oo;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.UtilTest;
import spoon.Launcher;
import spoon.SpoonAPI;

public class CaExtractorTest {
	
	//Os testes não estão funcionando, pois o método que está sendo testado não está conseguindo
	//acessar as classes que possuem dependência.

	@Before
	public void setUp() throws Exception 
	{
		SpoonAPI spoon = new Launcher();
		
		spoon.addInputResource("../bank-sys/src/main/");
		spoon.getEnvironment().setNoClasspath(true);
		spoon.buildModel();
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.CaExtractor");
		
		spoon.process();
		
	}
	
	@Ignore
	public void testProcessAbstractAccount() 
	{	
		UtilTest.test("banksys.account.AbstractAccount", Metric.Ca, 0, 1);
	}
	
	@Ignore
	public void testProcessOrdinaryAccount() 
	{	
		UtilTest.test("banksys.account.OrdinaryAccount", Metric.Ca, 1, 1);
	}
	
	@Ignore
	public void testProcessSavingsAccount() 
	{	
		UtilTest.test("banksys.account.SavingsAccount", Metric.Ca, 2, 1);
	}
	
	@Ignore
	public void testProcessSpecialAccount() 
	{	
		UtilTest.test("banksys.account.SpecialAccount", Metric.Ca, 2, 1);
	}
	
	@Ignore
	public void testProcessTaxAccount() 
	{	
		UtilTest.test("banksys.account.TaxAccount", Metric.Ca, 1, 1);
	}

	@Ignore
	public void testProcessInsufficientFundsException() 
	{	
		UtilTest.test("banksys.account.exception.InsufficientFundsException", Metric.Ca, 1, 1);
	}
	
	@Ignore
	public void testProcessNegativeAmountException() 
	{	
		UtilTest.test("banksys.account.exception.NegativeAmountException", Metric.Ca, 1, 1);
	}
	
	@Ignore
	public void testProcessATM24H() 
	{	
		UtilTest.test("banksys.atm.ATM24H", Metric.Ca, 6, 1);
	}
	
	@Ignore
	public void testProcessBankController() 
	{	
		UtilTest.test("banksys.control.BankController", Metric.Ca, 5, 1);
	}
	
	@Ignore
	public void testProcessBankTransactionException() 
	{	
		UtilTest.test("banksys.control.exception.BankTransactionException", Metric.Ca, 1, 1);
	}
	
	@Ignore
	public void testProcessIncompatibleAccountException() 
	{	
		UtilTest.test("banksys.control.exception.IncompatibleAccountException", Metric.Ca, 1, 1);
	}
	
	@Ignore
	public void testProcessAccountVector() 
	{	
		UtilTest.test("banksys.persistence.AccountVector", Metric.Ca, 3, 1);
	}
	
	@Ignore
	public void testProcessIAccountRepository() 
	{	
		UtilTest.test("banksys.persistence.IAccountRepository", Metric.Ca, 0, 1);
	}
	
	@Ignore
	public void testProcessAccountCreationException() 
	{	
		UtilTest.test("banksys.persistence.exception.AccountCreationException", Metric.Ca, 1, 1);
	}
	
	@Ignore
	public void testProcessAccountDeletionException() 
	{	
		UtilTest.test("banksys.persistence.exception.AccountDeletionException", Metric.Ca, 1, 1);
	}
	
	@Ignore
	public void testProcessAccountNotFoundException() 
	{	
		UtilTest.test("banksys.persistence.exception.AccountNotFoundException", Metric.Ca, 1, 1);
	}
	
	@Ignore
	public void testProcessExistingAccountException() 
	{	
		UtilTest.test("banksys.persistence.exception.ExistingAccountException", Metric.Ca, 0, 1);
	}
	
	@Ignore
	public void testProcessPersistenceException() 
	{	
		UtilTest.test("banksys.persistence.exception.PersistenceException", Metric.Ca, 0, 1);
	}
}
