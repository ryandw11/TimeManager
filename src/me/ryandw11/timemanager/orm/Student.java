package me.ryandw11.timemanager.orm;

import java.util.List;

import me.ryandw11.rsql.orm.Column;
import me.ryandw11.rsql.orm.Table;

@Table
public class Student {
	@Column
	public int id;
	@Column
	public String name;
	@Column
	public String clazz;
	@Column
	public int grade;
	@Column
	public double totalHours;
	@Column
	List<String> hours;
	
	public void setUp(int id, String name, int grade, String clazz, double total, List<String> hours) {
		this.id = id;
		this.name = name;
		this.grade = grade;
		this.clazz = clazz;
		this.totalHours = total;
		this.hours = hours;
	}

}
