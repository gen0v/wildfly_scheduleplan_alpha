package model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;


public class Worker implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -3653901748541461241L;
	//Variables
	private int id = 0;
	private String forename = null;
	private String name = null;
	
	private Job job;
	
	private HashMap<LocalDate,Schedule> schedule = null;

	public Worker(int id, String forename, String name, Job job, HashMap<LocalDate,Schedule> schedule) {
		this.id = id;
		this.forename = forename;
		this.name = name;
		this.job = job;
		this.schedule = schedule;
	}
	
//	public void print() {
//		System.out.println("ID\t: \t " + id);
//		System.out.println("Worker\t: \t " + forename + " " + name);
//		System.out.println("Job\t: \t " + job.name());
//		System.out.println("-------------------------");
//		System.out.println("\t Plan");
//		System.out.println("-------------------------");
//		for(Map.Entry<LocalDate, Schedule> entry : schedule.entrySet()) {
//			System.out.println(entry.getKey() + " | " + entry.getValue().name());
//		}
//		System.out.println("-------------------------");
//	}

	public boolean verifyWorker() {
		if(forename == null | name == null | schedule == null)return false;
		else return true;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getForename() {
		return forename;
	}

	public String getName() {
		return name;
	}
	
	public Schedule getSchedule(LocalDate date) {
		if(schedule.get(date) == null) {
			schedule.put(date, new Schedule());
		}
		return schedule.get(date);
	}
	
	public HashMap<LocalDate,Schedule> getSchedule(){
		return schedule;
	}
	
	public void setSchedule(HashMap<LocalDate,Schedule> schedule) {
		this.schedule = schedule;
	}
	
//	public Job getJob() {
//		return job;
//	}
//	
//	public void setJob(Job job) {
//		this.job = job;
//	}
	
	public String getJob() {
		return job.getName();
	}
	
	public void setJob(String job) {
		this.setJob(job);
	}

	public void setScheduleForDay(LocalDate d, Schedule schedule2) {
		schedule.replace(d, schedule2);
		System.out.println("[Worker] Replaced -> " + d + " " + schedule2.getName());
	}
	
	
}
