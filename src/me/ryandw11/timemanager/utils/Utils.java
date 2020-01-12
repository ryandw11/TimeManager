package me.ryandw11.timemanager.utils;

import java.util.ArrayList;
import java.util.List;

import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.orm.Hour;
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
	
	public static boolean classNameExists(String name) {
		return Main.currentWorkspace.classes.contains(name);
	}
	
	public static void removeHours(Student su, List<String> hours) {
		List<String> curHours = new ArrayList<>(su.hourIDs);
		curHours.removeAll(hours);
		su.hourIDs = curHours;
	}
	
	public static double addUpHours(Student su) {
		List<String> badIds = new ArrayList<>();
		double total = 0;
		System.out.println(su.hourIDs);
		for(String id : su.hourIDs) {
			int ids;
			try {
				ids = Integer.valueOf(id);
			}catch(NumberFormatException ex) {
				badIds.add(id);
				continue;
			}
			Hour hd = Main.listOfHours.get(ids);
			if(hd.id == -1) {
				badIds.add(id);
				continue;
			}
			total += hd.time;
		}
		if(badIds.size() > 0) removeHours(su, badIds);
		return total;
	}

}
