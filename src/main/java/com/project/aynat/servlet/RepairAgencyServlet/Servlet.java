package com.project.aynat.servlet.RepairAgencyServlet;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.project.aynat.servlet.RepairAgencyServlet.commands.*;
import com.project.aynat.servlet.RepairAgencyServlet.exception.AppException;
import org.apache.log4j.Logger;

public class Servlet extends HttpServlet {
    private final Map<String, Command> commands = new HashMap<>();
    private static final Logger LOG = Logger.getLogger(Servlet.class);

    public void init() {
        //common commands
        commands.put("login", new Login());
        commands.put("logout", new Logout());
        commands.put("registration", new Registration());

        //client commands
        commands.put("myorder", new ClientOrders());
        commands.put("neworder", new ClientNewOrder());

        //manager commands
        commands.put("manageorders", new ManagerOrders());
        commands.put("changepaymentstatus", new ManagerPaymentStatus());
        commands.put("choosemaster", new ManagerChooseMaster());
        commands.put("manageuseraccounts", new ManagerManageAccounts());
        commands.put("addmoney", new ManagerAddMoney());

        //master commands
        commands.put("checkorders", new MasterOrders());
        commands.put("changeprogresstatus", new MasterProgressStatus());
        commands.put("assignprice", new MasterAssignPrice());
    }

    public void doGet(HttpServletRequest request,
                      HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        process(request, response);
    }

    private void process(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Command command;
        String commandName = request.getParameter("command");
        if (commandName == null) {
            String path = request.getRequestURI();
            path = path.replaceAll(".*/app/", "");
            command = commands.getOrDefault(path,
                    (request1, response1) -> "/index.jsp)");
        } else {
            command = commands.get(commandName);
        }

        String page = null;

        try {
            page = command.execute(request, response);
        } catch (AppException | SQLException e) {
            LOG.error("Some problem occured", e);
        }
        if (page.contains("redirect:")) {
            response.sendRedirect(page.replace("redirect:", ""));
        } else {
            request.getRequestDispatcher(page).forward(request, response);
        }
    }
}
