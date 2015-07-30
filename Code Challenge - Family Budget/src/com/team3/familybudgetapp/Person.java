package com.team3.familybudgetapp;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Kevin Phair, Seamus O'Toole
 * Created 30 Jul 2015
 *
 */
public class Person {

	String firstName = "";
	String lastName = "";
	Date dob = null;
	boolean earner = false;
	ArrayList<Expense> expenses = new ArrayList<Expense>();
	double income = 0.0;
	double taxBracket = 0.0;
	
}
