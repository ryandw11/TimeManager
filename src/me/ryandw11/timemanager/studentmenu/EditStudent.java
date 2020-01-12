package me.ryandw11.timemanager.studentmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.orm.Student;
import me.ryandw11.timemanager.utils.Utils;

/**
 * Student Edit Menu
 * @author Ryandw11
 *
 */
public class EditStudent {
	private JFrame frame;
	
	public EditStudent(Student stu) {
		JFrame frame = new JFrame();
		frame.setTitle("Time Manager | Edit Student");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 230);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JPanel namePnl = new JPanel();
		JLabel nameLbl = new JLabel("Name: ");
		JTextField jtf = new JTextField();
		jtf.setColumns(15);
		jtf.setText(stu.name);
		namePnl.add(nameLbl);
		namePnl.add(jtf);
		
		JPanel gradePnl = new JPanel();
		JLabel gradeLbl = new JLabel("Grade: ");
		SpinnerModel gradeModel = new SpinnerNumberModel(1, 1, 12, 1);
		JSpinner grade = new JSpinner(gradeModel);
		grade.setValue(stu.grade);
		gradePnl.add(gradeLbl);
		gradePnl.add(grade);
		
		List<String> choices = new ArrayList<>();	//TODO Get classes
		choices.add("None");
		choices.addAll(Main.currentWorkspace.classes);
		boolean isDisabled = false;
		if(choices.size() < 2) {
			isDisabled = true;
		}
		JComboBox<String> cb = new JComboBox<String>(choices.toArray(new String[0]));
		cb.setSelectedIndex(choices.indexOf(stu.clazz));
		if(stu.clazz.equals("")) {
			cb.setSelectedIndex(0);
		}
		if(isDisabled) cb.setEnabled(false);
		
		JPanel titleGrde = new JPanel();
		JPanel nameGrade = new JPanel();
		JLabel title = new JLabel("Edit Student", SwingConstants.CENTER);
		title.setFont(new Font("Welcome!", Font.PLAIN, 30));
		titleGrde.setLayout(new BorderLayout());
		titleGrde.add(title, BorderLayout.NORTH);
		nameGrade.add(namePnl);
		nameGrade.add(gradePnl);
		titleGrde.add(nameGrade, BorderLayout.CENTER);
		
		JLabel error = new JLabel("", SwingConstants.CENTER);
		
		JLabel classLbl = new JLabel("Class: ");
		JPanel classes = new JPanel();
		classes.add(classLbl);
		classes.add(cb);
		JPanel submit = new JPanel();
		JButton btn = new JButton("Edit Student");
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				remove();
			}
			
		});
		submit.add(btn);
		submit.add(cancel);
		
		
		JPanel classSubmit = new JPanel();
		classSubmit.setLayout(new BorderLayout());
		classSubmit.add(classes, BorderLayout.NORTH);
		classSubmit.add(submit, BorderLayout.CENTER);
		
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				System.out.println(jtf.getText());
				if(jtf.getText().equals("") || jtf.getText().equals(" ")) {
					jtf.setBackground(Color.red);
					jtf.setForeground(Color.white);
					error.setText("Error: The name cannot be blank.");
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
				else if(!jtf.getText().equals(stu.name) && Utils.studentNameExists(jtf.getText())) {
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
				stu.setUp(stu.id, jtf.getText(), (int) grade.getValue(), (String) cb.getSelectedItem(), stu.totalHours, stu.hourIDs);
				Main.currentInstanceofMainScreen.updateStudentData();
				frame.dispose();
			}
			
		});
		
		frame.add(titleGrde, BorderLayout.NORTH);
		frame.add(classSubmit, BorderLayout.CENTER);
		frame.add(error, BorderLayout.SOUTH);
		
		this.frame = frame;
	}
	
	public void show() {
		frame.setVisible(true);
	}
	
	public void hide() {
		frame.setVisible(false);
	}
	
	public void remove() {
		frame.dispose();
	}
}
