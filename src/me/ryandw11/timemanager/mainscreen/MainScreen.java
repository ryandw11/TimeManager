package me.ryandw11.timemanager.mainscreen;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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

import com.thizzer.jtouchbar.JTouchBar;
import com.thizzer.jtouchbar.item.TouchBarItem;
import com.thizzer.jtouchbar.item.view.TouchBarButton;
import com.thizzer.jtouchbar.item.view.TouchBarTextField;
import com.thizzer.jtouchbar.item.view.TouchBarView;
import com.thizzer.jtouchbar.item.view.action.TouchBarViewAction;

import me.ryandw11.jtml.JTML;
import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.classes.AddClass;
import me.ryandw11.timemanager.classes.RemoveClass;
import me.ryandw11.timemanager.mainscreen.menu.MenuManager;
import me.ryandw11.timemanager.mainscreen.menu.SortManager;
import me.ryandw11.timemanager.orm.Student;
import me.ryandw11.timemanager.studentmenu.AddHours;
import me.ryandw11.timemanager.studentmenu.AddStudent;
import me.ryandw11.timemanager.studentmenu.EditStudent;
import me.ryandw11.timemanager.studentmenu.ViewStudent;

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
	private SortManager sortMgr;
	
	public MainScreen() {
		JFrame frame = new JFrame();
		selectedStudents = new ArrayList<>();
		frame.setTitle("TimeManager | " + Main.currentWorkspace.name + " Workspace");
		
		JPanel rightButtons = new JPanel();
		JPanel leftButtons = new JPanel();
		JPanel pnl = new JPanel();
		
		frame.setSize(1200, 800);
		frame.setMinimumSize(new Dimension(1200, 800));
		frame.setResizable(true);
		frame.setBackground(Color.white);
		
		JTouchBar jTouchBar = new JTouchBar();
		jTouchBar.setCustomizationIdentifier("MySwingJavaTouchBar");
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String is = classloader.getResource("students.html").toExternalForm();
		Map<String, Object> jsmap = new HashMap<>();
		jsmap.put("studentData", new StudentDataHandler(this));
		JTML jtml = new JTML(pnl, is, jsmap, new CallJs(), "callJs");
		//TODO remove below. Useless after the next update of JTML
		MainScreen.jtml = jtml;
		
		makeRightButtons(rightButtons);
		SortManager mgr = new SortManager(MainScreen.jtml);
		leftButtons.add(mgr);
		this.sortMgr = mgr;
		
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
	
	public void updateSort() {
		this.sortMgr.updateSort();
	}
	
	public void updateStudentData() {
		this.selectedStudents = new ArrayList<>();
		this.jtml.executeJavaScript("loadData()");
	}
	
	public void close() {
		this.frame.dispose();
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
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				AddClass ac = new AddClass();
				ac.show();
			}
			
		});
		JButton rmv = new JButton("Remove Class");
		rmv.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				RemoveClass rvc = new RemoveClass();
				rvc.show();
			}
			
		});
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
		student.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				AddStudent as = new AddStudent();
				updateSelect();
				as.show();
			}
			
		});
		none.add(student, BorderLayout.SOUTH);
		return none;
	}
	
	JPanel makeOne() {
		JPanel one = new JPanel();
//		one.setBorder(tt);
		JButton edit = new JButton("Edit Student");
		edit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				EditStudent es = new EditStudent(selectedStudents.get(0));
				selectedStudents.clear();
				updateSelect();
				es.show();
				
			}
			
		});
		JPanel pp = new JPanel();
		JButton addhour = new JButton("Add Hours");
		addhour.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				AddHours ah = new AddHours(selectedStudents.get(0));
				selectedStudents.clear();
				updateSelect();
				ah.show();
			}
			
		});
		JButton delete = new JButton("Delete Student");
		JButton viewHours = new JButton("View Hours");
		viewHours.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent ae) {
				ViewStudent vs = new ViewStudent(selectedStudents.get(0));
			}
			
		});
		MainScreen inst = this;
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int n = JOptionPane.showOptionDialog(frame, "Are you sure you want to delete this student?", "Delete Student", JOptionPane.YES_NO_OPTION,
					    JOptionPane.QUESTION_MESSAGE, null, null, null);
				if(n == 0) {
					Main.listofStudents.remove(inst.selectedStudents.get(0));
					inst.selectedStudents.remove(0);
					inst.updateSelect();
					inst.updateStudentData();
				}
				
			}
			
		});
		one.setLayout(new BorderLayout());
//		JPanel edp = new JPanel();
//		edp.add(edit);
//		pp.add(edp);
		pp.add(addhour);
		pp.add(delete);
		JPanel pnl = new JPanel();
		pnl.add(viewHours);
		one.add(edit, BorderLayout.NORTH);
		one.add(pp, BorderLayout.CENTER);
		one.add(pnl, BorderLayout.SOUTH);
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
		MainScreen inst = this;
		delete.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent evt) {
				int n = JOptionPane.showOptionDialog(frame, "Are you sure you want to delete the selected students?", "Delete Students", JOptionPane.YES_NO_OPTION,
					    JOptionPane.QUESTION_MESSAGE, null, null, null);
				if(n == 0) {
					for(Student stu : inst.selectedStudents) {
						Main.listofStudents.remove(stu);
					}
					inst.selectedStudents = new ArrayList<>();
					inst.updateSelect();
					inst.updateStudentData();
				}
				
			}
			
		});
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
