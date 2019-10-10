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

public class AddStudent {
	private JFrame frame;
	
	public AddStudent() {
		JFrame frame = new JFrame();
		frame.setTitle("Time Manager | Add Student");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 230);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JPanel namePnl = new JPanel();
		JLabel nameLbl = new JLabel("Name: ");
		JTextField jtf = new JTextField();
		jtf.setColumns(15);
		namePnl.add(nameLbl);
		namePnl.add(jtf);
		
		JPanel gradePnl = new JPanel();
		JLabel gradeLbl = new JLabel("Grade: ");
		SpinnerModel gradeModel = new SpinnerNumberModel(1, 1, 12, 1);
		JSpinner grade = new JSpinner(gradeModel);
		gradePnl.add(gradeLbl);
		gradePnl.add(grade);
		
		List<String> choices = new ArrayList<>();	//TODO Get classes
		choices.addAll(Main.currentWorkspace.classes);
		boolean isDisabled = false;
		if(choices.size() < 1) {
			choices.add("None");
			isDisabled = true;
		}
		JComboBox<String> cb = new JComboBox<String>(choices.toArray(new String[0]));
		if(isDisabled) cb.setEnabled(false);
		
		JPanel titleGrde = new JPanel();
		JPanel nameGrade = new JPanel();
		JLabel title = new JLabel("Add a Student", SwingConstants.CENTER);
		title.setFont(new Font("Welcome!", Font.PLAIN, 30));
		titleGrde.setLayout(new BorderLayout());
		titleGrde.add(title, BorderLayout.NORTH);
		nameGrade.add(namePnl);
		nameGrade.add(gradePnl);
		titleGrde.add(nameGrade, BorderLayout.CENTER);
		
		JLabel error = new JLabel("Error", SwingConstants.CENTER);
		
		JLabel classLbl = new JLabel("Class: ");
		JPanel classes = new JPanel();
		classes.add(classLbl);
		classes.add(cb);
		JPanel submit = new JPanel();
		JButton btn = new JButton("Add Student");
		submit.add(btn);
		
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
				else if(Utils.studentNameExists(jtf.getText())) {
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
				Student stu = new Student();
				Main.currentWorkspace.currentStudentId += 1;
				stu.setUp(Main.currentWorkspace.currentStudentId, jtf.getText(), (int) grade.getValue(), (String) cb.getSelectedItem(), 0, new ArrayList<String>());
				Main.listofStudents.add(stu);
				Main.currentInstanceofMainScreen.updateStudentData();
				frame.dispose();
//				Main.currentInstanceofMainScreen.close();
//				frame.dispose();
//				MainScreen ms = new MainScreen();
//				Main.currentInstanceofMainScreen = ms;
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
