package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;
import javax.swing.JComboBox;
import javax.swing.SwingConstants;

public class EditSalary {
	private JTextField textFieldEditSalaryAmount;
	private JTextField textFieldHoursPerWeek;
		public EditSalary(){
			JFrame editPerson = new JFrame("Edit Salary");
			editPerson.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			try 
			{
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				e.printStackTrace();
			}
			JPanel panelEditPerson = new JPanel();
			panelEditPerson.setLayout(null);
			panelEditPerson.setOpaque(true);
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
			panelEditPerson.add(scroller);
			panelEditPerson.add(inputpanel);
			editPerson.getContentPane().add(BorderLayout.CENTER, panelEditPerson);
			
			JLabel lblSchedule = new JLabel("Schedule");
			lblSchedule.setBounds(10, 33, 46, 14);
			panelEditPerson.add(lblSchedule);
			
			JComboBox comboBoxSchedule = new JComboBox();
			comboBoxSchedule.setEditable(true);
			comboBoxSchedule.setBounds(141, 30, 123, 20);
			panelEditPerson.add(comboBoxSchedule);
			editPerson.setVisible(true);
			editPerson.setResizable(false);
			editPerson.setSize(428, 246);
			
			comboBoxSchedule.addItem("");
			comboBoxSchedule.addItem("Yearly");
			comboBoxSchedule.addItem("Hourly");
			
			JLabel lblAmount = new JLabel("Amount");
			lblAmount.setBounds(10, 90, 46, 14);
			panelEditPerson.add(lblAmount);
			
			textFieldEditSalaryAmount = new JTextField();
			textFieldEditSalaryAmount.setHorizontalAlignment(SwingConstants.RIGHT);
			textFieldEditSalaryAmount.setBounds(141, 87, 86, 20);
			panelEditPerson.add(textFieldEditSalaryAmount);
			textFieldEditSalaryAmount.setColumns(10);
			
			JLabel lblHoursWorkedPer = new JLabel("Hours worked per week");
			lblHoursWorkedPer.setBounds(10, 144, 132, 14);
			panelEditPerson.add(lblHoursWorkedPer);
			
			textFieldHoursPerWeek = new JTextField();
			textFieldHoursPerWeek.setBounds(141, 141, 58, 20);
			panelEditPerson.add(textFieldHoursPerWeek);
			textFieldHoursPerWeek.setColumns(10);
			
		}
}
