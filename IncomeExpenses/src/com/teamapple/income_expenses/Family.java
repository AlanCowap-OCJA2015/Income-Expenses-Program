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

	
	public static void calculateSpareMoney(){
		totalSpareMoney = totalIncome - totalExpenses;
		
		for(Expense expense: expenses){
			double tempDisplay = 0.0;
			tempDisplay = totalIncome - expense.getAmount();
			//System.out.print(totalIncome + totalDisplay + " "tempDisplay);
		}
		}
	
}
