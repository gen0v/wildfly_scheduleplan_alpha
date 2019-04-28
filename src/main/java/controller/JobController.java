package controller;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import interfaces.Data;
import model.Job;

@SuppressWarnings("deprecation")
@ManagedBean
public class JobController implements Data{

	private ArrayList<Job> jobList = null;
	String path = "/home/wildfly/data/jobslist";
	
	
	public JobController() {
		jobList = new ArrayList<Job>();
	}

	public boolean loadJobs() {
		ArrayList<Job> temp = (ArrayList<Job>) loadFromFile(path);
		if(temp == null) {
			System.out.println("[SC] loading failed");
			return false;
		}else {
			jobList = temp;
			return true;
		}
	}

	public ArrayList<Job> getJobList() {
		loadJobs();
		return jobList;
	}


	public void setJobList(ArrayList<Job> jobList) {
		this.jobList = jobList;
	}
	
	public void add(Job s) {
		if(s == null) {
			System.out.println("Cannot add ... is NULL");
			return;
		}
		jobList.add(s);
		saveToFile(path, jobList);
	}

	public void saveJobs() {
		saveToFile(path, jobList);
	}
	
	public boolean delete(Job job) {
		if(jobList.remove(job)) {
			saveJobs();
			return true;
		}
		
		return false;
	}

	
}
