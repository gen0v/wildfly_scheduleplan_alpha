package view;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import controller.WorkerController;
import interfaces.Data;
import model.Worker;

@SuppressWarnings("deprecation")
@ManagedBean
public class ScheduleView implements Data{

	private WorkerController wController = null;
	private ArrayList<LocalDate> day_array = null;
	private String month_name = "";
	private ArrayList<Worker> worker = null;
	
	public ArrayList<LocalDate> getDay_array() {
		return day_array;
	}

	public void setDay_array(ArrayList<LocalDate> day_array) {
		this.day_array = day_array;
	}

	@PostConstruct
	public void init() {
		wController = new WorkerController();
		worker = wController.getWorkerList();
		month_name = getMonthName();
		day_array = fillDayArray();
		
		System.out.println("[SCHEDULEVIEW] (Init) Done!");
	}

//	/**
//	 * Function to fill the array
//	 * can be reworked to work like
//	 * from today +31 days not just
//	 * 1 month
//	 */
//	private void fillDayArray() {
//		
//		Calendar cal = Calendar.getInstance();
//		//System.out.println(cal.get(cal.MONTH)+1);
//		int monthAsNumber = (cal.get(cal.MONTH)) + 1;
//		month_name = new SimpleDateFormat("MMMM").format(cal.getTime());
//		int year = cal.get(cal.YEAR);
//		YearMonth yearMonthObject = YearMonth.of(year, monthAsNumber);
//		int daysInMonth = yearMonthObject.lengthOfMonth();
//		cal = null;
//		
//		day_array = new ArrayList<LocalDate>();
//		ArrayList<LocalDate> temp = new ArrayList<LocalDate>();
//		
//		for (int i = 1; i <= daysInMonth; i++) {
//			//day_array.add(LocalDate.of(year, monthAsNumber, i));
//			temp.add(LocalDate.of(year, monthAsNumber, i));
//			//System.out.println(LocalDate.of(year, monthAsNumber, i));
//		}
//		day_array = temp;
//	}


	public String getMonth_name() {
		return month_name;
	}

	public void setMonth_name(String month_name) {
		this.month_name = month_name;
	}

	
	public ArrayList<Worker> getWorker() {
		return worker;
	}

	public void setWorker(ArrayList<Worker> worker) {
		if(worker == null) {
			System.out.println("[ScheduleView] setWorker is executed but NULL!");
			return;
		}
		System.out.println("[ScheduleView] setWorker is executed!");
		this.worker = worker;
	}
	
}
