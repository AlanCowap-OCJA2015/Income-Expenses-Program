public class ExpenseTest {
//class to test functionality of our Expenses Class
	public static void main(String[] args) {
		Expenses exp = new Expenses();
		
		//System.out.println("Hello");

		exp.setName("Gym Membership");
		exp.setType(TypeOfExpense.MONTHLY);
		exp.setAmount(55.00);
		
//		Expenses exp = new Expenses("Gym Membership", 55.0, TypeOfExpense.MONTHLY);
		
		System.out.println("Name of expense is " + exp.getName());
		System.out.println("It is " + exp.getAmount() + " euro");
		switch(exp.getType()){
		case WEEKLY:
			System.out.println("It must be paid every week");
			break;
		case MONTHLY:
			System.out.println("It must be paid every month");
			break;
		case BIMONTHLY:
			System.out.println("It must be paid every two month");
			break;
		case YEARLY:
			System.out.println("It must be paid every year");
			break;
		}
		
		System.out.println(Expenses.totalExpense);
	}

}
