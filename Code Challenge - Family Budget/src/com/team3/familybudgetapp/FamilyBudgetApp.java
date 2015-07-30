package com.team3.familybudgetapp;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class FamilyBudgetApp extends JFrame {

	private JPanel contentPaneMainWindow;

	private Family family = new Family();
	int selectedMember = 0;
	public JList list;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FamilyBudgetApp frame = new FamilyBudgetApp();
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
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 553, 413);
		contentPaneMainWindow = new JPanel();
		contentPaneMainWindow.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPaneMainWindow);
		contentPaneMainWindow.setLayout(null);

		JLabel lblFamilyMembers = new JLabel("Family Members");
		lblFamilyMembers.setBounds(100, 11, 121, 14);
		contentPaneMainWindow.add(lblFamilyMembers);

		JButton btnAddPerson = new JButton("Add Person");
		btnAddPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddPerson(list, family.members);
			}
		});
		btnAddPerson.setBounds(266, 37, 134, 23);
		contentPaneMainWindow.add(btnAddPerson);

		JButton btnRemovePerson = new JButton("Remove Person");
		btnRemovePerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RemovePerson(list, family.members, list.getSelectedIndex());
			}
		});
		btnRemovePerson.setBounds(266, 98, 134, 23);
		contentPaneMainWindow.add(btnRemovePerson);

		JButton btnEditPerson = new JButton("Edit Person");
		btnEditPerson.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditPerson();
				
			}
		});
		btnEditPerson.setBounds(266, 154, 134, 23);
		contentPaneMainWindow.add(btnEditPerson);

		JButton btnLoad = new JButton("Load");
		btnLoad.setBounds(266, 212, 134, 23);
		contentPaneMainWindow.add(btnLoad);

		JButton btnSave = new JButton("Save");
		btnSave.setBounds(266, 269, 134, 23);
		contentPaneMainWindow.add(btnSave);

		JButton btnEditExpenses = new JButton("Edit Expenses");
		btnEditExpenses.setBounds(25, 303, 115, 23);
		contentPaneMainWindow.add(btnEditExpenses);

		JButton btnReports = new JButton("Reports");
		btnReports.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnReports.setBounds(141, 303, 115, 23);
		contentPaneMainWindow.add(btnReports);
		
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
//				list.setListData(listData);
				selectedMember = list.getSelectedIndex();
				System.out.println(selectedMember);
			}
		});
		list.setBounds(25, 37, 231, 255);
		contentPaneMainWindow.add(list);
	}
}