package com.project.aynat.servlet.RepairAgencyServlet.commands;

import com.project.aynat.servlet.RepairAgencyServlet.db.DBManager;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.AgencyUser;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ManagerChooseMaster implements Command {

    DBManager dbManager = DBManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String orderId = request.getParameter("orderId");
        request.setAttribute("orderId", orderId);
        List<AgencyUser> masters = dbManager.findAllByRole(2);
        request.setAttribute("masters", masters);
        String masterName = request.getParameter("master");
        if (masterName != null) {
            String message = request.getParameter("message");
            request.setAttribute("message", message);
            Order order = new Order();
            order.setId(Long.valueOf(orderId));
            order.setMasterId(masterName);
            boolean status = dbManager.updateOrderManagerMaster(order);
            if (status) {
                request.setAttribute("message", "Master successfully added");
            } else {
                request.setAttribute("message", "Some problem occured");
            }
        }
        return "/manager/choosemaster.jsp?orderId=" + orderId;
    }
}
