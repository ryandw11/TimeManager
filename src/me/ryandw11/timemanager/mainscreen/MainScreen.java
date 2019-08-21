package me.ryandw11.timemanager.mainscreen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;
import javax.swing.border.Border;

import com.sun.glass.events.KeyEvent;

import me.ryandw11.jtml.JTML;
import me.ryandw11.timemanager.orm.Student;

public class MainScreen {
	
	public static JTML jtml;
	public JFrame frame;
	public List<Student> selectedStudents;
	public JMenuBar menubar;
	
	public MainScreen() {
		JFrame frame = new JFrame();
		selectedStudents = new ArrayList<>();
		
		JPanel rightButtons = new JPanel();
		JPanel leftButtons = new JPanel();
		JPanel pnl = new JPanel();
		
		frame.setSize(1200, 800);
		frame.setMinimumSize(new Dimension(800, 800));
		frame.setResizable(true);
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String is = classloader.getResource("students.html").toExternalForm();
		Map<String, Object> jsmap = new HashMap<>();
		jsmap.put("studentData", new StudentDataHandler(this));
		JTML jtml = new JTML(pnl, is, jsmap, new CallJs(), "callJs");
		this.jtml = jtml;
		
//		jtml.executeJavaScript("loadData()");
		
		makeRightButtons(rightButtons);
		
		frame.add(rightButtons, BorderLayout.EAST);
		frame.add(leftButtons, BorderLayout.WEST);
		frame.add(pnl, BorderLayout.CENTER);
		createMenu();
		frame.setJMenuBar(this.menubar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		this.frame = frame;
	}
	
	protected void makePanel() {
		
	}
	
	protected void makeRightButtons(JPanel pnl) {
		pnl.setLayout(new BorderLayout());
		pnl.add(makeNone(), BorderLayout.NORTH);
		pnl.add(makeOne(), BorderLayout.CENTER);
	}
	
	JPanel makeNone() {
		JPanel none = new JPanel();
		JButton student = new JButton("new Student");
		none.add(student);
		return none;
	}
	
	JPanel makeOne() {
		JPanel one = new JPanel();
		JButton edit = new JButton("Edit Student");
		JPanel pp = new JPanel();
		JButton addhour = new JButton("Add Time");
		JPanel btm = new JPanel();
		JButton delete = new JButton("Delete Student");
		one.setLayout(new BorderLayout());
		pp.add(addhour);
		btm.add(delete);
		one.add(edit, BorderLayout.NORTH);
		one.add(pp, BorderLayout.CENTER);
		one.add(btm, BorderLayout.SOUTH);
		return one;
	}
	
	protected void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu file = new JMenu("File");
		file.setMnemonic(KeyEvent.VK_F);
		JMenuItem save = new JMenuItem("Save...");
		save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		file.add(save);
		JMenuItem newFile = new JMenuItem("New Workspace");
		newFile.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		file.add(newFile);
		menuBar.add(file);
		
		JMenu edit = new JMenu("Edit");
		edit.setMnemonic(KeyEvent.VK_E);
		JMenuItem addStudent = new JMenuItem("Add Student");
		edit.add(addStudent);
		if(selectedStudents.size() == 1) {
			JMenuItem editStudent = new JMenuItem("Edit Student");
			edit.add(editStudent);
			JMenuItem addHours = new JMenuItem("Add Hours to Student");
			edit.add(addHours);
			JMenuItem deleteStudent = new JMenuItem("Delete Student");
			edit.add(deleteStudent);
		}
		else if(selectedStudents.size() > 1) {
			JMenuItem addHours = new JMenuItem("Add Hours to Students");
			edit.add(addHours);
			JMenuItem deleteStudent = new JMenuItem("Delete Students");
			edit.add(deleteStudent);
		}
		menuBar.add(edit);
		
		JMenu settings = new JMenu("Settings");
		settings.setMnemonic(KeyEvent.VK_S);
		menuBar.add(settings);
		
		JMenu inport = new JMenu("Import");
		inport.setMnemonic(KeyEvent.VK_I);
		JMenuItem importStudents = new JMenuItem("Import Students");
		inport.add(importStudents);
		JMenuItem importWorkspace = new JMenuItem("Import Workspace");
		inport.add(importWorkspace);
		menuBar.add(inport);
		
		JMenu export = new JMenu("Export");
		export.setMnemonic(KeyEvent.VK_X);
		if(selectedStudents.size() == 1) {
			JMenuItem exportStudent = new JMenuItem("Export Student");
			export.add(exportStudent);
		}
		else if(selectedStudents.size() > 1) {
			JMenuItem exportStudent = new JMenuItem("Export Selected Students");
			export.add(exportStudent);
		}else {
			JMenuItem exportStudent = new JMenuItem("Export All Students");
			export.add(exportStudent);
		}
		menuBar.add(export);
		
		JMenu print = new JMenu("Print");
		print.setMnemonic(KeyEvent.VK_P);
		if(selectedStudents.size() == 1) {
			JMenuItem printStudent = new JMenuItem("Print Student");
			print.add(printStudent);
		}
		else if(selectedStudents.size() > 1) {
			JMenuItem exportStudent = new JMenuItem("Print Selected Students");
			print.add(exportStudent);
		}else {
			JMenuItem exportStudent = new JMenuItem("Print Students");
			print.add(exportStudent);
		}
		menuBar.add(print);
		this.menubar = menuBar;
	}

}
