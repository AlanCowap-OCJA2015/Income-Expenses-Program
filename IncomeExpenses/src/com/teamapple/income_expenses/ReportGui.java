package com.teamapple.income_expenses;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class ReportGui extends JFrame {

	private JPanel contentPane;
	private final JScrollPane scrollPane;
	private JLabel lblReport;
	private JButton btnSaveReport;
	private JComboBox<String> timeFrame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ReportGui frame = new ReportGui();
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
	public ReportGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 520, 605);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		final JTextPane textPane = new JTextPane();
		textPane.setBackground(Color.LIGHT_GRAY);
		textPane.setBounds(10, 11, 402, 240);
		textPane.setEditable(false);
		String line = "-------------------------------------------------"
				+ "-----------------------------------------------------------------------------------\n";
		textPane.setText("Total Income: " + Family.calculateTotalIncome() + "\n" + line);
		
		System.out.println(Family.family.size());
		
		for(Expense e:Family.expenses){
			textPane.setText(textPane.getText() + e.getName() + " - " + e.getAmount() + " = " + Family.calculateSpareMoney("YEARLY", e) + "\n");
		}
		
		
		scrollPane = new JScrollPane(textPane);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 110, 484, 404);
		contentPane.add(scrollPane);
		
		lblReport = new JLabel("Report");
		lblReport.setFont(new Font("Tunga", Font.PLAIN, 38));
		lblReport.setBounds(183, 28, 201, 58);
		contentPane.add(lblReport);
		
		btnSaveReport = new JButton("Save Report");
		btnSaveReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				

				JFileChooser saver = new JFileChooser();
				saver.showSaveDialog(null);
				String strFile = saver.getSelectedFile().toString();
				if(!strFile.endsWith(".txt")){
					strFile += ".txt";
				}
				
				File file = new File(strFile);
				String separator = System.getProperty("line.separator");
				String text = textPane.getText();
				text = text.replaceAll("\n", separator);
				try {
					
					PrintWriter out = new PrintWriter(file);
					out.println(text);
					out.close();
					
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		btnSaveReport.setBounds(193, 525, 109, 23);
		contentPane.add(btnSaveReport);
		
		timeFrame = new JComboBox<String>();
		timeFrame.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				
				String line = "-------------------------------------------------"
						+ "-----------------------------------------------------------------------------------\n";
				
				String frame = timeFrame.getSelectedItem().toString();
				frame = frame.toUpperCase();
				String income = "€" + Family.calculateTotalIncome(frame);
				if(income.length() >= income.indexOf('.') + 3){
					income = income.substring(0, income.indexOf('.') + 3);
				}
				textPane.setText("Total Income: " + income + "\n" + line);
				textPane.setText(textPane.getText() + "Expense:\t\tAmount Deducted:\tBalance:\n");
				
				for(Expense e:Family.expenses){
					String s = "";
					
					for(int i = 0; i < 30 - e.getName().length(); i++){
						s += ' ';
					}
							
					String formattedAmount = "" + e.getAmount(frame);
					if(formattedAmount.length() >= formattedAmount.indexOf('.') + 3){
						formattedAmount = formattedAmount.substring(0, formattedAmount.indexOf('.') + 3);
					}
					String formattedSpare = "" + Family.calculateSpareMoney(frame, e);
					if(formattedSpare.length() >= formattedSpare.indexOf('.') + 3){
						formattedSpare = formattedSpare.substring(0, formattedSpare.indexOf('.') + 3);
					}
					textPane.setText(textPane.getText() + e.getName() + s +" \t-€" + formattedAmount + "   \t\t€" + formattedSpare + "\n");
				
				
				}
				
				
			}
		});
		timeFrame.addItem("Weekly");
		timeFrame.addItem("Monthly");
		timeFrame.addItem("Yearly");
		timeFrame.setBounds(10, 526, 79, 20);
		contentPane.add(timeFrame);
		
	}
}
