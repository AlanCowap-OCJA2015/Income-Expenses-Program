package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Rectangle;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExpensesWindow {
	
	static JLabel lblExpenseAmount;
	static JButton btnAdd;
	static JButton btnEdit;
	static JButton btnRemove;
	static JList list;
	static JScrollPane scroller;
	static JPanel panelEditExpenses;
	static JFrame editExpenses;
	static JTextField textFieldCurrentlySelectedExpense;


	public ExpensesWindow(final ArrayList<Expense> expenses, String forWhom){
		editExpenses = new JFrame("Expenses Editor for " + forWhom);
		editExpenses.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		panelEditExpenses = new JPanel();
		panelEditExpenses.setOpaque(true);
		panelEditExpenses.setLayout(null);
		editExpenses.getContentPane().add(BorderLayout.CENTER, panelEditExpenses);
		
		btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new AddExpense (expenses);
			}
		});
		btnAdd.setBounds(295, 10, 89, 23);
		panelEditExpenses.add(btnAdd);
		
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new RemoveExpense (expenses, list.getSelectedIndex());
			}
		});
		btnRemove.setBounds(295, 45, 89, 23);
		btnRemove.setEnabled(false);
		panelEditExpenses.add(btnRemove);
		
		btnEdit = new JButton("Edit");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEdit.setBounds(295, 79, 89, 23);
		btnEdit.setEnabled(false);
		panelEditExpenses.add(btnEdit);
		
		// Create the JScrollPane for the expenses list and add the listbox to it
		scroller = new JScrollPane();
		scroller.setBounds(10, 11, 275, 235);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelEditExpenses.add(scroller);
		// Add the listbox to the JScrollPane
		list = new JList();
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				updateButtons (expenses);
				if (list.getSelectedIndex() >= 0) {
					textFieldCurrentlySelectedExpense.setText(String.format("%.2f", expenses.get(list.getSelectedIndex()).amount));
				} else {
					textFieldCurrentlySelectedExpense.setText("");
				}
			}
		});
		scroller.setViewportView(list);
		
		lblExpenseAmount = new JLabel("Selected expense");
		lblExpenseAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblExpenseAmount.setBounds(10, 260, 179, 14);
		panelEditExpenses.add(lblExpenseAmount);

		textFieldCurrentlySelectedExpense = new JTextField();
		textFieldCurrentlySelectedExpense.setBounds(199, 257, 86, 20);
		textFieldCurrentlySelectedExpense.setEditable(false);
		textFieldCurrentlySelectedExpense.setHorizontalAlignment(SwingConstants.RIGHT);
		panelEditExpenses.add(textFieldCurrentlySelectedExpense);
		textFieldCurrentlySelectedExpense.setColumns(10);

		
		panelEditExpenses.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnAdd, btnRemove, btnEdit, list}));
		editExpenses.setSize(400, 315);
		Rectangle screenSize = editExpenses.getGraphicsConfiguration().getBounds();
		editExpenses.setLocation(screenSize.width / 2 - editExpenses.getWidth() / 2, screenSize.height / 2 - editExpenses.getHeight() / 2);
		editExpenses.setResizable(false);
		updateList(expenses);
		editExpenses.setVisible(true);
		
	}
		
	// Update the contents of the listbox with the current family member list
	public static void updateList (ArrayList<Expense> expenses) {
		String[] listData = new String[expenses.size()];
		for (int e = 0; e < expenses.size(); ++e) { 
			listData[e] = expenses.get(e).description;  
		}
		list.setListData(listData);
		list.revalidate();
		list.repaint();
	}

	public static void updateButtons (ArrayList<Expense> expenses) {
		if (expenses.size() == 0) {
			btnRemove.setEnabled(false);
			btnEdit.setEnabled(false);
		} else {
			btnRemove.setEnabled(true);
			btnEdit.setEnabled(true);
		}
	}
	
}
