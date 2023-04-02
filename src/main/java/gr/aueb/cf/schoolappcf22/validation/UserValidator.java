package gr.aueb.cf.schoolappcf22.validation;

import gr.aueb.cf.schoolappcf22.dto.UserDTO;

public class UserValidator {

    private UserValidator() {}

    public static String validate (UserDTO dto) {
        if (dto.getUsername().equals("")) {
            return "Username: Empty";
        }

        if (dto.getPassword().equals("")) {
            return "Password: Empty";
        }
        return "";
    }
}
