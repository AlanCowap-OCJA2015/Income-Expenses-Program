/*Joe & Jenn code challenge
 * Class to deal with expenses creation for a family budget application 
 */
public class Expenses {
	
	//State
	
	private String name;
	private double amount;
	private TypeOfExpense type;
	public static double totalExpense;
	
	//Behaviour
	public Expenses(){
		this("No Bill", 0, TypeOfExpense.YEARLY);
	}
	
	public Expenses(String name, double amount, TypeOfExpense type){
		this.name = name;
		this.amount = amount;
		this.type = type;
		calculateTotalExpense(amount, type);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
		calculateTotalExpense(amount);
	}

	public TypeOfExpense getType() {
		return type;
	}

	public void setType(TypeOfExpense type) {
		this.type = type;
	} 
	
	private void calculateTotalExpense(double amount){
		totalExpense += this.calculateYearlyAmount(this);
	}
	
	private void calculateTotalExpense(double amount, TypeOfExpense type){
		totalExpense += this.calculateYearlyAmount(amount, type);
	}
	
	private double calculateYearlyAmount(double amount, TypeOfExpense type){
		switch(type){
		case WEEKLY:
			return (amount * 52);
		case MONTHLY:
			return (amount * 12);
		case BIMONTHLY:
			return (amount * 6);
		case YEARLY:
			return amount;
		default:
			return -1;
		}
	}
	
	public double calculateYearlyAmount(Expenses exp){
		switch(exp.getType()){
		case WEEKLY:
			return (this.amount * 52);
		case MONTHLY:
			return (this.amount * 12);
		case BIMONTHLY:
			return (this.amount * 6);
		case YEARLY:
			return this.amount;
		default:
			return -1;
		}
	}
	
	
	public String toString(){
		String output = "";
		output += "Name of Expense: " + this.name;
		output += ", Amount to be paid €" + this.amount;
		switch(this.getType()){
		case WEEKLY:
			output += ", Frequency: weekly";
			break;
		case MONTHLY:
			output += ", Frequency: monthly";
			break;
		case BIMONTHLY:
			output += ", Frequency: two months";
			break;
		case YEARLY:
			output += ", Frequency: yearly";
			break;
		}
		return output;
	}
	
	

}
