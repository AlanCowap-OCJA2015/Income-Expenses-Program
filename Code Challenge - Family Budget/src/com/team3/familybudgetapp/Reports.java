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
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class Reports {
	
	public static String generateReportText(Family family){
		String textToDisplay = null;
		double totalNetIncome = 0.0;
		double totalExpense = 0.0;
		textToDisplay = (family.getDescription() + "\r\n");
		for (Expense expense: family.expenses){
			textToDisplay += (expense.getDescription() + "\t" + expense.getAmount() + "\r\n");
			totalExpense += expense.getAmount();
		}
		for (Person person: family.members){
			totalNetIncome += person.getNetIncome(); 
			textToDisplay += (person.toString() + "\r\n");
			if (person.isEarner()){
				textToDisplay += ("\t\tGross Income: " + person.getIncome() + "\r\n\t\tNet Income: " + person.getNetIncome() + "\r\n");
			}
			for (Expense expense: person.expenses){
				textToDisplay += (expense.getDescription() + "\t" + expense.getAmount() + "\r\n");
				totalExpense += expense.getAmount();
			}
		}
		textToDisplay += ("\t\tTotal Net income:\t " + totalNetIncome + "\r\n");
		textToDisplay += ("\t\tTotal expenses:\t\t " + totalExpense + "\r\n");
		textToDisplay += ("\r\nNet Disposable income: \t\t\t" + (totalNetIncome - totalExpense));
		
		return textToDisplay;
	}
	public static void saveDataFile(Family family, File fileName){
//		File fileName = new File(family.getDescription() + ".dat");
		PrintWriter outfile = null;
		try {
			outfile = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
			outfile.println("£" + family.getDescription());
			for (Expense expense : family.expenses){
				outfile.println("$" + expense.getDescription() + "\t" + expense.getAmount());
			}
			for (Person person : family.getMembers()){
				outfile.println("%" + person.getFirstName() + "\t" + person.getLastName() + "\t" + 
						(person.isEarner()? "1" : "0") + "\t" + person.getIncome() + "\t" + person.getTaxBracket());
				for (Expense expense : person.expenses){
					outfile.println("$" + expense.getDescription() + "\t" + expense.getAmount());
				}
			}
			
		} catch (Exception e) {
		} finally {
			outfile.close();
		}
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
		BtnSaveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				File file = new File(family.getDescription() + ".dat");
				Reports.saveDataFile(family, file);
			}
		});
		
		BtnSaveData.setBounds(283, 254, 141, 23);
		panelAddPerson.add(BtnSaveData);
		reports.setVisible(true);
		reports.setResizable(false);
		reports.setSize(560, 313);
		
	}
}
