package myproj;

//import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import java.util.*;
import org.junit.*;
import models.*;
import services.*;

public class AllTests {

    static List<Account> accounts = new ArrayList<>();
    static AccountService accountService = new AccountServiceImpl(accounts);

    @BeforeClass
    public static void setUpBeforeClass() {
        accounts.add(new Account(1, AccountType.checkings, "John Doe", 1000));
        accounts.add(new Account(2, AccountType.savings, "Jane Doe", 2000));
    }

    @AfterClass
    public static void tearDownAfterClass() {
        accounts.clear();
    }
    
    //create account test cases
    @Test
    public void testCreateAccount_Success() {
        Account expectedResult = new Account(accounts.size(), AccountType.checkings, "New User", 90000);
        Account actualResult = accountService.createAccount(expectedResult);
        assertEquals(expectedResult, actualResult);
        accounts.remove(expectedResult);
    }
    @Test
    public void testCreateAccount_Fail() {
        Account newAccount = null;
        Account result = accountService.createAccount(newAccount);
        assertNull(result);
    }
    
    //get all accounts test cases
    @Test
    public void testGetAllAccounts_Success() {
        List<Account> result = accountService.getAllAccounts();
        assertEquals(accounts, result);
    }
    @Test
    public void testGetAllAccounts_Fail() {
        AccountServiceImpl emptyAccountService = new AccountServiceImpl(new ArrayList<>());
        List<Account> result = emptyAccountService.getAllAccounts();
        assertTrue(result.isEmpty());
    }
    
    //get account by id test cases
    @Test
    public void testGetAccountByID_Success() {
        Account result = accountService.getAccountByID(1);
        assertNotNull(result);
        assertEquals(1, result.getAccountID());
    }
    @Test
    public void testGetAccountByID_Fail() {
        Account result = accountService.getAccountByID(999);
        assertNull(result);
    }
    
    //get balance by id test cases
    @Test
    public void testGetBalanceByAccountID_Success() {
        double balance = accountService.getBalanceByAccountID(1);
        assertEquals(1000, balance, 0.0);
    }
    @Test
    public void testGetBalanceByAccountID_Fail() {
        double balance = accountService.getBalanceByAccountID(999);
        assertEquals(0, balance, 0.0);
    }
    
    //delete account test cases
    @Test
    public void testDeleteAccount_Success() {
        Account accountToDelete = accounts.get(0);
        Account result = accountService.deleteAccount(accountToDelete.getAccountID());
        assertEquals(accountToDelete, result);
        assertFalse(accounts.contains(accountToDelete));
        accounts.add(0, accountToDelete); // Add back for other tests
    }
    @Test
    public void testDeleteAccount_Fail() {
        Account result = accountService.deleteAccount(999);
        assertNull(result);
    }
    
    //update account test cases
    @Test
    public void testUpdateAccount_Success() {
        Account accountToUpdate = accounts.get(0);
        accountToUpdate.setBalance(5000);
        Account result = accountService.updateAccount(accountToUpdate);
        assertEquals(accountToUpdate, result);
        assertEquals(5000, accounts.get(0).getBalance(), 0.0);
    }
    @Test
    public void testUpdateAccount_Fail() {
        Account nonExistentAccount = new Account(999, AccountType.savings, "Non Existent", 3000);
        Account result = accountService.updateAccount(nonExistentAccount);
        assertNull(result);
    }
    
    //withdraw test cases
    @Test
    public void testWithdraw_Success() {
        Account accountToTest = new Account(accounts.size() + 1, AccountType.savings, "Jack Ville", 110);
        accounts.add(accountToTest);

        double initialBalance = accountToTest.getBalance();
        double amountToWithdraw = 100;
        double expectedResult = initialBalance - amountToWithdraw;

        double actualResult = accountService.withdraw(accountToTest.getAccountID(), amountToWithdraw);
        assertEquals(expectedResult, actualResult, 0.0);

        accounts.remove(accountToTest);
    }
    @Test
    public void testWithdraw_Fail() {
        Account accountToTest = accounts.get(0);
        double initialBalance = accountToTest.getBalance();
        double amountToWithdraw = initialBalance + 100; // More than balance

        double actualResult = accountService.withdraw(accountToTest.getAccountID(), amountToWithdraw);
        assertEquals(0.0, actualResult, 0.0); // Balance should remain unchanged
    }
}