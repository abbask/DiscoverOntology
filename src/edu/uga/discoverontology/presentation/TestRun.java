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
import edu.uga.discoverontology.service.SystemTestService;


@WebServlet("/RunTest")
public class TestRun extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	final static Logger logger = Logger.getLogger(OntologyTest.class);

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		loadPage(req, res);
	}


	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {		

		loadPage(req, res);
		
	}
	
	public void loadPage(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		SystemTestService systemTestService = new SystemTestService();

		ArrayList<MyTestSystem> testSystems = systemTestService.listAll();
		
	    String json = new Gson().toJson(testSystems);

	    res.setContentType("application/json");
	    res.setCharacterEncoding("UTF-8");
	    res.getWriter().write(json);
		
	}

}
