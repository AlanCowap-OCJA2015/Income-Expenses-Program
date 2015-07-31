import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;


public class editPersonFrame extends JFrame{

	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private Person[] family;

	public editPersonFrame(){
		final JFrame frame = new JFrame("Edit Person");
		frame.setResizable(true);

		frame.setVisible(true);
		frame.setSize(227, 476);

		getContentPane().setLayout(null);

		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(0, 86, 214, 352);
		//	getContentPane().add(panel_2);

		frame.getContentPane().setLayout(null);
		frame.getContentPane().add(panel_2);
		panel_2.setLayout(null);

		JPanel panel_3 = new JPanel();
		panel_3.setBounds(44, 149, 125, 57);
		panel_2.add(panel_3);

		JButton btnCalculateIncome = new JButton("Calculate Income");
		btnCalculateIncome.setMargin(new Insets(2, 9, 2, 9));
		panel_3.add(btnCalculateIncome);

		textField_6 = new JTextField();
		panel_3.add(textField_6);
		textField_6.setColumns(10);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(10, 308, 194, 33);
		panel_2.add(panel_4);

		JButton btnSave = new JButton("Save");
		panel_4.add(btnSave);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
			}
		});
		panel_4.add(btnCancel);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(10, 217, 194, 80);
		panel_2.add(panel_5);
		panel_5.setLayout(null);

		JLabel lblEarner = new JLabel("Earner?");
		lblEarner.setBounds(17, 9, 53, 14);
		panel_5.add(lblEarner);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(59, 16, 0, 0);
		panel_5.add(lblNewLabel);

		JRadioButton rdbtnNewRadioButton = new JRadioButton("Yes");
		rdbtnNewRadioButton.setBounds(110, 5, 78, 23);
		panel_5.add(rdbtnNewRadioButton);

		JRadioButton rdbtnNewRadioButton_1 = new JRadioButton("No");
		rdbtnNewRadioButton_1.setBounds(110, 32, 78, 23);
		panel_5.add(rdbtnNewRadioButton_1);
		
				JPanel panel = new JPanel();
				panel.setBounds(44, 11, 125, 58);
				panel_2.add(panel);
				//	getContentPane().add(panel);

				//230x390

				JLabel namePerson = new JLabel("Person Name");
				panel.add(namePerson);
				
						textField_4 = new JTextField();
						panel.add(textField_4);
						textField_4.setColumns(10);
						
								JPanel panel_1 = new JPanel();
								panel_1.setBounds(44, 80, 125, 58);
								panel_2.add(panel_1);
								//	getContentPane().add(panel_1);

								JLabel lblPersonRole = new JLabel("Person Role");
								panel_1.add(lblPersonRole);
								
										textField_5 = new JTextField();
										panel_1.add(textField_5);
										textField_5.setColumns(10);
										
										JComboBox<String> comboBox = new JComboBox<String>();
										comboBox.setBounds(46, 55, 118, 20);
										frame.getContentPane().add(comboBox);
																
										for(Person p : GUI.family){
											comboBox.addItem(p.getName());
										}
										
										JLabel lblPersonToEdit = new JLabel("Person to edit");
										lblPersonToEdit.setBounds(46, 30, 118, 14);
										frame.getContentPane().add(lblPersonToEdit);
	}
	
}//end class
