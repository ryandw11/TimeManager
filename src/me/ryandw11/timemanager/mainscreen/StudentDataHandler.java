package me.ryandw11.timemanager.mainscreen;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.orm.Student;

public class StudentDataHandler {
	private MainScreen ms;
	public StudentDataHandler(MainScreen ms) {
		this.ms = ms;
	}
	
	public String getData(){
		List<List<Object>> output = new ArrayList<>();
		// Testing
		List<Student> students = new ArrayList<>();
		Student st1 = new Student();
		st1.name = "yeet";
		st1.grade = 20;
		st1.clazz = "Cool Class";
		st1.totalHours = 25.3d;
		students.add(st1);
		Main.listofStudents = students;
		//End Testing
		Gson gson = new Gson();
		for(Student s : Main.listofStudents) {
			List<Object> stu = new ArrayList<>();
			stu.add(s.name);
			stu.add(s.grade);
			stu.add(s.clazz);
			stu.add(s.totalHours);
			output.add(stu);
		}
		return gson.toJson(output);
	}
	
	public void onSelect(String json) {
		Gson gson = new Gson();
		int[] input = gson.fromJson(json, int[].class);
		getStudents(input);
		System.out.println(ms.selectedStudents.size());
	}
	
	private void getStudents(int[] ids) {
		ms.selectedStudents = new ArrayList<>();
		for(int i : ids) {
			ms.selectedStudents.add(Main.listofStudents.get(i));
		}
		ms.createMenu();
	}
	
	public void logData(String s) {
		System.out.println(s);
	}

}
