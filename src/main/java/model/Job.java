package model;

import java.io.Serializable;

//public enum Job
//{
//	Dreher,Einleger,Endfertigung,Giesser,Schlosser
//}

public class Job implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4096869072277582627L;

	public Job(String name) {
		this.name = name;
	}

	private String name = "";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	
}