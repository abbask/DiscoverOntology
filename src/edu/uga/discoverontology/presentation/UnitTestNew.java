package edu.uga.discoverontology.presentation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import edu.uga.discoverontology.model.ExpectedValue;
import edu.uga.discoverontology.model.ExpectedValueGson;
import edu.uga.discoverontology.model.MyTestSystem;
import edu.uga.discoverontology.service.SystemTestService;
import edu.uga.discoverontology.service.UnitTestService;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

@WebServlet("/UnitTestNew")
public class UnitTestNew extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2L;
	final static Logger logger = Logger.getLogger(SystemTestNew.class);


	private String	   	   templatePath = null;
	static  String         templateDir = "WEB-INF/templates";
	static  String         templateName = "unitTestNew.ftl";
	
	private Configuration  cfg; 

	public void init() {

		cfg = new Configuration(Configuration.VERSION_2_3_23);
		cfg.setServletContextForTemplateLoading( getServletContext(), 
				templateDir );
	}
	
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		loadPage(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		

		save(req,res);
		
	}
	
	public void loadPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		Template       					template;
		String         					templatePath = null;
		BufferedWriter 					toClient = null;

		templatePath = templateDir + "/" + templateName;

		// load the template
		try {
			template = cfg.getTemplate(templateName);
		} 
		catch (IOException e) {
			throw new ServletException( 
					"Can't load template " + templateDir + "/" + templateName + ": " + e.toString());
		}
		
		Map<String, Object> root = new HashMap<>();				

		toClient = new BufferedWriter(
				new OutputStreamWriter(res.getOutputStream(), template.getEncoding()));
		res.setContentType("text/html; charset=" + template.getEncoding());

		SystemTestService systemTestService = new SystemTestService();
		ArrayList<MyTestSystem> systemTests = systemTestService.listAll();
		
		root.put("systemTests", systemTests);
		
		
		
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
	
	protected void save(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		int systemTestID = 0;
		String name = "";
		String assertType = "";
		String query ="";
		String message="";
		String scallar="";
		String triple="";

		systemTestID = (!req.getParameter("systemTest").equals(0))? Integer.valueOf(req.getParameter("systemTest")) : systemTestID ;
		name = (!req.getParameter("name").equals(""))? req.getParameter("name") : name ;
		query = (!req.getParameter("query").equals(""))? req.getParameter("query") : query ;
		message = (!req.getParameter("message").equals(""))? req.getParameter("message") : message ;
		
		
		assertType = (!req.getParameter("formAssertType").equals(""))? req.getParameter("formAssertType") : assertType ;
		scallar = (!req.getParameter("formValue").equals(""))? req.getParameter("formValue") : scallar ;
		triple = (!req.getParameter("formTripple").equals(""))? req.getParameter("formTripple") : triple ;
		
		
		System.out.println("assertType: " + assertType);
		System.out.println("scallar: " + scallar);
		System.out.println("triple: " + triple);
		
		Gson gson = new Gson();
	    
		//ArrayList<ArrayList<ExpectedValue>> tempExpectedValues = new ArrayList<ArrayList<ExpectedValue>>();
		ExpectedValue[][] tempExpectedValues = new ExpectedValue[1][1];
		
		ExpectedValue[][] expectedValues ;
		if (scallar.isEmpty() || scallar == null) {
			expectedValues = gson.fromJson(triple, ExpectedValue[][].class);
			assertType = "EQUAL";
		}
		else{
			ExpectedValue expectedValue = new ExpectedValue("scallar", "scallar", "0", scallar);
			tempExpectedValues[0][0]= expectedValue;
			expectedValues = tempExpectedValues;
			
			/*
			ExpectedValue expectedValue = new ExpectedValue("scallar", "scallar", "0", scallar);
			ArrayList<ExpectedValue> temp = new ArrayList<>();
			temp.add(expectedValue);
			System.out.println("expectedValue: " + expectedValue);
			tempExpectedValues.add(temp);
			System.out.println("tempExpectedValues: " + tempExpectedValues);
			expectedValues = new ExpectedValue[1][tempExpectedValues.size()];
			System.out.println("tempExpectedValues.size(): " + tempExpectedValues.size());
//			expectedValues = tempExpectedValues.toArray(expectedValues);
			expectedValues = tempExpectedValues.toArray(expectedValues);
			*/
		}
		
		UnitTestService unitTestService = new UnitTestService(); 
		System.out.println("expectedValues: " + expectedValues);
		
		System.out.println("name:" + name + " query:" + query + " assertType:" + assertType + " expectedValues:" + expectedValues + " message:" + message +  " systemTestID:" + systemTestID);
		
		unitTestService.Add(name, query,assertType, expectedValues, message,systemTestID);
		
		res.sendRedirect(req.getContextPath() + "/UnitTestList");
		
	}
	
}
