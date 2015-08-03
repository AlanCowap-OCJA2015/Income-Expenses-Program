package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog.ModalityType;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;

public class EditIncome {
	private JDialog editIncome;
	private JPanel panelEditIncome;
	private JTextField textFieldEditSalaryAmount;
	private JTextField textFieldHoursPerWeek;
	private JTextField textFieldAmount;
	private JLabel lblSchedule;
	private JLabel lblHoursWorkedPer;
	private JRadioButton rdbtnHourly;
	private JRadioButton rdbtnYearly;
	private JButton btnOK;
	private JButton btnCancel;

	public EditIncome (final Person member) {
		editIncome = new JDialog (EditMember.editMember, "Personal income for " + member.getFullName ());
		editIncome.setModalityType(ModalityType.DOCUMENT_MODAL);
		editIncome.setResizable (false);
		editIncome.setSize (265, 189);
		editIncome.setDefaultCloseOperation (JFrame.DISPOSE_ON_CLOSE);
		try {
			UIManager.setLookAndFeel (UIManager.getSystemLookAndFeelClassName ());
		} catch (Exception e) {
			e.printStackTrace ();
		}
		panelEditIncome = new JPanel ();
		panelEditIncome.setLayout (null);
		panelEditIncome.setOpaque (true);
		editIncome.getContentPane ().add (BorderLayout.CENTER, panelEditIncome);

		// Add Escape and Enter key listeners so the window can be confirmed or
		// cancelled with the keyboard
		ActionListener escListener = new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) { btnCancel.doClick (); }
		};
		editIncome.getRootPane ().registerKeyboardAction (escListener, KeyStroke.getKeyStroke (KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		ActionListener enterListener = new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) { btnOK.doClick (); }
		};
		editIncome.getRootPane ().registerKeyboardAction (enterListener, KeyStroke.getKeyStroke (KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		lblSchedule = new JLabel ("Payment schedule");
		lblSchedule.setHorizontalAlignment (SwingConstants.RIGHT);
		lblSchedule.setBounds (35, 14, 121, 38);
		panelEditIncome.add (lblSchedule);

		textFieldAmount = new JTextField ();
		textFieldAmount.setHorizontalAlignment (SwingConstants.RIGHT);
		textFieldAmount.setBorder (null);
		textFieldAmount.setForeground (Color.BLACK);
		textFieldAmount.setText ("Rate");
		textFieldAmount.setEditable (false);
		textFieldAmount.setBounds (70, 73, 86, 20);
		panelEditIncome.add (textFieldAmount);
		textFieldAmount.setColumns (10);

		lblHoursWorkedPer = new JLabel ("Hours worked per week");
		lblHoursWorkedPer.setHorizontalAlignment (SwingConstants.RIGHT);
		lblHoursWorkedPer.setBounds (35, 104, 121, 14);
		panelEditIncome.add (lblHoursWorkedPer);

		rdbtnHourly = new JRadioButton ("Hourly");
		rdbtnYearly = new JRadioButton ("Yearly");

		// Radio button for hourly wages
		rdbtnHourly.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				rdbtnYearly.setSelected (false);
				textFieldAmount.setText ("Rate");
				textFieldHoursPerWeek.setEnabled (true);
				lblHoursWorkedPer.setEnabled (true);
			}
		});
		rdbtnHourly.setBounds (162, 10, 68, 23);
		rdbtnHourly.setSelected (true);
		panelEditIncome.add (rdbtnHourly);

		// Radio button for selecting yearly income
		rdbtnYearly.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				rdbtnHourly.setSelected (false);
				textFieldAmount.setText ("Salary");
				textFieldHoursPerWeek.setEnabled (false);
				lblHoursWorkedPer.setEnabled (false);
			}
		});
		rdbtnYearly.setBounds (162, 36, 58, 23);
		panelEditIncome.add (rdbtnYearly);

		textFieldEditSalaryAmount = new JTextField ();
		textFieldEditSalaryAmount.setHorizontalAlignment (SwingConstants.RIGHT);
		textFieldEditSalaryAmount.setBounds (166, 73, 86, 20);
		panelEditIncome.add (textFieldEditSalaryAmount);
		textFieldEditSalaryAmount.setColumns (10);

		textFieldHoursPerWeek = new JTextField ();
		textFieldHoursPerWeek.setBounds (166, 101, 86, 20);
		panelEditIncome.add (textFieldHoursPerWeek);
		textFieldHoursPerWeek.setColumns (10);

		btnOK = new JButton ("OK");
		btnOK.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent arg0) {
				// Save changes to the Person object
				double amount;
				if (textFieldAmount.getText ().length () > 0) {
					try {
						amount = Double.parseDouble (textFieldAmount.getText());
					} catch (Exception e) {
						amount = 0.0;
					}
					if (amount == 0.0 && JOptionPane.showConfirmDialog (editIncome, 
							"The income has been set to zero\n"
							+ "Was this intentional?",
							"Income amount query", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != 0) return;
				} else {
					amount = 0.0;
				}
				member.income = amount;
				if (amount > 0.0) {
					member.earner = true;
				} else {
					member.earner = false;
				}
				FamilyBudgetApp.setDataChanged();
				editIncome.dispatchEvent (new WindowEvent (editIncome, WindowEvent.WINDOW_CLOSING));
					
			}
		});
		btnOK.setBounds (10, 132, 91, 23);
		panelEditIncome.add (btnOK);

		btnCancel = new JButton ("Cancel");
		btnCancel.addActionListener (new ActionListener () {
			public void actionPerformed (ActionEvent e) {
				editIncome.dispatchEvent (new WindowEvent (editIncome, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnCancel.setBounds (161, 132, 91, 23);

		Rectangle screenSize = editIncome.getGraphicsConfiguration ().getBounds ();
		editIncome.setLocation (screenSize.width / 2 - editIncome.getWidth () / 2,
				screenSize.height / 2 - editIncome.getHeight () / 2);
		panelEditIncome.add (btnCancel);
		panelEditIncome.setFocusTraversalPolicy (new FocusTraversalOnArray (
				new Component[] { textFieldEditSalaryAmount, textFieldHoursPerWeek, btnOK, btnCancel }));

		editIncome.setVisible (true);

	}
}
