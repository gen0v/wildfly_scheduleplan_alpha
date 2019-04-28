package view;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import interfaces.Data;

@SuppressWarnings("deprecation")
@ManagedBean
public class NewsView implements Data{
	
	String path = "/home/wildfly/data/news_test";
	private String text = "";
	private ArrayList<String> texts = new ArrayList<String>();

	public String getText() {
		text = (String) loadFromFile(path);
		return text;
	}
	
	public ArrayList<String> getTexts() {
		texts = (ArrayList<String>) loadFromFile(path);
		return texts;
	}
}
