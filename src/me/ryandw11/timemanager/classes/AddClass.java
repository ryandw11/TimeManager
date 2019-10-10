package me.ryandw11.timemanager.classes;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.utils.Utils;

/**
 * GUI for adding a class.
 * @author Ryan
 *
 */
public class AddClass {
	
private JFrame frame;
	
	public AddClass() {
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setAutoRequestFocus(true);
		frame.setTitle("Time Manager | Add Class");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 180);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JPanel namePnl = new JPanel();
		JLabel nameLbl = new JLabel("Class Name: ");
		JTextField jtf = new JTextField();
		jtf.setColumns(15);
		namePnl.add(nameLbl);
		namePnl.add(jtf);
		
		JLabel lbl = new JLabel("Add a Class", SwingConstants.CENTER);
		lbl.setFont(new Font("Add a Class!", Font.PLAIN, 30));
		
		JPanel classes = new JPanel();
		classes.setLayout(new BorderLayout());
		classes.add(lbl, BorderLayout.NORTH);
		classes.add(namePnl, BorderLayout.CENTER);
		
		
		JLabel error = new JLabel("", SwingConstants.CENTER);
		
		JPanel submit = new JPanel();
		JButton btn = new JButton("Add Class");
		submit.add(btn);
		
		JPanel classSubmit = new JPanel();
		classSubmit.setLayout(new BorderLayout());
		classSubmit.add(classes, BorderLayout.NORTH);
		classSubmit.add(submit, BorderLayout.CENTER);
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				if(jtf.getText().equals("") || jtf.getText().equals(" ")) {
					jtf.setBackground(Color.red);
					jtf.setForeground(Color.white);
					error.setText("Error: The name cannot be blank.");
					error.setForeground(Color.red);
					return;
				}
				else if(jtf.getText().equals("None")) {
					jtf.setBackground(Color.red);
					jtf.setForeground(Color.white);
					error.setText("Error: You cannot use the name 'None'.");
					error.setForeground(Color.red);
					return;
				}
				else if(jtf.getText().contains("|")) {
					jtf.setBackground(Color.red);
					jtf.setForeground(Color.white);
					error.setText("Error: The name cannot contain the symbol |");
					error.setForeground(Color.red);
					return;
				}
				else if(Utils.classNameExists(jtf.getText())) {
					jtf.setBackground(Color.red);
					jtf.setForeground(Color.white);
					error.setText("Error: That name already exists!");
					error.setForeground(Color.red);
					return;
				}
				else {
					jtf.setBackground(Color.white);
					jtf.setForeground(Color.black);
					error.setText("");
				}
				Main.currentWorkspace.classes.add(jtf.getText());
				Main.currentInstanceofMainScreen.updateSelect();
				Main.currentInstanceofMainScreen.updateSort();
				frame.dispose();

			}
			
		});
		
		frame.add(classSubmit, BorderLayout.NORTH);
		frame.add(error, BorderLayout.CENTER);
		
		this.frame = frame;
	}
	
	/**
	 * Display the GUI
	 */
	public void show() {
		frame.setVisible(true);
	}
	
	/**
	 * Hide the GUI
	 */
	public void hide() {
		frame.setVisible(false);
	}
	
	/**
	 * Get rid of the GUI
	 */
	public void remove() {
		frame.dispose();
	}

}
