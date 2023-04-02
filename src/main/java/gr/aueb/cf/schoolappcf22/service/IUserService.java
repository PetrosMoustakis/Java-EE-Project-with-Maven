package gr.aueb.cf.schoolappcf22.service;

import gr.aueb.cf.schoolappcf22.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappcf22.dto.UserDTO;
import gr.aueb.cf.schoolappcf22.model.User;
import gr.aueb.cf.schoolappcf22.service.exceptions.UserNotFoundException;

import java.util.List;

public interface IUserService {

    User insertUser (UserDTO userToInsert) throws UserDAOException;
    User updateUser (UserDTO userToUpdate) throws UserDAOException,UserNotFoundException;
    void deleteUser (int id) throws UserDAOException, UserNotFoundException;
    List<User> getUserByUsername (String username) throws UserDAOException;

    User getByUsername(String username) throws UserDAOException;
}
