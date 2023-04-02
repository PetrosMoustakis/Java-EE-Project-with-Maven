package gr.aueb.cf.schoolappcf22.authentication;

import gr.aueb.cf.schoolappcf22.dao.IUserDAO;
import gr.aueb.cf.schoolappcf22.dao.UserDAOImpl;
import gr.aueb.cf.schoolappcf22.dto.UserDTO;
import gr.aueb.cf.schoolappcf22.model.User;

public class AuthenticationProvider {

    private static final IUserDAO userDao = new UserDAOImpl();
    private AuthenticationProvider() {}

    public static User authenticate(UserDTO userDTO) {
        if (!userDao.isUserValid(userDTO.getUsername(),userDTO.getPassword())) {
            return null;
        }

        return new User(userDTO.getId(), userDTO.getUsername(), userDTO.getPassword());
    }
}
