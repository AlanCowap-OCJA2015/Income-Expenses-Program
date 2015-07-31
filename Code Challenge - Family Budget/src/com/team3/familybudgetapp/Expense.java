package com.team3.familybudgetapp;

/**
 * @author Kevin Phair, Seamus O'Toole
 * Created 30 Jul 2015
 *
 */
public class Expense {

	String description = "";
	double amount = 0.0;
	public Expense(){
		
	}
	public Expense(String description, double amount){
		this.setDescription(description);
		this.setAmount(amount);
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
}
