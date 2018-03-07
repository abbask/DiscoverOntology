package model;

import java.util.ArrayList;

public class MyTestSystem {
	
	private String name;
	private String endPoint;
	private String graph;
	private ArrayList<MyTestSuite> suites;
	
	public MyTestSystem() {
		
	}
	
	public MyTestSystem(String name, String endPoint, String graph, ArrayList<MyTestSuite> suites) {
		super();
		this.name = name;
		this.endPoint = endPoint;
		this.graph = graph;
		this.suites = suites;
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
	public ArrayList<MyTestSuite> getSuites() {
		return suites;
	}
	public void setSuites(ArrayList<MyTestSuite> suites) {
		this.suites = suites;
	}

	@Override
	public String toString() {
		return "MyTestSystem [name=" + name + ", endPoint=" + endPoint + ", graph=" + graph + ", suites=" + suites
				+ "]";
	}
	
	

}
