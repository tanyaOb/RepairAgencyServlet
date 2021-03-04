package com.project.aynat.servlet.RepairAgencyServlet.commands;

import com.project.aynat.servlet.RepairAgencyServlet.db.DBManager;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.AgencyUser;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ManagerAddMoney implements Command {

    DBManager dbManager = DBManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String balance = request.getParameter("ammount");
        String userName = request.getParameter("name");
        request.setAttribute("name", userName);

        if (balance != null) {
            if (Integer.parseInt(balance) < 0) {
                request.setAttribute("message", "Amount should be a positive number!");
                return "/manager/addmoney.jsp?name=" + userName;
            }
            AgencyUser client = new AgencyUser();
            client.setUserName(userName);
            client.setAccount(Integer.parseInt(balance));
            boolean result = dbManager.updateClientAccount(client);
            if (result) {
                request.setAttribute("message", "Account balance successfully refiled!");
            } else {
                request.setAttribute("message", "Account balance wasn't refiled! Some problem occurred");
            }
        }
        return "/manager/addmoney.jsp?name=" + userName;
    }
}
