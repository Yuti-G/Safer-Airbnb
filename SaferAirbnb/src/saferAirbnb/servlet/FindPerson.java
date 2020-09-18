package saferAirbnb.servlet;

import javax.servlet.annotation.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import saferAirbnb.model.*;
import saferAirbnb.dal.*;

@WebServlet("/findperson")
public class FindPerson extends HttpServlet {
	protected PersonDao personDao;
	
	
	@Override
	public void init() throws ServletException {
		personDao = PersonDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Person> people = new ArrayList<>();
        
        String name = req.getParameter("name");
        if (name == null || name .trim().isEmpty()) {
        	messages.put("success", "Please enter a valid name.");
        } else {
        	try {
        		people = personDao.getPeopleByName(name);
        		
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        	messages.put("success", "Displaying results for person " + name);
        	messages.put("previousName", name);
        }
        req.setAttribute("people", people);
        
        req.getRequestDispatcher("/FindPerson.jsp").forward(req, resp);
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
		Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        List<Person> people = new ArrayList<>();
        
        String name = req.getParameter("name");
        if (name == null || name .trim().isEmpty()) {
        	messages.put("success", "Please enter a valid name.");
        } else {
        	try {
        		people = personDao.getPeopleByName(name);
        		
        	} catch (SQLException e) {
        		e.printStackTrace();
        		throw new IOException(e);
        	}
        	messages.put("success", "Displaying results for person " + name);
        	messages.put("previousName", name);
        }
        req.setAttribute("people", people);
        
        req.getRequestDispatcher("/FindPerson.jsp").forward(req, resp);
	}
}
