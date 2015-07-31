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
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class RemoveExpense {
	
	/**
	 * TODO Change references to ExpensesWindow to parentWindow which will be passed in
	 *  
	 */
	public RemoveExpense(final ArrayList<Expense> expenses, final int expenseIndex){
		final JFrame removeExpense = new JFrame("Remove Expense");
		Rectangle screenSize = removeExpense.getGraphicsConfiguration().getBounds();
		removeExpense.setResizable(false);
		removeExpense.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel panelRemoveExpense = new JPanel();
		panelRemoveExpense.setLayout(null);
		panelRemoveExpense.setOpaque(true);
		removeExpense.getContentPane().add(BorderLayout.CENTER, panelRemoveExpense);
		
		JLabel lblAreYouSure = new JLabel("Are you sure you want to delete this expense?");
		lblAreYouSure.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreYouSure.setBounds(10, 11, 257, 54);
		panelRemoveExpense.add(lblAreYouSure);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				expenses.remove(expenseIndex);
				ExpensesWindow.updateList(expenses);
				ExpensesWindow.updateButtons(expenses);
				removeExpense.dispatchEvent(new WindowEvent(removeExpense, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnOkay.setBounds(10, 96, 89, 23);
		panelRemoveExpense.add(btnOkay);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removeExpense.dispatchEvent(new WindowEvent(removeExpense, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnCancel.setBounds(178, 96, 89, 23);
		panelRemoveExpense.add(btnCancel);
		removeExpense.setBounds(300, 200, 284, 156);
		removeExpense.setLocation(screenSize.width / 2 - removeExpense.getWidth() / 2, screenSize.height / 2 - removeExpense.getHeight() / 2);
		removeExpense.setVisible(true);
		removeExpense.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnOkay, btnCancel}));
		
	}
}
