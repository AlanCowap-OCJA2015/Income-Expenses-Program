package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JRootPane;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class AddPerson {
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldDay;
	private JTextField textFieldMonth;
	private JTextField textFieldYear;
	private JTextField textField;
	final JButton btnCancel;	
	final JButton btnOK;
	final JFrame addPerson;
	JComboBox genderComboBox;
	JLabel lblGender;
	JLabel lblYear;
	JLabel lblMonth;
	JLabel lblDay;
	JLabel lblDateOfBirth;
	JPanel panelAddPerson;
	JLabel lblFirstName;
	JLabel lblLastName;
	
	public AddPerson(final ArrayList<Person> members){
		JList list = FamilyBudgetApp.list;
		addPerson = new JFrame("Add Family Member");
		addPerson.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		panelAddPerson = new JPanel();
		panelAddPerson.setLayout(null);
		panelAddPerson.setOpaque(true);
		addPerson.getContentPane().add(BorderLayout.CENTER, panelAddPerson);

		// First name label and text field
		lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 40, 73, 14);
		panelAddPerson.add(lblFirstName);
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(85, 37, 243, 20);
		textFieldFirstName.grabFocus();
		panelAddPerson.add(textFieldFirstName);

		// Add Escape and Enter key listeners so the window can be confirmed or cancelled with the keyboard
	    ActionListener escListener = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) { btnCancel.doClick(); }
	    };
	    addPerson.getRootPane().registerKeyboardAction(escListener,
	            KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);

	    ActionListener enterListener = new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) { btnOK.doClick(); }
	    };
	    addPerson.getRootPane().registerKeyboardAction(enterListener,
	            KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0),
	            JComponent.WHEN_IN_FOCUSED_WINDOW);
	    
		// Last name label and text field. Add a Keylistener for ENTER and Escape
		lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 81, 73, 14);
		panelAddPerson.add(lblLastName);
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(85, 78, 243, 20);
		panelAddPerson.add(textFieldLastName);

		// Date of birth label
		lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setEnabled(false);
		lblDateOfBirth.setBounds(10, 132, 73, 14);
		panelAddPerson.add(lblDateOfBirth);
		
		// Date of birth : Day
		lblDay = new JLabel("DD");
		lblDay.setEnabled(false);
		lblDay.setBounds(85, 109, 29, 14);
		panelAddPerson.add(lblDay);
		textFieldDay = new JTextField();
		textFieldDay.setEnabled(false);
		textFieldDay.setBounds(85, 129, 40, 20);
		panelAddPerson.add(textFieldDay);
		
		// Date of birth: Month
		lblMonth = new JLabel("MM");
		lblMonth.setEnabled(false);
		lblMonth.setBounds(135, 109, 29, 14);
		panelAddPerson.add(lblMonth);
		textFieldMonth = new JTextField();
		textFieldMonth.setEnabled(false);
		textFieldMonth.setBounds(135, 129, 40, 20);
		panelAddPerson.add(textFieldMonth);
		
		// Date of birth: Year
		lblYear = new JLabel("YYYY");
		lblYear.setEnabled(false);
		lblYear.setBounds(185, 109, 40, 14);
		panelAddPerson.add(lblYear);
		textFieldYear = new JTextField();
		textFieldYear.setEnabled(false);
		textFieldYear.setBounds(185, 129, 59, 20);
		panelAddPerson.add(textFieldYear);

		// Gender label and combo box
		lblGender = new JLabel("Gender");
		lblGender.setEnabled(false);
		lblGender.setBounds(10, 177, 73, 14);
		panelAddPerson.add(lblGender);
		genderComboBox = new JComboBox();
		genderComboBox.setModel(new DefaultComboBoxModel(new String[] {"Male", "Female", "Other"}));
		genderComboBox.setEnabled(false);
		genderComboBox.setBounds(85, 173, 90, 22);
		panelAddPerson.add(genderComboBox);
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setBounds(185, 174, 143, 20);
		panelAddPerson.add(textField);

		btnOK = new JButton("Okay");
		btnOK.setBounds(16, 224, 89, 23);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a new Person object and add it to the ArrayList
				Person newPerson = new Person();
				newPerson.firstName = textFieldFirstName.getText();
				newPerson.lastName = textFieldLastName.getText();
				members.add(newPerson);
				
				// Update the list in the main window
				FamilyBudgetApp.updateList();
				FamilyBudgetApp.updateButtons();
				// Close our own window
				addPerson.dispatchEvent(new WindowEvent(addPerson, WindowEvent.WINDOW_CLOSING));
			}
		});
		panelAddPerson.add(btnOK);
		
		btnCancel = new JButton("Cancel");
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
		addPerson.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldFirstName, textFieldLastName, textFieldDay, textFieldMonth, textFieldYear, btnOK, btnCancel}));
		addPerson.setVisible(true);
		
	}
}
