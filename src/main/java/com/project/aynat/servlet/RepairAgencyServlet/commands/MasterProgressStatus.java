package com.project.aynat.servlet.RepairAgencyServlet.commands;

import com.project.aynat.servlet.RepairAgencyServlet.db.DBManager;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterProgressStatus implements Command {

    DBManager dbManager = DBManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String progressStatus = request.getParameter("status");
        String username = request.getParameter("name");
        request.setAttribute("name", username);
        String orderId = request.getParameter("orderId");
        request.setAttribute("orderId", orderId);
        if (!(progressStatus == null)) {
            Order order = new Order();
            order.setId(Long.valueOf(orderId));
            order.setStateMaster(progressStatus);
            Boolean status = dbManager.updateOrderMasterState(order);
            if (status) {
                request.setAttribute("message", "Status successfully changed");
            } else {
                request.setAttribute("message", "Some problem occured");
            }
        }
        return "/master/changeprogresstatus.jsp?name=" + username;
    }
}
