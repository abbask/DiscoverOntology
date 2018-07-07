package edu.uga.discoverontology.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import edu.uga.discoverontology.datastore.EndpointConnection;
import edu.uga.discoverontology.model.ExpectedValuesGroup;
import edu.uga.discoverontology.model.MyUnitTest;
import edu.uga.discoverontology.service.UnitTestService;


@WebServlet("/RunUnitTest")
public class RunUnitTest extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	final static Logger logger = Logger.getLogger(OntologyTest.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		loadPage(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		

		loadPage(req, res);
		
	}
	
	public void loadPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		UnitTestService unitTestService = new UnitTestService();
		
		int unit_test_id = 0;
		
		unit_test_id = (!req.getParameter("unit_test_id").equals(""))? Integer.valueOf(req.getParameter("unit_test_id")) : unit_test_id ;
		
		MyUnitTest unitTest = unitTestService.findByID(unit_test_id);
		
//		System.out.println(unitTest);
		
		String queryString  = unitTest.getQuery();
		
		EndpointConnection endpoint = new EndpointConnection ( unitTest.getSystemTest().getEndPoint(),unitTest.getSystemTest().getGraph());
		
		ArrayList<HashMap<String,String>> result =  endpoint.executeQueryForCol(queryString);
		
		System.out.println("result: " + result);
		System.out.println("result Size: " + result.size());
		
	    ArrayList<ExpectedValuesGroup> expectedValueGroups =	unitTest.getExpectedValueGroups();
	    System.out.println("expectedValueGroups: " + expectedValueGroups);
		System.out.println("expectedValueGroups Size: " + expectedValueGroups.size());
	    
	    for (ExpectedValuesGroup expectedValueGroup : expectedValueGroups) {
	    }
	    
		
	    String json = new Gson().toJson(result);

	    res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    if (result.size()== 0) {
	    	 res.setStatus(HttpServletResponse.SC_NOT_FOUND);
	    }
	    else {
	    	res.setStatus(HttpServletResponse.SC_OK);
	    }
	    
	    res.getWriter().write(json);
	    logger.info("RunUnitTest.loadPage : Found unit test and its expected values.[Ajax Call]");
		
	}

}
