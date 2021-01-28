package com.btp400;

import java.util.Scanner;
import com.seneca.accounts.*;
import com.seneca.business.*;


public class FinancialApp {
	public static void displayMenu(String bankname)
    {
        StringBuffer str = new StringBuffer("Welcome to " + bankname + " Bank!\n");
        str.append("1. Open an account.\n");
        str.append("2. Close an account.\n");
        str.append("3. Deposit money.\n");
        str.append("4. Withdraw money.\n");
        str.append("5. Display accounts.\n");
        str.append("6. Display a tax statement.\n");
        str.append("7. Exit\n");
		System.out.println(str);
    }
    
    public static int menuChoice()
    {         
        Scanner myObj = new Scanner(System.in);
        System.out.print("Please enter your choice>"); 
        while(myObj.hasNextInt() == false)
        {
        	System.out.println("Input not an Integer."); 
        	myObj = new Scanner(System.in);
            System.out.print("Please enter your choice>"); 
        }
        int choice = myObj.nextInt();
        return choice;
    }
    
    public static String userType()
    {
    	 Scanner myObj = new Scanner(System.in);                
         String choice = myObj.nextLine();
         
         return choice;
    }
    
    public static void menu(Bank obj) {
    	int choice;
    	String x = "";
    	do {
    		displayMenu(obj.getBankName());
        	choice = menuChoice();
    		switch(choice) {    		
    		case 1:
    			System.out.print("Please enter the account type (CHQ/GIC)>");
    			x = userType();
    			if (x.equals("CHQ"))
    			{   
    				System.out.print("Please enter account information at one line\n(e.g. John M. Doe;A1234;1000.00;1.5;2)\n>");
    				String a = userType();
    				String[] b = a.split(";");
    				Chequing acc = new Chequing(b[0].trim(), b[1].trim(), Double.parseDouble(b[2].trim()), Double.parseDouble(b[3].trim()), Integer.parseInt(b[4].trim()));
    				if(!(obj.addAccount(acc)))
    					System.out.println("Cannot Add This Account.");
    				else
    				System.out.println(acc);
    			}else if(x.equals("GIC"))
    			{        			
    				System.out.print("Please enter account information at one line\n(e.g. John M. Doe;A1234;1000.00;2;1.5)\n>");
    				String a = userType();
    				String[] b = a.split(";");
    				GIC acc = new GIC(b[0].trim(), b[1].trim(), Double.parseDouble(b[2].trim()), Integer.parseInt(b[3].trim()), Double.parseDouble(b[4].trim()));
    				if(!(obj.addAccount(acc)))
    					System.out.println("Cannot Add This Account.");
    				else
    				System.out.println(acc);
    			}else {
    				System.out.println("Incorrect Account Type.");
    			}
    			
    			break;
    		case 2:
    			System.out.print("Please enter Account Number>");
    			x = userType();
    			Account h = obj.removeAccount(x);
    			if(h == null)
    			{
    				System.out.println("Account Not Found.");
    			}else {    		
    			System.out.println(h);
    			System.out.println("Account " + x +" Deleted Successfully!\n");
    			}
    			break;
    		case 3:
    			System.out.print("Please Enter Account Number and amount for Deposit in one line\n(e.g. A1234, 200)\n>");
    			String a = userType();
				String[] b = a.split(";");				
    			Account tmp = obj.depositAcc(b[0].trim(), Double.parseDouble(b[1].trim()));
    			System.out.println(tmp);
    			break;
    		case 4:
    			System.out.print("Please Enter Account Number and amount for withdraw in one line\n(e.g. A1234, 200)\n>");
    			 a = userType();
				 b = a.split(";");				
    			 tmp = obj.withdrawAcc(b[0].trim(), Double.parseDouble(b[1].trim()));
    			System.out.println(tmp);
    			break;
    		case 5:
                System.out.println("Please choose one of the three options:");
                System.out.println("1. Display all accounts with the same account name");
                System.out.println("2. Display all accounts with the same final balance");
                System.out.println("3. Display all accounts opened at the bank");
                choice = menuChoice();
                switch (choice)
                {
                    case 1:
                        System.out.print("Please enter account name\n(e.g. Lastname, Firstname [MiddleName{optional}])\n>");
                        a = userType();
                        Account[] w = obj.searchByAccountName(a);
                       
                        for (int i = 0; i < w.length; i++)
                            displayAccount(w[i]);
                        break;
                    case 2:
                        System.out.print("Please enter final balance>");
                        a = userType();
                        w = obj.searchByBalance(Integer.parseInt(a));
                        for (int i = 0; i < w.length; i++)
                            displayAccount(w[i]);
                        break;
                    case 3:
                        for (int i = 0; i < obj.getArrayList().size(); i++)
                        {
                            displayAccount(obj.getArrayList().get(i));
                        }
                        break;
                }
    			break;
    		case 6:
    			System.out.print("Please enter the name of the Account you want Tax statement to be displayed\n(e.g. Lastname, Firstname [MiddleName{optional}])\n>");
    			x = userType();
    			Account[] tmpAcc = obj.searchByAccountName(x);
    			if( tmpAcc.length>0)
    			{
    				int cnt = 1;
    				System.out.println("Name: " + tmpAcc[0].getFullName());
    				System.out.println("Tax Rate: " + ((GIC)tmpAcc[0]).getTax() + "%\n");
    			for(int i = 0; i < tmpAcc.length; ++i) {    				
    				if(tmpAcc[i] instanceof GIC)
    				{
    					System.out.println("[" + cnt + "]");    					
    					System.out.println(((GIC) tmpAcc[i]).createTaxStatement());
    					cnt++;
    				}
    			}
    			} else
    				System.out.println("Account not Found!");
    			System.out.println("\n");
    			break;
    		case 7:
    			System.out.println("Thank you!");
    			choice = 7;
    			break;
    		default:
    			System.out.println("Input Out Of Range, should be 1-7!");
    			break;
    			
    		}
    		
    	} while(choice != 7);
    	
    }
    
    public static void loadBank( Bank bank ) {
    	Chequing acc = new Chequing("John Doe", "C0002", 2000, 25, 3);
		bank.addAccount(acc);
		acc = new Chequing("Mary Ryan", "C0001", 2000, 25, 3);
		bank.addAccount(acc);
		GIC acc2 = new GIC("John Doe", "B0002", 15000, 4, 2.5);
		bank.addAccount(acc2);
		acc2 = new GIC("Mary Ryan", "B0001", 6000, 2, 1.5);
		bank.addAccount(acc2);
    }
    
    public static void displayAccount( Account account ) {
    	System.out.println(account);    		
    }

    
    public static void main(String args[])
    {
    	Bank b1 = new Bank("Seneca@York");
    	loadBank(b1);
    	menu(b1);
    }
}
