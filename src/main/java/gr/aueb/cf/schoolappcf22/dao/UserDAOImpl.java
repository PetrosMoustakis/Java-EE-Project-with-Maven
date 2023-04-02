package gr.aueb.cf.schoolappcf22.dao;

import gr.aueb.cf.schoolappcf22.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappcf22.model.User;
import gr.aueb.cf.schoolappcf22.service.util.DBUtil;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements IUserDAO {
    @Override
    public User insert(User user) throws UserDAOException {
        String sql = "INSERT INTO USERS (USERNAME,PASSWORD) VALUES (?,?)";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            String username = user.getUsername();
            String password = user.getPassword();
            String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());


            p.setString(1, username);
            p.setString(1, hashedPassword);
            p.executeUpdate();
            return user;

        } catch (SQLException | ClassNotFoundException e) {
            throw new UserDAOException("SQL Error in User" + user + " insertion");
        }
    }

    @Override
    public User update(User user) throws UserDAOException {
        String sql = "UPDATE USERS SET USERNAME = ?, PASSWORD = ? WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            int id = user.getId();
            String username = user.getUsername();
            String password = user.getPassword();
            String hashedPassword = BCrypt.hashpw(password,BCrypt.gensalt());


            p.setString(1,username);
            p.setString(2,hashedPassword);
            p.setInt(3, id);
            p.executeUpdate();
            return user;

        } catch (SQLException | ClassNotFoundException e) {
            throw new UserDAOException("SQL Error in User" + user.getUsername() + " update");
        }
    }

    @Override
    public void delete(int id) throws UserDAOException {
        String sql = "DELETE FROM USERS WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setInt(1 , id);
            p.executeUpdate();

        } catch (SQLException | ClassNotFoundException e) {
            throw new UserDAOException("SQL Error in User with id " + id + " deleted");
        }
    }

    @Override
    public User getById(int id) throws UserDAOException {
        User user = null;
        ResultSet rs;
        String sql = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE ID = ?";

        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setInt(1, id);
            rs = p.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("ID"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"));
            }
            return user;

        } catch (SQLException | ClassNotFoundException e) {
            throw new UserDAOException("SQL Error in User with id: " + id);
        }
    }


    @Override
    public User getByUsername(String username) throws UserDAOException {
       User user = null;
        ResultSet rs;
        String sql = "SELECT ID, USERNAME, PASSWORD FROM USERS WHERE USERNAME LIKE ?";


        try (Connection conn = DBUtil.getConnection();
             PreparedStatement p = conn.prepareStatement(sql)) {

            p.setString(1, username);
            rs = p.executeQuery();

            if (rs.next()) {
                user = new User(
                        rs.getInt("ID"),
                        rs.getString("USERNAME"),
                        rs.getString("PASSWORD"));
            }
            return user;

        } catch (SQLException | ClassNotFoundException e) {
            throw new UserDAOException("SQL Error in User with username: " + username);
        }
    }

    @Override
    public boolean isUserValid(String username, String password) {
        User user = null;

        try {
          user = getByUsername(username);
          if (user == null) {
              return false;
          }
          String hashedPassword = user.getPassword();
          return BCrypt.checkpw(hashedPassword, password);
        } catch (UserDAOException e) {
            throw new RuntimeException(e);
        }
    }
}
