package models;

public class Account {
	private int accountID;
	private AccountType accountType;
	private String accountOwnerName;
	private double balance;
	
	
	
	public Account(int accountID, AccountType accountType, String accountOwnerName, double balance) {
		super();
		this.accountID = accountID;
		this.accountType = accountType;
		this.accountOwnerName = accountOwnerName;
		this.balance = balance;
	}

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public AccountType getAccountType() {
		return accountType;
	}

	public void setAccountType(AccountType accountType) {
		accountType = accountType;
	}

	public String getAccountOwnerName() {
		return accountOwnerName;
	}

	public void setAccountOwnerName(String accountOwnerName) {
		this.accountOwnerName = accountOwnerName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Account [accountID=" + accountID + ", accountType=" + accountType + ", accountOwnerName="
				+ accountOwnerName + ", balance=" + balance + "]";
	}

	
}
