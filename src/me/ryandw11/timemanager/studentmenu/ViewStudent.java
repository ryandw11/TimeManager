package me.ryandw11.timemanager.studentmenu;

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

/**
 * The screen that shows the student's hours
 * @author Ryandw11
 *
 */
public class ViewStudent {
	
	public static JTML jtml;
	public JFrame frame;
	
	public ViewStudent(Student stu) {
		JFrame frame = new JFrame();
		frame.setTitle("TimeManager | " + stu.name);
		
		JPanel pnl = new JPanel();
		
		frame.setSize(1400, 800);
		frame.setMinimumSize(new Dimension(800, 800));
		frame.setResizable(true);
		frame.setBackground(Color.white);
		
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		String is = classloader.getResource("hours.html").toExternalForm();
		Map<String, Object> jsmap = new HashMap<>();
		jsmap.put("hourData", new HourDataHandler(this));
		JTML jtml = new JTML(pnl, is, jsmap, new HourJS(), "callJs");
		//TODO remove below. Useless after the next update of JTML
		ViewStudent.jtml = jtml;
		System.out.println(pnl.getMaximumSize() + " " + pnl.getSize());
		frame.setLayout(new GridLayout(1, 1));
		frame.add(pnl);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		frame.setVisible(true);
		this.frame = frame;
	}
	
	public void close() {
		this.frame.dispose();
	}

}
