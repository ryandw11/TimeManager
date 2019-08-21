package me.ryandw11.timemanager;

import java.io.File;
import java.util.List;

import me.ryandw11.rsql.RSQL;
import me.ryandw11.rsql.properties.RProperties;
import me.ryandw11.rsql.properties.subproperties.JSONProperties;
import me.ryandw11.timemanager.mainscreen.MainScreen;
import me.ryandw11.timemanager.orm.Student;
import me.ryandw11.timemanager.orm.Workspace;
import me.ryandw11.timemanager.startup.FirstTime;

public class Main {
	
	public static Workspace currentWorkspace;
	public static List<Student> listofStudents;

	public static void main(String[] args) {
		RProperties rp = new JSONProperties().setFile("data" + File.separator + "workspaces.json");
		RSQL rsql = new RSQL(rp);
		if(rsql.exists()) {
//			SelectWorkspace sw = new SelectWorkspace();
//			sw.show();
			MainScreen ms = new MainScreen();
		}else {
			FirstTime ft = new FirstTime();
			ft.show();
		}
		
		

	}

}
