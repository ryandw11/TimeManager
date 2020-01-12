package me.ryandw11.timemanager.studentmenu;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import me.ryandw11.rsql.RSQL;
import me.ryandw11.rsql.properties.RProperties;
import me.ryandw11.rsql.properties.subproperties.JSONProperties;
import me.ryandw11.rsql.properties.subproperties.SQLProperties;
import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.orm.Hour;
import me.ryandw11.timemanager.orm.Student;

/**
 * Handles the interaction between the MainScreen class and the javascript.
 * @author Ryandw11
 *
 */
public class HourDataHandler {
	private ViewStudent vs;
	public HourDataHandler(ViewStudent vs) {
		this.vs = vs;
	}
	
	/**
	 * Called by the javascript when it loads in.
	 * @return The array of students.
	 */
	public String getData(){
		List<List<Object>> output = new ArrayList<>();
		if(Main.listOfHours == null) Main.listOfHours = new ArrayList<>();
		//End Testing
		Gson gson = new Gson();
		for(Hour s : Main.listOfHours) {
			List<Object> hr = new ArrayList<>();
			hr.add(s.id);
			hr.add(s.time);
			hr.add(s.description);
			output.add(hr);
		}
		return gson.toJson(output);
	}
	
	public void onViewMore(String json) {
		Gson gson = new Gson();
		int input = gson.fromJson(json, int.class);
		System.out.println(input);
	}
	
	/**
	 * Log data. Used as a print for javascript. Currently useless as the latest version of JTML includes this automatically.
	 * @param s The string that javascript uses.
	 */
	public void logData(String s) {
		System.out.println(s);
	}

}
