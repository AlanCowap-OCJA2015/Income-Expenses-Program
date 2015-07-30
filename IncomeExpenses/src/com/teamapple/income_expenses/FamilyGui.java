package com.teamapple.income_expenses;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;

public class FamilyGui extends JFrame {

	private JPanel contentPane;
	private JTextField familySizeTextField;
	private JTextField nameTextField;
	private JTextField wagesTextField;
	private JTextField hoursTextField;
	private int familySize = 0;
	private int currentFamilyMember = 1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FamilyGui frame = new FamilyGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FamilyGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 335, 301);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		JLabel lblSizeOfFamily = new JLabel("Family Size");
		lblSizeOfFamily.setBounds(10, 22, 70, 14);
		contentPane.add(lblSizeOfFamily);
		
		familySizeTextField = new JTextField();
		familySizeTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent arg0) {
				try{
					
					if(familySizeTextField.getText().length() == 0){
						familySize = 0;
					}else{
						familySize = Integer.parseInt(familySizeTextField.getText());
					}
				}catch(NumberFormatException nfe){}
			}
		});
		familySizeTextField.setBounds(87, 19, 41, 20);
		contentPane.add(familySizeTextField);
		familySizeTextField.setColumns(10);
		
		final JLabel familyMemberLabel = new JLabel("Family Member 1");
		familyMemberLabel.setBounds(21, 58, 117, 14);
		contentPane.add(familyMemberLabel);
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(10, 83, 46, 14);
		contentPane.add(lblName);
		
		JLabel lblRole = new JLabel("Role");
		lblRole.setBounds(10, 115, 28, 14);
		contentPane.add(lblRole);
		
		final JLabel wagesLabel = new JLabel("Wages");
		wagesLabel.setBounds(10, 176, 41, 14);
		contentPane.add(wagesLabel);
		
		final JCheckBox afterTax = new JCheckBox("After tax?");
		afterTax.setBounds(144, 172, 97, 23);
		contentPane.add(afterTax);
		
		final JLabel hoursLabel = new JLabel("Hours:");
		hoursLabel.setBounds(222, 207, 46, 14);
		contentPane.add(hoursLabel);
		
		hoursTextField = new JTextField();
		hoursTextField.setBounds(268, 204, 41, 20);
		contentPane.add(hoursTextField);
		hoursTextField.setColumns(10);
		
		final JComboBox<String> timeForSalary = new JComboBox<String>();
		timeForSalary.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				String selected = arg0.getItem().toString();
				if(selected.equals("Yearly")){
					hoursLabel.setVisible(false);
					hoursTextField.setVisible(false);
				}else{
					hoursLabel.setVisible(true);
					hoursTextField.setVisible(true);
				}
			}
		});
		timeForSalary.addItem("Hourly");
		timeForSalary.addItem("Yearly");
		timeForSalary.setBounds(244, 173, 65, 20);
		
		
		final JCheckBox earner = new JCheckBox("Earner?");
		earner.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(earner.isSelected()){
					wagesLabel.setVisible(true);
					wagesTextField.setVisible(true);
					afterTax.setVisible(true);
					timeForSalary.setVisible(true);
					hoursLabel.setVisible(true);
					hoursTextField.setVisible(true);
				}else{
					wagesLabel.setVisible(false);
					wagesTextField.setVisible(false);
					afterTax.setVisible(false);
					timeForSalary.setVisible(false);
					hoursLabel.setVisible(false);
					hoursTextField.setVisible(false);
				}
			}
		});
		earner.setBounds(10, 136, 97, 23);
		contentPane.add(earner);
		
		
		
		
		
		
		contentPane.add(timeForSalary);
		
		
		
		nameTextField = new JTextField();
		nameTextField.setBounds(48, 80, 80, 20);
		contentPane.add(nameTextField);
		nameTextField.setColumns(10);
		
		wagesTextField = new JTextField();
		wagesTextField.setBounds(58, 173, 80, 20);
		contentPane.add(wagesTextField);
		wagesTextField.setColumns(10);
		
		
		final JComboBox<String> roleComboBox = new JComboBox<String>();
		roleComboBox.addItem("Father");
		roleComboBox.addItem("Mother");
		roleComboBox.addItem("Daughter");
		roleComboBox.addItem("Son");
		roleComboBox.addItem("Other");
		roleComboBox.setBounds(48, 112, 80, 20);
		contentPane.add(roleComboBox);
		
		
		
		final JButton submitButton = new JButton("Next Member");
		submitButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				
				if(currentFamilyMember <= familySize){
					addToFamily();
					currentFamilyMember++;
					if(!(currentFamilyMember > familySize)){
						familyMemberLabel.setText(familyMemberLabel.getText().substring(0, familyMemberLabel.getText().length()-1) + currentFamilyMember);
					}else{
						setVisible(false);
						IncomeExpenses.runExpense();
					}
					
					nameTextField.setText("");
					roleComboBox.setSelectedIndex(0);
					wagesTextField.setText("");
					hoursTextField.setText("");
					earner.setSelected(false);
					afterTax.setSelected(false);
					timeForSalary.setSelectedIndex(0);
					
				}
				
				
				if(currentFamilyMember == familySize){
					submitButton.setText("Add Expenses");
				}
				System.out.println("curMem:" + currentFamilyMember + "famSize" + familySize);
				System.out.println(Family.family.size());
			}
			
			public void addToFamily(){
			
				//If family size text field is still editable and contains 1 or higher, set editable to false;
				if(familySizeTextField.isEditable() && familySize >= 1){
					familySizeTextField.setEditable(false);
				}
				if(familySize >= 1){
					
					String name = nameTextField.getText();
					String role = roleComboBox.getSelectedItem().toString();
					String wages = wagesTextField.getText();
					String hours = hoursTextField.getText();
					FamilyRole fRole = FamilyRole.Other;
					switch(role){
						case "Father":
							fRole = FamilyRole.Father;
							break;
						case "Mother":
							fRole = FamilyRole.Mother;
							break;
						case "Daughter":
							fRole = FamilyRole.Daughter;
							break;
						case "Son":
							fRole = FamilyRole.Son;
							break;
						case "Other":
							fRole = FamilyRole.Other;
							break;
							
					}
					
					
					boolean isEarner = earner.isSelected();
					boolean isHourly = timeForSalary.getSelectedItem().toString().equals("Hourly");
					boolean isAfterTax = afterTax.isSelected();
					Person p;
					if(isEarner){
						double salary = 0;
						double hoursWorked = 0;
						try{
							salary = Double.parseDouble(wages);
							hoursWorked = Double.parseDouble(hours);
						}catch(NumberFormatException nfe){}
						if(isHourly){
							p = new Person(name,fRole,salary,hoursWorked,isAfterTax);
						}else{
							p = new Person(name,fRole,salary,isAfterTax);
						}
					}else{
						
						p = new Person(name,fRole);
					}
					System.out.println("gOT INTO MEHTOD");
					
					
					Family.family.add(p);
					
				}
			}
		});
		
		
		submitButton.setBounds(10, 229, 118, 23);
		contentPane.add(submitButton);
		
		
		
		wagesLabel.setVisible(false);
		wagesTextField.setVisible(false);
		afterTax.setVisible(false);
		timeForSalary.setVisible(false);
		hoursLabel.setVisible(false);
		hoursTextField.setVisible(false);
		
	}
}
