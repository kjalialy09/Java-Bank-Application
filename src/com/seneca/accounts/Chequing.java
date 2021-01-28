package com.seneca.accounts;
//package com.seneca.accounts;

import java.text.DecimalFormat;

public class Chequing extends Account {
	private static final String ACCTYPE = "CHQ";
	private double serviceCharge;
	private int maxTransactions;
	private double[] transactions;
	private double totalCharges;
	private int pos; // keep track of array position

	public Chequing()
	{
		super();
		serviceCharge = 0.25;
		maxTransactions = 3;
	}
	
	/**
	 * Constructs Chequing object
	 * @param name: will be passed to the superclass
	 * @param num: will be passed to the superclass
	 * @param balance: will be passed to the superclass
	 * @param charge: service charge per transaction
	 * @param numOfTrans: number of transactions
	 */
	public Chequing(String name, String num, double balance, double charge, int numOfTrans)
	{
		super(name, num, balance);
		setServiceCharge(charge/100);
		setMaxTransactions(numOfTrans);
		setTransactions(getMaxTransactions());
		totalCharges = 0;
		pos = 0;
	}
	
	/**
	 * 
	 * @param c: represent the service charge that will be stored in its field
	 */
	public void setServiceCharge(double c) 
	{ 
		if (c > 0)
			serviceCharge = c; 
		else
			serviceCharge = 0.25;
	}
	
	/**
	 * 
	 * @param n: represent the maximum transactions that will be stores in its field
	 */
	public void setMaxTransactions(int n) 
	{
		if (n > 0)
			maxTransactions = n; 
		else
			maxTransactions = 3;
	}
	
	/**
	 * 
	 * @param m: represent the size of transactions which will allocate memory in this method
	 */
	public void setTransactions(int m) { transactions = new double[m]; }
	
	/**
	 * 
	 * @return serviceCharge
	 */
	public Double getServiceCharge() { return serviceCharge; }
	
	/**
	 * 
	 * @return maxTransactions
	 */
	public Integer getMaxTransactions() { return maxTransactions; }
	
	/**
	 * 
	 * @return transactions
	 */
	public double[] getTransactions() { return transactions; }
	
	/**
	 * 
	 * @return totalCharges
	 */
	public Double getTotalCharges() { return totalCharges; }
	
	/**
	 * return boolean value which validates two object's equality
	 * @Override
	 */
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;
		if (!(obj instanceof Chequing) || !(super.equals(obj)))
			return false;
		
		Chequing tmp = (Chequing) obj;
		

		return getServiceCharge().equals(tmp.getServiceCharge()) &&
				getMaxTransactions().equals(tmp.getMaxTransactions()) &&
				getTransactions().equals(tmp.getTransactions()) &&
				getTotalCharges().equals(tmp.getTotalCharges());
	}
	
	/**
	 * return the string representation of this object
	 * @Override
	 */
	public String toString()
	{
		/**
		 * Account Type        : 
		 * Service Charge      : 
		 * Total Charges       : 
		 * List of Transactions: 
		 * Final Balance       : 
		 */
		StringBuffer str = new StringBuffer(super.toString());
		str.append("Account Type        : " + ACCTYPE + '\n');
		str.append("Service Charge      : " + "$" + new DecimalFormat("0.00").format(getServiceCharge()) + '\n'); 
		str.append("Total Charges       : " + "$" + new DecimalFormat("0.00").format(getTotalCharges()) + '\n');  
		str.append("List of Transactions: ");
		
		for (int i = 0; i < transactions.length; i++)
		{
			if (i == transactions.length - 1)
			{
				if (transactions[i] > 0) 
					str.append("+" + new DecimalFormat("0.00").format(transactions[i]));
				else
					str.append(new DecimalFormat("0.00").format(transactions[i]));
				break;
			}
			if (transactions[i] > 0)
				str.append("+" + new DecimalFormat("0.00").format(transactions[i]) + ", ");
			else
				str.append(new DecimalFormat("0.00").format(transactions[i]) + ", ");
		}
		str.append('\n');
		str.append("Final Balance       : $" + new DecimalFormat("###00.00").format(this.balance()) + '\n');
		
		return str.toString();
	}
	
	/**
	 * return the numerical representation of this object
	 * @Override
	 */
	public int hashCode()
	{
		int hash = 17;
		
		hash += super.hashCode();
		
		hash = hash * 37 + (int)serviceCharge;
		hash = hash * 37 + maxTransactions;
		
		for (int i = 0; i < transactions.length; i++)
			hash = hash * 37 + (int)transactions[i];
		hash = hash * 37 + (int)totalCharges;
		
		return hash;
	}
	
	/**
	 * subtracts its account balance
	 * @param amount
	 * @return true if successfully withdrew money from account
	 * @Override
	 */
	public boolean withdraw(double amount)
	{
		if (amount > 0 && pos <= maxTransactions)
		{
			double tmp = amount;
			if (super.withdraw(tmp))
			{
				totalCharges += serviceCharge;
				transactions[pos++] = -amount;
				return true;
			}
			else
				return false;
		}
		else
			return false;
	}
	
	/**
	 * adds amount to the account's balance
	 * @param amount
	 * @Override
	 */
	public void deposit(double amount)
	{
		if (amount > 0 && pos <= maxTransactions)
		{
			super.deposit(amount);
			totalCharges += serviceCharge;
			transactions[pos++] = amount;
		}
	}
	
	/**
	 * 
	 * @return final balance
	 * @Override
	 */
	public Double balance()
	{
		int cnt = 0;
		for(int i = 0 ; i < transactions.length; ++i)
		{
			if (transactions[i] != 0)
				cnt++;
		}
		double tmp = super.getBalance() - (cnt * serviceCharge);
		//super.setBalance(tmp);
		return tmp;
	}
	
	// Main
	/*public static void main(String args[]) {
		Chequing c1 = new Chequing("Mary Ryan", "B0003", 145.00, 25, 3);
		c1.deposit(50);
		c1.withdraw(25);
		c1.deposit(1000);
		System.out.println(c1);
	}*/
}
