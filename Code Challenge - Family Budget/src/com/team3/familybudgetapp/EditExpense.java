package com.team3.familybudgetapp;

import java.awt.BorderLayout;
import java.awt.Component;
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
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import org.eclipse.wb.swing.FocusTraversalOnArray;
import java.awt.Dialog.ModalExclusionType;
import java.awt.Dialog.ModalityType;

public class EditExpense extends JDialog {
	private JTextField textFieldDescription;
	private JTextField textFieldAmount;
	private final JButton btnCancel;
	private final JButton btnOK;
	private final JLabel lblDescription;
	private JLabel lblAmount;
	private final JPanel panelEditExpense;
	private final JDialog editExpense;

	/**
	 * TODO Change references to ExpensesWindow to parentWindow which will be passed in 
	 * 
	 */
	public EditExpense(final Expense expense){
		JList list = ExpensesWindow.list;
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
		
		lblDescription = new JLabel("Description");
		lblDescription.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDescription.setBounds(10, 14, 65, 14);
		panelEditExpense.add(lblDescription);
		textFieldDescription = new JTextField();
		textFieldDescription.setBounds(85, 11, 243, 20);
		textFieldDescription.grabFocus();
		panelEditExpense.add(textFieldDescription);
		
		lblAmount = new JLabel("Amount");
		lblAmount.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAmount.setBounds(10, 45, 67, 14);
		panelEditExpense.add(lblAmount);
		textFieldAmount = new JTextField();
		textFieldAmount.setBounds(85, 42, 105, 20);
		panelEditExpense.add(textFieldAmount);

		if (expense.description != null && expense.description.length () > 0) {
			textFieldDescription.setText (expense.description);
			textFieldAmount.setText (String.format("%.2f", expense.amount));
		}

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
						tempAmount = Double.parseDouble(textFieldAmount.getText());
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
					expense.amount = tempAmount;
					expense.description = textFieldDescription.getText();
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
		editExpense.setFocusTraversalPolicy(new FocusTraversalOnArray(new Component[]{textFieldDescription, textFieldAmount, btnOK, btnCancel}));
		editExpense.setVisible(true);
		
	}
}
