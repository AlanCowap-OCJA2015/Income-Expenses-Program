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
	String initials = "";
	String lastName = "";
	enum genders {MALE, FEMALE, OTHER}
	genders gender = Person.genders.MALE;
	String genderOther = "";
	Calendar dob = null;
	boolean earner = false;				// Is an "earner" flag really necessary?
	ArrayList<Expense> expenses = new ArrayList<Expense>();
	double income = 0.0;
	enum incomeSchedules {HOURLY, WEEKLY, MONTHLY}
	incomeSchedules incomeSchedule = Person.incomeSchedules.HOURLY;
	double taxBracket = 0.0;
	
	public Person(){
		
	}
	public Person(String firstName, String lastName){
		
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
	public String getInitials () {
		return initials;
	}
	public void setInitials (String initials) {
		this.initials = initials;
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
	public genders getGender () {
		return gender;
	}
	public void setGender (genders gender) {
		this.gender = gender;
	}
	public String getGenderOther () {
		return genderOther;
	}
	public void setGenderOther (String genderOther) {
		this.genderOther = genderOther;
	}
	public incomeSchedules getIncomeSchedule () {
		return incomeSchedule;
	}
	public void setIncomeSchedule (incomeSchedules incomeSchedule) {
		this.incomeSchedule = incomeSchedule;
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
		return this.firstName + (this.firstName.length() > 0 ? " " : "") + (this.initials.length() > 0 ? " " : "") + this.lastName;
	}
	
}
