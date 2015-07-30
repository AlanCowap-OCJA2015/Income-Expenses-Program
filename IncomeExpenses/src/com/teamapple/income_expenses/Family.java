package com.teamapple.income_expenses;

import java.util.ArrayList;

public class Family {
	
	public static ArrayList<Person> family = new ArrayList<Person>();
	
	public static double totalIncome = 0;
	
	public static void calculateTotalIncome(){
		
		totalIncome = 0;
		
		for(Person member:family){
			totalIncome += member.getSalary();
		}
		System.out.println(totalIncome);
	}

}
