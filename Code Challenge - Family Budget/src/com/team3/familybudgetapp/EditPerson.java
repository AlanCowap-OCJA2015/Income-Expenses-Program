package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class EditPerson {
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldDateOfBirthDay;
	private JTextField textFieldDateOfBirthMonth;
	private JTextField textFieldDateOfBirthYear;
	private JTextField textFieldGender;
	
	public EditPerson(final Person member){
		JList list = FamilyBudgetApp.list;
		final JFrame editPerson = new JFrame("Edit Family Member");
		editPerson.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel panelEditPerson = new JPanel();
		panelEditPerson.setLayout(null);
		panelEditPerson.setOpaque(true);
		editPerson.getContentPane().add(BorderLayout.CENTER, panelEditPerson);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 40, 73, 14);
		panelEditPerson.add(lblFirstName);
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(85, 37, 243, 20);
		textFieldFirstName.setText(member.firstName);
		panelEditPerson.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		textFieldFirstName.grabFocus();
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 81, 73, 14);
		panelEditPerson.add(lblLastName);
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(85, 78, 243, 20);
		textFieldLastName.setText(member.lastName);
		panelEditPerson.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setEnabled(false);
		lblDateOfBirth.setBounds(10, 132, 73, 14);
		JLabel lblDd = new JLabel("DD");
		lblDd.setEnabled(false);
		lblDd.setBounds(85, 109, 29, 14);
		panelEditPerson.add(lblDd);
		JLabel lblMm = new JLabel("MM");
		lblMm.setEnabled(false);
		lblMm.setBounds(135, 109, 29, 14);
		panelEditPerson.add(lblMm);
		JLabel lblYyyy = new JLabel("YYYY");
		lblYyyy.setEnabled(false);
		lblYyyy.setBounds(185, 109, 40, 14);
		panelEditPerson.add(lblYyyy);
		panelEditPerson.add(lblDateOfBirth);
		textFieldDateOfBirthDay = new JTextField();
		textFieldDateOfBirthDay.setEnabled(false);
		textFieldDateOfBirthDay.setBounds(85, 129, 40, 20);
		panelEditPerson.add(textFieldDateOfBirthDay);
		textFieldDateOfBirthDay.setColumns(10);
		textFieldDateOfBirthMonth = new JTextField();
		textFieldDateOfBirthMonth.setEnabled(false);
		textFieldDateOfBirthMonth.setBounds(135, 129, 40, 20);
		panelEditPerson.add(textFieldDateOfBirthMonth);
		textFieldDateOfBirthMonth.setColumns(10);
		textFieldDateOfBirthYear = new JTextField();
		textFieldDateOfBirthYear.setEnabled(false);
		textFieldDateOfBirthYear.setBounds(185, 129, 59, 20);
		panelEditPerson.add(textFieldDateOfBirthYear);
		textFieldDateOfBirthYear.setColumns(10);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setEnabled(false);
		lblGender.setBounds(10, 177, 73, 14);
		panelEditPerson.add(lblGender);
		textFieldGender = new JTextField();
		textFieldGender.setEnabled(false);
		textFieldGender.setBounds(85, 174, 90, 20);
		panelEditPerson.add(textFieldGender);
		textFieldGender.setColumns(10);
		
		JButton btnOK = new JButton("OK");
		btnOK.setBounds(10, 276, 89, 23);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Save changes to the Person object
				member.firstName = textFieldFirstName.getText();
				member.lastName = textFieldLastName.getText();
				
				// Update the list in the main window
				FamilyBudgetApp.updateList();
				FamilyBudgetApp.updateButtons();;
				editPerson.dispatchEvent(new WindowEvent(editPerson, WindowEvent.WINDOW_CLOSING));
			}
		});
		panelEditPerson.add(btnOK);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(239, 276, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editPerson.dispatchEvent(new WindowEvent(editPerson, WindowEvent.WINDOW_CLOSING));
			}
		});
		panelEditPerson.add(btnCancel);
		
		JButton btnIncome = new JButton("Personal Income...");
		btnIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditIncome(member);
			}
		});
		btnIncome.setBounds(10, 225, 154, 23);
		panelEditPerson.add(btnIncome);
		
		JButton btnExpenses = new JButton("Personal Expenses...");
		btnExpenses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ExpensesWindow(member.expenses, member.getFullName());
			}
		});
		btnExpenses.setBounds(185, 225, 143, 23);
		panelEditPerson.add(btnExpenses);
		editPerson.setResizable(false);
		editPerson.setSize(346, 335);
		Rectangle screenSize = editPerson.getGraphicsConfiguration().getBounds();
		editPerson.setLocation(screenSize.width / 2 - editPerson.getWidth() / 2, screenSize.height / 2 - editPerson.getHeight() / 2);
		editPerson.setVisible(true);
		editPerson.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldFirstName, textFieldLastName, textFieldDateOfBirthDay, textFieldDateOfBirthMonth, textFieldDateOfBirthYear, textFieldGender, btnOK, btnCancel}));
		
	}
}
