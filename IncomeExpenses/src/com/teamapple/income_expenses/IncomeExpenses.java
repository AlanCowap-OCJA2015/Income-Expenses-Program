package com.teamapple.income_expenses;

public class IncomeExpenses {

	public static void main(String[] args) {
		FamilyGui g = new FamilyGui();
		g.setVisible(true);
	}
	
	public static void runExpense(){
		ExpensesGui eg = new ExpensesGui();
		eg.setVisible(true);
	}
	
	public static void runReport(){
		ReportGui rg = new ReportGui();
		rg.setVisible(true);
	}
	
	
		
		
	

}
