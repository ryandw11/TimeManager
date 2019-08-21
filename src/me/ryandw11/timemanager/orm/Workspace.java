package me.ryandw11.timemanager.orm;

import java.util.List;

import me.ryandw11.rsql.orm.Column;
import me.ryandw11.rsql.orm.Table;

@Table
public class Workspace {
	
	@Column
	public String name;
	
	@Column
	public List<String> classes;
}
