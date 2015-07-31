import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class BudgetApp {

	//instance
	private ArrayList<Person> family = new ArrayList<Person>();
	private ArrayList<Expenses> bills = new ArrayList<Expenses>();

	public static void main(String[] args) {
		BudgetApp ba = new BudgetApp();
		ba.runApp();
	}



	private void runApp(){
		Scanner scan = new Scanner(System.in);
		boolean isRunning = true;

		System.out.println("Welcome");
		System.out.print("How many people in your family: ");
		while(!scan.hasNextInt()){
			scan.next();
			System.out.print("Sorry not a valid number, please try again:");
		}
		int numMembersOfFamily = scan.nextInt();

		for(int i = 0; i < numMembersOfFamily; ++i){
			addMember();
		}

		do{
			System.out.println("What would you like to do:");
			System.out.println("1) View family");
			System.out.println("2) Add family member");
			System.out.println("3) Add expense");
			System.out.println("4) View total income");
			System.out.println("5) View total expenses");
			System.out.println("6) View discretionary income");
			System.out.println("7) Save report to file");
			System.out.println("8) Quit");
			System.out.print("Please pick an option: ");
			int userChoice = scan.nextInt();

			switch(userChoice){
			case 1:
				viewFamily();
				break;
			case 2:
				addMember();
				break;
			case 3:
				addExpense();
				break;
			case 4:
				System.out.println(Person.netTotalIncome);
				break;
			case 5:
				System.out.println(Expenses.totalExpense);
				break;
			case 6:
				System.out.println(Person.netTotalIncome - Expenses.totalExpense);
				break;
			case 7:
				saveToReport();
				break;
			case 8:
				System.out.println("Goodbye");
				isRunning = false;
				break;
			default:
				System.out.println("Invalid choice");
				break;
			}

			System.out.println();
		}while(isRunning);


	}

	private void saveToReport() {
		// TODO Auto-generated method stub
		try {
			
			System.out.println("Hello");
			
			BufferedWriter bw = new BufferedWriter(new FileWriter(new File("report.txt")));
			String report = "";
			
			for(int i = 0; i < family.size(); ++i){
				report += family.get(i) + "\r\n";
			}
			
			for(int i = 0; i < bills.size(); ++i){
				report += bills.get(i) + "\r\n";
			}
			
			bw.write(report);
			bw.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}



	private void viewFamily(){
		for(Person member: family){
			System.out.println(member);
		}

	}

	private void addMember(){
		Scanner scanMember = new Scanner(System.in);
		String name = "";
		String role = "";
		boolean isEarner = false;

		System.out.print("Name of person: ");
		name = scanMember.nextLine();

		System.out.println("Role in family: ");
		role = scanMember.next();

		System.out.println("Are they contributing to the family income (Yes/No): ");
		String contributing = scanMember.next();
		contributing = contributing.toLowerCase();
		if(contributing.contains("y")){
			isEarner = true;
			family.add(new Person(name, role, isEarner, Person.calcSalary()));
		}else if(contributing.contains("n")){
			System.out.println(isEarner);
			family.add(new Person(name, role, isEarner));
		}else{
			System.out.println("Not a valid input, auto setting to no");
			family.add(new Person(name, role, isEarner));
		}
	}//end addMember
	
	private void addExpense(){
		String name = "";
		double amount = 0.0;
		TypeOfExpense type;
		
		System.out.println("What type of expense do you want to add");
		Scanner scan = new Scanner(System.in);
		name= scan.nextLine();
		
		System.out.println("Please enter the amount: ");
		amount = scan.nextDouble();
		
		System.out.println("How offten do you want to pay: ");
		String frequency = scan.next();
		
		frequency = frequency.toLowerCase();
		
		if(frequency.startsWith("w")){
			type=TypeOfExpense.WEEKLY;			
		}
		else if(frequency.startsWith("m")){
			type=TypeOfExpense.MONTHLY;
		}
		else if(frequency.startsWith("b")){
			type=TypeOfExpense.BIMONTHLY;
		}
		else if(frequency.contains("tw")){
			type=TypeOfExpense.BIMONTHLY;
		}
		else if(frequency.startsWith("y")){
			type=TypeOfExpense.YEARLY;
		}
		else{
			System.out.println("Invalid input, assuming bimonthly expense");
			type = TypeOfExpense.BIMONTHLY;
		}
		
		bills.add(new Expenses(name, amount, type));
	}

}

//Unused method, Discovered the Person class handled this for me
//	private double findEarning(){
//		Scanner scan = new Scanner(System.in);
//		double earnings = 0.0;
//		
//		System.out.print("Are you entering before or after tax (Yes/No):");
//		String taxed = scan.next();
//		taxed = taxed.toLowerCase();
//		
//		if(taxed.contains("y")){
//
//		}else if(taxed.contains("n")){
//
//		}else{
//			System.out.println("Not a valid input, auto setting to taxed");
//		}
//		
//		System.out.print("Do they get paid weekly or yearly: ");
//		String frequency = scan.next();
//		
//		frequency = frequency.toLowerCase();
//		
//		if(frequency.contains("y")){
//			;
//		}else if(frequency.contains("w")){
//			;
//		}else{
//			System.out.println("Not a valid input, guessing");	
//		}
//		
//		return earnings;
//	}
