package me.ryandw11.timemanager.studentmenu;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import me.ryandw11.timemanager.Main;
import me.ryandw11.timemanager.orm.Hour;

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
		for(String s : vs.student.hourIDs) {
			Hour h;
			try {
				h = Main.listOfHours.get(Integer.valueOf(s));
			}
			catch(NumberFormatException ex) {
				continue;
			}
			List<Object> hr = new ArrayList<>();
			hr.add(h.id);
			hr.add(h.time);
			hr.add(h.description);
			output.add(hr);
		}
		return gson.toJson(output);
	}
	
	public void onViewMore(String json) {
		Gson gson = new Gson();
		int input = gson.fromJson(json, int.class);
		DeleteHours dh = new DeleteHours(vs.student, input, vs);
		dh.show();
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
