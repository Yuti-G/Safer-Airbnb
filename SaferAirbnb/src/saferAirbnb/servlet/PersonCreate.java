package saferAirbnb.servlet;

import saferAirbnb.dal.*;
import saferAirbnb.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/personcreate")
public class PersonCreate extends HttpServlet {
	protected PersonDao personDao;
	
	@Override
	public void init() throws ServletException {
		personDao = PersonDao.getInstance();
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.
        req.getRequestDispatcher("/PersonCreate.jsp").forward(req, resp);
    }
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String name = req.getParameter("name");
        if (name == null || name.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
            // Create the Person.
            int personId = Integer.parseInt(req.getParameter("personId"));

            try {
                // Exercise: parse the input for StatusLevel.
                Person person = new Person(personId, name);
                person = personDao.create(person);
                messages.put("success", "Successfully created person " + name);
            } catch (SQLException e) {
                e.printStackTrace();
                // throw new IOException(e);
            	messages.put("success", e.toString());
            }
        }
        req.getRequestDispatcher("/PersonCreate.jsp").forward(req, resp);
	}
}
