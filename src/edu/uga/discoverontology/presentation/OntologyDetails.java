package edu.uga.discoverontology.presentation; 

import java.io.*;
import java.util.*;

import javax.servlet.*;
import javax.servlet.http.*;

import freemarker.template.*;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import freemarker.cache.FileTemplateLoader;
import freemarker.cache.MultiTemplateLoader;
import freemarker.cache.TemplateLoader;
import freemarker.template.*;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import edu.uga.discoverontology.datastore.DataStoreConnection;

/**
 * Servlet implementation class Result
 */
@WebServlet("/OntologyDetails")
public class OntologyDetails extends HttpServlet {
	private String	   	   templatePath = null;
	static  String         templateDir = "WEB-INF/templates";
	static  String         templateName = "querySPARQL.ftl";

	private Configuration  cfg; 

	public void init() {

		// Initialize the FreeMarker configuration
		//
		// Create a configuration instance
		cfg = new Configuration(Configuration.VERSION_2_3_23);

		// Set the location of template files
		cfg.setServletContextForTemplateLoading( getServletContext(), 
				templateDir );
	}

	public void doGet( 
			HttpServletRequest  req, 
			HttpServletResponse res 
			)
					throws ServletException, IOException
	{

		listAllInfo(req, res );

	}

	public void doPost( 
			HttpServletRequest  req, 
			HttpServletResponse res 
			)
					throws ServletException, IOException
	{

		String action = req.getParameter("action");
		String graphName = req.getParameter("graph");
		String endpoint = req.getParameter("point");
		
		switch (action) {
		case "change":
			updateAllInfo(res, graphName, endpoint);
			break;
		case "showClasses":
			showClasses(res, endpoint, graphName);
			break;
		case "showObjectProperties":
			showObjectProperties(res, endpoint, graphName);
			break;

		case "showDataProperties":
			showDataProperties(res, endpoint, graphName);
			break;
		default:
			break;
		}


	}

	//USED
	public void showClasses(HttpServletResponse res, String endpoint, String graphName) throws ServletException, IOException{	

		Map<String, Object> root = new HashMap<>();	

		Map<String, Object> classList = new HashMap<>();			

		DataStoreConnection query = new DataStoreConnection(endpoint, graphName);

		classList = query.restoreAllClasses();

		root.put("classes", classList.get("classes"));

		Gson gson = new Gson();
		String result = gson.toJson(root);

		res.setContentType("application/json");
		PrintWriter pw = res.getWriter ();
		pw.print(result);
		pw.close();

	}

	//USED
	public void showObjectProperties(HttpServletResponse res, String endpoint, String graphName) throws ServletException, IOException{	

		Map<String, Object> root = new HashMap<>();	

		Map<String, Object> objectPropertyList = new HashMap<>();		

		DataStoreConnection query = new DataStoreConnection(endpoint, graphName);

		objectPropertyList = query.restoreAllObejectProperties();	

		root.put("objectProperties", objectPropertyList.get("objectProperties"));

		Gson gson = new Gson();
		String result = gson.toJson(root);

		res.setContentType("application/json");
		PrintWriter pw = res.getWriter ();
		pw.print(result);
		pw.close();

	}

	//USED
	public void showDataProperties(HttpServletResponse res, String endpoint, String graphName) throws ServletException, IOException{	

		Map<String, Object> root = new HashMap<>();	

		Map<String, Object> dataPropertyList = new HashMap<>();	

		DataStoreConnection query = new DataStoreConnection(endpoint, graphName);

		dataPropertyList = query.restoreAllDataProperties();
		
		root.put("dataProperties", dataPropertyList.get("dataProperties"));
		
		Gson gson = new Gson();
		String result = gson.toJson(root);

		res.setContentType("application/json");
		PrintWriter pw = res.getWriter ();
		pw.print(result);
		pw.close();

	}
	
	//USED
	public void listAllInfo(HttpServletRequest  req,HttpServletResponse res )throws ServletException, IOException{
		Template       			template;
		String         			servletPath = null;
		String         			templatePath = null;
		BufferedWriter 			toClient = null;
		LinkedList<LinkedList<String>>    parameters = null;
		LinkedList<String>     		parameter = null;
		String	     			field;
		String	     			val;
		int    	     			i;

		// set the template path
		templatePath = templateDir + "/" + templateName;

		// load the template
		try {
			template = cfg.getTemplate("querySPARQL.ftl");
		} 
		catch (IOException e) {
			throw new ServletException( 
					"Can't load template " + templateDir + "/" + templateName + ": " + e.toString());
		}

		Map<String, Object> root = new HashMap<>();	
		
		Map<String, Object> classCount = new HashMap<>();
		Map<String, Object> dataPropertyCount = new HashMap<>();
		Map<String, Object> objectPropertyCount = new HashMap<>();
		
		Map<String, Object> classInstance = new HashMap<>();
		
	
		String graphName = req.getParameter("graph");
		String endpoint = req.getParameter("point");		
		
		DataStoreConnection query = new DataStoreConnection(endpoint, graphName);
		

		root.put("endpoint", query.getServiceURI());
		root.put("graphName", query.getGraphName());
		
		
		classCount = query.countClasses();
		dataPropertyCount = query.countDataProperty();
		objectPropertyCount = query.countObjectProperty();
		
		classInstance = query.restoreInstancesOfClasses();
		
		
		root.put("classCount", classCount.get("classCount"));
		root.put("dataPropertyCount", dataPropertyCount.get("dataPropertyCount"));
		root.put("objectPropertyCount", objectPropertyCount.get("objectPropertyCount"));

		root.put("classInstance", classInstance.get("classInstance"));
		
		

		toClient = new BufferedWriter(
				new OutputStreamWriter(res.getOutputStream(), template.getEncoding()));
		res.setContentType("text/html; charset=" + template.getEncoding());

		try {
			template.process(root, toClient);
			toClient.flush();
		} catch (TemplateException e) {
			throw new ServletException(
					"Error while processing FreeMarker template", e);
		}

		toClient.close();
	}
	
	//USED
	public void updateAllInfo(HttpServletResponse res,String graphName, String endPoint )throws ServletException, IOException{

		BufferedWriter 			toClient = null;
		LinkedList<LinkedList<String>>    parameters = null;
		LinkedList<String>     		parameter = null;
		String	     			field;
		String	     			val;
		int    	     			i;

		

		Map<String, Object> root = new HashMap<>();	
		
		Map<String, Object> classCount = new HashMap<>();
		Map<String, Object> dataPropertyCount = new HashMap<>();
		Map<String, Object> objectPropertyCount = new HashMap<>();
		
		Map<String, Object> classInstance = new HashMap<>();
		
		
		DataStoreConnection query = new DataStoreConnection(endPoint, graphName);
		

		root.put("endpoint", query.getServiceURI());
		root.put("graphName", query.getGraphName());
		
		
		classCount = query.countClasses();
		dataPropertyCount = query.countDataProperty();
		objectPropertyCount = query.countObjectProperty();
		
		classInstance = query.restoreInstancesOfClasses();
		
		
		root.put("classCount", classCount.get("classCount"));
		root.put("dataPropertyCount", dataPropertyCount.get("dataPropertyCount"));
		root.put("objectPropertyCount", objectPropertyCount.get("objectPropertyCount"));

		root.put("classInstance", classInstance.get("classInstance"));
		
		Gson gson = new Gson();
		String result = gson.toJson(root);

		res.setContentType("application/json");
		PrintWriter pw = res.getWriter ();
		pw.print(result);
		pw.close();
		
		
	}

}
