package me.ryandw11.timemanager.studentmenu;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.orm.Hour;
import me.ryandw11.timemanager.orm.Student;
import me.ryandw11.timemanager.utils.Utils;

public class DeleteHours {
	private JFrame frame;
	
	Student stu;
	
	public DeleteHours(Student stu, int hourId, ViewStudent vs) {
		this.stu = stu;
		JFrame frame = new JFrame();
		frame.setTitle("Time Manager | Delete Hours");
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setSize(500, 230);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JLabel title = new JLabel("Delete", SwingConstants.CENTER);
		title.setFont(new Font("Delete", Font.PLAIN, 30));
		
		JButton deleteLocal = new JButton("Delete off of Student");
		JButton deleteGlobal = new JButton("Delete Globally");
		
		deleteLocal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				int n = JOptionPane.showOptionDialog(frame, "Are you sure you want to delete this time for the student?", "Delete Time", JOptionPane.YES_NO_OPTION,
					    JOptionPane.QUESTION_MESSAGE, null, null, null);
				if(n == 0) {
					stu.hourIDs.remove(String.valueOf(hourId));
					stu.totalHours = Utils.addUpHours(stu);
					vs.reload();
					Main.currentInstanceofMainScreen.updateStudentData();
					remove();
				}
				
			}
			
		});
		
		deleteGlobal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				int n = JOptionPane.showOptionDialog(frame, "Are you sure you want to delete this time for all students?", "Delete Time", JOptionPane.YES_NO_OPTION,
					    JOptionPane.QUESTION_MESSAGE, null, null, null);
				if(n == 0) {
					Hour nh = new Hour();
					nh.setUp(-1, -1, "null");
					Main.listOfHours.set(hourId, nh);
					for(Student su : Main.listofStudents) {
						su.totalHours = Utils.addUpHours(su);
					}
					vs.reload();
					Main.currentInstanceofMainScreen.updateStudentData();
					remove();
				}
			}
			
		});
		
		JPanel centerPnl = new JPanel();
		centerPnl.add(deleteLocal);
		centerPnl.add(deleteGlobal);
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				remove();
			}
			
		});
		
		frame.add(title, BorderLayout.NORTH);
		frame.add(centerPnl, BorderLayout.CENTER);
		frame.add(cancel, BorderLayout.SOUTH);
		
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
