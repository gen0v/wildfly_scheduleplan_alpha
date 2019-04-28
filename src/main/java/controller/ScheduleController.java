package controller;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import interfaces.Data;
import model.Schedule;

@SuppressWarnings("deprecation")
@ManagedBean
public class ScheduleController implements Data{

	private ArrayList<Schedule> scheduleList = null;
	String path = "/home/wildfly/data/scheduleslist";
	
	
	public ScheduleController() {
		scheduleList = new ArrayList<Schedule>();
	}

	public boolean loadSchedules() {
		ArrayList<Schedule> temp = (ArrayList<Schedule>) loadFromFile(path);
		if(temp == null) {
			System.out.println("[SC] loading failed");
			return false;
		}else {
			scheduleList = temp;
			return true;
		}
	}

	public ArrayList<Schedule> getScheduleList() {
		loadSchedules();
		return scheduleList;
	}


	public void setScheduleList(ArrayList<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}
	
	public void add(Schedule s) {
		if(s == null) {
			System.out.println("Cannot add ... is NULL");
			return;
		}
		scheduleList.add(s);
		saveToFile(path, scheduleList);
	}

	public void saveSchedules() {
		saveToFile(path, scheduleList);
	}
	
	public boolean delete(Schedule schedule) {
		if(scheduleList.remove(schedule)) {
			saveSchedules();
			return true;
		}
		
		return false;
	}

	
}
