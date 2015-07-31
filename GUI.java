import java.awt.BorderLayout;

public class GUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtTotalGross;
	private JTextField txtTotalNet;
	private JTextField txtTotalExpenses;
	private JTextField txtSurplus;
	private JTextArea txtDisplayFamily;

	public static ArrayList<Person> family = new ArrayList<Person>();
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		GUI gui = new GUI();
		gui.runApp();
	}
	
	private void runApp(){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI frame = new GUI();
					frame.setVisible(true);
					txtDisplayFamily.setText(viewFamily());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		SwingUtilities.updateComponentTreeUI(this);
		this.invalidate();
		this.validate();
		this.repaint();
	}
	
	/**
	 * Create the frame.
	 */
	public GUI() {

		try {
			// Set System L&F
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (UnsupportedLookAndFeelException e) {
			// handle exception
		}
		catch (ClassNotFoundException e) {
			// handle exception
		}
		catch (InstantiationException e) {
			// handle exception
		}
		catch (IllegalAccessException e) {
			// handle exception
		}


		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("My Budget App");

		JPanel panel = new JPanel();
		panel.setBounds(464, 11, 110, 390);
		contentPane.add(panel);
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblManageMembers = new JLabel("Manage Members");
		panel.add(lblManageMembers);

		JButton addPerson = new JButton("Add Person");
		addPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new addPersonFrame();
			}
		});
		addPerson.setPreferredSize(new Dimension(107, 23));
		panel.add(addPerson);

		JButton editPerson = new JButton("Edit Person");
		editPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new editPersonFrame();
			}
		});
		editPerson.setPreferredSize(new Dimension(107, 23));
		panel.add(editPerson);

		JLabel lblManageExpenses = new JLabel("Manage Expenses");
		panel.add(lblManageExpenses);

		JButton btnAddExpense = new JButton("Add Expense");
		panel.add(btnAddExpense);

		JButton btnEditExpense = new JButton("Edit Expense");
		panel.add(btnEditExpense);

		JLabel lblReport = new JLabel("Report");
		panel.add(lblReport);

		JButton btnSaveReport = new JButton("Save Report");
		panel.add(btnSaveReport);

		txtDisplayFamily = new JTextArea();
		txtDisplayFamily.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		txtDisplayFamily.setBounds(10, 31, 130, 163);
		contentPane.add(txtDisplayFamily);

		JLabel lblFamilyMembers = new JLabel("Family Members");
		lblFamilyMembers.setBounds(10, 11, 130, 14);
		contentPane.add(lblFamilyMembers);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 205, 130, 196);
		contentPane.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		JLabel lblTotalGrossIncome = new JLabel("Total Gross Income");
		panel_1.add(lblTotalGrossIncome);

		txtTotalGross = new JTextField();
		panel_1.add(txtTotalGross);
		txtTotalGross.setColumns(10);

		JLabel lblTotalNetIncome = new JLabel("Total Net Income");
		panel_1.add(lblTotalNetIncome);

		txtTotalNet = new JTextField();
		panel_1.add(txtTotalNet);
		txtTotalNet.setColumns(10);

		JLabel lblTotalExpenses = new JLabel("Total Expenses");
		panel_1.add(lblTotalExpenses);

		txtTotalExpenses = new JTextField();
		panel_1.add(txtTotalExpenses);
		txtTotalExpenses.setColumns(10);

		JLabel lblSurplus = new JLabel("Discrestionary");
		panel_1.add(lblSurplus);

		txtSurplus = new JTextField();
		panel_1.add(txtSurplus);
		txtSurplus.setColumns(10);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(150, 31, 304, 163);
		contentPane.add(panel_2);
		
	}
	
	private String viewFamily(){
		String output = "";
		for(Person p: family){
			output += p.toString();
		}//end for
		return output;
	}
	
}
