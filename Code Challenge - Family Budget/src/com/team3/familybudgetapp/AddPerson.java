package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.Font;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Dialog.ModalExclusionType;

public class AddPerson {
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldDateOfBirthDay;
	private JTextField textFieldDateOfBirthMonth;
	private JTextField textFieldDateOfBirthYear;
	private JTextField textFieldGender;
	
	public AddPerson(final ArrayList<Person> members){
		JList list = FamilyBudgetApp.list;
		final JFrame addPerson = new JFrame("Add Family Member");
		addPerson.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel panelAddPerson = new JPanel();
		panelAddPerson.setLayout(null);
		panelAddPerson.setOpaque(true);
		addPerson.getContentPane().add(BorderLayout.CENTER, panelAddPerson);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 40, 73, 14);
		panelAddPerson.add(lblFirstName);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(85, 37, 243, 20);
		panelAddPerson.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		textFieldFirstName.grabFocus();
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 81, 73, 14);
		panelAddPerson.add(lblLastName);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(85, 78, 243, 20);
		panelAddPerson.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setEnabled(false);
		lblDateOfBirth.setBounds(10, 132, 73, 14);
		panelAddPerson.add(lblDateOfBirth);
		
		textFieldDateOfBirthDay = new JTextField();
		textFieldDateOfBirthDay.setEnabled(false);
		textFieldDateOfBirthDay.setBounds(85, 129, 40, 20);
		panelAddPerson.add(textFieldDateOfBirthDay);
		textFieldDateOfBirthDay.setColumns(10);
		
		textFieldDateOfBirthMonth = new JTextField();
		textFieldDateOfBirthMonth.setEnabled(false);
		textFieldDateOfBirthMonth.setBounds(135, 129, 40, 20);
		panelAddPerson.add(textFieldDateOfBirthMonth);
		textFieldDateOfBirthMonth.setColumns(10);
		
		textFieldDateOfBirthYear = new JTextField();
		textFieldDateOfBirthYear.setEnabled(false);
		textFieldDateOfBirthYear.setBounds(185, 129, 59, 20);
		panelAddPerson.add(textFieldDateOfBirthYear);
		textFieldDateOfBirthYear.setColumns(10);
		
		JLabel lblDd = new JLabel("DD");
		lblDd.setEnabled(false);
		lblDd.setBounds(85, 109, 29, 14);
		panelAddPerson.add(lblDd);
		
		JLabel lblMm = new JLabel("MM");
		lblMm.setEnabled(false);
		lblMm.setBounds(135, 109, 29, 14);
		panelAddPerson.add(lblMm);
		
		JLabel lblYyyy = new JLabel("YYYY");
		lblYyyy.setEnabled(false);
		lblYyyy.setBounds(185, 109, 40, 14);
		panelAddPerson.add(lblYyyy);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setEnabled(false);
		lblGender.setBounds(10, 177, 73, 14);
		panelAddPerson.add(lblGender);
		
		textFieldGender = new JTextField();
		textFieldGender.setEnabled(false);
		textFieldGender.setBounds(85, 174, 90, 20);
		panelAddPerson.add(textFieldGender);
		textFieldGender.setColumns(10);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.setBounds(16, 224, 89, 23);
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a new Person object and add it to the ArrayList
				Person newPerson = new Person();
				newPerson.firstName = textFieldFirstName.getText();
				newPerson.lastName = textFieldLastName.getText();
				members.add(newPerson);
				
				// Update the list in the main window
				FamilyBudgetApp.updateList();
				FamilyBudgetApp.updateButtons();;
				addPerson.dispatchEvent(new WindowEvent(addPerson, WindowEvent.WINDOW_CLOSING));
			}
		});
		panelAddPerson.add(btnOkay);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(239, 224, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPerson.dispatchEvent(new WindowEvent(addPerson, WindowEvent.WINDOW_CLOSING));
			}
		});
		panelAddPerson.add(btnCancel);
		addPerson.setResizable(false);
		addPerson.setSize(346, 284);
		Rectangle screenSize = addPerson.getGraphicsConfiguration().getBounds();
		addPerson.setLocation(screenSize.width / 2 - addPerson.getWidth() / 2, screenSize.height / 2 - addPerson.getHeight() / 2);
		addPerson.setVisible(true);
		addPerson.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldFirstName, textFieldLastName, textFieldDateOfBirthDay, textFieldDateOfBirthMonth, textFieldDateOfBirthYear, textFieldGender, btnOkay, btnCancel}));
		
	}
}
