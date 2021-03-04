package com.project.aynat.servlet.RepairAgencyServlet.commands;

import com.project.aynat.servlet.RepairAgencyServlet.db.DBManager;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.AgencyUser;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.Role;
import com.project.aynat.servlet.RepairAgencyServlet.exception.AppException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class Login implements Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws AppException {
        String username = request.getParameter("name");
        String password = request.getParameter("pass");
        if (username == null || username.equals("") || password == null || password.equals("")) {
            return "/login.jsp";
        }
        HttpSession session = request.getSession();
        DBManager dbManager = DBManager.getInstance();

        AgencyUser user = dbManager.getUser(username);
        if (user == null || !password.equals(user.getPassword())) {
            request.setAttribute("message", "Cannot find user with such login/password");
            return "/login.jsp";
        }

        Role userRole = Role.getRole(user);

        String forward = "";

        if (userRole == Role.MANAGER) {
            forward = "/app?command=manageorders";
        }

        if (userRole == Role.CLIENT) {
            forward = "/app?command=myorder";
        }

        if (userRole == Role.MASTER) {
            forward = "/app?command=checkorders";
        }

        session.setAttribute("user", user);
        session.setAttribute("userRole", userRole);

        return forward;
    }
}