package com.project.aynat.servlet.RepairAgencyServlet.db;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.project.aynat.servlet.RepairAgencyServlet.db.domain.AgencyUser;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.Order;
import com.project.aynat.servlet.RepairAgencyServlet.db.domain.bean.ClientOrder;
import org.apache.log4j.Logger;

public class DBManager {
    private static DBManager instance;
    private static Connection connection;

    private static final Logger LOG = Logger.getLogger(DBManager.class);

    private static final String SELECT_ALL_BY_ROLE = "SELECT * FROM agency_users WHERE user_role=?";
    private static final String SELECT_CLIENT_BY_ORDER_ID = "SELECT * FROM agency_users au INNER JOIN user_orders uo ON au.id = uo.client_id WHERE uo.id=?";
    private static final String SELECT_ALL_ORDERS_BY_MASTER_ID = "SELECT * FROM user_orders WHERE master_id=?";
    private static final String SELECT_ALL_ORDERS_CLIENTS = "SELECT * FROM user_orders uo INNER JOIN agency_users au ON uo.client_id = au.id";
    private static final String SELECT_ALL_ORDERS_BY_CLIENT_ID = "SELECT * FROM user_orders uo INNER JOIN agency_users au ON uo.client_id = au.id WHERE au.user_name =?";
    private static final String SELECT_USER_BY_USERNAME = "SELECT * FROM agency_users WHERE user_name =?";
    private static final String INSERT_INTO_USER = "INSERT INTO agency_users(id, user_password, user_name, user_role) VALUES (DEFAULT, ?, ?, ?)";
    private static final String INSERT_INTO_ORDER = "INSERT INTO user_orders(id, category, description, model, client_id) VALUES (DEFAULT, ?, ?, ?, ?)";
    private static final String UPDATE_ORDER_BY_MASTER_STATE = "UPDATE user_orders SET state_master=? WHERE id=?";
    private static final String UPDATE_ORDER_BY_MASTER_PRICE = "UPDATE user_orders SET order_price=? WHERE id=?";
    private static final String UPDATE_ORDER_BY_MANAGER_STATE = "UPDATE user_orders SET state_manager=? WHERE id=?";
    private static final String UPDATE_ORDER_BY_MANAGER_MASTER = "UPDATE user_orders SET master_id=? WHERE id=?";
    private static final String UPDATE_CLIENT_ACCOUNT_BY_MANAGER = "UPDATE agency_users SET client_account=? WHERE user_name=?";

