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
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class AddExpense {
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;

	/**
	 * TODO Change references to ExpensesWindow to parentWindow which will be passed in 
	 * 
	 */
	public AddExpense(final ArrayList<Expense> expenses){
		JList list = ExpensesWindow.list;
		final JFrame addExpense = new JFrame("Add Expense");
		addExpense.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel panelAddExpense = new JPanel();
		panelAddExpense.setLayout(null);
		panelAddExpense.setOpaque(true);
		addExpense.getContentPane().add(BorderLayout.CENTER, panelAddExpense);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(10, 14, 65, 14);
		panelAddExpense.add(lblDescription);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(85, 11, 243, 20);
		panelAddExpense.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		textFieldFirstName.grabFocus();
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(10, 45, 67, 14);
		panelAddExpense.add(lblAmount);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(85, 42, 105, 20);
		panelAddExpense.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.setBounds(10, 70, 89, 23);
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a new Person object and add it to the ArrayList
				Expense newExpense = new Expense();
				newExpense.description = textFieldFirstName.getText();
				if (textFieldLastName.getText().length() > 0) {
					newExpense.amount = Double.parseDouble(textFieldLastName.getText());
				} else {
					newExpense.amount = 0.0;
				}
				expenses.add(newExpense);
				
				// Update the list in the main window
				ExpensesWindow.updateList(expenses);
				ExpensesWindow.updateButtons(expenses);;
				addExpense.dispatchEvent(new WindowEvent(addExpense, WindowEvent.WINDOW_CLOSING));
			}
		});
		panelAddExpense.add(btnOkay);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(239, 70, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addExpense.dispatchEvent(new WindowEvent(addExpense, WindowEvent.WINDOW_CLOSING));
			}
		});
		panelAddExpense.add(btnCancel);
		panelAddExpense.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldFirstName, textFieldLastName, btnOkay, btnCancel, lblDescription, lblAmount}));
		addExpense.setResizable(false);
		addExpense.setSize(346, 128);
		Rectangle screenSize = addExpense.getGraphicsConfiguration().getBounds();
		addExpense.setLocation(screenSize.width / 2 - addExpense.getWidth() / 2, screenSize.height / 2 - addExpense.getHeight() / 2);
		addExpense.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldFirstName, textFieldLastName, btnOkay, btnCancel}));
		addExpense.setVisible(true);
		
	}
}
