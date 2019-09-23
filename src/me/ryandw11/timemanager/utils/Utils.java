package me.ryandw11.timemanager.utils;

import java.util.ArrayList;
import java.util.List;

import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.orm.Student;
import me.ryandw11.timemanager.orm.Workspace;

public class Utils {
	
	public static List<Workspace> getWorkspaces(List<Object> input){
		List<Workspace> output = new ArrayList<>();
		for(Object o : input) {
			output.add((Workspace) o); 
		}
		return output;
	}
	
	public static boolean studentNameExists(String name) {
		for(Student std : Main.listofStudents) {
			if(std.name.equals(name)) return true;
		}
		return false;
	}

}
