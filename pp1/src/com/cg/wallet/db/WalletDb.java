package com.cg.wallet.db;

	import java.time.LocalDate;

	import java.time.LocalDateTime;

	import java.time.Month;

	import java.util.HashMap;







	import com.cg.wallet.beans.Account;



	public class WalletDb {

	private static HashMap<String,Account> walletDb=new HashMap<String,Account>();

	public static HashMap<String, Account> getWalletMap() {

	return walletDb;

	}

	Account obj=new Account();

	static{

		

	walletDb.put("8900742408",new Account("8900742408","Sayan","say@gmail.com",10000.0,LocalDateTime.now()));

	walletDb.put("9952367077",new Account("9952367077","Ayush","ayush@gmail.com",20000.0,LocalDateTime.now()));

	walletDb.put("9474549288",new Account("9474549288","Himanshu","himanshu@gmail.com",1500.0,LocalDateTime.now()));

	walletDb.put("9434226871",new Account("9434226871","Samriti","sammy@gmail.com",2000.0,LocalDateTime.now()));

	}

	}
	
	
	

