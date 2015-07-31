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

public class EditExpense {
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	
	/**
	 * TODO Merge addExpense with this
	 * TODO Change references to ExpensesWindow to parentWindow which will be passed in 
	 */
	public EditExpense(final ArrayList<Expense> expenses){
		JList list = ExpensesWindow.list;
		final Expense expense = expenses.get(list.getSelectedIndex());
		final JFrame editExpense = new JFrame("Edit Expense");
		editExpense.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel panelEditExpense = new JPanel();
		panelEditExpense.setLayout(null);
		panelEditExpense.setOpaque(true);
		editExpense.getContentPane().add(BorderLayout.CENTER, panelEditExpense);
		
		JLabel lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(10, 14, 65, 14);
		panelEditExpense.add(lblDescription);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(85, 11, 243, 20);
		panelEditExpense.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		textFieldFirstName.grabFocus();
		
		JLabel lblAmount = new JLabel("Amount");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(10, 45, 67, 14);
		panelEditExpense.add(lblAmount);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(85, 42, 105, 20);
		panelEditExpense.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.setBounds(10, 70, 89, 23);
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a new Person object and add it to the ArrayList
				if (textFieldLastName.getText().length() > 0) {
					expense.amount = Double.parseDouble(textFieldLastName.getText());
				} else {
					expense.amount = 0.0;
				}
				
				// Update the list in the main window
				ExpensesWindow.updateList(expenses);
				ExpensesWindow.updateButtons(expenses);;
				editExpense.dispatchEvent(new WindowEvent(editExpense, WindowEvent.WINDOW_CLOSING));
			}
		});
		panelEditExpense.add(btnOkay);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(239, 70, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editExpense.dispatchEvent(new WindowEvent(editExpense, WindowEvent.WINDOW_CLOSING));
			}
		});
		panelEditExpense.add(btnCancel);
		panelEditExpense.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldFirstName, textFieldLastName, btnOkay, btnCancel, lblDescription, lblAmount}));
		editExpense.setResizable(false);
		editExpense.setSize(346, 128);
		Rectangle screenSize = editExpense.getGraphicsConfiguration().getBounds();
		editExpense.setLocation(screenSize.width / 2 - editExpense.getWidth() / 2, screenSize.height / 2 - editExpense.getHeight() / 2);
		editExpense.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldFirstName, textFieldLastName, btnOkay, btnCancel}));
		editExpense.setVisible(true);
		
	}
}
