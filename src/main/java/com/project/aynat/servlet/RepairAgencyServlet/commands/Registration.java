package com.project.aynat.servlet.RepairAgencyServlet.commands;

import com.project.aynat.servlet.RepairAgencyServlet.db.DBManager;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.AgencyUser;
import com.project.aynat.servlet.RepairAgencyServlet.exception.AppException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Registration implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException, AppException {
        String role = request.getParameter("role");
        String username = request.getParameter("name");
        String password = request.getParameter("pass");
        if (username == null || username.equals("") || password == null || password.equals("")) {
            return "/registration.jsp";
        }
        int userRole = 0;
        switch (role) {
            case "Client":
                userRole = 0;
                break;
            case "Manager":
                userRole = 1;
                break;
            case "Master":
                userRole = 2;
                break;
        }

        DBManager dbManager = DBManager.getInstance();

        if (username.isEmpty() || password.isEmpty()) {
            throw new AppException("Login/password cannot be empty");
        }

        AgencyUser user = dbManager.getUser(username);

        if (user != null) {
            request.setAttribute("message", "User with provided name already exists!");
            System.out.println("user already exists");
        } else {
            AgencyUser agencyUser = new AgencyUser();
            agencyUser.setUserName(username);
            agencyUser.setPassword(password);
            agencyUser.setUserRole(userRole);
            try {
                dbManager.insertUser(agencyUser);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return "/registration.jsp";
    }
}

