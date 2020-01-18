package me.ryandw11.timemanager.orm;


import me.ryandw11.rsql.orm.Column;
import me.ryandw11.rsql.orm.Table;

@Table
public class ExportStudent {
	@Column
	public String name;
	@Column
	public String clazz;
	@Column
	public int grade;
	@Column
	public double totalHours;
	@Column
	public String hourIDs;
	
	public void setUp(Student s) {
		this.name = s.name;
		this.grade = s.grade;
		this.clazz = s.clazz;
		this.totalHours = s.totalHours;
		this.hourIDs = String.join(", ", s.hourIDs);
	}
	
	

}
