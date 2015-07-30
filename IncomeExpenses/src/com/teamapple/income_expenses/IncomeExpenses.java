package com.teamapple.income_expenses;

public class IncomeExpenses {

	public static void main(String[] args) {
		new IncomeExpenses().runProgram();

	}
	
	public void runProgram(){
		FamilyGui g = new FamilyGui();
		new ExpensesGui();
	}

}
