package com.cg.accountservice;

import java.time.LocalDate;

import com.cg.account.bean.Account;
import com.cg.account.exception.AccountException;
import com.cg.accountdao.AccountDao;
import com.cg.accountdao.IAccountDao;


public class AccountService implements IAccountService {
	IAccountDao accountDao=new AccountDao();

	@Override
	public String createAccount(Account account) throws AccountException {
		// TODO Auto-generated method stub
		if(!account.getMobileNo().matches("\\d{10}")){
			throw new AccountException("Mobile Number Should contain ten digits");
		}
		if(account.getName()==null ||account.getName().isEmpty()){
			throw new AccountException("Customer Name cannot be null");
		}
		else if(!account.getName().matches("[A-Z][a-z]{2,}")){
			throw new AccountException("Customer Name should start with capital letter, should contain only capital letters and should contain minimum three characters");
		}
		if(!account.getEmailId().matches("[a-z\\d]+@[a-z]+\\.com")){
			throw new AccountException("Enter a correct Email address");
		}
		if(account.getBalance()<=0){
			throw new AccountException("Minimum balance should be greater than zero");
		}
		return accountDao.createAccount(account);
	}

	@Override
	public double showBalance(String mobileNo) throws AccountException {
		// TODO Auto-generated method stub
		if(!mobileNo.matches("\\d{10}")){
			throw new AccountException("Mobile Number Should contain ten digits");
		}
		return accountDao.showBalance(mobileNo);
	}

	@Override
	public Account deposit(String mobileNo,double depositAmount) throws AccountException {
		// TODO Auto-generated method stub
		
		if(!mobileNo.matches("\\d{10}")){
			throw new AccountException("Mobile Number Should contain ten digits");
		}
		Account account=accountDao.deposit(mobileNo, depositAmount);
		
		if(depositAmount<=0){
			throw new AccountException("Deposit Amount should be greater than zero");
		}
		account.setBalance(account.getBalance()+depositAmount);
		account.setLocalDate(LocalDate.now());
		return account;
	}

	@Override
	public Account withdraw(String mobileNo, double withdrawalAmount)
			throws AccountException {
		// TODO Auto-generated method stub
		if(!mobileNo.matches("\\d{10}")){
			throw new AccountException("Mobile Number Should contain ten digits");
		}
		Account account=accountDao.withdraw(mobileNo, withdrawalAmount);
		if(account.getBalance()<withdrawalAmount){
			throw new AccountException("Insufficient Balance");
		}
		account.setBalance(account.getBalance()-withdrawalAmount);
		account.setLocalDate(LocalDate.now());
		return account;
	}

	@Override
	public Account transactionDetails(String mobileNo) throws AccountException {
		// TODO Auto-generated method stub
		if(!mobileNo.matches("\\d{10}")){
			throw new AccountException("Mobile Number Should contain ten digits");
		}
		
		return accountDao.transactionDetails(mobileNo);
	}

	@Override
	public boolean fundTransfer(String sender, String receiver, double amount)
			throws AccountException {
		// TODO Auto-generated method stub
		if(!sender.matches("\\d{10}")){
			throw new AccountException("Mobile Number Should contain ten digits");
		}
		if(!receiver.matches("\\d{10}")){
			throw new AccountException("Mobile Number Should contain ten digits");
		}
		
		IAccountService service=new AccountService();
		Account account1=service.withdraw(sender, amount);
		Account account2=service.deposit(receiver, amount);
		return true;
	}

}

