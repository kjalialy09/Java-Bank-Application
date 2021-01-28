package com.seneca.accounts;
//package com.seneca.accounts;
/**
 * Assignment 1
 * @author Kyle Alialy
 * @version 1.0
 * @since 02/16/2020
 */

import java.text.DecimalFormat;
import java.math.BigDecimal;

public class Account {
	private String firstName;
	private String lastName;
	private String middleName;
	private String accountNumber;
	private BigDecimal accountBalance;
	
	public Account()
	{
		this("", "", 0);
	}
	
	/**
	 * Construct Account Object
	 * @param name: a string that contain the name of the user's account
	 * @param num: a string that contain the account number of the user's account
	 * @param bal: an integer that contain the current balance of the user's account
	 */
	public Account(String name, String num, double bal)
	{
		setName(name);
		setAccountNumber(num);
		setBalance(bal);
	}
	
	/**
	 * validates the name passed in parameters and sets it to fullName, firstName, lastName
	 * @param name: a string containing the name of the user's account
	 */
	public void setName(String name)
	{
		if(name == null || name.indexOf(" ") < 0 || name.isEmpty()) 
		{
			firstName = "";
			lastName = "";
			middleName = "";
		}	
		else
		{
			String str = name.replaceAll("\\s+", " ");
			String tmp[] = str.split(" ");
			if(tmp.length == 2)
			{				
			firstName = tmp[0];
			lastName = tmp[1];			
			}else {
				firstName = tmp[0];
				middleName = tmp[1];
				lastName = tmp[2];
			}
		}
	}
	
	/**
	 * sets the accountNumber to the account
	 * @param num: containing the account number of the user
	 */
	public void setAccountNumber(String num) 
	{
		if (num != null)
			accountNumber = num; 
		else
			accountNumber = "";
	}
	
	/**
	 * validates the balance and stores the value to its field
	 * @param bal: integer containing the balance of the account
	 */
	public void setBalance(double bal)
	{
		if (bal > 0)
			accountBalance = new BigDecimal(bal);
		else
			accountBalance = new BigDecimal(0);
	}
	
	/**
	 * 
	 * @return firstName and lastName
	 */
	public String getFullName() { 
		if(middleName != null)
		return lastName + ", " + firstName + " " + middleName; 
		else
			return lastName + ", " + firstName;
		}

	/**
	 * 
	 * @return firstName
	 */
	public String getFirstName() { return firstName; }
	
	public String getMiddleName() { return middleName;	}
	
	/**
	 * 
	 * @return lastName
	 */
	public String getLastName() { return lastName; }
	
	/**
	 * 
	 * @return accountNumber
	 */
	public String getAccountNumber() { return accountNumber; }
	
	/**
	 * 
	 * @return accountBalance
	 */
	public Double getBalance() { return accountBalance.doubleValue(); }
	
	/**
	 * return the string representation of this object
	 * @Override
	 */
	public String toString()
	{
		/**
		 * Name           : Smith, John
		 * Number         : A0007
		 * Current Balance: $1002.99
		 */
		
		StringBuffer str = new StringBuffer("Name           : " + getFullName() + '\n');
		str.append("Number         : " + getAccountNumber() + '\n');
		str.append("Current Balance: " + "$" + new DecimalFormat("0.00").format(getBalance()) + '\n');
		
		return str.toString();
	}
	
	/**
	 * return the numerical representation of this object
	 * @Override
	 */
	public int hashCode()
	{
		int hash = 17;
		
		hash = hash * 37 + getFullName().hashCode();
		hash = hash * 37 + getAccountNumber().hashCode();
		hash = hash * 37 + accountBalance.intValue();
		
		return hash;
	}
	
	/**
	 * return boolean value which validates two object's equality
	 */
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;
		if (!(obj instanceof Account))
			return false;
		
		Account tmp = (Account) obj;
				
		return this.getFullName().equals(tmp.getFullName()) &&
				this.getAccountNumber().equals(tmp.getAccountNumber()) &&
				this.getBalance().equals(tmp.getBalance());
	}
	
	/**
	 * subtracts its account balance
	 * @param amount
	 * @return true if successfully withdrew money from account
	 */
	public boolean withdraw(double amount)
	{
		if (amount < 0 || amount > getBalance())
			return false;
		else
		{
			accountBalance = accountBalance.subtract(BigDecimal.valueOf(amount));
			return true;
		}
	}
	
	/**
	 * adds amount to the account's balance
	 * @param amount
	 */
	public void deposit(double amount)
	{
		if (amount > 0)
			accountBalance = accountBalance.add(BigDecimal.valueOf(amount));
	}
		
	// Main
	/*public static void main(String args[]) {
		Account a1 = new Account(" John                    Smith", "A0007", 1002.99);
		System.out.println(a1);
	}*/
	
}
