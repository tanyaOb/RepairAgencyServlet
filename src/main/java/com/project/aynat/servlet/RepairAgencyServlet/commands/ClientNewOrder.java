package com.project.aynat.servlet.RepairAgencyServlet.commands;

import com.project.aynat.servlet.RepairAgencyServlet.db.DBManager;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.Category;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.Order;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;

public class ClientNewOrder implements Command {

    DBManager dbManager = DBManager.getInstance();

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws SQLException {
        String username = request.getParameter("name");
        request.setAttribute("name", username);
        String category = request.getParameter("category");
        String description = request.getParameter("description");
        String model = request.getParameter("modelOrder");
        Order order = new Order();
        order.setCategory(category);
        order.setDescription(description);
        order.setModelOrder(model);
        List<Category> categoryList = Arrays.asList(Category.values());
        request.setAttribute("categoryList", categoryList);
        boolean status = dbManager.insertOrder(order, username);
        if (status) {
            request.setAttribute("message", "Order is saved");
        } else {
            request.setAttribute("message", "some problem");
        }
        return "/client/neworder.jsp?name=" + username + "";
    }
}
