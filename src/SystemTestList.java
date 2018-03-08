import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import model.MyTestSuite;
import model.MyTestSystem;

@WebServlet("/SystemTestList")
public class SystemTestList extends HttpServlet{
	
	private static final long serialVersionUID = 1L;


	private String	   	   templatePath = null;
	static  String         templateDir = "WEB-INF/templates";
	static  String         templateName = "systemTestList.ftl";

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

//		compareAll(req,res);
		
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

		ArrayList<MyTestSystem> testSystems = new ArrayList<>();

		root.put("testSystems", testSystems);
		
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
	

}
