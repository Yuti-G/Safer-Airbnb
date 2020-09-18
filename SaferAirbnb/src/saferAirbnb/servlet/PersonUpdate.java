package saferAirbnb.servlet;

import saferAirbnb.dal.*;
import saferAirbnb.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/personupdate")
public class PersonUpdate extends HttpServlet {
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

        // Retrieve person and validate.
        String personIdStr = req.getParameter("personId");
        if (personIdStr == null || personIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid PersonId.");
        } else {
            try {
                int personId = Integer.parseInt(personIdStr);
                Person person = personDao.getPersonById(personId);
                if(person == null) {
                    messages.put("success", "Person does not exist.");
                }
                req.setAttribute("person", person);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.getRequestDispatcher("/PersonUpdate.jsp").forward(req, resp);
    }
	
	@Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve person and validate.
        String personIdStr = req.getParameter("personId");
        if (personIdStr == null || personIdStr.trim().isEmpty()) {
            messages.put("success", "Please enter a valid PersonId.");
        } else {
        	try {
                int personId = Integer.parseInt(personIdStr);
                Person person = personDao.getPersonById(personId);
                if(person == null) {
                    messages.put("success", "Person does not exist.");
                } else {
                    String name = req.getParameter("name");
                    if (name == null || name.trim().isEmpty()) {
                        messages.put("success", "Please enter a valid name.");
                    } else {
                        person = personDao.updateName(person, name);
                        messages.put("success", "Successfully updated name for person " + personId);
                    }
                }
                req.setAttribute("person", person);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/PersonUpdate.jsp").forward(req, resp);
    }
}
