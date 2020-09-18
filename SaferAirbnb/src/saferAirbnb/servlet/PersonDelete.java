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

@WebServlet("/persondelete")
public class PersonDelete extends HttpServlet {
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
        // Provide a title and render the JSP.
        messages.put("title", "Delete Person");
        req.getRequestDispatcher("/PersonDelete.jsp").forward(req, resp);
    }
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate id.
        String personIdStr = req.getParameter("personId");
        if (personIdStr == null  || personIdStr.trim().isEmpty()) {
            messages.put("title", "Invalid HostId");
            messages.put("disableSubmit", "true");
        } else {
            // Delete the Person.
            
            try {
            	int personId = Integer.parseInt(personIdStr);
                Person person = new Person(personId);
                person = personDao.delete(person);

                // Update the message.
                if (person == null) {
                    messages.put("title", "Successfully deleted person " + personId);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("title", "Failed to delete person " + personId);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                messages.put("title", "Failed to delete person " + personIdStr);
                messages.put("disableSubmit", "false");
            } catch (NumberFormatException e) {
                messages.put("title", personIdStr + " is not a valid number.");
                messages.put("disableSubmit", "false");
            }
        }

        req.getRequestDispatcher("/PersonDelete.jsp").forward(req, resp);
    }
}
