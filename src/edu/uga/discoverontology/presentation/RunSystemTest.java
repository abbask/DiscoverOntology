package edu.uga.discoverontology.presentation;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.google.gson.Gson;

import edu.uga.discoverontology.model.MyTestSystem;
import edu.uga.discoverontology.model.MyUnitTest;
import edu.uga.discoverontology.service.SystemTestService;
import edu.uga.discoverontology.service.UnitTestService;


@WebServlet("/RunTest")
public class RunSystemTest extends HttpServlet{
	
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
		
		int system_test_id = 0;
		
		system_test_id = (!req.getParameter("system_test_id").equals(""))? Integer.valueOf(req.getParameter("system_test_id")) : system_test_id ;
		ArrayList<MyUnitTest> unitTests = unitTestService.listBySystemTest(system_test_id);
		System.out.println(unitTests.size());
	    String json = new Gson().toJson(unitTests);

	    res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write(json);
	    logger.info("RunSystemTest.loadPage : list of unit tests retrieved.[Ajax Call]");
		
	}

}
