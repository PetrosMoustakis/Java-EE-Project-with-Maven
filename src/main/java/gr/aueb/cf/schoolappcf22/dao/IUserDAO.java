package gr.aueb.cf.schoolappcf22.dao;

//import gr.aueb.cf.schoolappcf22.dao.exceptions.UserDAOException;
//import gr.aueb.cf.schoolappcf22.model.User;

//import java.util.List;

import gr.aueb.cf.schoolappcf22.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappcf22.model.User;

import java.util.List;

public interface IUserDAO {
    User insert(User user) throws UserDAOException;
    User update(User user) throws UserDAOException;
    void delete(int id) throws UserDAOException;
    User getById(int id) throws UserDAOException;
    User getByUsername(String username) throws UserDAOException;
    boolean isUserValid(String username, String password);
}
