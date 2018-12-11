package br.ufc.jmetrix.extractor.normal.oo;

import org.junit.Before;
import org.junit.Test;


import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.*;

import spoon.Launcher;
import spoon.SpoonAPI;

public class LCOM3ExtractorTest {
	
	@Before
	public void setUp() throws Exception 
	{
		SpoonAPI spoon = new Launcher();
		
		spoon.addInputResource("../home/travis/build/lincolnrocha/bank-sys/src/main/");
		spoon.getEnvironment().setNoClasspath(true);
		spoon.buildModel();
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.LCOM3Extractor");
		
		spoon.process();
		
	}
	
	@Test
	public void testProcessAbstractAccount() 
	{
		
		UtilTest.test("banksys.account.AbstractAccount", Metric.LCOM3, 2.5/3.0, 0.0001);
	}
	
	@Test
	public void testProcessOrdinaryAccount() 
	{
		
		UtilTest.test("banksys.account.OrdinaryAccount", Metric.LCOM3, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessSavingsAccount() 
	{
		
		UtilTest.test("banksys.account.SavingsAccount", Metric.LCOM3, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessSpecialAccount() 
	{
		
		UtilTest.test("banksys.account.SpecialAccount", Metric.LCOM3, 0, 0.0001);
	}
	
	@Test
	public void testProcessTaxAccount() 
	{
		
		UtilTest.test("banksys.account.TaxAccount", Metric.LCOM3, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessInsufficientFundsException() 
	{
		
		UtilTest.test("banksys.account.exception.InsufficientFundsException", Metric.LCOM3, 5.0/6.0, 0.0001);
	}
	
	@Test
	public void testProcessNegativeAmountException() 
	{
		
		UtilTest.test("banksys.account.exception.NegativeAmountException", Metric.LCOM3, 1, 0.0001);
	}
	
	@Test
	public void testProcessBankController() 
	{
		
		UtilTest.test("banksys.control.BankController", Metric.LCOM3, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessBankTransactionException() 
	{
		
		UtilTest.test("banksys.control.exception.BankTransactionException", Metric.LCOM3, 1.0, 0.0001);
	}
	
	@Test
	public void testProcessIncompatibleAccountException() 
	{
		
		UtilTest.test("banksys.control.exception.IncompatibleAccountException", Metric.LCOM3, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessAccountVector() 
	{
		
		UtilTest.test("banksys.persistence.AccountVector", Metric.LCOM3, 1.0/3.0, 0.0001);
	}
	
	@Test
	public void testProcessAccountXStream() 
	{
		
		UtilTest.test("banksys.persistence.AccountXStream", Metric.LCOM3, 9.0/16.0, 0.0001);
	}
	
	@Test
	public void testProcessAccountCreationException() 
	{
		
		UtilTest.test("banksys.persistence.exception.AccountCreationException", Metric.LCOM3, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessAccountDeletionException() 
	{
		
		UtilTest.test("banksys.persistence.exception.AccountDeletionException", Metric.LCOM3, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessAccountNotFoundException() 
	{
		
		UtilTest.test("banksys.persistence.exception.AccountNotFoundException", Metric.LCOM3, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessExistingAccountException() 
	{
		
		UtilTest.test("banksys.persistence.exception.ExistingAccountException", Metric.LCOM3, 1.0, 0.0001);
	}
	
	@Test
	public void testProcessFlushException() 
	{
		
		UtilTest.test("banksys.persistence.exception.FlushException", Metric.LCOM3, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessPersistenceException() 
	{
		
		UtilTest.test("banksys.persistence.exception.PersistenceException", Metric.LCOM3, 1.0, 0.0001);
	}
	
	@Test
	public void testProcessATM24H() 
	{
		
		UtilTest.test("banksys.view.ATM24H", Metric.LCOM3, 0.0, 0.0001);
	}
	

}

