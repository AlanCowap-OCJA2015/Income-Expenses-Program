import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;
import java.awt.Insets;
import javax.swing.JRadioButton;

public class addPersonFrame extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField nameField;
	private JTextField roleField;
	private JTextField incomeField;

	public addPersonFrame() {

		final JFrame frame = new JFrame("Add Person");

		frame.setVisible(true);
		frame.setSize(400,  300);

		getContentPane().setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(53, 11, 114, 58);
		//	getContentPane().add(panel);

		//230x390

		JLabel namePerson = new JLabel("Person Name");
		panel.add(namePerson);

		nameField = new JTextField();
		panel.add(nameField);
		nameField.setColumns(10);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(53, 80, 114, 58);
		//	getContentPane().add(panel_1);

		JLabel lblPersonRole = new JLabel("Person Role");
		panel_1.add(lblPersonRole);

		roleField = new JTextField();
		panel_1.add(roleField);
		roleField.setColumns(10);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 0, 214, 352);
		//		getContentPane().add(panel_2);

		frame.setSize(230, 390);
		frame.getContentPane().setLayout(null);

		frame.getContentPane().add(panel);
		frame.getContentPane().add(panel_1);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		final JPanel pnlIncome = new JPanel();
		pnlIncome.setVisible(false);
		pnlIncome.setBounds(44, 149, 125, 57);
		panel_2.add(pnlIncome);

		JButton btnCalculateIncome = new JButton("Calculate Income");
		btnCalculateIncome.setMargin(new Insets(2, 9, 2, 9));
		pnlIncome.add(btnCalculateIncome);

		incomeField = new JTextField();
		pnlIncome.add(incomeField);
		incomeField.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 308, 194, 33);
		panel_2.add(panel_4);

		JRadioButton noButton = new JRadioButton("No");
		final JRadioButton yesButton = new JRadioButton("Yes");
		yesButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(yesButton.isSelected()){
					pnlIncome.setVisible(true);
				}

			}
		});

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameField.getText();
				String role = roleField.getText();
				double income;
				if(incomeField.getText().length() != 0){
					income = Double.parseDouble(incomeField.getText());
				}
				else{
					income = 0.0;
				}

				boolean isEarner = yesButton.isSelected() ? true : false;

				Person p = new Person(name, role, isEarner, income);

				GUI.family.add(p);
				
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));

			}
		});
		panel_4.add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel_4.add(btnCancel);

		JPanel pnlEarner = new JPanel();
		pnlEarner.setBounds(10, 217, 194, 80);
		panel_2.add(pnlEarner);
		pnlEarner.setLayout(null);

		JLabel lblEarner = new JLabel("Earner?");
		lblEarner.setBounds(17, 9, 53, 14);
		pnlEarner.add(lblEarner);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(59, 16, 0, 0);
		pnlEarner.add(lblNewLabel);


		yesButton.setBounds(110, 5, 78, 23);
		pnlEarner.add(yesButton);


		noButton.setBounds(110, 32, 78, 23);
		pnlEarner.add(noButton);
		//frame.add(panel);

	}
}
