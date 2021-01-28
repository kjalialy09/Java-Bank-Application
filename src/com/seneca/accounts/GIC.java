package com.seneca.accounts;

import java.math.BigDecimal;
import java.text.DecimalFormat;

public class GIC extends Account implements Taxable {
	private int invPeriod;
	private BigDecimal TaxAmount;
	private BigDecimal anInterest;
	private BigDecimal startBalance;

	public GIC() {
		super();
		setInvPeriod(1);
		setAnInterest(1.25);
		setStartBalance(0);
	}
	
	public GIC(String name, String acc, double stBal, int years, double rate) {
		super(name, acc, stBal);
		setInvPeriod(years);
		setAnInterest(rate);
		setStartBalance(stBal);
	}
	
	public void setInvPeriod(int year) {
		invPeriod = year;
	}
	
	public void setAnInterest(double rate) {
		anInterest = new BigDecimal(rate/100);		
	}
	
	public void setStartBalance(double st) {
		startBalance = new BigDecimal(st);		
	}
	
	public int getInvPeriod() {
		return invPeriod;
	}
	
	public String getTax() {
		return new DecimalFormat("#.##").format(taxRate*100);
	}
	
	public double getAnInterest() {
		return anInterest.doubleValue();		
	}
	
	public double getStartBalance() {
		return startBalance.doubleValue();		
	}
	
	public Double getBalance() {
		BigDecimal x = startBalance;
		BigDecimal y = anInterest;
		y = y.add(new BigDecimal(1));
		x = x.multiply(y.pow(getInvPeriod()));
		return x.doubleValue();
	}
	
	public void calculateTax() {
		TaxAmount = new BigDecimal(getBalance()).subtract(startBalance).multiply(new BigDecimal(taxRate));
	}
	
	public double getTaxAmount() {		
		return TaxAmount.doubleValue();
	}
	
	public String createTaxStatement() {
		calculateTax();
		//String s = String.format("sss%6.2f",(new BigDecimal(getBalance())).subtract(startBalance).doubleValue());
		StringBuffer str = new StringBuffer();
		str.append("Account Number : " + getAccountNumber() +'\n');
		str.append("Interest Income: $" + String.format("%6.2f",(new BigDecimal(getBalance())).subtract(startBalance).doubleValue()) + '\n');
		str.append("Amount of Tax  : $" + String.format("%6.2f",getTaxAmount()));
		return str.toString();
	}
	
	public String toString()
	{
		/**
		 * Name           : Smith, John
		 * Number         : A0007
		 * Current Balance: $1002.99
		 */	
		String h;
		if(super.getMiddleName() == null)
		h = (super.getFirstName() + " " + super.getLastName());
		else
		 h = (super.getFirstName() + " " + super.getMiddleName() + " " + super.getLastName());
		
		StringBuffer str = new StringBuffer(new Account(h , super.getAccountNumber(), this.getStartBalance()).toString());
		str.append("Account Type               : GIC" + '\n');
		str.append("Annual Interest Rate       : " + new DecimalFormat("0.00").format(getAnInterest()*100) + "%\n");
		str.append("Period of Investment       : " + invPeriod + " years\n");
		str.append("Interest Income at Maturity: " + "$" +
								new DecimalFormat("0.00").format((new BigDecimal(getBalance())).subtract(startBalance)) + "\n");
		str.append("Balance at Maturity        : " + "$" + new DecimalFormat("0.00").format(getBalance()) + "\n");	
		
		return str.toString();
	}
	
	public int hashCode()
	{
		int hash = 17;
		
		hash = hash * 37 + super.hashCode();
		hash = hash * 37 + anInterest.hashCode();
		hash = hash * 37 + startBalance.hashCode() + invPeriod;
		
		return hash;
	}
	
	public boolean equals(Object obj)
	{
		if (obj == this)
			return true;
		if (!(obj instanceof GIC) || !(super.equals(obj)))
			return false;
		
		GIC tmp = (GIC) obj;		
		BigDecimal a = new BigDecimal(tmp.getAnInterest());
		BigDecimal b = new BigDecimal(tmp.getStartBalance());

		return this.startBalance.equals(b) &&
				this.anInterest.equals(a) && this.invPeriod == tmp.getInvPeriod() ;
	}
	
	public boolean withdraw()
	{
		return false;
	}
	
	public void deposit()
	{
		
	}
	// Main
	public static void main(String args[]) {
		GIC a = new GIC("Mary Ryan", "C005", 1000, 2, 1.5);
		System.out.println(a);
		//str.append("Interest income: $" + String.format("%6",new DecimalFormat("0.00").format((new BigDecimal(getBalance())).subtract(startBalance))) + '\n');
		//str.append("Amount of tax  : $" + String.format("%6",new DecimalFormat("0.00").format(getTaxAmount())) +'\n');
	}
}
