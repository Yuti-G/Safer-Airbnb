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


@WebServlet("/hostupdate")
public class HostUpdate extends HttpServlet {

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

        // Retrieve host and validate.
        String hostId = req.getParameter("hostId");
        if (hostId == null || hostId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid HostId.");
        } else {
            try {
                int hostIdInt = Integer.parseInt(hostId);
                Host host = hostDao.getHostById(hostIdInt);
                if(host == null) {
                    messages.put("success", "Host does not exist.");
                }
                req.setAttribute("host", host);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }
        req.getRequestDispatcher("/HostUpdate.jsp").forward(req, resp);
    }

    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        // Map for storing messages.
        Map<String, String> messages = new HashMap<String, String>();
        req.setAttribute("messages", messages);

        // Retrieve host and validate.

        // Retrieve host and validate.
        String hostId = req.getParameter("hostId");
        if (hostId == null || hostId.trim().isEmpty()) {
            messages.put("success", "Please enter a valid HostId.");
        } else {
            try {
                int hostIdInt = Integer.parseInt(hostId);
                Host host = hostDao.getHostById(hostIdInt);
                if(host == null) {
                    messages.put("success", "Host does not exist. No update to perform.");
                } else {
                    String newHostName = req.getParameter("hostname");
                    if (newHostName == null || newHostName.trim().isEmpty()) {
                        messages.put("success", "Please enter a valid name.");
                    } else {
                        host = hostDao.updateHostByName(host, newHostName);
                        messages.put("success", "Successfully updated name for host, ID:" + hostIdInt);
                    }
                }
                req.setAttribute("host", host);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new IOException(e);
            }
        }

        req.getRequestDispatcher("/HostUpdate.jsp").forward(req, resp);
    }
}
