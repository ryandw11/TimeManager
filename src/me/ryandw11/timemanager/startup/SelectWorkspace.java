package me.ryandw11.timemanager.startup;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import me.ryandw11.rsql.RSQL;
import me.ryandw11.rsql.properties.RProperties;
import me.ryandw11.rsql.properties.subproperties.JSONProperties;
import me.ryandw11.timemanager.orm.Workspace;
import me.ryandw11.timemanager.orm.Workspaces;

public class SelectWorkspace {
	
	private JFrame frame;
	
	public SelectWorkspace() {
		JFrame frame = new JFrame("Time Manager | Welcome Back!");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(800, 200);
		frame.setResizable(false);
		frame.setLocationRelativeTo(null);
		
		
		JPanel panel = new JPanel();
		
		JLabel title = new JLabel("Welcome Back!", SwingConstants.CENTER);
		title.setFont(new Font("Welcome Back!", Font.PLAIN, 30));
		
//		JLabel text = new JLabel("Welcome Back to Time Manager! Please select the workspace you want to open.", SwingConstants.CENTER);
		JLabel text = new JLabel("<html><body><form>\r\n" + 
				"  First name:<br>\r\n" + 
				"  <input type=\"text\" name=\"firstname\"><br>\r\n" + 
				"  Last name:<br>\r\n" + 
				"  <input type=\"text\" name=\"lastname\">\r\n" + 
				"</form></body></html>", SwingConstants.CENTER);

		
		List<String> choices = calculateChoices();
		
		JComboBox<String> cb = new JComboBox<String>(choices.toArray(new String[0]));
		cb.setPreferredSize(new Dimension(150, 25));
		JButton jb = new JButton("Load Workspace");
		jb.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				File f = new File("data");
				if(!f.exists()) f.mkdir();
				
				RProperties rp = new JSONProperties().setFile("data" + File.separator + "workspaces.json");
				RSQL rsql = new RSQL(rp);
				String name = "yeet";
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
				
				frame.dispose();
				
			}
			
		});
		
		JPanel erbtn = new JPanel();
		erbtn.setLayout(new BorderLayout());
		JPanel btnpnl = new JPanel();
		btnpnl.add(jb);
		erbtn.add(btnpnl, BorderLayout.CENTER);
		
		panel.setLayout(new BorderLayout());
		JPanel cbp = new JPanel();
		cbp.add(cb);
		
		panel.add(title, BorderLayout.NORTH);
		panel.add(text, BorderLayout.CENTER);
		panel.add(cbp, BorderLayout.SOUTH);
		frame.add(erbtn, BorderLayout.SOUTH);
		frame.add(panel, BorderLayout.NORTH);
		
		this.frame = frame;
	}
	
	private List<String> calculateChoices() {
		RProperties rp = new JSONProperties().setFile("data" + File.separator + "workspaces.json");
		RSQL rsql = new RSQL(rp);
		List<Object> wp = rsql.get(Workspaces.class);
		Workspaces wps = (Workspaces) wp.get(0);
		List<String> output = new ArrayList<String>();
		output.add(wps.lastWorkspace);
		for(String s : wps.workspaces) {
			if(s.equals(wps.lastWorkspace)) continue;
			else output.add(s);
		}
		
		return output;
	}
	
	public void show() {
		this.frame.setVisible(true);
	}

}
