package com.teamapple.income_expenses;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ExpensesGui extends JFrame {

	private JPanel contentPane;
	private JTextField expenseTextField;
	private JTextField costTextField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ExpensesGui frame = new ExpensesGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ExpensesGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblExpense = new JLabel("Expense");
		lblExpense.setBounds(10, 33, 97, 14);
		contentPane.add(lblExpense);
		
		JLabel lblCost = new JLabel("Cost");
		lblCost.setBounds(188, 33, 46, 14);
		contentPane.add(lblCost);
		

		final JComboBox<String> paymentTime = new JComboBox<String>();
		paymentTime.addItem("Weekly");
		paymentTime.addItem("Monthly");
		paymentTime.setBounds(294, 87, 79, 20);
		contentPane.add(paymentTime);
		paymentTime.setVisible(false);
		
		final JCheckBox reoccurring = new JCheckBox("Reoccurring");
		reoccurring.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				if(reoccurring.isSelected()){
					paymentTime.setVisible(true);
				}else{
					paymentTime.setVisible(false);
				}
			}
		});
		reoccurring.setBounds(294, 57, 97, 23);
		contentPane.add(reoccurring);
		
		
		expenseTextField = new JTextField();
		expenseTextField.setBounds(10, 58, 169, 20);
		contentPane.add(expenseTextField);
		expenseTextField.setColumns(10);
		
		costTextField = new JTextField();
		costTextField.setBounds(189, 58, 86, 20);
		contentPane.add(costTextField);
		costTextField.setColumns(10);
		
		JButton submitExpense = new JButton("Add Expense");
		submitExpense.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String expense = expenseTextField.getText();
				String cost = costTextField.getText();
				boolean reoccur = reoccurring.isSelected();
				String time = paymentTime.getSelectedItem().toString();
				boolean isWeekly = time.equals("Weekly");
				double amount = Double.parseDouble(cost);
				
				Family.expenses.add(new Expense(expense,amount,reoccur, isWeekly));
				
			}
		});
		submitExpense.setBounds(10, 126, 132, 23);
		contentPane.add(submitExpense);
		
		JButton gotoReport = new JButton("Proceed to report");
		gotoReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				setVisible(false);
				IncomeExpenses.runReport();
			}
		});
		gotoReport.setBounds(152, 126, 132, 23);
		contentPane.add(gotoReport);
		
	}

}
