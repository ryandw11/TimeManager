package me.ryandw11.timemanager.mainscreen;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import me.ryandw11.rsql.RSQL;
import me.ryandw11.rsql.properties.RProperties;
import me.ryandw11.rsql.properties.subproperties.JSONProperties;
import me.ryandw11.rsql.properties.subproperties.SQLProperties;
import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.orm.Student;

/**
 * Handles the interaction between the MainScreen class and the javascript.
 * @author Ryandw11
 *
 */
public class StudentDataHandler {
	private MainScreen ms;
	public StudentDataHandler(MainScreen ms) {
		this.ms = ms;
	}
	
	/**
	 * Called by the javascript when it loads in.
	 * @return The array of students.
	 */
	public String getData(){
		List<List<Object>> output = new ArrayList<>();
		
//		Main.listofStudents = new ArrayList<>();
		if(Main.listofStudents == null) Main.listofStudents = new ArrayList<>();
		// Testing
		
//		Student st1 = new Student();
//		st1.name = "yeet";
//		st1.grade = 20;
//		st1.clazz = "Cool Class";
//		st1.totalHours = 25.3d;
//		Student st2 = new Student();
//		st2.name = "Student 2";
//		st2.grade = 90;
//		st2.clazz = "Awe Class";
//		st2.totalHours = 235.3d;
//		students.add(st1);
//		students.add(st2);
//		Main.listofStudents = students;
		//End Testing
		Gson gson = new Gson();
		for(Student s : Main.listofStudents) {
			System.out.println(s.name);
			List<Object> stu = new ArrayList<>();
			stu.add(s.name);
			stu.add(s.grade);
			stu.add(s.clazz);
			stu.add(s.totalHours);
			output.add(stu);
		}
		return gson.toJson(output);
	}
	
	/**
	 * Called when javascript detects when a checkbox is clicked.
	 * @param json The array json from javascript
	 */
	public void onSelect(String json) {
		Gson gson = new Gson();
		int[] input = gson.fromJson(json, int[].class);
		getStudents(input);
	}
	
	/**
	 * Handles the list of students in the MainScreen class and updating the MenuBar.
	 * @param ids The list of ids provided from the onSelect() method above.
	 */
	private void getStudents(int[] ids) {
		ms.selectedStudents = new ArrayList<>();
		for(int i : ids) {
			ms.selectedStudents.add(Main.listofStudents.get(i));
		}
		
		ms.menubar.update();
		ms.updateSelect();
	}
	
	/**
	 * Log data. Used as a print for javascript. Currently useless as the latest version of JTML includes this automatically.
	 * @param s The string that javascript uses.
	 */
	public void logData(String s) {
		System.out.println(s);
	}

}
