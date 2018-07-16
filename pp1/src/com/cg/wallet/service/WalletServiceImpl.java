package com.cg.wallet.service;



	import java.time.LocalDateTime;



	import com.cg.wallet.beans.Account;

	import com.cg.wallet.dao.IWalletDao;

	import com.cg.wallet.dao.WalletDaoImpl;

	import com.cg.wallet.exception.WalletException;



	public class WalletServiceImpl implements IWalletService {

		IWalletDao dao = new WalletDaoImpl();



		@Override

		public String createAccount(Account acc) throws WalletException {

			if (!acc.getMobileNo().matches("\\d{10}")) {

				throw new WalletException("Mobile number should contain 10 digits");

			}

			if (acc.getName().isEmpty() || acc.getName() == null) {

				throw new WalletException("Name cannot be empty");

			} else {

				if (!acc.getName().matches("[A-Z][A-Za-z]{3,}")) {

					throw new WalletException("Name should start with capital letter and should contain only alphabets");

				}

			}

			if (acc.getEmail().matches("[a-z0-9]+@[a-z]+\\.com")) {

				throw new WalletException("Enter valid emailid");

			}

			if (acc.getBalance() <= 0) {

				throw new WalletException("Balance should be greater than zero");

			}

			return dao.createAccount(acc);

		}



		@Override

		public double showBalance(String mobileNo) throws WalletException {

			if (!mobileNo.matches("\\d{10}")) {

				throw new WalletException("Mobile number should contain 10 digits");

			}

			return dao.showBalance(mobileNo);

		}



		@Override

		public Account deposit(String mobileNo, double depositAmt) throws WalletException {

			if (!mobileNo.matches("\\d{10}")) {

				throw new WalletException("Mobile number should contain 10 digits");

			}

			Account acc = dao.deposit(mobileNo);

			if (depositAmt <= 0) {

				throw new WalletException("Deposit amount must be greater than zero");

			}

			acc.setBalance(acc.getBalance() + depositAmt);

			acc.setDate(LocalDateTime.now());

			return acc;

		}



		@Override

		public Account withdraw(String mobileNo, double withdrawAmt) throws WalletException {

			if (!mobileNo.matches("\\d{10}")) {

				throw new WalletException("Mobile number should contain 10 digits");

			}

			Account acc = dao.withdraw(mobileNo);

			if (withdrawAmt > acc.getBalance() || withdrawAmt <= 0) {

				throw new WalletException(

						"The amount to be withdrawn should be greater than available balance and greater than zero");

			}

			acc.setBalance(acc.getBalance() - withdrawAmt);

			acc.setDate(LocalDateTime.now());

			return acc;

		}



		@Override

		public boolean fundTransfer(String sourceMobileNo, String destMobileNo, double transferAmt) throws WalletException {

			if (!sourceMobileNo.matches("\\d{10}")) {

				throw new WalletException("Mobile number should contain 10 digits");

			}

			if (!destMobileNo.matches("\\d{10}")) {

				throw new WalletException("Mobile number should contain 10 digits");

			}

			IWalletService service = new WalletServiceImpl();

			Account acc1 = service.withdraw(sourceMobileNo, transferAmt);

			Account acc2 = service.deposit(destMobileNo, transferAmt);

			return true;

		}



		@Override

		public Account printTransactionDetails(String mobileNo) throws WalletException {

			return dao.printTransactionDetails(mobileNo);

		}



	}
	
	

