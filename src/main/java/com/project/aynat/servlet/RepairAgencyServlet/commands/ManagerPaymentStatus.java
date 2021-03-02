package com.project.aynat.servlet.RepairAgencyServlet.commands;

import com.project.aynat.servlet.RepairAgencyServlet.db.DBManager;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ManagerPaymentStatus implements Command {

    DBManager dbManager = DBManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String paymentStatus = request.getParameter("status");
        String orderId = request.getParameter("orderId");
        String orderPrice = request.getParameter("orderPrice");
        request.setAttribute("orderPrice", orderPrice);
        request.setAttribute("orderId", orderId);

        if (!(paymentStatus == null)) {
            if (paymentStatus.equals("Paid")) {
                dbManager.calculateClientBalance(Long.valueOf(orderId), Integer.parseInt(orderPrice));
            }
            Order order = new Order();
            order.setId(Long.valueOf(orderId));
            order.setStateManager(paymentStatus);
            boolean status = dbManager.updateOrderManagerState(order);
            if (status) {
                request.setAttribute("message", "Status successfully changed");
            } else {
                request.setAttribute("message", "Some problem occured");
            }
        }
        return "/manager/changepaymentstatus.jsp?orderPrice=" + orderPrice + "&orderId=" + orderId;
    }
}
