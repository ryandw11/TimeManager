package me.ryandw11.timemanager.mainscreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
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
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.apache.poi.xssf.usermodel.TextAlign;
import org.apache.xmlbeans.impl.jam.JField;

import me.ryandw11.jtml.JTML;
import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.mainscreen.menu.MenuManager;
import me.ryandw11.timemanager.mainscreen.menu.SortManager;
import me.ryandw11.timemanager.orm.Student;

/**
 * The mainscreen of the program.
 * @author Ryandw11
 *
 */
public class MainScreen {
	
	public static JTML jtml;
	public JFrame frame;
	public List<Student> selectedStudents;
	public MenuManager menubar;
	private JPanel rightButtons;
	
	public MainScreen() {
		JFrame frame = new JFrame();
		selectedStudents = new ArrayList<>();
//		frame.setTitle("TimeManager | " + Main.currentWorkspace.name + " Workspace");
		
		JPanel rightButtons = new JPanel();
		JPanel leftButtons = new JPanel();
		JPanel pnl = new JPanel();
		
		frame.setSize(1200, 800);
		frame.setMinimumSize(new Dimension(1200, 800));
		frame.setResizable(true);
		frame.setBackground(Color.white);
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String is = classloader.getResource("students.html").toExternalForm();
		Map<String, Object> jsmap = new HashMap<>();
		jsmap.put("studentData", new StudentDataHandler(this));
		JTML jtml = new JTML(pnl, is, jsmap, new CallJs(), "callJs");
		//TODO remove below. Useless after the next update of JTML
		MainScreen.jtml = jtml;
		
		makeRightButtons(rightButtons);
//		makeSort(leftButtons);
		leftButtons.add(new SortManager(MainScreen.jtml));
		
		frame.add(rightButtons, BorderLayout.EAST);
		frame.add(leftButtons, BorderLayout.WEST);
		frame.add(pnl, BorderLayout.CENTER);
		MenuManager mu = new MenuManager(this);
		this.menubar = mu;
		frame.setJMenuBar(this.menubar);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.setVisible(true);
		this.frame = frame;
		this.rightButtons = rightButtons;
	}
	
	protected void makeSort(JPanel pnl) {
//		Border bb = BorderFactory.createLineBorder(Color.black);
//		TitledBorder tt = BorderFactory.createTitledBorder(bb, "Sort Students");
//		JPanel jp = new JPanel();
//		jp.setLayout(new BorderLayout());
//		jp.setBorder(tt);
//		
//		JPanel clasz = new JPanel();
//		JLabel byClass = new JLabel("By Class:");
//		List<String> choices = new ArrayList<>();	//TODO Get classes
//		choices.add("None");
//		choices.add("Example Two");
//		JComboBox<String> cb = new JComboBox<String>(choices.toArray(new String[0]));
//		clasz.add(byClass);
//		clasz.add(cb);
//		jp.add(clasz, BorderLayout.NORTH);
//		
//		JPanel grade = new JPanel();
//		JLabel byGrade = new JLabel("By Grade:");
//		SpinnerModel sm = new SpinnerNumberModel(1, 1, 12, 1);
//		JSpinner js = new JSpinner(sm);
//		grade.add(byGrade);
//		grade.add(js);
//		jp.add(grade, BorderLayout.CENTER);
//		
//		JPanel name = new JPanel();
//		JLabel byName = new JLabel("By Name:");
//		JTextField ji = new JTextField();
//		ji.setColumns(10);
//		ji.getDocument().addDocumentListener(new DocumentListener() {
//
//			@Override
//			public void changedUpdate(DocumentEvent arg0) {
//				// TODO Auto-generated method stub
//				
//			}
//
//			@Override
//			public void insertUpdate(DocumentEvent e) {
//				System.out.println("sort('" + ji.getText() + "', null, null, null, null)");
//				jtml.executeJavaScript("sort('" + ji.getText() + "', null, null, null, null)");
//				
//			}
//
//			@Override
//			public void removeUpdate(DocumentEvent arg0) {
//				jtml.executeJavaScript("sort('" + ji.getText() + "', null, null, null, null)");
//			}
//			
//		});
//		name.add(byName);
//		name.add(ji);
//		jp.add(name, BorderLayout.SOUTH);
//		
//		pnl.add(jp);
		
	}
	
