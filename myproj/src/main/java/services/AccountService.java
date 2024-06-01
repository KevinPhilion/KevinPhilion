package services;

import java.util.*;

import exceptions.InsufficientFundsException;
import models.Account;

public interface AccountService {
      List<Account> getAllAccounts();
      
      Account getAccountByID(int id);
      
      double getBalanceByAccountID(int id);
      
      Account createAccount(Account account);
      Account deleteAccount(int accountID);
      Account updateAccount(Account account);
      
      double withdraw(int accountID, double amount);
}
