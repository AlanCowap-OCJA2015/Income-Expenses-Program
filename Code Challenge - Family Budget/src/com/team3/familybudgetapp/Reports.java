package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.JTextArea;

public class Reports {
	
	private Person thisPerson = new Person();
	public static String generateReportText(Family family){
		String textToDisplay = null;
		double totalNetIncome = 0.0;
		double totalExpense = 0.0;
		textToDisplay = (family.getDescription() + "\r\n");
		for (Expense expense: family.expenses){
			textToDisplay += (expense.getDescription() + "\t\t\t" + expense.getAmount() + "\r\n");
			totalExpense += expense.getAmount();
		}
		for (Person person: family.members){
			totalNetIncome += person.getNetIncome(); 
			textToDisplay += (person.toString() + "\r\n\t\tGross Income: " + person.getIncome() + "\r\n\t\tNet Income: " + person.getNetIncome());
			for (Expense expense: person.expenses){
				textToDisplay += (expense.getDescription() + "\t\t\t" + expense.getAmount() + "\r\n");
				totalExpense += expense.getAmount();
			}
		}
		textToDisplay += ("\t\tTotal Net income:\t " + totalNetIncome + "\r\n");
		textToDisplay += ("\t\tTotal expenses:\t " + totalExpense + "\r\n");
		textToDisplay += ("\nNet Disposable income: \t\t" + (totalNetIncome - totalExpense));
		
		return textToDisplay;
	}
	
	public Reports(final Family family){
		final JFrame reports = new JFrame("Reports");
		final String reportText = Reports.generateReportText(family);
		reports.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel panelAddPerson = new JPanel();
		panelAddPerson.setLayout(null);
		panelAddPerson.setOpaque(true);
		JScrollPane scroller = new JScrollPane();
		scroller.setLocation(16, 11);
		scroller.setSize(528, 232);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
//		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		panelAddPerson.add(scroller);
		
		JTextArea textReport = new JTextArea();
		scroller.setViewportView(textReport);
		textReport.setText(reportText);
		panelAddPerson.add(inputpanel);
		reports.getContentPane().add(BorderLayout.CENTER, panelAddPerson);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				reports.dispatchEvent(new WindowEvent(reports, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnOkay.setBounds(16, 254, 89, 23);
		panelAddPerson.add(btnOkay);
		
		JButton btnSaveText = new JButton("Save to text File");
		btnSaveText.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File fileName = new File(family.getDescription() + ".txt");
				PrintWriter outfile = null;
				try {
					outfile = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
					outfile.print(reportText);
				} catch (Exception e1) {
				} finally {
					outfile.close();
				}
//				reports.dispatchEvent(new WindowEvent(reports, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnSaveText.setBounds(129, 254, 123, 23);
		panelAddPerson.add(btnSaveText);
		
		JButton BtnSaveData = new JButton("Export to data file");
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String textToDisplay = null;
				double totalNetIncome = 0.0;
				double totalExpense = 0.0;
				textToDisplay = (family.getDescription() + "\n");
				for (Expense expense: family.expenses){
					textToDisplay += (expense.getDescription() + "\t\t\t" + expense.getAmount() + "\n");
					totalExpense += expense.getAmount();
				}
				for (Person person: family.members){
					totalNetIncome += person.getNetIncome(); 
					textToDisplay += (person.toString() + "\n\t\tGross Income: " + person.getIncome() + "\n\t\tNet Income: " + person.getNetIncome());
					for (Expense expense: person.expenses){
						textToDisplay += (expense.getDescription() + "\t\t\t" + expense.getAmount() + "\n");
						totalExpense += expense.getAmount();
					}
				}
				textToDisplay += ("\t\tTotal Net income:\t " + totalNetIncome + "\n");
				textToDisplay += ("\t\tTotal expenses:\t " + totalExpense + "\n");
				textToDisplay += ("\nNet Disposable income: \t\t" + (totalNetIncome - totalExpense));
			}
		});
		BtnSaveData.setBounds(283, 254, 141, 23);
		panelAddPerson.add(BtnSaveData);
		reports.setVisible(true);
		reports.setResizable(false);
		reports.setSize(560, 313);
		
	}
}
