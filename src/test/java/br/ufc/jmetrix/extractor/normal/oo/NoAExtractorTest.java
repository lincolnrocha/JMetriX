package br.ufc.jmetrix.extractor.normal.oo;

import org.junit.Before;
import org.junit.Test;

import br.ufc.jmetrix.model.Metric;
import br.ufc.jmetrix.util.UtilTest;
import spoon.Launcher;
import spoon.SpoonAPI;

public class NoAExtractorTest
{
   
   @Before
   public void setUp() throws Exception 
   {
       SpoonAPI spoon = new Launcher();
       
       spoon.addInputResource("../bank-sys/src/main/");
       spoon.getEnvironment().setNoClasspath(true);
       spoon.buildModel();
       spoon.addProcessor("br.ufc.jmetrix.extractor.normal.oo.NoAExtractor");
       
       spoon.process();
       
   }
   
   @Test    
   public void testProcessAbstractAccount() 
   {
       
       UtilTest.test("banksys.account.AbstractAccount", Metric.NoA, 2.0, 0.0);
   }
   
   @Test
   public void testProcessOrdinaryAccount() 
   {
       
       UtilTest.test("banksys.account.OrdinaryAccount", Metric.NoA, 0.0, 0.0);
   }
   
   @Test
   public void testProcessSavingsAccount() 
   {
       
       UtilTest.test("banksys.account.SavingsAccount", Metric.NoA, 0.0, 0.0);
   }
   
   @Test
   public void testProcessSpecialAccount() 
   {
       
       UtilTest.test("banksys.account.SpecialAccount", Metric.NoA, 1.0, 0.0);
   }
   
   @Test
   public void testProcessTaxAccount() 
   {
       
       UtilTest.test("banksys.account.TaxAccount", Metric.NoA, 0.0, 0.0);
   }

   @Test
   public void testProcessInsufficientFundsException() 
   {
       
       UtilTest.test("banksys.account.exception.InsufficientFundsException", Metric.NoA, 3.0, 0.0);
   }
   
   @Test
   public void testProcessNegativeAmountException() 
   {
       
       UtilTest.test("banksys.account.exception.NegativeAmountException", Metric.NoA, 2.0, 0.0);
   }
   
   @Test
   public void testProcessBankController() 
   {
       
       UtilTest.test("banksys.control.BankController", Metric.NoA, 1.0, 0.0);
   }
   
   @Test
   public void testProcessBankTransactionException() 
   {
       
       UtilTest.test("banksys.control.exception.BankTransactionException", Metric.NoA, 3.0, 0.0);
   }
   
   @Test
   public void testProcessIncompatibleAccountException() 
   {
       
       UtilTest.test("banksys.control.exception.IncompatibleAccountException", Metric.NoA, 1.0, 0.0);
   }
   
   @Test
   public void testProcessAccountVector() 
   {
       
       UtilTest.test("banksys.persistence.AccountVector", Metric.NoA, 1.0, 0.0);
   }
   
   @Test
   public void testProcessAccountXStream() 
   {
       
       UtilTest.test("banksys.persistence.AccountXStream", Metric.NoA, 2.0, 0.0);
   }
   
   @Test
   public void testProcessAccountCreationException() 
   {
       
       UtilTest.test("banksys.persistence.exception.AccountCreationException", Metric.NoA, 1.0, 0.0);
   }
   
   @Test
   public void testProcessAccountDeletionException() 
   {
       
       UtilTest.test("banksys.persistence.exception.AccountDeletionException", Metric.NoA, 1.0, 0.0);
   }
   
   @Test
   public void testProcessAccountNotFoundException() 
   {
       
       UtilTest.test("banksys.persistence.exception.AccountNotFoundException", Metric.NoA, 1.0, 0.0);
   }
   
   @Test
   public void testProcessExistingAccountException() 
   {
       
       UtilTest.test("banksys.persistence.exception.ExistingAccountException", Metric.NoA, 2.0, 0.0);
   }
   
   @Test
   public void testProcessFlushException() 
   {
       
       UtilTest.test("banksys.persistence.exception.FlushException", Metric.NoA, 1.0, 0.0);
   }
   
   @Test
   public void testProcessPersistenceException() 
   {
       
       UtilTest.test("banksys.persistence.exception.PersistenceException", Metric.NoA, 3.0, 0.0);
   }
   
   @Test
   public void testProcessATM24H() 
   {
       
       UtilTest.test("banksys.view.ATM24H", Metric.NoA, 1.0, 0.0);
   }

}
