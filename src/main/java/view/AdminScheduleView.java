package view;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import controller.ScheduleController;
import interfaces.Data;
import model.Schedule;
import model.Worker;

@SuppressWarnings("deprecation")
@ManagedBean
public class AdminScheduleView implements Data{

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private ArrayList<Schedule> scheduleList = null;
	String path = "/home/wildfly/data/scheduleslist";
	
	private String name = "";
	private String startTime = "";
	private String endTime = "";
	
	ScheduleController sController = null;
	
	@PostConstruct
	public void init() {
		//scheduleList = (ArrayList<Schedule>) loadFromFile(path);
		sController = new ScheduleController();
		setScheduleList(sController.getScheduleList());
		System.out.println("@PostConstruct ASV finished!");
	}
	
	public ArrayList<Schedule> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(ArrayList<Schedule> scheduleList) {
		this.scheduleList = scheduleList;
	}
	
	public void addSchedule() {
		Schedule s = new Schedule(name,startTime,endTime);
		System.out.println("Created Schedule with " + name + " " + startTime + " " + endTime);
		sController.add(s);
	}
	
	public void delete(Schedule schedule) {
		if(sController.delete(schedule)) {
			System.out.println("[ASV] Successfully removed schedule...");
		}
		System.out.println("[ASV] FAILED to remove schedule...");
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
}
