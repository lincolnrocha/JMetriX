package br.ufc.jmetrix.extractor.normal.oo;

import org.junit.Before;
import org.junit.Test;


import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.*;

import spoon.Launcher;
import spoon.SpoonAPI;

public class NoPAExtractorTest {
	
	@Before
	public void setUp() throws Exception 
	{
		SpoonAPI spoon = new Launcher();
		
		spoon.addInputResource("../bank-sys/src/main/");
		spoon.getEnvironment().setNoClasspath(true);
		spoon.buildModel();
		spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoPAExtractor");
		
		spoon.process();
		
	}
	
	@Test
	public void testProcessAbstractAccount() 
	{
		
		UtilTest.test("banksys.account.AbstractAccount", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessOrdinaryAccount() 
	{
		
		UtilTest.test("banksys.account.OrdinaryAccount", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessSavingsAccount() 
	{
		
		UtilTest.test("banksys.account.SavingsAccount", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessSpecialAccount() 
	{
		
		UtilTest.test("banksys.account.SpecialAccount", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessTaxAccount() 
	{
		
		UtilTest.test("banksys.account.TaxAccount", Metric.NoPA, 0.0, 0.0001);
	}

	@Test
	public void testProcessInsufficientFundsException() 
	{
		
		UtilTest.test("banksys.account.exception.InsufficientFundsException", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessBankController() 
	{
		
		UtilTest.test("banksys.control.BankController", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessBankTransactionException() 
	{
		
		UtilTest.test("banksys.control.exception.BankTransactionException", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessIncompatibleAccountException() 
	{
		
		UtilTest.test("banksys.control.exception.IncompatibleAccountException", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessAccountVector() 
	{
		
		UtilTest.test("banksys.persistence.AccountVector", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessAccountXStream() 
	{
		
		UtilTest.test("banksys.persistence.AccountXStream", Metric.NoPA, 1.0, 0.0001);
	}
	
	@Test
	public void testProcessAccountCreationException() 
	{
		
		UtilTest.test("banksys.persistence.exception.AccountCreationException", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessAccountDeletionException() 
	{
		
		UtilTest.test("banksys.persistence.exception.AccountDeletionException", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessAccountNotFoundException() 
	{
		
		UtilTest.test("banksys.persistence.exception.AccountNotFoundException", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessExistingAccountException() 
	{
		
		UtilTest.test("banksys.persistence.exception.ExistingAccountException", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessFlushException() 
	{
		
		UtilTest.test("banksys.persistence.exception.FlushException", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessPersistenceException() 
	{
		
		UtilTest.test("banksys.persistence.exception.PersistenceException", Metric.NoPA, 0.0, 0.0001);
	}
	
	@Test
	public void testProcessATM24H() 
	{
		
		UtilTest.test("banksys.view.ATM24H", Metric.NoPA, 0.0, 0.0001);
	}
}
