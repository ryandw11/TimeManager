package me.ryandw11.timemanager.studentmenu;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextArea;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;

import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.orm.Hour;
import me.ryandw11.timemanager.orm.Student;
import me.ryandw11.timemanager.utils.Utils;

public class AddHours {
	private JFrame frame;
	
	Student stu;
	
	public AddHours(Student stu) {
		this.stu = stu;
		JFrame frame = new JFrame();
		frame.setTitle("Time Manager | Add Hours");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 230);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JLabel title = new JLabel("Add Hours", SwingConstants.CENTER);
		title.setFont(new Font("Add Hours", Font.PLAIN, 30));
		
		JLabel actDescLbl = new JLabel("Description of activity:");
		JLabel hoursLbl = new JLabel("Number of Hours: ");
		
		JTextArea actDesc = new JTextArea(5, 40);
		
		SpinnerModel hoursModel = new SpinnerNumberModel(1, 0.01, 99.99, 0.1);
		JSpinner hours = new JSpinner(hoursModel);
		
		JPanel actDescPnl = new JPanel(new BorderLayout());
		actDescPnl.add(actDescLbl, BorderLayout.NORTH);
		actDescPnl.add(actDesc, BorderLayout.CENTER);
		
		JPanel hourPnl = new JPanel();
		hourPnl.add(hoursLbl);
		hourPnl.add(hours);
		
		JPanel centerPnl = new JPanel();
		centerPnl.add(hourPnl);
		centerPnl.add(actDescPnl);
		
		JPanel bottomPnl = new JPanel();
		JButton submit = new JButton("Submit");
		submit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				Hour hr = new Hour();
				hr.setUp(Main.currentWorkspace.currentHourId, (double) hours.getValue(), actDesc.getText());
				System.out.println(Main.listOfHours);
				Main.listOfHours.add(hr);
				stu.hourIDs.add(String.valueOf(Main.currentWorkspace.currentHourId));
				stu.totalHours = Utils.addUpHours(stu);
				Main.currentWorkspace.currentHourId += 1;
				Main.currentInstanceofMainScreen.updateStudentData();
				remove();
			}
			
		});
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				remove();
			}
			
		});
		bottomPnl.add(submit);
		bottomPnl.add(cancel);
		
		frame.add(title, BorderLayout.NORTH);
		frame.add(centerPnl, BorderLayout.CENTER);
		frame.add(bottomPnl, BorderLayout.SOUTH);
		
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
