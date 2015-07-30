package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
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

public class AddPerson {
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldDateOfBirthDay;
	private JTextField textFieldDateOfBirthMonth;
	private JTextField textFieldDateOfBirthYear;
	private JTextField textFieldGender;
	public AddPerson(final JList list, final ArrayList<Person> members){
		final JFrame addPerson = new JFrame("Add Person");
		addPerson.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel panelAddPerson = new JPanel();
		panelAddPerson.setLayout(null);
		panelAddPerson.setOpaque(true);

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
		panelAddPerson.add(scroller);
		panelAddPerson.add(inputpanel);
		addPerson.getContentPane().add(BorderLayout.CENTER, panelAddPerson);
		
		JLabel lblFirstName = new JLabel("First Name");
		lblFirstName.setBounds(10, 40, 95, 14);
		panelAddPerson.add(lblFirstName);
		
		textFieldFirstName = new JTextField();
		textFieldFirstName.setBounds(130, 37, 167, 20);
		panelAddPerson.add(textFieldFirstName);
		textFieldFirstName.setColumns(10);
		
		JLabel lblLastName = new JLabel("Last Name");
		lblLastName.setBounds(10, 81, 95, 14);
		panelAddPerson.add(lblLastName);
		
		textFieldLastName = new JTextField();
		textFieldLastName.setBounds(130, 78, 167, 20);
		panelAddPerson.add(textFieldLastName);
		textFieldLastName.setColumns(10);
		
		JLabel lblDateOfBirth = new JLabel("Date of Birth");
		lblDateOfBirth.setBounds(10, 132, 95, 14);
		panelAddPerson.add(lblDateOfBirth);
		
		textFieldDateOfBirthDay = new JTextField();
		textFieldDateOfBirthDay.setBounds(130, 129, 40, 20);
		panelAddPerson.add(textFieldDateOfBirthDay);
		textFieldDateOfBirthDay.setColumns(10);
		
		textFieldDateOfBirthMonth = new JTextField();
		textFieldDateOfBirthMonth.setBounds(180, 129, 40, 20);
		panelAddPerson.add(textFieldDateOfBirthMonth);
		textFieldDateOfBirthMonth.setColumns(10);
		
		textFieldDateOfBirthYear = new JTextField();
		textFieldDateOfBirthYear.setBounds(230, 129, 40, 20);
		panelAddPerson.add(textFieldDateOfBirthYear);
		textFieldDateOfBirthYear.setColumns(10);
		
		JLabel lblDd = new JLabel("DD");
		lblDd.setBounds(130, 109, 29, 14);
		panelAddPerson.add(lblDd);
		
		JLabel lblMm = new JLabel("MM");
		lblMm.setBounds(180, 109, 29, 14);
		panelAddPerson.add(lblMm);
		
		JLabel lblYyyy = new JLabel("YYYY");
		lblYyyy.setBounds(230, 109, 40, 14);
		panelAddPerson.add(lblYyyy);
		
		JLabel lblGender = new JLabel("Gender");
		lblGender.setBounds(10, 177, 73, 14);
		panelAddPerson.add(lblGender);
		
		textFieldGender = new JTextField();
		textFieldGender.setBounds(130, 174, 90, 20);
		panelAddPerson.add(textFieldGender);
		textFieldGender.setColumns(10);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Create a new Person object and add it to the ArrayList
				Person newPerson = new Person();
				newPerson.firstName = textFieldFirstName.getText();
				newPerson.lastName = textFieldLastName.getText();
				members.add(newPerson);
				Vector<String> listData = new Vector();
				for (Person p : members) {
					listData.add(p.firstName + " " + p.lastName);
				}
				list.setListData(listData);
				list.revalidate();
				list.repaint();
				addPerson.dispatchEvent(new WindowEvent(addPerson, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnOkay.setBounds(16, 241, 89, 23);
		panelAddPerson.add(btnOkay);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				addPerson.dispatchEvent(new WindowEvent(addPerson, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnCancel.setBounds(130, 241, 89, 23);
		panelAddPerson.add(btnCancel);
		addPerson.setVisible(true);
		addPerson.setResizable(false);
		addPerson.setSize(389, 313);
		
	}
}
