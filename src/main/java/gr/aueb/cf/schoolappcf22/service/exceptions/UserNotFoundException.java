package gr.aueb.cf.schoolappcf22.service.exceptions;

import gr.aueb.cf.schoolappcf22.model.User;

public class UserNotFoundException extends Exception {

    private final static long serialVersionUID = 1L;

    public UserNotFoundException(User user) {
        super("User with id" + user.getId() + " does not exist");
    }

    public UserNotFoundException(String s) {
        super(s);
    }
}
