/**
 * 
 */
package com.teamapple.income_expenses;

import java.util.ArrayList;

/**
 * @author Colm, Sam, Mubasher, Thomas
 *
 */
public class Person {
	private String name;
	private FamilyRole familyRole;
	private double salary;
	
	
	
	public Person(){
		this("Joe Bloggs",FamilyRole.Other);
	}
	

	public Person(String name, FamilyRole familyRole, double salary, double hoursWorked,
			boolean isAfterTax){
		this.name = name;
		this.familyRole = familyRole;
		salary = calculateTax(salary, isAfterTax);
		this.salary += calculateSalary(salary, hoursWorked);
		
		
	}
	
	public Person(String name, FamilyRole familyRole, double salary, boolean isAfterTax){
		this.name = name;
		this.familyRole = familyRole;
		this.salary = calculateTax(salary, isAfterTax);
		
	}
	
	public Person(String name, FamilyRole familyRole){
		this.name = name;
		this.familyRole = familyRole;
	}
	
	private double calculateTax(double salary, boolean isAfterTax){
		if(isAfterTax){
			return salary;
		}else{
			return salary - ((salary * 20) / 100); 
		}
	}
	
	public String getName() {
		return name;
	}

	public FamilyRole getFamilyRole() {
		return familyRole;
	}


	public double getSalary() {
		return salary;
	}

	private double calculateSalary(double salary, double hoursWorked){
		salary *= hoursWorked;
		salary *= 4;
		return salary *= 12;
	}
	
	@Override
	public String toString(){
		return "Name: " + getName() + " Family Role:  " + getFamilyRole() + " Salary: " + getSalary();
	}
	
}


enum FamilyRole{
	Father,
	Mother,
	Son,
	Daughter,
	Other;

}