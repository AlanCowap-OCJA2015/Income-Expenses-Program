package com.team3.familybudgetapp;

import java.awt.BorderLayout;
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
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class RemovePerson {
	public RemovePerson(final JList list, final ArrayList<Person> members, final int personIndex){
		final JFrame removePerson = new JFrame("Remove Person");
		removePerson.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		try 
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		JPanel panelRemovePerson = new JPanel();
		panelRemovePerson.setLayout(null);
		panelRemovePerson.setOpaque(true);
		removePerson.getContentPane().add(BorderLayout.CENTER, panelRemovePerson);
		
		JLabel lblAreYouSure = new JLabel("Are you sure you want to delete this person?");
		lblAreYouSure.setHorizontalAlignment(SwingConstants.CENTER);
		lblAreYouSure.setBounds(10, 11, 257, 54);
		panelRemovePerson.add(lblAreYouSure);
		
		JButton btnOkay = new JButton("Okay");
		btnOkay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				members.remove(personIndex);
				Vector<String> listData = new Vector();
				for (Person p : members) {
					listData.add(p.firstName + " " + p.lastName);
				}
				list.setListData(listData);
				list.revalidate();
				list.repaint();
				
				removePerson.dispatchEvent(new WindowEvent(removePerson, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnOkay.setBounds(10, 96, 89, 23);
		panelRemovePerson.add(btnOkay);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				removePerson.dispatchEvent(new WindowEvent(removePerson, WindowEvent.WINDOW_CLOSING));
			}
		});
		btnCancel.setBounds(178, 96, 89, 23);
		panelRemovePerson.add(btnCancel);
		removePerson.setVisible(true);
		removePerson.setResizable(false);
		removePerson.setSize(283, 155);
		
	}
}
