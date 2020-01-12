package me.ryandw11.timemanager.studentmenu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import me.ryandw11.jtml.JTML;
import me.ryandw11.timemanager.orm.Student;

/**
 * The screen that shows the student's hours
 * @author Ryandw11
 *
 */
public class ViewStudent {
	
	public static JTML jtml;
	public JFrame frame;
	public Student student;
	
	public ViewStudent(Student stu) {
		student = stu;
		JFrame frame = new JFrame();
		frame.setTitle("TimeManager | " + stu.name);
		
		JPanel pnl = new JPanel();
		
		frame.setSize(800, 600);
		frame.setMinimumSize(new Dimension(800, 600));
		frame.setResizable(false);
		frame.setBackground(Color.white);
		frame.setLocationRelativeTo(null);
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String is = classloader.getResource("hours.html").toExternalForm();
		Map<String, Object> jsmap = new HashMap<>();
		jsmap.put("hourData", new HourDataHandler(this));
		JTML jtml = new JTML(pnl, is, jsmap, new HourJS(), "callJs");
		//TODO remove below. Useless after the next update of JTML
		ViewStudent.jtml = jtml;
		JLabel lbl = new JLabel(stu.name + "'s Hours", SwingConstants.CENTER);
		lbl.setFont(new Font("Welcome!", Font.PLAIN, 30));
		
		JButton leave = new JButton("Close");
		leave.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				close();
			}
			
		});
		
		frame.add(lbl, BorderLayout.NORTH);
		frame.add(pnl, BorderLayout.CENTER);
		frame.add(leave, BorderLayout.SOUTH);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.setVisible(true);
		this.frame = frame;
	}
	
	public void reload() {
		jtml.executeJavaScript("loadData()");
	}
	
	public void close() {
		this.frame.dispose();
	}

}
