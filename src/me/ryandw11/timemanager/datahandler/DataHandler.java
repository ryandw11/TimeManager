package me.ryandw11.timemanager.datahandler;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import me.ryandw11.rsql.RSQL;
import me.ryandw11.rsql.properties.RProperties;
import me.ryandw11.rsql.properties.subproperties.JSONProperties;
import me.ryandw11.rsql.properties.subproperties.SQLProperties;
import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.orm.Hour;
import me.ryandw11.timemanager.orm.Student;
import me.ryandw11.timemanager.orm.Workspace;

public class DataHandler {
	public static void save() {
		if(Main.listofStudents.size() == 0) return;
		RProperties rp = new SQLProperties().setName("data" + File.separator + Main.currentWorkspace.name);
		RSQL rsql = new RSQL(rp);
		List<Object> output = new ArrayList<>();
		for(Student s : Main.listofStudents) {
			output.add(s);
		}
		rsql.process(output);
		
		if(Main.listOfHours != null && Main.listOfHours.size() != 0) {
			List<Object> hOutput = new ArrayList<>();
			for(Hour h : Main.listOfHours) {
				hOutput.add(h);
			}
			rsql.process(hOutput);
		}
		rsql.process(output);
		
		
		RSQL workspacesR = new RSQL(new JSONProperties().setFile("data" + File.separator + "workspaces.json"));
		List<Object> o = workspacesR.get(Workspace.class);
		List<Object> outputs = new ArrayList<>();
		o.forEach(et -> {
			if(!((Workspace) et).name.equals(Main.currentWorkspace.name))
				outputs.add((Workspace) et);
		});
		outputs.add(Main.currentWorkspace);
		
		workspacesR.process(outputs);
		
		JOptionPane.showMessageDialog(null, "Successfully saved the " + Main.currentWorkspace.name + " worksapce!", "Saved Workspace", JOptionPane.INFORMATION_MESSAGE);
	}

}
