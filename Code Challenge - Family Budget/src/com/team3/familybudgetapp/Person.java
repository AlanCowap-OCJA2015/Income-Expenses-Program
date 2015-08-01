package com.team3.familybudgetapp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Kevin Phair, Seamus O'Toole
 * Created 30 Jul 2015
 *
 */
public class Person {

	String firstName = "";
	String lastName = "";
	Calendar dob = null;
	boolean earner = false;
	ArrayList<Expense> expenses = new ArrayList<Expense>();
	double income = 0.0;
	double taxBracket = 0.0;
	
	public Person(){
	}
	public Person(String firstName, String lastName){
		this.setFirstName(firstName);
		this.setLastName(lastName);
	}
	public Person(String firstName, String lastName, boolean earner, double income, double taxBracket){
		this (firstName, lastName);
		this.setEarner(earner);
		this.setIncome(income);
		this.setTaxBracket(taxBracket);
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Calendar getDob() {
		return dob;
	}
	public void setDob(Calendar dob) {
		this.dob = dob;
	}
	public boolean isEarner() {
		return earner;
	}
	public void setEarner(boolean earner) {
		this.earner = earner;
	}
	public ArrayList<Expense> getExpenses() {
		return expenses;
	}
	public void setExpenses(ArrayList<Expense> expenses) {
		this.expenses = expenses;
	}
	public double getIncome() {
		return income;
	}
	public void setIncome(double income) {
		this.income = income;
	}
	public double getTaxBracket() {
		return taxBracket;
	}
	public void setTaxBracket(double taxBracket) {
		this.taxBracket = taxBracket;
	}
	public String toString(){
		return (this.lastName + ", " + this.firstName);
	}
	public double getNetIncome(){
		return (this.getIncome()-(this.getIncome()*this.getTaxBracket()));
	}

	public String getFullName() {
		return firstName + (firstName.length() > 0 ? " " : "") + lastName;
	}
	
}
