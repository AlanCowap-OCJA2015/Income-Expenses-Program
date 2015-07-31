package com.teamapple.income_expenses;

import java.util.ArrayList;

public class Family {
	
	public static ArrayList<Person> family = new ArrayList<Person>();
	public static ArrayList<Expense> expenses = new ArrayList<Expense>();
	
	public static double totalIncome = 0;
	public static double totalExpenses = 0;
	public static double totalSpareMoney = 0;
	
	
	public static double calculateTotalIncome(){
		
		totalIncome = 0;
		
		for(Person member:family){
			totalIncome += member.getSalary();
		}
		totalSpareMoney = totalIncome;
		return totalIncome;
	}
	
	public static double calculateTotalIncome(String timescale){
		calculateTotalIncome();
		int timeFactor = 1;
		
		switch(timescale){
		case "YEARLY": timeFactor = 1; break;
		case "MONTHLY": timeFactor = 12;  break;
		case "WEEKLY":	timeFactor = 52; break;
		}
		
		return totalIncome/timeFactor;
	}
	
	public static void calculateTotalExpenses(){
		totalExpenses = 0;
		
		for(Expense expense: expenses){
			totalExpenses += expense.getAmount();
		}
		
		System.out.println(totalExpenses);
	}
	
	
	public static double calculateSpareMoney(String timescale, Expense expense){
		//totalSpareMoney = totalIncome;
		System.out.println("Total Income before expenses = " + totalIncome); 
		int timeFactor = 1;
		
		switch(timescale){
		case "YEARLY": timeFactor = 1; break;
		case "MONTHLY": timeFactor = 12;  break;
		case "WEEKLY":	timeFactor = 52; break;
		}
		
		//for(Expense expense: expenses){
			if(expense.isRecurring()){
				totalSpareMoney -= (expense.getAmount()/timeFactor);
				//System.out.print(expense.getName() + " -" + (expense.getAmount()/timeFactor) + " = "+ totalSpareMoney);
			}else{
				System.out.println(expense.getAmount());
				totalSpareMoney -= expense.getAmount();
				//System.out.print(expense.getName() + " -" + (expense.getAmount()) + " = "+ totalSpareMoney);
			}
			
		//}
		
		return totalSpareMoney/timeFactor;
		
		//System.out.println("Total Income after expenses = " + totalSpareMoney); 
		
	}
	
}
