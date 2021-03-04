package com.project.aynat.servlet.RepairAgencyServlet.commands;

import com.project.aynat.servlet.RepairAgencyServlet.db.DBManager;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.AgencyUser;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.bean.ClientOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManagerManageAccounts implements Command {

    DBManager dbManager = DBManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        List<ClientOrder> orders = dbManager.findAllClientsOrders();
        List<AgencyUser> users = dbManager.findAllByRole(0); //client
        request.setAttribute("users", users);
        return "/manager/manageuseraccounts.jsp";
    }
}
