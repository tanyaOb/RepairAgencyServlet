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

        if (!(balance == null)) {
            AgencyUser client = new AgencyUser();
            client.setUserName(userName);
            client.setAccount(Integer.valueOf(balance));
            boolean result = dbManager.updateClientAccount(client);
            if (result) {
                System.out.println("success");
            }
        }
        return "/manager/addmoney.jsp?name=" + userName;
    }
}
