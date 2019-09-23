package me.ryandw11.timemanager.mainscreen.menu;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import me.ryandw11.jtml.JTML;
import me.ryandw11.rsql.RSQL;
import me.ryandw11.rsql.properties.RProperties;
import me.ryandw11.rsql.properties.subproperties.JSONProperties;
import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.orm.Workspace;

public class SortManager extends JPanel {
	
	private static final long serialVersionUID = 537693680453630212L;
	
	private JTML jtml;
	private JComboBox<String> cb;
	private JSpinner grade;
	private JTextField jt;
	private JSpinner h1;
	private JSpinner h2;
	
	public SortManager(JTML jtml) {
		Border bb = BorderFactory.createLineBorder(Color.black);
		TitledBorder tt = BorderFactory.createTitledBorder(bb, "Sort Students");
		this.setLayout(new BorderLayout());
		this.setBorder(tt);
		
		JPanel name = new JPanel();
		JLabel byName = new JLabel("By Name:");
		JTextField ji = new JTextField();
		ji.setColumns(10);
		ji.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent arg0) {}

			@Override
			public void insertUpdate(DocumentEvent e) {
				proccessSort();
				
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				proccessSort();
			}
			
		});
		name.add(byName);
		name.add(ji);
		this.add(name, BorderLayout.NORTH);
		this.jt = ji;
		
		JPanel clasz = new JPanel();
		JLabel byClass = new JLabel("By Class:");
		List<String> choices = new ArrayList<>();	//TODO Get classes
		choices.addAll(Main.currentWorkspace.classes);
		boolean isDisabled = false;
		if(choices.size() < 1) {
			choices.add("None");
			isDisabled = true;
		}
		JComboBox<String> cb = new JComboBox<String>(choices.toArray(new String[0]));
		cb.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent evt) {
				proccessSort();
				
			}
			
		});
		if(isDisabled) {
			cb.setEnabled(false);
		}
		clasz.add(byClass);
		clasz.add(cb);
		this.add(clasz, BorderLayout.CENTER);
		this.cb = cb;
		
		
		JPanel bottom = new JPanel();
		bottom.setLayout(new BorderLayout());
		
		JPanel hour = new JPanel();
		hour.setLayout(new BorderLayout());
		JLabel hlb = new JLabel("By Hours:");
		hour.add(hlb, BorderLayout.NORTH);
		JPanel input = new JPanel();
		SpinnerModel hourstd = new SpinnerNumberModel(0.0, 0.0, 999.0, 1.0);
		JSpinner hourspin1 = new JSpinner(hourstd);
		hourspin1.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				proccessSort();
			}
		});
		this.h1 = hourspin1;
		input.add(hourspin1);
		input.add(new JLabel("< hours <"));
		SpinnerModel hourstd2 = new SpinnerNumberModel(0.0, 0.0, 999.0, 1.0);
		JSpinner hourspin2 = new JSpinner(hourstd2);
		hourspin2.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				proccessSort();
			}
			
		});
		this.h2 = hourspin2;
		input.add(hourspin2);
		hour.add(input, BorderLayout.SOUTH);
		bottom.add(hour, BorderLayout.CENTER);
		
		JPanel grade = new JPanel();
		JLabel byGrade = new JLabel("By Grade:");
		SpinnerModel sm = new SpinnerNumberModel(0, 0, 12, 1);
		JSpinner js = new JSpinner(sm);
		js.addChangeListener(new ChangeListener() {

			@Override
			public void stateChanged(ChangeEvent e) {
				int value = (int) Math.floor(Double.parseDouble(js.getValue().toString()));
				if(value > 12 || value < 0) value = 0;
				js.setValue(value);
				proccessSort();
				
			}
			
		});
		grade.add(byGrade);
		grade.add(js);
		bottom.add(grade, BorderLayout.NORTH);
		this.add(bottom, BorderLayout.SOUTH);
		this.grade = js;
		
		this.jtml = jtml;
	}
	
	public void proccessSort() {
		String param1 = "'" + jt.getText() + "'";
		String param2 = "'" + cb.getItemAt(cb.getSelectedIndex()) + "'";
		String param3 = "'" + grade.getValue().toString() + "'";
		String param4 = "'" + h1.getValue().toString() + "'";
		String param5 = "'" + h2.getValue().toString() + "'";
		
//		if(param1.replace("'", "").equals("")) param1 = "null";
		if(param2.replace("'", "").equals("None") || param2.replace("'", "").equals("Select A Class")) param2 = "null";
		if(param3.replace("'", "").equals("0")) param3 = "null";
		if(param5.replace("'", "").equals("0.0")) param5 = "null";
		Main.currentInstanceofMainScreen.selectedStudents = new ArrayList<>();
		Main.currentInstanceofMainScreen.updateSelect();
		jtml.executeJavaScript("sort(" + param1 + ", " + param2 + ", " + param3 + ", " + param4 + ", " + param5 + ")");
	}

}
