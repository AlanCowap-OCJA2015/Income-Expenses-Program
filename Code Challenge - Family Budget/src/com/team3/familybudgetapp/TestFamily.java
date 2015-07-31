package com.team3.familybudgetapp;

public class TestFamily {

	TestFamily(Family family) {

		family.setDescription("Flintstones");
		family.expenses.add(new Expense("Mortgage", 15000));
		family.expenses.add(new Expense("Food", 5000));
		family.members.add(new Person("Fred", "Flintstone", true, 40000, 0.25));
		family.members.get(0).expenses.add(new Expense("Car", 3000));
		family.members.add(new Person("Wilma", "Flintstone"));
		family.members.get(1).expenses.add(new Expense("Phone", 300));
	}

}
