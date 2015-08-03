package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class EditMember {
	static JDialog editMember;
	private JPanel panelEditMember;
	private JTextField textFieldFirstName;
	private JTextField textFieldLastName;
	private JTextField textFieldDay;
	private JTextField textFieldMonth;
	private JTextField textFieldYear;
	private JTextField textField;
	private JButton btnCancel;
	private JButton btnOK;
	private JComboBox genderComboBox;
	private JLabel lblGender;
	private JLabel lblYear;
	private JLabel lblMonth;
	private JLabel lblDay;
	private JLabel lblDateOfBirth;
	private JLabel lblFirstName;
	private JLabel lblLastName;
	private JTextField textFieldInitials;
	private JLabel lblInitials;
	private JButton btnIncome;
	private JButton btnExpenses;

	/**
	 * Create a modal dialog window which will block the main window until editing has been done
	 * 
	 * @param member to be edited
	 */
	public EditMember (final Person member) {
		editMember = new JDialog (FamilyBudgetApp.familyBudgetAppWindow, "Add Family Member");
		editMember.setModalityType(ModalityType.DOCUMENT_MODAL);
		editMember.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		try {
			UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName ());
		} catch (Exception e) {
			e.printStackTrace ();
		}
		panelEditMember = new JPanel ();
		panelEditMember.setLayout (null);
		panelEditMember.setOpaque (true);
		editMember.getContentPane ().add (BorderLayout.CENTER, panelEditMember);

		// Add Escape and Enter key listeners so the window can be confirmed or
		// cancelled with the keyboard
		ActionListener escListener = new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				btnCancel.doClick ();
			}
		};
		editMember.getRootPane ().registerKeyboardAction (escListener, KeyStroke.getKeyStroke (KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		ActionListener enterListener = new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) {
				btnOK.doClick ();
			}
		};
		editMember.getRootPane ().registerKeyboardAction (enterListener, KeyStroke.getKeyStroke (KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		// First name
		lblFirstName = new JLabel ("First Name");
		lblFirstName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFirstName.setBounds (10, 14, 67, 14);
		panelEditMember.add (lblFirstName);
		textFieldFirstName = new JTextField ();
		textFieldFirstName.setBounds (85, 11, 140, 20);
		textFieldFirstName.grabFocus ();
		panelEditMember.add (textFieldFirstName);

		// Initials
		lblInitials = new JLabel("Initials");
		lblInitials.setHorizontalAlignment(SwingConstants.RIGHT);
		lblInitials.setBounds(224, 14, 40, 14);
		panelEditMember.add(lblInitials);
		textFieldInitials = new JTextField();
		textFieldInitials.setBounds(269, 11, 59, 20);
		panelEditMember.add(textFieldInitials);
		textFieldInitials.setColumns(10);
		
		// Last name
		lblLastName = new JLabel ("Last Name");
		lblLastName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLastName.setBounds (10, 55, 67, 14);
		panelEditMember.add (lblLastName);
		textFieldLastName = new JTextField ();
		textFieldLastName.setBounds (85, 52, 243, 20);
		panelEditMember.add (textFieldLastName);

		// Date of birth label
		lblDateOfBirth = new JLabel ("Date of Birth");
		lblDateOfBirth.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDateOfBirth.setEnabled (false);
		lblDateOfBirth.setBounds (10, 101, 67, 14);
		panelEditMember.add (lblDateOfBirth);

		// Date of birth : Day
		lblDay = new JLabel ("DD");
		lblDay.setHorizontalAlignment(SwingConstants.CENTER);
		lblDay.setEnabled (false);
		lblDay.setBounds (85, 78, 40, 20);
		panelEditMember.add (lblDay);
		textFieldDay = new JTextField ();
		textFieldDay.setEnabled (false);
		textFieldDay.setBounds (85, 98, 40, 20);
		panelEditMember.add (textFieldDay);

		// Date of birth: Month
		lblMonth = new JLabel ("MM");
		lblMonth.setHorizontalAlignment(SwingConstants.CENTER);
		lblMonth.setEnabled (false);
		lblMonth.setBounds (135, 78, 40, 20);
		panelEditMember.add (lblMonth);
		textFieldMonth = new JTextField ();
		textFieldMonth.setEnabled (false);
		textFieldMonth.setBounds (135, 98, 40, 20);
		panelEditMember.add (textFieldMonth);

		// Date of birth: Year
		lblYear = new JLabel ("YYYY");
		lblYear.setHorizontalAlignment(SwingConstants.CENTER);
		lblYear.setEnabled (false);
		lblYear.setBounds (185, 78, 59, 20);
		panelEditMember.add (lblYear);
		textFieldYear = new JTextField ();
		textFieldYear.setEnabled (false);
		textFieldYear.setBounds (185, 98, 59, 20);
		panelEditMember.add (textFieldYear);

		// Gender label, combo box and "other" text field
		lblGender = new JLabel ("Gender");
		lblGender.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGender.setEnabled (false);
		lblGender.setBounds (10, 142, 67, 14);
		panelEditMember.add (lblGender);
		genderComboBox = new JComboBox ();
		genderComboBox.setModel (new DefaultComboBoxModel (new String[] { "Male", "Female", "Other" }));
		genderComboBox.setEnabled (false);
		genderComboBox.setBounds (85, 138, 90, 22);
		panelEditMember.add (genderComboBox);
		textField = new JTextField ();
		textField.setEnabled (false);
		textField.setBounds (185, 139, 143, 20);
		panelEditMember.add (textField);

		// Personal Income
		btnIncome = new JButton("Personal Income...");
		btnIncome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EditIncome(member);
				// Update the list in the main window
			}
		});
		btnIncome.setBounds(14, 171, 143, 23);
		panelEditMember.add(btnIncome);
		
		// Personal Expenses
		btnExpenses = new JButton("Personal Expenses...");
		btnExpenses.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ExpensesWindow(member.expenses, member.getFullName());
			}
		});
		btnExpenses.setBounds(185, 171, 143, 23);
		panelEditMember.add(btnExpenses);

		// If the Person object has been sent in for editing, populate the values of the controls
		if (member != null && member.getFullName().length() > 0) {
			textFieldFirstName.setText(member.firstName);
			textFieldInitials.setText(member.initials);
			textFieldLastName.setText(member.lastName);
		}

		// OK and Cancel buttons
		btnOK = new JButton ("OK");
		btnOK.setBounds (16, 224, 89, 23);
		btnOK.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				// Update the Person object
				if ((textFieldFirstName.getText() + textFieldInitials.getText() + textFieldLastName.getText()).length() == 0) {
					JOptionPane.showMessageDialog (editMember, 
							"A family member's name can not be blank\n"
							+ "You need to enter something.", 
							"Family member name error", 
							JOptionPane.NO_OPTION);
				} else {
					member.firstName = textFieldFirstName.getText ();
					member.initials = textFieldInitials.getText ();
					member.lastName = textFieldLastName.getText ();
					FamilyBudgetApp.setDataChanged();
					// Close our own window
					editMember.dispatchEvent (new WindowEvent (editMember, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
		panelEditMember.add (btnOK);

		btnCancel = new JButton ("Cancel");
		btnCancel.setBounds (239, 224, 89, 23);
		btnCancel.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				editMember.dispatchEvent (new WindowEvent (editMember, WindowEvent.WINDOW_CLOSING));
			}
		});
		panelEditMember.add (btnCancel);
		
		editMember.setResizable (false);
		editMember.setSize (346, 284);
		Rectangle screenSize = editMember.getGraphicsConfiguration ().getBounds ();
		editMember.setLocation (screenSize.width / 2 - editMember.getWidth () / 2,
				screenSize.height / 2 - editMember.getHeight () / 2);
		editMember.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldFirstName, textFieldInitials, textFieldLastName, textFieldDay, textFieldMonth, textFieldYear, genderComboBox, textField, btnIncome, btnExpenses, btnOK, btnCancel}));
		editMember.setVisible (true);

	}
}
