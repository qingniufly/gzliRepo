package com.simon.synchronize;

public class Main {

	public static void main(String[] args) {
		Account account = new Account();
		account.setBalance(1000);

		Company company = new Company(account);
		Thread companyT = new Thread(company);

		Bank bank = new Bank(account);
		Thread bankT = new Thread(bank);

		System.out.printf("Account : Initial Balance: %f\n",account.getBalance());

		companyT.start();
		bankT.start();

		try {
			companyT.join();
			bankT.join();
			System.out.printf("Account : Final Balance: %f\n",account.getBalance());
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
