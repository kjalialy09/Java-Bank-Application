package com.seneca.business;

import java.util.ArrayList;
import com.seneca.accounts.Account;

public class Bank  {
	//instance variables
	private String BankName;              
	private  ArrayList<Account> acc;
	
	public Bank() {                               //0 argument constructor
		 this("Seneca@York"); 
		 this.acc = new ArrayList<Account>();
		 }
	 
    public Bank(String bankName)                       
    {
    	BankName=bankName; 
    	this.acc = new ArrayList<Account>();
    }
    
    public String toString()
    {
    	String str;
    	str= "*** Welcome to the Bank of <Andre Jenah> ***\n";
    	if(acc.size()>=0)
    	{
    		str+= "It has " + acc.size() +" accounts.\n";    
    		for(int i=0; i<acc.size(); i++)
    		{
    			str+=i+1 + ". number: "+ acc.get(i).getAccountNumber() + ", name: " + acc.get(i).getFullName() + ", balance: $" +acc.get(i).getBalance() +"\n";
    		}	
    	}
    	return str;
    }

    @Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acc == null) ? 0 : acc.hashCode());
		result = prime * result + ((BankName == null) ? 0 : BankName.hashCode());
		return result;
	}
    
    public boolean equals(Object obj) 
    {
        if (obj == this) 
        {
            return true;
        }

        if (!(obj instanceof Bank)) 
        {
            return false;
        }

        Bank temp = (Bank) obj;

        return this.getArrayList().equals(temp.getArrayList()) && this.getBankName().equals(temp.getBankName());
     }
    
    public ArrayList<Account> getArrayList() {
		
		return acc;
	}
    
    public boolean addAccount(Account newAccount )
    {
    	if(newAccount==null)
    	{
    		return false;
    	}
    	
    	else
    	{
    		for(int i=0; i<acc.size(); i++)                    //loops through the array list
    		{
    			if(newAccount.getAccountNumber().equals(acc.get(i).getAccountNumber()))       //if equal
        		{
    				return false;
        	}
    		}
    	}
    		
    	acc.add(newAccount);         //add a new account
		return true;
    }
    
	public String getBankName() {
		
		return BankName;
	}
	
	public Account removeAccount(String accountNumber)
	{
		if(!accountNumber.equals("") && !accountNumber.equals(null)){
			for(int i=0;i<acc.size();i++){
				if(acc.get(i).getAccountNumber().equals(accountNumber)){
					Account tmp;
					tmp = acc.get(i);
					acc.remove(i);
					return tmp; 
				}
			}
		}
		return null;
	}
	
	public Account[] searchByBalance(int balance) 
    {
    	Account[] ReturnArray = new Account[0];
    	int count[]= new int [acc.size()];
        int a=0;
    	for (int i=0; i<acc.size(); i++)
    	{
    		if(acc.get(i).getBalance() == balance) {
    			count[a]=i;
    			a++;
			}
    	}
    	if(a>0)//if finds some thing
		{
			//allocate memory for return array
    		ReturnArray= new Account[a];
    		for(int h=0; h<a; h++)
    		{
    			ReturnArray[h]=acc.get(count[h]);
    		}
		}
    	return ReturnArray;
    }
	
	public Account[] searchByAccountName(String accountName)
	{
		Account[] ReturnArray = new Account[0];
		int count=0;
    	int[] find= new int [acc.size()];
    	for (int i=0; i<acc.size(); i++)
    	{
    		if(acc.get(i).getFullName().equals(accountName)) {
    			find[count]=i;
    			count++;
			}
    	}
    	if(count>0)//if finds some thing
		{
			//allocate memory for return array
    		ReturnArray= new Account[count];
    		for(int h=0; h<count; h++)
    		{
    			ReturnArray[h]=acc.get(find[h]);
    		}
		}
    	return ReturnArray;
	}
	
	public Account depositAcc(String accNum, double money)
    {
        for (int i = 0; i < acc.size(); i++)
        {
            if (accNum.equals(acc.get(i).getAccountNumber()))
            {
            	acc.get(i).deposit(money);
            	return acc.get(i);
            }
        }
        return null;
    }
	
	public Account withdrawAcc(String accNum, double money)
    {
        for (int i = 0; i < acc.size(); i++)
        {
            if (accNum.equals(acc.get(i).getAccountNumber()))
            {
            	acc.get(i).withdraw(money);
            	return acc.get(i);
            }
        }
        return null;
    }
	
	public Account[] getAllAccounts()
	    {
	        return acc.toArray(new Account[acc.size()]);
	    }
    
}
