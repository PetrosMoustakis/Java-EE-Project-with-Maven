package gr.aueb.cf.schoolappcf22.validation;

import gr.aueb.cf.schoolappcf22.dto.TeacherDTO;

public class Validator {

    private Validator() {}

    public static String validate(TeacherDTO dto) {
        if (dto.getFirstname().equals("")) {
            return "Firstname: Empty";
        }
        if ((dto.getFirstname().length()) <3 || (dto.getFirstname().length()) >32) {
            return "Firstname: Length";
        }
        if (dto.getLastname().equals("")) {
            return "Lastname: Empty";
        }
        if ((dto.getLastname().length()) <3 || (dto.getLastname().length()) >32) {
            return "Lastname: Length";
        }

        return "";
    }
}
