package com.cg.account.db;

import java.time.LocalDate;
import java.util.HashMap;

import com.cg.account.bean.Account;

public class AccountDb {
	private static HashMap<String, Account> accountDb=new HashMap<String, Account>();

	public static HashMap<String, Account> getAccountDb() {
		return accountDb;
	}
	static{
		accountDb.put("1234567890", new Account("1234567890", "xyz", "xyz@gmail.com", 50000, LocalDate.now()));
		accountDb.put("4567891230", new Account("4567891230", "yzx", "yzx@gmail.com", 40000, LocalDate.now()));
		accountDb.put("7894561230", new Account("7894561230", "zxy", "zxy@gmail.com", 60000, LocalDate.now()));
	}
}

