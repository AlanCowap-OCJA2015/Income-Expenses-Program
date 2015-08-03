package com.team3.familybudgetapp;

/**
 * @author Kevin Phair, Seamus O'Toole
 * Created 30 Jul 2015
 *
 */
public class Expense {

	private String description = "";
	private double amount = 0.0;
	public enum Recurrences {ONCE, DAILY, WEEKLY, MONTHLY, YEARLY}
	private Recurrences recurs = Recurrences.ONCE;
	
	public Expense(){
		
	}
	public Expense(String description, double amount){
		this.setDescription(description);
		this.setAmount(amount);
		this.setRecurs (Recurrences.ONCE);
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
	public Recurrences getRecurs () {
		return recurs;
	}
	public char getRecursCode () {
		switch (recurs) {
			case ONCE: return 'O';
			case DAILY: return 'D';
			case WEEKLY: return 'W';
			case MONTHLY: return 'M';
			case YEARLY: return 'Y';
			default: return '?';			
		}
	}
	public String getRecursString () {
		switch (recurs) {
			case ONCE: return "Once-off";
			case DAILY: return "Daily";
			case WEEKLY: return "Weekly";
			case MONTHLY: return "Monthly";
			case YEARLY: return "Yearly";
			default: return "Undefined";			
		}
	}

	public void setRecurs (Recurrences recurs) {
		this.recurs = recurs;
	}
	
}
