package edu.uga.discoverontology.presentation;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

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
		String expectedValue="";
		String message="";
		
		
		systemTestID = (!req.getParameter("systemTest").equals(0))? Integer.valueOf(req.getParameter("systemTest")) : systemTestID ;
		name = (!req.getParameter("name").equals(""))? req.getParameter("name") : name ;
		assertType = (!req.getParameter("assertType").equals(""))? req.getParameter("assertType") : assertType ;
		query = (!req.getParameter("query").equals(""))? req.getParameter("query") : query ;
		expectedValue = (!req.getParameter("expectedValue").equals(""))? req.getParameter("expectedValue") : expectedValue ;
		message = (!req.getParameter("message").equals(""))? req.getParameter("message") : message ;
		
		UnitTestService unitTestService = new UnitTestService(); 
		unitTestService.Add(name, assertType, query, expectedValue, message,systemTestID);
		
		
		res.sendRedirect(req.getContextPath() + "/UnitTestList");
	
	}
	
}
