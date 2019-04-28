package interfaces;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.Calendar;

import model.Worker;

public interface Data {


	public default boolean saveToFile(String path, Object o) {
		System.out.println("Saving to file...");
		FileOutputStream output;
		try {
			
			output = new FileOutputStream(path);
			ObjectOutputStream oos = new ObjectOutputStream(output);
			oos.writeObject(o);
			return true;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public default Object loadFromFile(String path) {
		System.out.println("Loading from file...");
		File file = new File(path);
		FileWriter writer;
		try {
			
			
			FileInputStream input = new FileInputStream(path);
			if(!file.exists()) {
				System.out.println("File " + path + " DOESNT exist..");
				writer = new FileWriter(file);
			}
			System.out.println("File " + path + " exist..");
			if(file.length() == 0) return null;
			ObjectInputStream objectInputStream = new ObjectInputStream(input);
			Object o = objectInputStream.readObject();
			System.out.println("Loading finished, everything is fine...");
			return o;
			
		} catch (FileNotFoundException e) {
			System.out.println("FileNotFoundException is invoked...");
			file.getParentFile().mkdirs();

			try {
				writer = new FileWriter(file);
			} catch (IOException e1) {
				System.out.println("IOExceotion is invoked...");
				e1.printStackTrace();
			}
	
			//tryAgain
			loadFromFile(path);
		} catch (ClassNotFoundException e) {
			System.out.println("ClassNotFoundException is invoked...");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
		
	}
	
	/**
	 * Function to fill the array
	 * can be reworked to work like
	 * from today +31 days not just
	 * 1 month
	 */
	public default ArrayList<LocalDate> fillDayArray() {
		
		Calendar cal = Calendar.getInstance();
		//System.out.println(cal.get(cal.MONTH)+1);
		int monthAsNumber = (cal.get(cal.MONTH)) + 1;
		int year = cal.get(cal.YEAR);
		YearMonth yearMonthObject = YearMonth.of(year, monthAsNumber);
		int daysInMonth = yearMonthObject.lengthOfMonth();
		cal = null;
		
		ArrayList<LocalDate> day_array = new ArrayList<LocalDate>();
		ArrayList<LocalDate> temp = new ArrayList<LocalDate>();
		
		for (int i = 1; i <= daysInMonth; i++) {
			//day_array.add(LocalDate.of(year, monthAsNumber, i));
			temp.add(LocalDate.of(year, monthAsNumber, i));
			//System.out.println(LocalDate.of(year, monthAsNumber, i));
		}
		day_array = temp;
		return day_array;
	}
	
	public default String getMonthName() {
		Calendar cal = Calendar.getInstance();
		//System.out.println(cal.get(cal.MONTH)+1);
		int monthAsNumber = (cal.get(cal.MONTH)) + 1;
		return(new SimpleDateFormat("MMMM").format(cal.getTime()));
	}
	
	
}
