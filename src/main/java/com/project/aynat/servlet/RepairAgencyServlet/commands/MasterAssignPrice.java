package com.project.aynat.servlet.RepairAgencyServlet.commands;

import com.project.aynat.servlet.RepairAgencyServlet.db.DBManager;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MasterAssignPrice implements Command {

    DBManager dbManager = DBManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String price = request.getParameter("price");
        String username = request.getParameter("name");
        String orderId = request.getParameter("orderId");
        request.setAttribute("name", username);
        request.setAttribute("orderId", orderId);
        if (price != null) {
            if (Integer.parseInt(price) < 0) {
                request.setAttribute("message", "Price should be a positive number");
                return "/master/assignprice.jsp?";
            }
            Order order = new Order();
            order.setId(Long.valueOf(orderId));
            order.setOrderPrice(Integer.parseInt(price));
            boolean status = dbManager.updateOrderMasterPrice(order);
            if (status) {
                request.setAttribute("message", "Price successfully assigned");
            } else {
                request.setAttribute("message", "Some problem occured");
            }
        }
        return "/master/assignprice.jsp?";
    }
}
