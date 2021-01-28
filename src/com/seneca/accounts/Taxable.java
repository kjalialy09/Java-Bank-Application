package com.seneca.accounts;

public interface Taxable {
	final double taxRate = 0.15;
	void calculateTax();
	double getTaxAmount();
	String createTaxStatement();
}

