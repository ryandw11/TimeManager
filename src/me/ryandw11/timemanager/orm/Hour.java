package me.ryandw11.timemanager.orm;

import me.ryandw11.rsql.orm.Column;
import me.ryandw11.rsql.orm.Table;

@Table
public class Hour {
	@Column
	public int id;
	@Column
	public double time;
	@Column
	public String description;
	
	public void setUp(int id, double time, String description) {
		this.id = id;
		this.time = time;
		this.description = description;
	}

}
