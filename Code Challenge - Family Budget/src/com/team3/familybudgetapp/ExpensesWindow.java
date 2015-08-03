package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.ListSelectionModel;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class ExpensesWindow {
	
	static JDialog editExpenses;
	private JPanel panelEditExpenses;
	private JButton btnAdd;
	private JButton btnRemove;
	private JScrollPane scroller;
	JTable table;
	private JButton btnOK;
	private JButton btnEdit;


	@SuppressWarnings("serial")
	public ExpensesWindow(final ArrayList<Expense> expenses, String forWhom){
		editExpenses = new JDialog(FamilyBudgetApp.familyBudgetAppWindow, "Expenses Editor for " + forWhom);
		editExpenses.setModalityType(ModalityType.APPLICATION_MODAL);
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

		// Add an Enter key listener so the window can be dismissed
		// with the keyboard
		ActionListener enterListener = new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) { btnOK.doClick (); }
		};
		editExpenses.getRootPane ().registerKeyboardAction (enterListener, KeyStroke.getKeyStroke (KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		// Add an expense
		btnAdd = new JButton("Add...");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Expense newExpense = new Expense ();
				new EditExpense (newExpense);
				table.add(new JButton("Test"));
				
				expenses.add(newExpense);
				
				// Update the list in the main window
				updateTable(expenses);
				updateButtons(expenses);
			}
		});
		btnAdd.setBounds(295, 10, 89, 23);
		panelEditExpenses.add(btnAdd);
		
		// Remove an expense
		btnRemove = new JButton("Remove");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (JOptionPane.showConfirmDialog (editExpenses, 
						"Deleting an expense cannot be undone\n"
						+ "Do you want to proceed?", 
						"Remove expense from list", 
						JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE) == 0) {

					expenses.remove(table.getSelectedRow());
				}
				updateTable(expenses);
				updateButtons(expenses);
			}
		});
		btnRemove.setBounds(295, 45, 89, 23);
		btnRemove.setEnabled(false);
		panelEditExpenses.add(btnRemove);
		
		// Edit an expense
		btnEdit = new JButton("Edit...");
		btnEdit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditExpense(expenses.get (table.getSelectedRow()));
					
				// Update the list in the main window
				updateTable(expenses);
				updateButtons(expenses);
			}
		});
		btnEdit.setBounds(293, 79, 91, 23);
		btnEdit.setEnabled(false);
		panelEditExpenses.add(btnEdit);

		
		// Expenses list
		// Create the JScrollPane for the expenses list and add the table to it
		scroller = new JScrollPane();
		scroller.setBounds(10, 11, 275, 235);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		panelEditExpenses.add(scroller);
		
		table = new JTable();
		scroller.setViewportView(table);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		table.setShowHorizontalLines(true);
		table.setShowVerticalLines(false);
		table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				updateButtons(expenses);
			}
		});
			
		table.setModel(new DefaultTableModel(
			new Object[][] { {"Test", new Double(0.0), "Once-Off"},},
			new String[] {"Description", "Amount", "Recurring"}
		) {
			Class[] columnTypes = new Class[] { String.class, Double.class, String.class };
			public Class getColumnClass(int columnIndex) {
				return columnTypes[columnIndex];
			}
		});
		table.getColumnModel().getColumn(0).setResizable(false);
		table.getColumnModel().getColumn(0).setPreferredWidth(95);
		table.getColumnModel().getColumn(1).setResizable(false);
		table.getColumnModel().getColumn(1).setPreferredWidth(58);
		table.getColumnModel().getColumn(2).setResizable(false);
		table.getColumnModel().getColumn(2).setPreferredWidth(64);
		table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
//			public DoubleRenderer() { super(); }
			public void setValue (Object value) {
				setText(value == null ? "" : String.format("%.2f", value));
			}
		});

		// The OK button
		btnOK = new JButton("OK");
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editExpenses.dispatchEvent(new WindowEvent(editExpenses, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnOK.setBounds(10, 257, 91, 23);
		panelEditExpenses.add(btnOK);
		
		panelEditExpenses.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{btnAdd, btnRemove}));
		editExpenses.setSize(400, 315);
		Rectangle screenSize = editExpenses.getGraphicsConfiguration().getBounds();
		editExpenses.setLocation(screenSize.width / 2 - editExpenses.getWidth() / 2, screenSize.height / 2 - editExpenses.getHeight() / 2);
		editExpenses.setResizable(false);
		updateTable(expenses);
		editExpenses.setVisible(true);

		
	}
		
	// Update the contents of the table with the current family member list
	@SuppressWarnings("serial")
	public void updateTable (ArrayList<Expense> expenses) {
		Object[][] tableData = new Object[expenses.size()][3];
		for (int e = 0; e < expenses.size(); ++e) { 
			tableData[e][0] = expenses.get(e).getDescription();  
//			tableData[e][1] = String.format("%.2f", expenses.get(e).getAmount());  
			tableData[e][1] = expenses.get(e).getAmount();  
			tableData[e][2] = expenses.get(e).getRecursString();  
		}

		table.setModel(new DefaultTableModel(
				tableData,
				new String[] { "Description", "Amount", "Recurring" }
			) {
				Class[] columnTypes = new Class[] {
					String.class, Double.class, String.class
				};
				public Class getColumnClass(int columnIndex) {
					return columnTypes[columnIndex];
				}
			});
		// Create a new cell renderer for the middle column
		table.getColumnModel().getColumn(1).setCellRenderer(new DefaultTableCellRenderer() {
			public void setValue (Object value) {
				setText(value == null ? "" : String.format("%.2f", value));
			}
		});

	
	}

	public void updateButtons (ArrayList<Expense> expenses) {
		if ((expenses.size() > 0) && (table.getSelectedRow() >= 0)) {
			btnRemove.setEnabled(true);
			btnEdit.setEnabled(true);
		} else {
			btnRemove.setEnabled(false);
			btnEdit.setEnabled(false);
		}
	}
}
