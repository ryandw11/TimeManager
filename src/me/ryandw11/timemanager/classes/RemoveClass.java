package me.ryandw11.timemanager.classes;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.orm.Student;

/**
 * GUI for removing a class.
 * @author Ryan
 *
 */
public class RemoveClass {
	
private JFrame frame;
	
	public RemoveClass() {
		JFrame frame = new JFrame();
		frame.setAlwaysOnTop(true);
		frame.setAutoRequestFocus(true);
		frame.setTitle("Time Manager | Remove Class");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 180);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JPanel namePnl = new JPanel();
		JLabel nameLbl = new JLabel("Select a class to remove: ");
		List<String> choices = new ArrayList<>();
		choices.addAll(Main.currentWorkspace.classes);
		if(choices.size() < 2) {
			frame.dispose();
			JOptionPane.showMessageDialog(null, "There are no classes to remove!", "No Classes", JOptionPane.ERROR_MESSAGE);
			return;
		}
		JComboBox<String> cb = new JComboBox<String>(choices.toArray(new String[0]));
		
		namePnl.add(nameLbl);
		namePnl.add(cb);
		
		JLabel lbl = new JLabel("Remove a Class", SwingConstants.CENTER);
		lbl.setFont(new Font("Remove a Class!", Font.PLAIN, 30));
		
		JPanel classes = new JPanel();
		classes.setLayout(new BorderLayout());
		classes.add(lbl, BorderLayout.NORTH);
		classes.add(namePnl, BorderLayout.CENTER);
		
		
		JLabel error = new JLabel("", SwingConstants.CENTER);
		
		JPanel submit = new JPanel();
		JButton btn = new JButton("Remove Class");
		submit.add(btn);
		
		JPanel classSubmit = new JPanel();
		classSubmit.setLayout(new BorderLayout());
		classSubmit.add(classes, BorderLayout.NORTH);
		classSubmit.add(submit, BorderLayout.CENTER);
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				Main.currentWorkspace.classes.remove(cb.getSelectedItem());
				
				int i = 0;
				for(Student stu : Main.listofStudents) {
					if(stu.clazz.equals(cb.getSelectedItem())) {
						Main.listofStudents.get(i).clazz = "None";
					}
					i++;
				}
				Main.currentInstanceofMainScreen.updateStudentData();
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
