package view;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import controller.JobController;
import controller.ScheduleController;
import controller.WorkerController;
import interfaces.Data;
import model.Job;
import model.Schedule;
import model.Schedule;
import model.Worker;
import net.bootsfaces.component.dataTable.DataTable;
import net.bootsfaces.component.dataTableColumn.DataTableColumn;

@ViewScoped
@SuppressWarnings("deprecation")
@ManagedBean
public class AdminWorkerManagementView implements Data{
	
	
	private int id = 0;
	private String forename = null;
	private String name = null;
	private Job job = null;
	private Schedule schedule = null;
	private ArrayList<Job> jobs = null;
	private ArrayList<Schedule> schedules = null;
	private ArrayList<Worker> worker = null;
	private WorkerController wController = null;
	private JobController jController = null;
	private ScheduleController sController = null;
	private Worker selectedWorker = null;
	private ArrayList<LocalDate> days = null;
	private String selectedWorkersString = "";
	private String initialValue = "";
	
	@PostConstruct
	public void init() {
		wController = new WorkerController();
		jController = new JobController();
		sController = new ScheduleController();
		fillSchedules();
		fillJobs();
		worker = wController.getWorkerList();
		days = fillDayArray();
		
		//init Job list TODO
		
	}
	
	private void fillJobs() {
		jobs = new ArrayList<Job>();
		jobs = jController.getJobList();
		//Dreher,Einleger,Endfertigung,Giesser,Schlosser
//		jobs.add(new Job("Dreher"));
//		jobs.add(new Job("Einleger"));
//		jobs.add(new Job("Endfertigung"));
//		jobs.add(new Job("Giesser"));
//		jobs.add(new Job("Schlosser"));
		
//		//TODO
//		for(Job j : Job.values()) {
//			System.out.println("[AWMV] Job added : " + j.toString());
////			jobs.add(j);
//		}
	}
	
