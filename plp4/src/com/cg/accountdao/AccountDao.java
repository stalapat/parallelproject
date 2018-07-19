package com.cg.accountdao;

import java.util.HashMap;

import com.cg.account.bean.Account;
import com.cg.account.db.AccountDb;
import com.cg.account.exception.AccountException;

public class AccountDao implements IAccountDao {
	static HashMap<String, Account> accountMap=AccountDb.getAccountDb();

	@Override
	public String createAccount(Account account) throws AccountException {
		// TODO Auto-generated method stub
		if(accountMap.containsKey(account.getMobileNo())){
			throw new AccountException("Customer with the mobile Number "+account.getMobileNo()+" already exists in the database");
		}
		if(accountMap.containsKey(account.getEmailId())){
			throw new AccountException("Customer with the emailId "+account.getEmailId()+" already exists in the database");
		}
		accountMap.put(account.getMobileNo(), account);
		return account.getMobileNo();
	}

	@Override
	public double showBalance(String mobileNo) throws AccountException {
		// TODO Auto-generated method stub
		Account account=accountMap.get(mobileNo);
		if(account==null){
			throw new AccountException("Customer with the mobile Number "+mobileNo+" does not exists in the database");
		}
		return account.getBalance();
	}

	@Override
	public Account deposit(String mobileNo,double depositAmount) throws AccountException {
		// TODO Auto-generated method stub
		Account account=accountMap.get(mobileNo);
		if(account==null){
			throw new AccountException("Customer with the mobile Number "+mobileNo+" does not exists in the database");
		}
		return account;
	}

	@Override
	public Account withdraw(String mobileNo, double withdrawalAmount)
			throws AccountException {
		// TODO Auto-generated method stub
		Account account=accountMap.get(mobileNo);
		if(account==null){
			throw new AccountException("Customer with the mobile Number "+mobileNo+" does not exists in the database");
		}
		return account;
	}

	@Override
	public Account transactionDetails(String mobileNo) throws AccountException {
		// TODO Auto-generated method stub
		Account account=accountMap.get(mobileNo);
		if(account==null){
			throw new AccountException("Customer with the mobile Number "+mobileNo+" does not exists in the database");
		}
		return account;
	}

	@Override
	public boolean fundTransfer(String sender, String receiver, double amount)
			throws AccountException {
		// TODO Auto-generated method stub
		Account account1=accountMap.get(sender);
		if(account1==null){
			throw new AccountException("Customer with the mobile Number "+sender+" does not exists in the database");
		}
		Account account2=accountMap.get(receiver);
		if(account2==null)
		{
			throw new AccountException("Customer with the mobile Number "+receiver+" does not exists in the Database");
		}
		return true;
	}

}

