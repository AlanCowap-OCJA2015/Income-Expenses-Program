package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;
import javax.swing.text.DefaultCaret;

public class EditPerson {
	private JTextField textFieldEditPersonFirstName;
	private JTextField textFieldEditPersonLastName;
	private JTextField textFieldtextFieldEditPersonDay;
	private JTextField textFieldEditPersonMonth;
	private JTextField textFieldEditPersonYear;
	private JTextField textFieldEditPersonGender;
	public EditPerson(final JList list, final ArrayList<Person> members, final int personIndex){
		JFrame editPerson = new JFrame("Edit Person");
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
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 11, 77, 14);
		panelEditPerson.add(lblFirstName);
		
		textFieldEditPersonFirstName = new JTextField();
		textFieldEditPersonFirstName.setBounds(97, 8, 161, 20);
		panelEditPerson.add(textFieldEditPersonFirstName);
		textFieldEditPersonFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 53, 77, 14);
		panelEditPerson.add(lblLastName);
		
		textFieldEditPersonLastName = new JTextField();
		textFieldEditPersonLastName.setBounds(97, 50, 161, 20);
		panelEditPerson.add(textFieldEditPersonLastName);
		textFieldEditPersonLastName.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(10, 95, 77, 14);
		panelEditPerson.add(lblDateOfBirth);
		
		textFieldtextFieldEditPersonDay = new JTextField();
		textFieldtextFieldEditPersonDay.setBounds(97, 92, 33, 20);
		panelEditPerson.add(textFieldtextFieldEditPersonDay);
		textFieldtextFieldEditPersonDay.setColumns(10);
		
		textFieldEditPersonMonth = new JTextField();
		textFieldEditPersonMonth.setBounds(140, 92, 33, 20);
		panelEditPerson.add(textFieldEditPersonMonth);
		textFieldEditPersonMonth.setColumns(10);
		
		textFieldEditPersonYear = new JTextField();
		textFieldEditPersonYear.setBounds(183, 92, 33, 20);
		panelEditPerson.add(textFieldEditPersonYear);
		textFieldEditPersonYear.setColumns(10);
		
		JLabel lblDd = new JLabel("DD");
		lblDd.setBounds(97, 77, 33, 14);
		panelEditPerson.add(lblDd);
		
		JLabel lblMm = new JLabel("MM");
		lblMm.setBounds(140, 77, 33, 14);
		panelEditPerson.add(lblMm);
		
		JLabel lblYyyy = new JLabel("YYYY");
		lblYyyy.setBounds(183, 77, 46, 14);
		panelEditPerson.add(lblYyyy);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(10, 135, 46, 14);
		panelEditPerson.add(lblGender);
		
		textFieldEditPersonGender = new JTextField();
		textFieldEditPersonGender.setBounds(97, 132, 86, 20);
		panelEditPerson.add(textFieldEditPersonGender);
		textFieldEditPersonGender.setColumns(10);
		
		JLabel lblSalary = new JLabel("Salary");
		lblSalary.setBounds(10, 176, 46, 14);
		panelEditPerson.add(lblSalary);
		
		JButton btnEditSalary = new JButton("Edit");
		btnEditSalary.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditSalary();
			}
		});
		btnEditSalary.setBounds(97, 172, 89, 23);
		panelEditPerson.add(btnEditSalary);
		
		JButton btnExpenses = new JButton("Expenses");
		btnExpenses.setBounds(10, 234, 89, 23);
		panelEditPerson.add(btnExpenses);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Person newPerson = new Person();
				newPerson.firstName = textFieldEditPersonFirstName.getText();
				newPerson.lastName = textFieldEditPersonLastName.getText();
				members.add(newPerson);
				Vector<String> listData = new Vector();
				for (Person p : members) {
					listData.add(p.firstName + " " + p.lastName);
				}
				list.setListData(listData);
				list.revalidate();
				list.repaint();
			}
		});
		btnOkay.setBounds(109, 234, 89, 23);
		panelEditPerson.add(btnOkay);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(208, 234, 89, 23);
		panelEditPerson.add(btnCancel);
		editPerson.setVisible(true);
		editPerson.setResizable(false);
		editPerson.setSize(428, 332);
		
	}
}
