/*Peter & Alex code challenge
 * Class to deal with person creation for a family budget application
 * with different tax brackets for different numbers of persons working in family etc
 * 
 */

import java.util.Scanner;

public class Person {
	static double netTotalIncome;
	static double grossTotalIncome;

	static int numTaxPayers = 0;

	private String name;
	private String role;

	private boolean isEarner;
	private boolean isAfterTax;

	private double income;
	//getters and setters
	public static double getNetTotalIncome() {
		return netTotalIncome;
	}

	public static void setNetTotalIncome(double totalIncome) {
		Person.netTotalIncome = totalIncome;
	}

	public static double getGrossTotalIncome() {
		return grossTotalIncome;
	}

	public static void setGrossTotalIncome(double grossTotalIncome) {
		Person.grossTotalIncome = grossTotalIncome;
	}

	public static int getNumTaxPayers() {
		return numTaxPayers;
	}

	public static void setNumTaxPayers(int numTaxPayers) {
		Person.numTaxPayers = numTaxPayers;
	}

	public boolean isAfterTax() {
		return isAfterTax;
	}

	public void setAfterTax(boolean isAfterTax) {
		this.isAfterTax = isAfterTax;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEarner() {
		return isEarner;
	}

	public void setEarner(boolean isEarner) {
		this.isEarner = isEarner;
	}

	public double getIncome() {
		return income;
	}

	public void setIncome(double income) {
		this.income = income;
	}

	public Person(){
		this("", "", false, 0);
	}
	//create person
	public Person(String name, String role, boolean isEarner, double income){
		this.name = name;
		this.role = role;
		this.income = income;
		this.isEarner = isEarner;
		
		numTaxPayers++;

		grossTotalIncome += income;

		netTotalIncome += calcTax();

	}
	
	//default work with yearly salary below
	
	private double calcTax() {
		
		double tempNet = 0;
		//tax bracket different for two earners
		if(numTaxPayers == 2 && grossTotalIncome > 65600D){
			
			double tempGross = grossTotalIncome - 65600D;
			double taxAt40 = (tempGross * 40D) / 100D;
			double taxAt20 = 65600D * 20 / 100;
			
			tempNet = grossTotalIncome - (taxAt40 + taxAt20);	
		
		} else if(numTaxPayers == 2 && grossTotalIncome < 65600D){
			
			double taxAt20 = grossTotalIncome * 20 / 100;
			tempNet = grossTotalIncome - taxAt20;
			//tax bracket different for 1 earner
		} else if(numTaxPayers == 1 && grossTotalIncome > 42800D){
			
			double tempGross = grossTotalIncome - 42800D;
			double taxAt40 = (tempGross * 40D) / 100D;
			double taxAt20 = 42800D * 20 / 100;
			
			tempNet = grossTotalIncome - (taxAt40 + taxAt20);
			
		} else if(numTaxPayers == 1 && grossTotalIncome < 42800D){
			
			double taxAt20 = grossTotalIncome * 20 / 100;
			tempNet = grossTotalIncome - taxAt20;
			
		}
		
		return tempNet;
	}
	//calc hours and weeks worked to yearly salary
	public static Double calcSalary(){
		System.out.println("1) Enter yearly salary\n2) Enter hourly rate");
		System.out.print("Please choose: ");

		Scanner scan = new Scanner(System.in);

		int choice = scan.nextInt();

		System.out.print("Please enter amount: ");

		Scanner scan2 = new Scanner(System.in);

		double income = scan2.nextDouble();

		if(choice == 2){
			income = calcHourly(income);
		}

		return income;
	}

	private static double calcHourly(double income) {
		System.out.println("Please enter amount of hours you work a week");
		Scanner scan = new Scanner(System.in);
		int hours = scan.nextInt();

		System.out.println("Please enter amount of weeks you work a year");
		Scanner scan2 = new Scanner(System.in);
		int weeks = scan2.nextInt();

		return income * hours * weeks;
	}


	@Override
	public String toString(){
		String temp = "Name: " + this.name + "\nRole: " + this.role + "\nEarner: " + this.isEarner + "\nIncome: ";
		String temp2 = String.format("%.2f", income);

		return temp + "€" + temp2;
	}

}
