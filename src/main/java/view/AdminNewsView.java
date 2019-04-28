package view;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import interfaces.Data;
import model.Worker;

@ViewScoped
@SuppressWarnings("deprecation")
@ManagedBean
public class AdminNewsView implements Data {

	String path = "/home/wildfly/data/news_test";
	private ArrayList<String> texts = new ArrayList<String>();
	private String text = "";

	@PostConstruct
	public void init() {
		texts = (ArrayList<String>) loadFromFile(path);
		if (texts == null) {
			System.out.println("[ANV] texts is NULL");
			texts = new ArrayList<String>();
			saveToFile(path, texts);
		}
		System.out.println("[ANV] Init finished!");
	}

	public String getText() {
		// text = (String) loadFromFile(path);
		return text;
	}

	public void setText(String text) {
		System.out.println("[ANV] Text is : " + text);
		//this.text = text;
		
		//image formating
		text = text.replaceAll("<img", "<img style=\"width:100%\"");
		//text = text.replaceFirst("src", "value");
		texts.add(text);
		saveToFile(path, texts);
		//this.text = "";
		System.out.println("[ANV] SetText complete!");
	}

	public ArrayList<String> getTexts() {
		return texts;
	}

	public void setTexts(ArrayList<String> texts) {
		this.texts = texts;
	}

	public void delete(String text) {
		if (text == null) {
			System.out.println("[ANV] News selected NULL ! ");
			return;
		} else {
			System.out.println("[ANV] Deleting News...");
			if (texts.remove(text)) {
				saveToFile(path, texts);
				System.out.println("[ANV] Deleting complete!");
			} else {
				System.out.println("[ANV] Deleting failed!");
			}
		}
	}

}
