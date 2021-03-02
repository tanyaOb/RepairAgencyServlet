package com.project.aynat.servlet.RepairAgencyServlet.commands;

import com.project.aynat.servlet.RepairAgencyServlet.db.DBManager;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.bean.ClientOrder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

public class ClientOrders implements Command {

    DBManager dbManager = DBManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("name");
        List<ClientOrder> orders = dbManager.findAllOrdersByClientId(username);
        Integer balance = orders.get(0).getAccount();
        request.setAttribute("orders", orders);
        request.setAttribute("name", username);
        request.setAttribute("balance", balance);
        HttpSession session = request.getSession();
        session.setAttribute("name", username);
        return "/client/myorder.jsp?name=" + username + "";
    }
}
