package view;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import controller.JobController;
import interfaces.Data;
import model.Job;

@SuppressWarnings("deprecation")
@ManagedBean
public class AdminJobView implements Data{

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	private ArrayList<Job> jobList = null;
	String path = "/home/wildfly/data/jobslist";
	
	private String name = "";
	
	JobController jController = null;
	
	@PostConstruct
	public void init() {
		//jobList = (ArrayList<Job>) loadFromFile(path);
		jController = new JobController();
		setJobList(jController.getJobList());
		System.out.println("@PostConstruct AJV finished!");
	}
	
	public ArrayList<Job> getJobList() {
		return jobList;
	}

	public void setJobList(ArrayList<Job> jobList) {
		this.jobList = jobList;
	}
	
	public void addJob() {
		Job s = new Job(name);
		System.out.println("Created Job with " + name);
		jController.add(s);
	}
	
	public void delete(Job job) {
		if(jController.delete(job)) {
			System.out.println("[AJV] Successfully removed job...");
		}
		System.out.println("[AJV] FAILED to remove job...");
	}

}
