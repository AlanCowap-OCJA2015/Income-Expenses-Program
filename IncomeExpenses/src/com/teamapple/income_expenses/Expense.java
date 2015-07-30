/**
 * 
 */
package com.teamapple.income_expenses;

/**
 * @author Colm, Sam, Mubahser, Thomas
 *
 */
public class Expense {
	private String name;
	private double amount;
	private boolean isRecurring;

	public boolean isRecurring() {
		return isRecurring;
	}

	public Expense(){
		this("na", 0.0, false, false);
		
	}
	
	public Expense(String name, double amount, boolean isRecurring, boolean isWeekly){
		this.name = name;
		this.isRecurring = isRecurring;
		if(isRecurring){
			if(isWeekly){
				amount *= 52;
			}else{
				amount *= 12;
			}
			
			this.amount = amount;
			
		}else{
			this.amount = amount;
		}
	
	}
	
	public String getName() {
		return name;
	}

	public double getAmount() {
		return amount;
	}
	
	

}
