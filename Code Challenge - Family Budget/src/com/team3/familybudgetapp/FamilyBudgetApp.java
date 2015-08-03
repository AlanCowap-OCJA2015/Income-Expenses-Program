package com.team3.familybudgetapp;



import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/*
 * Specification
 * Create an income and expense tracker for families
 * Application should allow the following to be done:
 *  - Specify size of family
 *  - How many are earners and non-earners
 *  - Specify amout
 */
public class FamilyBudgetApp extends JFrame implements ActionListener{

	private JPanel contentPaneMainWindow;

	private static Family family = new Family();
	
	public Rectangle screenSize;
	
	static JList list;
	static JLabel lblFamilyMembers;
	static JButton btnRemovePerson;
	static JButton btnEditPerson;
	static JButton btnLoad;
	static JButton btnSave;
	static JButton btnAddPerson;
	static JButton btnEditExpenses;
	static JButton btnReports;
	JFileChooser fc;
	
	static public JFrame familyBudgetAppWindow;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				new TestFamily(family);
				try {
					FamilyBudgetApp frame = new FamilyBudgetApp();
					updateButtons();
					updateList();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	/*
	 * Copying the style for this from the FileChooserDemo class from java oracle tutorial. 
	 * It would seem logical that it would be more efficient to have a single listener for all the buttons 
	 * instead of a separate listener for each button.
	 * Disregard above, still need listeners on each button. Is tidier though. 
	 */
	public void actionPerformed(ActionEvent e) {
		// a switch would be obvious here, except that e.getSource returns an object and hence won't work with switch. 
		if (e.getSource() == btnAddPerson){
			new AddPerson(family.members);
		} else if (e.getSource() == btnRemovePerson){
			new RemovePerson(list, family.members, list.getSelectedIndex());
		} else if (e.getSource() == btnEditPerson){
			new EditPerson(family.members.get(list.getSelectedIndex()));
		} else if (e.getSource() == btnEditExpenses){
			new ExpensesWindow(family.expenses, "the family");
		} else if (e.getSource() == btnReports){
			new Reports(family);
		} else if (e.getSource() == btnSave){
			int returnVal = fc.showSaveDialog(FamilyBudgetApp.this);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile();
				Reports.saveDataFile(family, file);
			}
		} else if (e.getSource() == btnLoad){
			int returnVal = fc.showOpenDialog(FamilyBudgetApp.this);
			if (returnVal == JFileChooser.APPROVE_OPTION){
				File file = fc.getSelectedFile();
				family = new Family();
				Reports.loadFamily(family, file);
				FamilyBudgetApp.updateList ();
			}
		}
	}
	
	/**
	 * Create the frame.
	 */
	public FamilyBudgetApp() {
		fc = new JFileChooser(System.getProperty("user.dir"));
		screenSize = this.getGraphicsConfiguration().getBounds();
		familyBudgetAppWindow = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		setBounds(screenSize.width / 2 - 120, screenSize.height / 2 - 180, 420, 333);
		setLocation(screenSize.width / 2 - getWidth() / 2, screenSize.height / 2 - getHeight() / 2);
		System.out.println();
		contentPaneMainWindow = new JPanel();
		contentPaneMainWindow.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneMainWindow);
		contentPaneMainWindow.setLayout(null);

		// Add a person to the family members list
		btnAddPerson = new JButton("Add Member");
		btnAddPerson.setBounds(266, 37, 134, 23);
		btnAddPerson.addActionListener(this);
//		Commenting this out to put in single listener method above. 
//		btnAddPerson.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				new AddPerson(family.members);
//			}
//		});
		contentPaneMainWindow.add(btnAddPerson);

		// Remove a person from the family members list
		btnRemovePerson = new JButton("Remove Member");
		btnRemovePerson.setBounds(266, 71, 134, 23);
//		Commenting this out to put in single listener method above. 
//		btnRemovePerson.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new RemovePerson(list, family.members, list.getSelectedIndex());
//			}
//		});
		// also seem to require
		btnRemovePerson.addActionListener(this);
		contentPaneMainWindow.add(btnRemovePerson);

		// Edit the details of a family member
		btnEditPerson = new JButton("Edit Member");
		btnEditPerson.setBounds(266, 105, 134, 23);
		btnEditPerson.setEnabled(false);
		btnEditPerson.addActionListener(this);
//		Commenting this out to put in single listener method above.
//		btnEditPerson.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new EditPerson(family.members.get(list.getSelectedIndex()));
//			}
//		});
		contentPaneMainWindow.add(btnEditPerson);

		// Load a family data file
		btnLoad = new JButton("Load");
		btnLoad.setBounds(336, 162, 64, 23);
		contentPaneMainWindow.add(btnLoad);
		btnLoad.addActionListener(this);

		// Save the current family data file
		btnSave = new JButton("Save");
		btnSave.setBounds(266, 162, 64, 23);
		contentPaneMainWindow.add(btnSave);
		btnSave.addActionListener(this);

		// Edit the family expenses list
		btnEditExpenses = new JButton("Family Expenses...");
		btnEditExpenses.setBounds(266, 235, 134, 23);
		btnEditExpenses.addActionListener(this);
//		Commenting this out to put in single listener method above.
//		btnEditExpenses.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				new ExpensesWindow(family.expenses, "the family");
//			}
//		});
		contentPaneMainWindow.add(btnEditExpenses);

		// Generate budget reports
		btnReports = new JButton("Reports");
		btnReports.setBounds(266, 269, 134, 23);
		btnReports.addActionListener(this);
//		Commenting this out to put in single listener method above.
//		btnReports.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				new Reports(family);
//			}
//		});
		contentPaneMainWindow.add(btnReports);
		
		// Family members list heading
		lblFamilyMembers = new JLabel("Family Members");
		lblFamilyMembers.setBounds(100, 11, 121, 14);
		contentPaneMainWindow.add(lblFamilyMembers);

		// A JScrollPane to hold the list control so it can be scrolled if it gets too big to fit.
		JScrollPane scroller = new JScrollPane();
		scroller.setBounds(10, 37, 246, 255);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		contentPaneMainWindow.add(scroller);
		
		// The list of family members
		list = new JList();
		scroller.setViewportView(list);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				updateButtons();
			}
		});

	} // End of FamilyBudgetApp window handlers

	
	// Update the contents of the listbox with the current family member list
	public static void updateList () {
		String[] listData = new String[family.members.size()];
		for (int p = 0; p < family.members.size(); ++p) { 
			listData[p] = family.members.get(p).getFullName();  
		}
		list.setListData(listData);
		list.revalidate();
		list.repaint();

	}
	
	public static void updateButtons () {
		if (FamilyBudgetApp.family.members.size() == 0) {
			btnRemovePerson.setEnabled(false);
			btnEditPerson.setEnabled(false);
		} else {
			btnRemovePerson.setEnabled(true);
			btnEditPerson.setEnabled(true);
		}
	}
	
}