package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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

import com.team3.familybudgetapp.Expense.Recurrences;

public class EditExpense extends JDialog {
	private JDialog editExpense;
	private JPanel panelEditExpense;
	private JTextField textFieldDescription;
	private JTextField textFieldAmount;
	private JButton btnCancel;
	private JButton btnOK;
	private JLabel lblDescription;
	private JLabel lblAmount;
	private JComboBox comboBox;
	private JLabel lblRecurs;

	/**
	 * Edit a specific expense
	 */
	public EditExpense(final Expense expense){
		editExpense = new JDialog (ExpensesWindow.editExpenses, "Add Expense");
		editExpense.setModalityType(ModalityType.DOCUMENT_MODAL);
		editExpense.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		panelEditExpense = new JPanel();
		panelEditExpense.setLayout(null);
		panelEditExpense.setOpaque(true);
		editExpense.getContentPane().add(BorderLayout.CENTER, panelEditExpense);

		// Add Escape and Enter key listeners so the window can be confirmed or
		// cancelled with the keyboard
		ActionListener escListener = new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) { btnCancel.doClick (); }
		};
		editExpense.getRootPane ().registerKeyboardAction (escListener, KeyStroke.getKeyStroke (KeyEvent.VK_ESCAPE, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);

		ActionListener enterListener = new ActionListener () {
			@Override
			public void actionPerformed (ActionEvent e) { btnOK.doClick (); }
		};
		editExpense.getRootPane ().registerKeyboardAction (enterListener, KeyStroke.getKeyStroke (KeyEvent.VK_ENTER, 0),
				JComponent.WHEN_IN_FOCUSED_WINDOW);
		
		// Expense description
		lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(10, 14, 65, 14);
		panelEditExpense.add(lblDescription);
		textFieldDescription = new JTextField();
		textFieldDescription.setBounds(85, 11, 243, 20);
		textFieldDescription.grabFocus();
		panelEditExpense.add(textFieldDescription);
		
		// Expense amount
		lblAmount = new JLabel("Amount");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(10, 45, 67, 14);
		panelEditExpense.add(lblAmount);
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(85, 42, 77, 20);
		panelEditExpense.add(textFieldAmount);

		// Recurring option label and combo box
		lblRecurs = new JLabel("Recurs");
		lblRecurs.setHorizontalAlignment(SwingConstants.RIGHT);
		lblRecurs.setBounds(172, 45, 46, 14);
		panelEditExpense.add(lblRecurs);
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Once-off", "Daily", "Weekly", "Monthly", "Yearly"}));
		comboBox.setBounds(228, 42, 100, 22);
		comboBox.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
			}
		});
		panelEditExpense.add(comboBox);

		// If expense object already defined, set the values of the controls accordingly
		if (expense.getDescription() != null && expense.getDescription().length() > 0) {
			textFieldDescription.setText (expense.getDescription());
			textFieldAmount.setText (String.format("%.2f", expense.getAmount()));
			comboBox.setSelectedIndex(expense.getRecurs().ordinal());
		}
		
		// The OK buttons
		btnOK = new JButton("OK");
		btnOK.setBounds(10, 70, 89, 23);
		btnOK.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// Check the input fields before changing anything in the expense object
				if (textFieldDescription.getText ().length () == 0) {
					JOptionPane.showMessageDialog (editExpense, 
						"An expense description cannot be blank\n"
						+ "You need to enter something.", 
						"Expense description error", 
						JOptionPane.NO_OPTION);
				} else {
					double tempAmount;
					if (textFieldAmount.getText().length() > 0) {
						try {
							tempAmount = Double.parseDouble(textFieldAmount.getText());
						} catch (Exception x) {
							tempAmount = 0.0;
						}
					} else {
						tempAmount = 0.0;
					}
					if (tempAmount == 0.0) {
						if (JOptionPane.showConfirmDialog (editExpense, 
							"There is no valid expense amount\n"
							+ "Was this intentional?",
							"Expense amount error", 
							JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) != 0) return;
					}
					expense.setAmount (tempAmount);
					expense.setDescription (textFieldDescription.getText());
					switch (comboBox.getSelectedIndex()) {
						case -1:
						case 0: expense.setRecurs(Recurrences.ONCE); break;
						case 1: expense.setRecurs(Recurrences.DAILY); break;
						case 2: expense.setRecurs(Recurrences.WEEKLY); break;
						case 3: expense.setRecurs(Recurrences.MONTHLY); break;
						case 4: expense.setRecurs(Recurrences.YEARLY); break;
						default: expense.setRecurs(Recurrences.ONCE); break;
					}
					FamilyBudgetApp.setDataChanged();
					editExpense.dispatchEvent(new WindowEvent(editExpense, WindowEvent.WINDOW_CLOSING));
				}
			}
		});
		panelEditExpense.add(btnOK);
		
		btnCancel = new JButton("Cancel");
		btnCancel.setBounds(239, 70, 89, 23);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				editExpense.dispatchEvent(new WindowEvent(editExpense, WindowEvent.WINDOW_CLOSING));
			}
		});
		panelEditExpense.add(btnCancel);
		
		panelEditExpense.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldDescription, textFieldAmount, btnOK, btnCancel, lblDescription, lblAmount}));
		editExpense.setResizable(false);
		editExpense.setSize(346, 128);
		Rectangle screenSize = editExpense.getGraphicsConfiguration().getBounds();
		editExpense.setLocation(screenSize.width / 2 - editExpense.getWidth() / 2, screenSize.height / 2 - editExpense.getHeight() / 2);
		editExpense.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldDescription, textFieldAmount, comboBox, btnOK, btnCancel}));
		editExpense.setVisible(true);
		
	}
}
