package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import interfaces.Data;
import model.Job;
import model.Schedule;
import model.Worker;

enum Month {
	ZERO, Januar, Februar, Maerz, April, Mai, Juni, Juli, August, September, Oktober, November, Dezember
}

public class WorkerController implements Data{

	private ArrayList<Worker> workerList = null;
	String path_workers = "/home/wildfly/data/workerlist";
	
	/**TODO
	 * Constructor
	 */
	public WorkerController() {
		print("Contructor is called...");
		
		if(!loadWorkersFromFile()) {
			createNewWorkersList();
			saveWorkersToFile();
		}
		
	}
	
	public void createNewWorkersList() {
		print("Creating new workers list...");
		workerList = new ArrayList<Worker>();
	}
	
//	public boolean loadWorkersFromFile() {
//		print("loading Workers from file...");
//		try {
//			
//			
//			FileInputStream input = new FileInputStream(path_workers);
//			ObjectInputStream oos = new ObjectInputStream(input);
//			workerList = (ArrayList<Worker>) oos.readObject();
//			print("loading workers finished, everything is fine...");
//			return true;
//			
//		} catch (FileNotFoundException e) {
//			print("FileNotFoundException is invoked...");
//			File file = new File(path_workers);
//			file.getParentFile().mkdirs();
//
//			try {
//				FileWriter writer = new FileWriter(file);
//			} catch (IOException e1) {
//				// TODO Auto-generated catch block
//				e1.printStackTrace();
//			}
//	
//			//tryAgain
//			loadWorkersFromFile();
//		} catch (IOException e) {
//			// OOS Exception
//			e.printStackTrace();
//		} catch (ClassNotFoundException e) {
//			print("ClassNotFoundException is invoked...");
//			e.printStackTrace();
//		}
//		
//		return false;
//	}
	
	public boolean loadWorkersFromFile() {
		workerList = (ArrayList<Worker>) loadFromFile(path_workers);
		if(workerList != null) return true;
		return false;
	}
	
//	public boolean saveWorkersToFile() {
//		print("Saving workerlist to file...");
//		FileOutputStream output;
//		try {
//			
//			output = new FileOutputStream(path_workers);
//			ObjectOutputStream oos = new ObjectOutputStream(output);
//			oos.writeObject(workerList);
//			return true;
//			
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return false;
//	}
	
	public boolean saveWorkersToFile() {
		return saveToFile(path_workers, workerList);
	}
	
	public void addWorker(Worker workerToAdd) {
		if(workerToAdd.verifyWorker() && lookForDuplicateId(workerToAdd.getId())) {
			workerList.add(workerToAdd);
			saveWorkersToFile();
		}
		else
			print("Error adding worker, something is wrong with the worker.");
	}
	
	public boolean lookForDuplicateId(int id) {
		for(Worker w : workerList) {
			if(id == w.getId()) {
				print("Duplicate ID! Not allowed..");
				return false;
			}
		}
		return true;
	}

	public boolean deleteWorkerById() {
		//TODO
		return false;
	}
	
	public boolean updateWorkersJob() {
		//TODO
		return false;
	}
	
	public boolean updateWorkersDaySchedule(Worker w) {
		System.out.println("[WorkerController] updating Workers day schedule...");
		for(LocalDate d : w.getSchedule().keySet()) {
			w.setScheduleForDay(d,new Schedule("TESTSCHICHT"));
			System.out.println("Updating " + d);
		}
		return true;
	}
	
	public boolean updateWorkersWeekSchedule() {
		//TODO
		return false;
	}
	/**
	 * Set the WorkerList
	 * @param workerList
	 */
	public void setWorkerList(ArrayList<Worker> workerList) {
		saveWorkersToFile();
		this.workerList = workerList;
		loadWorkersFromFile();
	}
	public ArrayList<Worker> getWorkerList() {
		return workerList;
	}
	
//	public void printAllWorker() {
//		for(Worker w : workerList) {
//			w.print();
//		}
//	}
	
	public void print(String s) {
		System.out.println("[WorkerController] " + s);
	}
	
	
	
//	public void printMonth(int year, Month month) {
//		YearMonth yM = YearMonth.of(year, month.ordinal());
//		int daysInMonth = yM.lengthOfMonth(); 
//		
//		System.out.println("Printing Schedule for " + month +" " + year);
//		System.out.println("=========================");
//		System.out.println("Days: " + daysInMonth);
//		System.out.println("=========================");
//		for(int i = 0; i <= daysInMonth; i++) {
//			if(i!=0)System.out.print("\t" + i);	
//			else System.out.print("\t");
//		}
//		System.out.println();
//		System.out.println("="+Job.Dreher+"=");
//		for(Worker w : workerList) {
//			if(w.job == Job.Dreher) {
//				System.out.print(w.getName() + "\t");
//				for(int i = 1; i <= daysInMonth; i++) {
//					System.out.print("\t" + w.getSchicht(LocalDate.of(year, month.ordinal(), i)));
//				}
//				System.out.println();
//			}
//		}
//
//		System.out.println("="+Job.Einleger+"=");
//		for(Worker w : workerList) {
//			if(w.job == Job.Einleger) {
//				System.out.print(w.getName() + "\t");
//				for(int i = 1; i <= daysInMonth; i++) {
//					System.out.print("\t" + w.getSchicht(LocalDate.of(year, month.ordinal(), i)));
//				}
//				System.out.println();
//			}
//		}
//	}
}
