package com.teamapple.income_expenses;

import java.util.ArrayList;

public class Family {
	
	public static ArrayList<Person> family = new ArrayList<Person>();
	public static ArrayList<Expense> expenses = new ArrayList<Expense>();
	
	public static double totalIncome = 0;
	public static double totalExpenses = 0;
	public static double totalSpareMoney = 0;
	
	
	public static void calculateTotalIncome(){
		
		totalIncome = 0;
		
		for(Person member:family){
			totalIncome += member.getSalary();
		}
		System.out.println(totalIncome);
	}
	
	public static void calculateTotalExpenses(){
		totalExpenses = 0;
		
		for(Expense expense: expenses){
			totalExpenses += expense.getAmount();
		}
		
		System.out.println(totalExpenses);
	}

	
	public static void calculateSpareMoney(String timescale){
		totalSpareMoney = totalIncome;
		System.out.println("Total Income before expenses = " + totalIncome); 
		int timeFactor = 1;
		
		switch(timescale){
		case "YEARLY": timeFactor = 1; break;
		case "MONTHLY": timeFactor = 12;  break;
		case "WEEKLY":	timeFactor = 52; break;
		}
		
		for(Expense expense: expenses){
			totalSpareMoney -= (expense.getAmount()/timeFactor);
			System.out.print(expense.getName() + " -" + (expense.getAmount()/timeFactor) + " = "+ totalSpareMoney);
		}
		
		System.out.println("Total Income after expenses = " + totalSpareMoney); 
		
	}
	
}