	private void fillSchedules() {
//		setSchedules(new ArrayList<Schedule>());
//		setSchedules(sController.getScheduleList());
		schedules = new ArrayList<Schedule>();
		schedules = sController.getScheduleList();
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

	public void setForename(String forename) {
		this.forename = forename;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Job getJob() {
		return job;
	}

	public void setJob(Job job) {
		this.job = job;
	}

	public void addWorker(ActionEvent e) {
		System.out.println("DEBUG 1");
		
		if(forename == null || name == null || job == null) {
			System.out.println("[AWMV] Cannot add worker, something is missing !");
			return;
		}
		id = worker.size();
		
		System.out.println("DEBUG 2");
		
		//For adding we need to verify and test the id
		while(!(wController.lookForDuplicateId(id))){
			id++;
		}
		
		System.out.println("DEBUG 3");
		//create empty hashmap
		HashMap<LocalDate, Schedule> temp = new HashMap<LocalDate, Schedule>();
		//ArrayList<LocalDate> days = fillDayArray();
		for(LocalDate d : days) {
			temp.put(d, new Schedule("X"));
		}
		
		Worker w = new Worker(id, forename, name, job, temp);
		
		System.out.println("DEBUG 4");
		
		if(w.verifyWorker()) {
			//worker.add(w);
			wController.addWorker(w);
			//wController.setWorkerList(worker);
			wController.saveWorkersToFile();
			worker = wController.getWorkerList();
			
			System.out.println("[AWMV] Worker sucessfullly added!");
		}else {
			System.out.println("[AWMV] Couldnt verify worker...");
		}
		
		System.out.println("DEBUG 5");
	}

	public ArrayList<Worker> getWorker() {
		return worker;
	}

	public void setWorker(ArrayList<Worker> worker) {
		this.worker = worker;
		System.out.println("[AWMV] Worker selected !");
	}

	public ArrayList<Job> getJobs() {
//		for(Job j : jobs) {
//			//System.out.println("[AWMV] getJobs : " + j.toString());
//		}
		return jobs;
	}

	public void setJobs(ArrayList<Job> jobs) {
		this.jobs = jobs;
	}

	
	public Worker getSelectedWorker() {
		return selectedWorker;
	}

	public void setSelectedWorker(Worker selectedWorker) {
		this.selectedWorker = selectedWorker;
	}
	
	public void delete(Worker worker) {
		if(worker == null) {
			System.out.println("[AWMV] Worker selected NULL ! ");
			return;
		}
		else {
			System.out.println("[AWMV] Worker selected : " + worker.getName());
			System.out.println("[AWMV] Deleting Worker...");
			deleteWorker(worker);
		}
	}

	private boolean deleteWorker(Worker w) {
		if(worker.remove(w)) {
			wController.setWorkerList(worker);
			worker = wController.getWorkerList();
			return true;
		}
		return false;
	}

	public ArrayList<Schedule> getSchedules() {
		return schedules;
	}

	public void setSchedules(ArrayList<Schedule> schedules) {
		this.schedules = schedules;
	}
	
	
	public Schedule getSchedule() {
		return schedule;
	}
	
	public void setSchedule(Schedule schedule) {
		System.out.println("THIS IS SET SCHEDULE " + schedule.getName());
		this.schedule = schedule;
	}

	//TESTING
//	public void onSelect(Worker w, Object o) {
//		System.out.println("DEBUG ONSELECT 1");
//		if(w == null || o == null) {
//			System.out.println("[AWMV] Selection is NULL...");
//		}
//		else {
//			System.out.println("DEBUG ONSELECT 2");
//			//System.out.println("Selection is " + w.getName() + " " + o.toString());
//		}
//	}
	public void onSelect(ActionEvent e) {
		System.out.println("DEBUG ONSELECT 1");
	}
	
	public void setSchedulesForWorker() {
		//set marked workers schedule
		
	}

	public void test(Worker w, Object[] s) {
		if(w == null) {
			System.out.println("W is NULL");
		}else {
			System.out.println("W is " + w.getName());
			wController.updateWorkersDaySchedule(w);
		}
		if(s == null) {
			System.out.println("S is NULL");

		}else {
			System.out.println("S is " + s);
		}
		//DataTableColumn c = new DataTableColumn();
		
	}
	
	public void printSomething() {
		System.out.println("printSomething...");
//		//int temp = 0;
//		//HashMap<String, String> testing = new HashMap<String, String>();
//		String initialValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("initialValue");
//		//Map<String, String> m = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
//		
//		int row = -1;
//		int column = -1;
//		
//		if(initialValue == null) {
//			System.out.println("Nothing selected!");
//		}
//		else {
//			System.out.println("Selected -> " + initialValue);
//			//String mydata = "{'row':4,'column':11,'columnVisible':11}";
//			
//			Pattern pattern = Pattern.compile("\\{'row':([0-9]+),'column':([0-9]+),.*");
//			
//			Matcher matcher = pattern.matcher(initialValue);
//			
//			if (matcher.find())
//			{
//			    System.out.println("Row " + matcher.group(1));
//			    row = Integer.parseInt(matcher.group(1));
//			    System.out.println("Column " + matcher.group(2));
//			    column = Integer.parseInt(matcher.group(2));
//			    
////			    //changing worker schedule
////			    worker.get(row).setScheduleForDay(days.get(column-2), new Schedule(getSchedule().getName()));
////			    wController.setWorkerList(worker);
////			    setWorker(wController.getWorkerList());
//			}
//		}
	}
	
	
	
	public void testingJavascript() {
		System.out.println("TESTING JAVASCRIPT");
		//Worker selWorker ;
		Schedule temp = new Schedule("UPS");
		
		//row X column Y
		int row;
		int col;
		//String initialValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("initialValue");
		
		//test initialValue!!
		if(initialValue.equals("")) {
			System.out.println("INITIALVALUE IS EMPTY");
			return;
		}
		
		initialValue = initialValue.replaceFirst("row ", "");
		initialValue = initialValue.replaceAll("row ", ",");
		initialValue = initialValue.replaceAll("column", "");
		
		String[] lines = initialValue.split(",");

		Pattern pattern = Pattern.compile("\\s*([0-9]*)\\s*([0-9]*)\\s*");
		Matcher matcher;
		
		for(String s : lines) {
			System.out.println("[X] " + s);
			matcher = pattern.matcher(s);
			if (matcher.find())
			{
			    //System.out.println("Row " + matcher.group(1));
			    row = Integer.parseInt(matcher.group(1));
			    //System.out.println("Column " + matcher.group(2));
			    col = Integer.parseInt(matcher.group(2));
			    
			    if(schedule != null) {
			    	temp = schedule;
			    }else
			    {
			    	System.out.println("GetSchedule() returned null !!");
			    }
			    worker.get(row).setScheduleForDay(days.get(col-2), temp);
			    wController.setWorkerList(worker);
			    setWorker(wController.getWorkerList());
			    
			    
			}
		}
		

		
		
		
		
//		Matcher matcher = pattern.matcher(initialValue);
//		
//		if (matcher.find())
//		{
//		    System.out.println("Row " + matcher.group(1));
//		    row = Integer.parseInt(matcher.group(1));
//		    System.out.println("Column " + matcher.group(2));
//		    col = Integer.parseInt(matcher.group(2));
//		    
//		}
		
		//adding workers to selected list
//		selectedWorkerList = new ArrayList<Worker>();
		
		
		
		
	}

	public String getSelectedWorkersString() {
		return selectedWorkersString;
	}

	public void setSelectedWorkersString(String selectedWorkersString) {
		this.selectedWorkersString = selectedWorkersString;
	}

	public void setInitialValue() {
		initialValue = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("initialValue");
	}



	
}
