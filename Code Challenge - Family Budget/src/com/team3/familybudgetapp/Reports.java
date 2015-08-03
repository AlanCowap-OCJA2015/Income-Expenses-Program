package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class Reports {
	
	public static void loadFamily(Family family, File file){
		Scanner scan = null;
		try {
			scan = new Scanner(file);
			int familyMemberCount = -1;
			while (scan.hasNextLine()){
//				System.out.println("Debug text: in while - scan loop");
				char[] tempCharArray = scan.nextLine().toCharArray();
//				System.out.println("Debug text: current line is: " + String.valueOf(tempCharArray));
				if (tempCharArray[0] == '£'){ //this is the family description
					family.setDescription(String.copyValueOf(tempCharArray, 1, (tempCharArray.length-1)));
//					System.out.println("Debug text: Family name is " + family.getDescription());
				} else if (tempCharArray[0] == '$'){ //this an expense
					StringBuilder expenseNameSB = new StringBuilder(); 
					StringBuilder expenseAmountSB = new StringBuilder(); 
					StringBuilder currentSB = expenseNameSB;
					for (int i=1; i<tempCharArray.length; ++i){
						if (tempCharArray[i] != '\t'){
							currentSB.append(tempCharArray[i]);
						} else {
							currentSB = expenseAmountSB;
						}
					}
					if (familyMemberCount == -1){
						family.expenses.add(new Expense(expenseNameSB.toString(), Double.parseDouble(expenseAmountSB.toString())));
					} else {
						family.members.get(familyMemberCount).expenses.add(new Expense(expenseNameSB.toString(), 
																	Double.parseDouble(expenseAmountSB.toString())));
					}
//					System.out.println("Debug text: expense name is " + family.expenses.get(0).getDescription());
//					System.out.println("Debug text: expense amount is " + family.expenses.get(0).getAmount());
				} else if (tempCharArray[0] == '%'){ //this a person
					++familyMemberCount;
					StringBuilder firstNameSB = new StringBuilder();
					StringBuilder lastNameSB = new StringBuilder();
					Boolean earner = false;
					StringBuilder incomeSB = new StringBuilder();
					StringBuilder taxSB = new StringBuilder();
					StringBuilder currentSB = firstNameSB;
					int wordCount = 0;
					for (int i=1; i<tempCharArray.length; ++i){
						if (tempCharArray[i] != '\t'){
							currentSB.append(tempCharArray[i]);
						} else {
							switch (++wordCount) {
							case 1: currentSB = lastNameSB; break;
							case 2: if (tempCharArray[++i] == '1') earner = true; break;
							case 3: currentSB = incomeSB; break;
							case 4: currentSB = taxSB; break;
							default: System.out.println("debug text: we should not ever hit this default"); break;
							}
						}
					}
					if (earner){
						family.members.add(new Person(firstNameSB.toString(), lastNameSB.toString(), true, 
								Double.parseDouble(incomeSB.toString()), Double.parseDouble(taxSB.toString())));
					} else {
						family.members.add(new Person(firstNameSB.toString(), lastNameSB.toString()));
					}
					
				}
				
			}
		} catch (FileNotFoundException e) {
			System.out.println("Debug text: hit the exception on try-catch block for file IO");
		} finally {
			scan.close();
//			System.out.println("Debug text: in the finally of file IO try-catch");
		}
//		System.out.println("Debug text: About to return family");
	}
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
	public static void saveTextFile(String textToSave, File file){
		PrintWriter outfile = null;
		try {
			outfile = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			outfile.print(textToSave);
		} catch (Exception e1) {
		} finally {
			outfile.close();
		}
	}
	public static void saveDataFile(Family family, File file){
		PrintWriter outfile = null;
		try {
			outfile = new PrintWriter(new BufferedWriter(new FileWriter(file)));
			outfile.println("£" + family.getDescription());
			for (Expense expense : family.expenses){
				outfile.println("$" + expense.getDescription() + "\t" + expense.getAmount());
			}
			for (Person person : family.getMembers()){
				outfile.println("%" + person.getFirstName() + "\t" + person.getLastName() + "\t" + 
						(person.isEarner()? "1" : "0") + "\t" + person.getIncome() + "\t" + person.getTaxCutOff());
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
				JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
				int rc = jfc.showDialog(null, "Save Report.");
				if (rc == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					Reports.saveTextFile(reportText, file);
				} else {
				}
				return; 
			}
		});
		btnSaveText.setBounds(129, 254, 123, 23);
		panelAddPerson.add(btnSaveText);
		
		JButton BtnSaveData = new JButton("Export to data file");
		BtnSaveData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(System.getProperty("user.dir"));
				int rc = jfc.showDialog(null, "Save");
				if (rc == JFileChooser.APPROVE_OPTION) {
					File file = jfc.getSelectedFile();
					Reports.saveDataFile(family, file);
				} else {
				}
				return; 
			}
		});
		
		BtnSaveData.setBounds(283, 254, 141, 23);
		panelAddPerson.add(BtnSaveData);
		reports.setVisible(true);
		reports.setResizable(false);
		reports.setSize(560, 313);
		
	}
}