package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;
import javax.swing.JList;
import javax.swing.JButton;

public class Expenses {
	private JTextField textFieldCurrentlySelectedExpense;
	public Expenses(){
		JFrame editSalary = new JFrame("Expenses");
		editSalary.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel panelEditSalary = new JPanel();
		panelEditSalary.setLayout(null);
		panelEditSalary.setOpaque(true);
		JTextArea textArea = new JTextArea(15, 50);
		textArea.setWrapStyleWord(true);
		textArea.setEditable(false);
		textArea.setFont(Font.getFont(Font.SANS_SERIF));
		JScrollPane scroller = new JScrollPane(textArea);
		scroller.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scroller.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		JPanel inputpanel = new JPanel();
		inputpanel.setLayout(new FlowLayout());
		DefaultCaret caret = (DefaultCaret) textArea.getCaret();
		caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
		panelEditSalary.add(scroller);
		panelEditSalary.add(inputpanel);
		editSalary.getContentPane().add(BorderLayout.CENTER, panelEditSalary);
		
		JList listExpenses = new JList();
		listExpenses.setBounds(21, 11, 195, 265);
		panelEditSalary.add(listExpenses);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.setBounds(226, 11, 89, 23);
		panelEditSalary.add(btnAdd);
		
		JButton btnRemove = new JButton("Remove");
		btnRemove.setBounds(226, 45, 89, 23);
		panelEditSalary.add(btnRemove);
		
		JButton btnEdit = new JButton("Edit");
		btnEdit.setBounds(226, 79, 89, 23);
		panelEditSalary.add(btnEdit);
		
		textFieldCurrentlySelectedExpense = new JTextField();
		textFieldCurrentlySelectedExpense.setEditable(false);
		textFieldCurrentlySelectedExpense.setHorizontalAlignment(SwingConstants.RIGHT);
		textFieldCurrentlySelectedExpense.setBounds(226, 149, 86, 20);
		panelEditSalary.add(textFieldCurrentlySelectedExpense);
		textFieldCurrentlySelectedExpense.setColumns(10);
		editSalary.setVisible(true);
		editSalary.setResizable(false);
		editSalary.setSize(485, 315);
		
		
		
		
		
		
		
	
	}
	}
