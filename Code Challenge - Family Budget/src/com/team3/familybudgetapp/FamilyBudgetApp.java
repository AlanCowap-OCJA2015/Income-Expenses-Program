package com.team3.familybudgetapp;

import java.awt.EventQueue;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.xml.transform.OutputKeys;

/*
 * Specification
 * Create an income and expense tracker for families
 * Application should allow the following to be done:
 *  - Specify size of family
 *  - How many are earners and non-earners
 *  - Specify amout
 */
public class FamilyBudgetApp extends JFrame {

	static JFrame familyBudgetAppWindow;
	private JPanel contentPaneMainWindow;

	private JLabel lblFamilyMembers;
	private JList list;
	private JButton btnAddPerson;
	private JButton btnRemovePerson;
	private JButton btnEditPerson;
	private JButton btnLoad;
	private JButton btnSave;
	private JButton btnEditExpenses;
	private JButton btnReports;
	
	static boolean dataChanged = false; 
	private static Family family = new Family();
	public Rectangle screenSize;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
//				new TestFamily(family);
				try {
					FamilyBudgetApp frame = new FamilyBudgetApp();
					frame.updateButtons();
					frame.updateList();
					frame.setVisible(true);
					frame.setResizable(false);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the frame.
	 */
	public FamilyBudgetApp() {
		
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
		btnAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Person newPerson = new Person();
				new EditMember(newPerson);

				family.members.add (newPerson);

				// Update the list in the main window
				updateList ();
				updateButtons ();
				
			}
		});
		contentPaneMainWindow.add(btnAddPerson);

		// Remove a person from the family members list
		btnRemovePerson = new JButton("Remove Member");
		btnRemovePerson.setBounds(266, 71, 134, 23);
		btnRemovePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog (contentPaneMainWindow, 
						"Deleting records cannot be undone\n"
						+ "Do you want to proceed?", 
						"Remove family member", 
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {
					family.members.remove(list.getSelectedIndex());
				}
				updateList();
				updateButtons();
			}
		});
		contentPaneMainWindow.add(btnRemovePerson);

		// Edit the details of a family member
		btnEditPerson = new JButton("Edit Member");
		btnEditPerson.setBounds(266, 105, 134, 23);
		btnEditPerson.setEnabled(false);
		btnEditPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditMember(family.members.get(list.getSelectedIndex()));
			}
		});
		contentPaneMainWindow.add(btnEditPerson);

		// Load a family data file
		btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (isDataChanged()) {
					
					JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
					int rc = jfc.showDialog(null, "Select data file to load");
					if (rc == JFileChooser.APPROVE_OPTION) {
						File file = jfc.getSelectedFile();
						String filename = file.getAbsolutePath();
						
						// Call method to open the file and load it
						// It should return true if successful
						
						clearDataChanged();
						updateList();
						updateButtons();
						
					} else {
						System.out.println("User cancelled the file chooser.");
					}
				}
			}
		});
		btnLoad.setBounds(336, 162, 64, 23);
		contentPaneMainWindow.add(btnLoad);

		// Save the current family data file
		btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
				int rc = jfc.showDialog(null, "Select data file for saving");
				if (rc == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					String filename = file.getAbsolutePath();
					// Call method to open the file and save it
					// It should return true if successful
					clearDataChanged();
				} else {
					System.out.println("User cancelled the file chooser.");
				}
				return; 
			}
		});
		btnSave.setBounds(266, 162, 64, 23);
		contentPaneMainWindow.add(btnSave);

		// Edit the family expenses list
		btnEditExpenses = new JButton("Family Expenses...");
		btnEditExpenses.setBounds(266, 235, 134, 23);
		btnEditExpenses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ExpensesWindow(family.expenses, "the family");
			}
		});
		contentPaneMainWindow.add(btnEditExpenses);

		// Generate budget reports
		btnReports = new JButton("Reports");
		btnReports.setBounds(266, 269, 134, 23);
		btnReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Reports(family);
			}
		});
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

	
	/**
	 *  Update the contents of the listbox with the current family member list
	 */
	public void updateList () {
		String[] listData = new String[family.members.size()];
		for (int p = 0; p < family.members.size(); ++p) { 
			listData[p] = family.members.get(p).getFullName();  
		}
		list.setListData(listData);
		list.revalidate();
		list.repaint();

	}
	
	/**
	 * Set the remove and edit buttons as enabled or disabled depending on whether
	 * there are members in the family and one of them has been selected in the list
	 */
	public void updateButtons () {
		if ((FamilyBudgetApp.family.members.size() > 0) && (list.getSelectedIndex() >= 0)) {
			btnRemovePerson.setEnabled(true);
			btnEditPerson.setEnabled(true);
		} else {
			btnRemovePerson.setEnabled(false);
			btnEditPerson.setEnabled(false);
		}
	}
	
	/**
	 * Set or get the data changed flag so the program can tell if the file may need to be 
	 * saved before exiting or loading a new file
	 * 
	 */
	public static void setDataChanged () {
		dataChanged = true;
	}
	public static void clearDataChanged () {
		dataChanged = false;
	}
	public static boolean isDataChanged () {
		return dataChanged;
	}
	
}