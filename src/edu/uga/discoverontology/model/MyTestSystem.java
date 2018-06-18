package edu.uga.discoverontology.model;

import java.util.ArrayList;

public class MyTestSystem {
	private int ID;
	private String name;
	private String endPoint;
	private String graph;
	private ArrayList<MyUnitTest> units;
	
	public MyTestSystem() {
		
	}
	
	public MyTestSystem(String name, String endPoint, String graph, ArrayList<MyUnitTest> units) {
		this.name = name;
		this.endPoint = endPoint;
		this.graph = graph;
		this.units = units;
	}
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getGraph() {
		return graph;
	}
	public void setGraph(String graph) {
		this.graph = graph;
	}
	public ArrayList<MyUnitTest> getSuites() {
		return units;
	}
	public void setSuites(ArrayList<MyUnitTest> units) {
		this.units = units;
	}

	@Override
	public String toString() {
		return "MyTestSystem [name=" + name + ", endPoint=" + endPoint + ", graph=" + graph + ", suites=" + units
				+ "]";
	}
	
	

}
