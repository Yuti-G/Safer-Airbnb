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


@WebServlet("/hostdelete")
public class HostDelete extends HttpServlet {

    protected HostDao hostDao;
    protected PersonDao personDao;

    @Override
    public void init() throws ServletException {
        hostDao = HostDao.getInstance();
        personDao = PersonDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        // Provide a title and render the JSP.
        messages.put("title", "Delete Host");
        req.getRequestDispatcher("/HostDelete.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate id.
        String hostId = req.getParameter("hostId");
        if (hostId == null  || hostId.trim().isEmpty()) {
            messages.put("title", "Invalid HostId");
            messages.put("disableSubmit", "true");
        } else {
            // Delete the Host.
            int hostIdInt = Integer.parseInt(hostId);
            Host host = new Host(hostIdInt);
            Person person = new Person(hostIdInt);
            try {
                host = hostDao.delete(host);
                if (host == null) {
                    person = personDao.delete(person);
                }

                // Update the message.
                if (host == null && person == null) {
                    messages.put("title", "Successfully deleted " + hostIdInt);
                    messages.put("disableSubmit", "true");
                } else {
                    messages.put("title", "Failed to delete " + hostIdInt);
                    messages.put("disableSubmit", "false");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/HostDelete.jsp").forward(req, resp);
    }
}

