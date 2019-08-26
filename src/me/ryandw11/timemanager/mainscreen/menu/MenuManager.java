package me.ryandw11.timemanager.mainscreen.menu;

import java.awt.event.ActionEvent;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;

import me.ryandw11.timemanager.mainscreen.MainScreen;

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
		
		export.removeAll();
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
		
		print.removeAll();
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
	}
	
	
	
	protected void createMenu() {
		file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		JMenuItem save = new JMenuItem("Save...");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		file.add(save);
		JMenuItem newFile = new JMenuItem("New Workspace");
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		file.add(newFile);
		add(file);
		
		edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		JMenuItem addStudent = new JMenuItem("Add Student");
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
		add(print);
	}

}
