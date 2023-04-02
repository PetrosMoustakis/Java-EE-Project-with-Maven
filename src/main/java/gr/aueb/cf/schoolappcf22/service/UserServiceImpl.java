package gr.aueb.cf.schoolappcf22.service;

import gr.aueb.cf.schoolappcf22.dao.IUserDAO;
import gr.aueb.cf.schoolappcf22.dao.exceptions.UserDAOException;
import gr.aueb.cf.schoolappcf22.dto.UserDTO;
import gr.aueb.cf.schoolappcf22.model.User;
import gr.aueb.cf.schoolappcf22.service.exceptions.UserNotFoundException;

import java.util.List;

public class UserServiceImpl implements IUserService {

    private final IUserDAO userDao;

    public UserServiceImpl(IUserDAO userDao) {
        this.userDao = userDao;
    }

    @Override
    public User insertUser(UserDTO userToInsert) throws UserDAOException {

        if (userToInsert == null) return null;

        try {
            User user = mapUser(userToInsert);

            return userDao.insert(user);
        } catch (UserDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public User updateUser(UserDTO userToUpdate) throws UserDAOException, UserNotFoundException {
        System.out.println("Check update service IN");


        if (userToUpdate == null) return null;

        try {
            if (userDao.getByUsername(userToUpdate.getUsername()) == null) {
                throw new UserNotFoundException("User with id" + userToUpdate.getId() + " not found");
            }

            User user = mapUser(userToUpdate);
            return userDao.update(user);
        } catch (UserDAOException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public void deleteUser(int id) throws UserDAOException, UserNotFoundException {

        try {
            if (userDao.getById(id) == null) {
                throw new UserNotFoundException("User with id" + id + " not found");
            }
            userDao.delete(id);
        } catch (UserDAOException | UserNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public List<User> getUserByUsername(String username) throws UserDAOException {
        return null;
    }

    @Override
    public User getByUsername(String username) throws UserDAOException{
        User user;
        if (username == null) return null;

        try {
            user = userDao.getByUsername(username);
            return user;
        } catch (UserDAOException e) {
            e.printStackTrace();
            throw e;
        }
    }

    private User mapUser (UserDTO dto) {
        return new User(dto.getId(), dto.getUsername(), dto.getPassword());
    }
}

