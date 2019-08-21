package me.ryandw11.timemanager.startup;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import me.ryandw11.rsql.RSQL;
import me.ryandw11.rsql.properties.RProperties;
import me.ryandw11.rsql.properties.subproperties.JSONProperties;
import me.ryandw11.timemanager.orm.Workspace;
import me.ryandw11.timemanager.orm.Workspaces;

/**
 * The GUI for when the user opens the program for the first time.
 * @author Ryandw11
 *
 */
public class FirstTime {
	
	private JFrame frame;
	private boolean valid;
	
	public FirstTime() {
		JFrame frame = new JFrame("Time Manager | Welcome!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 200);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Welcome!");
		title.setFont(new Font("Welcome!", Font.PLAIN, 30));
		
		JLabel text = new JLabel("Welcome to Time Manager! To begin you must create a work space. "
				+ "Please enter in the name of the new workspace.");
		
		JTextField jfield = new JTextField();
		jfield.setName("WorkSpace Name...");
		jfield.setColumns(20);
		jfield.setText("Name...");
		jfield.addFocusListener(new FocusListener() {
		    @Override
		    public void focusGained(FocusEvent e) {
		        if (jfield.getText().equals("Name...")) {
		        	jfield.setText("");
		        	jfield.setForeground(Color.BLACK);
		        }
		    }
		    @Override
		    public void focusLost(FocusEvent e) {
		        if (jfield.getText().isEmpty()) {
		        	jfield.setForeground(Color.GRAY);
		        	jfield.setText("Name...");
		        }
		    }
		    });
		JLabel error = new JLabel("", SwingConstants.CENTER);
		JButton jb = new JButton("Create Workspace");
		jb.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		jfield.getDocument().addDocumentListener(new DocumentListener() {

			@Override
			public void changedUpdate(DocumentEvent e) {}

			@Override
			public void insertUpdate(DocumentEvent e) {
				if(jfield.getText().equals("Name...")) return;
				if(jfield.getText().contains(" ")) {
					jfield.setBackground(Color.red);
					jfield.setForeground(Color.white);
					error.setText("Invalid name. The name cannot contain spaces!");
					valid = false;
				}
				else if(jfield.getText().contains(".")) {
					jfield.setBackground(Color.red);
					jfield.setForeground(Color.white);
					error.setText("Invalid name. The name cannot contain dots (.)!");
					valid = false;
				}
				else if(jfield.getText().contains("/") || jfield.getText().contains("\\")) {
					jfield.setBackground(Color.red);
					jfield.setForeground(Color.white);
					error.setText("Invalid name. The name cannot contain slashes (/ or \\)");
					valid = false;
				}
				else {
					error.setText("");
					jfield.setBackground(Color.WHITE);
					jfield.setForeground(Color.black);
					valid = true;
				}
				
				if(valid == false) {
					jb.setEnabled(false);
				}
				if(!jb.isEnabled() && valid) {
					jb.setEnabled(true);
				}
				
			}

			@Override
			public void removeUpdate(DocumentEvent e) {
				if(jfield.getText().equals("Name...")) return;
				if(jfield.getText().contains(" ")) {
					jfield.setBackground(Color.red);
					jfield.setForeground(Color.white);
					error.setText("Invalid name. The name cannot contain spaces!");
					valid = false;
				}
				else if(jfield.getText().contains(".")) {
					jfield.setBackground(Color.red);
					jfield.setForeground(Color.white);
					error.setText("Invalid name. The name cannot contain dots (.)!");
					valid = false;
				}
				else if(jfield.getText().contains("/") || jfield.getText().contains("\\")) {
					jfield.setBackground(Color.red);
					jfield.setForeground(Color.white);
					error.setText("Invalid name. The name cannot contain slashes (/ or \\)");
					valid = false;
				}
				else {
					error.setText("");
					jfield.setBackground(Color.WHITE);
					jfield.setForeground(Color.black);
					valid = true;
				}
				
				if(valid == false) {
					jb.setEnabled(false);
				}
				if(!jb.isEnabled() && valid) {
					jb.setEnabled(true);
				}
			}
			
		});
		
		
		
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(!valid) return;
				
				File f = new File("data");
				if(!f.exists()) f.mkdir();
				
				RProperties rp = new JSONProperties().setFile("data" + File.separator + "workspaces.json");
				RSQL rsql = new RSQL(rp);
				String name = jfield.getText();
				Workspace wp = new Workspace();
				wp.name = name;
				List<String> classes = new ArrayList<String>();
//				classes.add("none");
				wp.classes = classes;
				Workspaces wps = new Workspaces();
				wps.lastWorkspace = name;
				List<String> workspaces = new ArrayList<String>();
				workspaces.add(name);
				wps.workspaces = workspaces;
				
				rsql.process(Arrays.asList(wp));
				rsql.process(Arrays.asList(wps));
				
				close();
				
			}
			
		});
		
		JPanel erbtn = new JPanel();
		erbtn.setLayout(new BorderLayout());
		JPanel btnpnl = new JPanel();
		btnpnl.add(jb);
		erbtn.add(btnpnl, BorderLayout.CENTER);
		erbtn.add(error, BorderLayout.NORTH);
		
		panel.add(title);
		panel.add(text);
		panel.add(jfield);
		frame.add(erbtn, BorderLayout.SOUTH);
		frame.add(panel, BorderLayout.CENTER);
		
		this.frame = frame;
	}
	
	public void show() {
		this.frame.setVisible(true);
	}
	
	public void hide() {
		this.frame.setVisible(false);
	}
	
	public void close() {
		this.frame.dispose();
	}
	
	public boolean isValid() {
		return valid;
	}

}