    public Connection getConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            LOG.error("Some problem occurred with db driver", e);
            throw new RuntimeException("Some problem occurred with db driver", e);
        }
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/repair_agency_servlet?useUnicode=true&serverTimezone=UTC",
                    "root",
                    "tanya123");
            return conn;
        } catch (SQLException throwable) {
            LOG.error("Some problem occurred during connection to db", throwable);
            return null;
        }
    }

    private DBManager() {
        connection = getConnection();
    }

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    public List<AgencyUser> findAllByRole(int role) {
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        List<AgencyUser> users = new ArrayList<>();
        try {
            preparedstatement = connection.prepareStatement(SELECT_ALL_BY_ROLE);
            preparedstatement.setInt(1, role);
            resultSet = preparedstatement.executeQuery();
            while (resultSet.next()) {
                AgencyUser user = new AgencyUser();
                user.setId(resultSet.getLong("id"));
                user.setAccount(resultSet.getInt("client_account"));
                user.setPassword(resultSet.getString("user_password"));
                user.setUserName(resultSet.getString("user_name"));
                users.add(user);
            }
        } catch (Exception e) {
            LOG.error("Some problem occurred during db statement", e);
            return Collections.emptyList();
        } finally {
            close(preparedstatement);
            close(resultSet);
        }
        return users;
    }

    public List<ClientOrder> findAllClientsOrders() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<ClientOrder> orders = new ArrayList<>();
        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(SELECT_ALL_ORDERS_CLIENTS);
            while (resultSet.next()) {
                ClientOrder order = new ClientOrder();
                order.setId(resultSet.getLong(1));
                order.setCategory(resultSet.getString(2));
                order.setDescription(resultSet.getString(3));
                order.setMasterId(resultSet.getString(4));
                order.setModelOrder(resultSet.getString(5));
                order.setOrderPrice(resultSet.getInt(6));
                order.setStateManager(resultSet.getString(7));
                order.setStateMaster(resultSet.getString(8));
                order.setClientId(resultSet.getLong(9));
                order.setUserName(resultSet.getString("au.user_name"));
                order.setAccount(resultSet.getInt("au.client_account"));
                orders.add(order);
            }
        } catch (Exception e) {
            LOG.error("Some problem occured during db statement", e);
            return Collections.emptyList();
        } finally {
            close(statement);
            close(resultSet);
        }
        return orders;
    }

    public List<Order> findAllOrdersByMasterId(String username) {
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        List<Order> orders = new ArrayList<>();
        try {
            preparedstatement = connection.prepareStatement(SELECT_ALL_ORDERS_BY_MASTER_ID);
            preparedstatement.setString(1, username);
            resultSet = preparedstatement.executeQuery();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getLong(1));
                order.setCategory(resultSet.getString(2));
                order.setDescription(resultSet.getString(3));
                order.setModelOrder(resultSet.getString(5));
                order.setOrderPrice(resultSet.getInt(6));
                order.setStateMaster(resultSet.getString(8));
                order.setClientId(resultSet.getLong(9));
                orders.add(order);
            }
        } catch (Exception e) {
            LOG.error("Some problem occured during db statement", e);
            return Collections.emptyList();
        } finally {
            close(preparedstatement);
            close(resultSet);
        }
        return orders;
    }

    public List<ClientOrder> findAllOrdersByClientId(String username) {
        PreparedStatement preparedstatement = null;
        ResultSet resultSet = null;
        List<ClientOrder> orders = new ArrayList<>();
        try {
            preparedstatement = connection.prepareStatement(SELECT_ALL_ORDERS_BY_CLIENT_ID);
            preparedstatement.setString(1, username);
            resultSet = preparedstatement.executeQuery();

            while (resultSet.next()) {
                ClientOrder order = new ClientOrder();
                order.setId(resultSet.getLong(1));
                order.setCategory(resultSet.getString(2));
                order.setDescription(resultSet.getString(3));
                order.setMasterId(resultSet.getString(4));
                order.setModelOrder(resultSet.getString(5));
                order.setOrderPrice(resultSet.getInt(6));
                order.setStateManager(resultSet.getString(7));
                order.setStateMaster(resultSet.getString(8));
                order.setClientId(resultSet.getLong(9));
                order.setUserName(resultSet.getString("user_name"));
                order.setAccount(resultSet.getInt("client_account"));
                orders.add(order);
            }
        } catch (Exception e) {
            LOG.error("Some problem occured during db statement", e);
            return Collections.emptyList();
        } finally {
            close(preparedstatement);
            close(resultSet);
        }
        return orders;
    }

    public AgencyUser getUser(String username) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        AgencyUser user = null;
        try {
            preparedStatement = connection.prepareStatement(SELECT_USER_BY_USERNAME);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = new AgencyUser();
                user.setId(resultSet.getLong("id"));
                user.setAccount(resultSet.getInt("client_account"));
                user.setPassword(resultSet.getString("user_password"));
                user.setUserName(resultSet.getString("user_name"));
                user.setUserRole(resultSet.getInt("user_role"));
            }
        } catch (SQLException e) {
            LOG.error("Some problem occurred during db statement", e);
            return null;
        } finally {
            close(preparedStatement);
            close(resultSet);
        }
        return user;
    }

    public boolean insertUser(AgencyUser user) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        try {
            preparedStatement = connection.prepareStatement(INSERT_INTO_USER);
            // preparedStatement = connection.prepareStatement(INSERT_INTO_USER, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1, user.getPassword());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setInt(3, user.getUserRole());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
        } catch (Exception e) {
            LOG.error("Some problem occured during db statement", e);
            return false;
        } finally {
            close(preparedStatement);
            close(resultSet);
        }
        return true;
    }

    public boolean insertOrder(Order order, String username) throws SQLException {
        PreparedStatement preparedStatement = null;
        connection.setAutoCommit(false);
        try {
            AgencyUser user = getUser(username);
            preparedStatement = connection.prepareStatement(INSERT_INTO_ORDER);
            preparedStatement.setObject(1, order.getCategory());
            preparedStatement.setString(2, order.getDescription());
            preparedStatement.setString(3, order.getModelOrder());
            preparedStatement.setLong(4, user.getId());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            LOG.error("Some problem occurred during db statement", e);
            return false;
        } finally {
            close(preparedStatement);
        }
        return true;
    }

    public boolean updateOrderMasterState(Order order) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ORDER_BY_MASTER_STATE);
            preparedStatement.setString(1, order.getStateMaster());
            preparedStatement.setLong(2, order.getId());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
        } catch (Exception e) {
            LOG.error("Some problem occurred during db statement", e);
            return false;
        } finally {
            close(preparedStatement);
        }
        return true;
    }

    public boolean updateOrderManagerState(Order order) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ORDER_BY_MANAGER_STATE);
            preparedStatement.setString(1, order.getStateManager());
            preparedStatement.setLong(2, order.getId());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
        } catch (Exception e) {
            LOG.error("Some problem occurred during db statement", e);
            return false;
        } finally {
            close(preparedStatement);
        }
        return true;
    }

    public boolean updateOrderManagerMaster(Order order) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ORDER_BY_MANAGER_MASTER);
            preparedStatement.setString(1, order.getMasterId());
            preparedStatement.setLong(2, order.getId());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
        } catch (Exception e) {
            LOG.error("Some problem occurred during db statement", e);
            return false;
        } finally {
            close(preparedStatement);
        }
        return true;
    }

    public boolean updateOrderMasterPrice(Order order) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_ORDER_BY_MASTER_PRICE);
            preparedStatement.setInt(1, order.getOrderPrice());
            preparedStatement.setLong(2, order.getId());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
        } catch (Exception e) {
            LOG.error("Some problem occurred during db statement", e);
            return false;
        } finally {
            close(preparedStatement);
        }
        return true;
    }

    public boolean calculateClientBalance(Long orderId, int price) throws SQLException {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        AgencyUser client = null;
        connection.setAutoCommit(false);
        try {
            preparedStatement = connection.prepareStatement(SELECT_CLIENT_BY_ORDER_ID);
            preparedStatement.setLong(1, orderId);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                client = new AgencyUser();
                client.setUserName(resultSet.getString("user_name"));
                client.setAccount(resultSet.getInt("client_account"));
            }

            preparedStatement = connection.prepareStatement(UPDATE_CLIENT_ACCOUNT_BY_MANAGER);
            preparedStatement.setInt(1, client.getAccount() - price);
            preparedStatement.setString(2, client.getUserName());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            LOG.error("Some problem occurred during db statement", e);
            return false;
        } finally {
            close(preparedStatement);
            close(resultSet);
        }
        return true;
    }

    public boolean updateClientAccount(AgencyUser user) throws SQLException {
        PreparedStatement preparedStatement = null;
        connection.setAutoCommit(false);
        try {
            AgencyUser client = getUser(user.getUserName());
            int old_balance = client.getAccount();
            preparedStatement = connection.prepareStatement(UPDATE_CLIENT_ACCOUNT_BY_MANAGER);
            preparedStatement.setInt(1, user.getAccount() + old_balance);
            preparedStatement.setString(2, user.getUserName());
            if (preparedStatement.executeUpdate() != 1) {
                return false;
            }
            connection.commit();
        } catch (Exception e) {
            connection.rollback();
            LOG.error("Some problem occurred during db statement", e);
            return false;
        } finally {
            close(preparedStatement);
        }
        return true;
    }

    private void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException ex) {
                LOG.error("Some problem occurred while closing statement", ex);
            }
        }
    }

    private void close(ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException ex) {
                LOG.error("Some problem occurred while closing resultSet", ex);
            }
        }
    }
}