	public void updateSelect() {
		if(selectedStudents.size() <= 1) {
			this.rightButtons.remove(1);
			this.rightButtons.add(makeOne(), BorderLayout.CENTER, 1);
		
			this.rightButtons.updateUI();
		}
		else {
			this.rightButtons.remove(1);
			this.rightButtons.add(makeTwo(), BorderLayout.CENTER, 1);
		
			this.rightButtons.updateUI();
		}
	}
	
	protected void makeRightButtons(JPanel pnl) {
		pnl.removeAll();
		GridLayout grid = new GridLayout(0,1);
		pnl.setLayout(grid);
		pnl.add(makeNone(), BorderLayout.NORTH);
		pnl.add(makeOne(), BorderLayout.CENTER);
		pnl.add(addClass(), BorderLayout.SOUTH);
	}
	
	JPanel addClass() {
		Border bb = BorderFactory.createLineBorder(Color.black);
		TitledBorder tt = BorderFactory.createTitledBorder(bb, "Manage Classes");
		JPanel jpn = new JPanel();
		jpn.setBorder(tt);
		JButton btn = new JButton("Add Class");
		JButton rmv = new JButton("Remove Class");
		jpn.add(btn);
		jpn.add(rmv);
		return jpn;
	}
	
	JPanel makeNone() {
		Border bb = BorderFactory.createLineBorder(Color.black);
		TitledBorder tt = BorderFactory.createTitledBorder(bb, "Add Student");
		JPanel none = new JPanel();
		none.setLayout(new BorderLayout());
		none.setBorder(tt);
		JButton student = new JButton("Add Student");
		none.add(student, BorderLayout.SOUTH);
		return none;
	}
	
	JPanel makeOne() {
		JPanel one = new JPanel();
//		one.setBorder(tt);
		JButton edit = new JButton("Edit Student");
		JPanel pp = new JPanel();
		JButton addhour = new JButton("Add Hours");
		JButton delete = new JButton("Delete Student");
		one.setLayout(new BorderLayout());
//		JPanel edp = new JPanel();
//		edp.add(edit);
//		pp.add(edp);
		pp.add(addhour);
		pp.add(delete);
		one.add(edit, BorderLayout.NORTH);
		one.add(pp, BorderLayout.CENTER);
		Border bb = BorderFactory.createLineBorder(Color.black);
		TitledBorder tt = BorderFactory.createTitledBorder(bb, "Manage Student");
		JPanel finalP = new JPanel();
		finalP.setBorder(tt);
		finalP.setLayout(new BorderLayout());
		finalP.add(one, BorderLayout.CENTER);
		finalP.setAlignmentY(SwingConstants.CENTER);
		if(selectedStudents.size() < 1) {
			finalP.removeAll();
			JLabel lbl = new JLabel("Select a student for options.");
			lbl.setHorizontalAlignment(SwingConstants.CENTER);
			finalP.add(lbl);
		}
		return finalP;
	}
	
	JPanel makeTwo() {
		JPanel one = new JPanel();
//		one.setBorder(tt);
		JPanel pp = new JPanel();
		JButton addhour = new JButton("Add Hours");
		JButton delete = new JButton("Delete Students");
		one.setLayout(new BorderLayout());
//		JPanel edp = new JPanel();
//		edp.add(edit);
//		pp.add(edp);
//		pp.add(addhour);
		pp.add(delete);
		one.add(addhour, BorderLayout.NORTH);
		one.add(pp, BorderLayout.CENTER);
		Border bb = BorderFactory.createLineBorder(Color.black);
		TitledBorder tt = BorderFactory.createTitledBorder(bb, "Manage Students");
		JPanel finalP = new JPanel();
		finalP.setBorder(tt);
		finalP.setLayout(new BorderLayout());
		finalP.add(one, BorderLayout.CENTER);
		finalP.setAlignmentY(SwingConstants.CENTER);
		return finalP;
	}
	
	

}
