package main;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;



@ManagedBean
public class HelloWorld implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private String hello = "hello";
	private String world = "world";

	public HelloWorld() {
		
	}
	
	
	public String getHello() {
		return hello;
	}

	public void setHello(String hello) {
		this.hello = hello;
	}


	public String getWorld() {
		return world;
	}


	public void setWorld(String world) {
		this.world = world;
	}
	
	
}
