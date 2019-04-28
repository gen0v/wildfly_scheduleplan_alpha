package model;

import java.io.Serializable;
import java.time.LocalDate;

//public enum Schicht
//{
//	FR,SP,_,UR,KR
//}

public class Schedule implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 8141536049312625607L;
	private String name = "";
	private String work_time = "";
	private String start_time = "";
	private String end_time = "";
	
	public String getStart_time() {
		return start_time;
	}

	public void setStart_time(String start_time) {
		this.start_time = start_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public Schedule() {
	}
	
	public Schedule(String name) {
		this.name = name;
	}
	
	public Schedule(String name, String start, String end) {
		this.name = name;
		start_time = start;
		end_time = end;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWork_time() {
		return work_time;
	}

	public void setWork_time(String work_time) {
		this.work_time = work_time;
	}

	public String toString() {
		return "Its a schedule !";
	}
	
	
	
}
