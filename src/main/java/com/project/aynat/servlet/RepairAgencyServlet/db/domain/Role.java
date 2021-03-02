package com.project.aynat.servlet.RepairAgencyServlet.db.domain;

public enum Role {
    CLIENT,
    MANAGER,
    MASTER;

    public static Role getRole(AgencyUser user) {
        int roleId = user.getUserRole();
        return Role.values()[roleId];
    }

    public String getName() {
        return name().toLowerCase();
    }
}
