package saferAirbnb.servlet;

import saferAirbnb.dal.*;
import saferAirbnb.model.*;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.annotation.*;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/hostcreate")
public class HostCreate extends HttpServlet {

    protected HostDao hostDao;

    @Override
    public void init() throws ServletException {
        hostDao = HostDao.getInstance();
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);
        //Just render the JSP.
        req.getRequestDispatcher("/HostCreate.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve and validate name.
        String hostName = req.getParameter("hostname");
        if (hostName == null || hostName.trim().isEmpty()) {
            messages.put("success", "Invalid UserName");
        } else {
            // Create the Host.
            int hostId = Integer.parseInt(req.getParameter("hostId"));
            String name = req.getParameter("hostname");
            String hostLocation = req.getParameter("hostLocation");
            String hostAbout = req.getParameter("hostAbout");
            String hostResponseTime = req.getParameter("hostResponseTime");
            String hostResponseRate = req.getParameter("hostResponseRate");
            String hostAcceptanceRate = req.getParameter("hostAcceptanceRate");
            String hostUrl = req.getParameter("hostUrl");

            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String stringDob = req.getParameter("hostSince");
            Date hostSince = new Date();
            try {
                hostSince = dateFormat.parse(stringDob);
            } catch (ParseException e) {
                e.printStackTrace();
                throw new IOException(e);
            }

            try {
                // Exercise: parse the input for StatusLevel.
                Host host = new Host(hostId, name, new java.sql.Date(hostSince.getTime()), hostLocation, hostAbout, hostResponseTime, hostResponseRate, hostAcceptanceRate, hostUrl);
                host = hostDao.create(host);
                messages.put("success", "Successfully created " + hostName);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/HostCreate.jsp").forward(req, resp);
    }
}
