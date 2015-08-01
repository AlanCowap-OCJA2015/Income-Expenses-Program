package com.team3.familybudgetapp;

import java.util.ArrayList;

/**
 * @author Kevin Phair, Seamus O'Toole
 * Created 30 Jul 2015
 *
 */
public class Family {

	private String description;
	
	ArrayList<Person> members = new ArrayList<Person>();

	ArrayList<Expense> expenses = new ArrayList<Expense>();

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public ArrayList<Person> getMembers() {
		return members;
	}
	public ArrayList<Expense> getExpenses() {
		return expenses;
	}
}
