package me.ryandw11.timemanager.mainscreen.menu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import me.ryandw11.rsql.RSQL;
import me.ryandw11.rsql.properties.RProperties;
import me.ryandw11.rsql.properties.subproperties.ExcelProperties;
import me.ryandw11.rsql.properties.subproperties.SQLProperties;
import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.datahandler.DataHandler;
import me.ryandw11.timemanager.mainscreen.MainScreen;
import me.ryandw11.timemanager.orm.ExportStudent;
import me.ryandw11.timemanager.orm.Hour;
import me.ryandw11.timemanager.orm.Student;
import me.ryandw11.timemanager.studentmenu.AddHours;
import me.ryandw11.timemanager.studentmenu.AddHoursStudents;
import me.ryandw11.timemanager.studentmenu.AddStudent;
import me.ryandw11.timemanager.studentmenu.EditStudent;

public class MenuManager extends JMenuBar{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private MainScreen ms;
	JMenu file, edit, settings, inport, export, print;
	
	public MenuManager(MainScreen ms) {
		this.ms = ms;
		createMenu();
	}
	
	public void update() {
		edit.removeAll();
		JMenuItem addStudent = new JMenuItem("Add Student");
		addStudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				AddStudent as = new AddStudent();
				ms.updateSelect();
				as.show();	
			}
			
		});
		edit.add(addStudent);
		if(ms.selectedStudents.size() == 1) {
			JMenuItem editStudent = new JMenuItem("Edit Student");
			editStudent.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent arg0) {
					EditStudent es = new EditStudent(ms.selectedStudents.get(0));
					ms.selectedStudents.clear();
					ms.updateSelect();
					es.show();
					
				}
				
			});
			edit.add(editStudent);
			JMenuItem addHours = new JMenuItem("Add Hours to Student");
			addHours.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AddHours ah = new AddHours(ms.selectedStudents.get(0));
					ms.selectedStudents.clear();
					ms.updateSelect();
					ah.show();
				}
				
			});
			edit.add(addHours);
			JMenuItem deleteStudent = new JMenuItem("Delete Student");
			deleteStudent.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int n = JOptionPane.showOptionDialog(ms.frame, "Are you sure you want to delete this student?", "Delete Student", JOptionPane.YES_NO_OPTION,
						    JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(n == 0) {
						Main.listofStudents.remove(ms.selectedStudents.get(0));
						ms.selectedStudents.remove(0);
						ms.updateSelect();
						ms.updateStudentData();
					}
				}
				
			});
			edit.add(deleteStudent);
		}
		else if(ms.selectedStudents.size() > 1) {
			JMenuItem addHours = new JMenuItem("Add Hours to Students");
			addHours.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					AddHoursStudents ahs = new AddHoursStudents(ms.selectedStudents);
					ahs.show();
				}
				
			});
			edit.add(addHours);
			JMenuItem deleteStudent = new JMenuItem("Delete Students");
			deleteStudent.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					int n = JOptionPane.showOptionDialog(ms.frame, "Are you sure you want to delete the selected students?", "Delete Students", JOptionPane.YES_NO_OPTION,
						    JOptionPane.QUESTION_MESSAGE, null, null, null);
					if(n == 0) {
						for(Student stu : ms.selectedStudents) {
							Main.listofStudents.remove(stu);
						}
						ms.selectedStudents = new ArrayList<>();
						ms.updateSelect();
						ms.updateStudentData();
					}
				}
				
			});
			edit.add(deleteStudent);
		}
		
		export.removeAll();
		if(ms.selectedStudents.size() == 1) {
			JMenuItem exportStudent = new JMenuItem("Export Student");
			exportStudent.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					RSQL export = new RSQL(new ExcelProperties().setFile("exports" + File.separatorChar + "Test"));
					List<Object> es = new ArrayList<>();
					ExportStudent exs = new ExportStudent();
					exs.setUp(ms.selectedStudents.get(0));
					es.add(exs);
					export.process(es);
					List<Object> hours = new ArrayList<>();
					for(Hour h : Main.listOfHours) {
						hours.add(h);
					}
					export.process(hours);
				}
				
			});
			export.add(exportStudent);
		}
		else if(ms.selectedStudents.size() > 1) {
			JMenuItem exportStudent = new JMenuItem("Export Selected Students");
			export.add(exportStudent);
		}else {
			JMenuItem exportStudent = new JMenuItem("Export All Students");
			export.add(exportStudent);
		}
		
		print.removeAll();
//		if(ms.selectedStudents.size() == 1) {
//			JMenuItem printStudent = new JMenuItem("Print Student");
//			print.add(printStudent);
//		}
//		else if(ms.selectedStudents.size() > 1) {
//			JMenuItem exportStudent = new JMenuItem("Print Selected Students");
//			print.add(exportStudent);
//		}else {
//			JMenuItem exportStudent = new JMenuItem("Print Students");
//			print.add(exportStudent);
//		}
	}
	
	
	
	protected void createMenu() {
		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		JMenuItem save = new JMenuItem("Save...");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		save.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				DataHandler.save();
			}
			
		});
		file.add(save);
		JMenuItem newFile = new JMenuItem("New Workspace");
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		file.add(newFile);
		add(file);
		
		edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		JMenuItem addStudent = new JMenuItem("Add Student");
		addStudent.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				AddStudent as = new AddStudent();
				as.show();
				
			}
			
		});
		edit.add(addStudent);
		if(ms.selectedStudents.size() == 1) {
			JMenuItem editStudent = new JMenuItem("Edit Student");
			edit.add(editStudent);
			JMenuItem addHours = new JMenuItem("Add Hours to Student");
			edit.add(addHours);
			JMenuItem deleteStudent = new JMenuItem("Delete Student");
			edit.add(deleteStudent);
		}
		else if(ms.selectedStudents.size() > 1) {
			JMenuItem addHours = new JMenuItem("Add Hours to Students");
			edit.add(addHours);
			JMenuItem deleteStudent = new JMenuItem("Delete Students");
			edit.add(deleteStudent);
		}
		add(edit);
		
		settings = new JMenu("Settings");
		settings.setMnemonic(KeyEvent.VK_S);
		add(settings);
		
		inport = new JMenu("Import");
		inport.setMnemonic(KeyEvent.VK_I);
		JMenuItem importStudents = new JMenuItem("Import Students");
		inport.add(importStudents);
		JMenuItem importWorkspace = new JMenuItem("Import Workspace");
		inport.add(importWorkspace);
		add(inport);
		
		export = new JMenu("Export");
		export.setMnemonic(KeyEvent.VK_X);
		if(ms.selectedStudents.size() == 1) {
			JMenuItem exportStudent = new JMenuItem("Export Student");
			export.add(exportStudent);
		}
		else if(ms.selectedStudents.size() > 1) {
			JMenuItem exportStudent = new JMenuItem("Export Selected Students");
			export.add(exportStudent);
		}else {
			JMenuItem exportStudent = new JMenuItem("Export All Students");
			export.add(exportStudent);
		}
		add(export);
		
		print = new JMenu("Print");
		print.setMnemonic(KeyEvent.VK_P);
		if(ms.selectedStudents.size() == 1) {
			JMenuItem printStudent = new JMenuItem("Print Student");
			print.add(printStudent);
		}
		else if(ms.selectedStudents.size() > 1) {
			JMenuItem exportStudent = new JMenuItem("Print Selected Students");
			print.add(exportStudent);
		}else {
			JMenuItem exportStudent = new JMenuItem("Print Students");
			print.add(exportStudent);
		}
//		add(print);
	}

}
