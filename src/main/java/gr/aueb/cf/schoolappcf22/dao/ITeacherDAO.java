package gr.aueb.cf.schoolappcf22.dao;

import gr.aueb.cf.schoolappcf22.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolappcf22.model.Teacher;

import java.util.List;

public interface ITeacherDAO {

    Teacher insert(Teacher teacher) throws TeacherDAOException;
    Teacher update(Teacher teacher) throws TeacherDAOException;
    void delete(int id) throws TeacherDAOException;
    List<Teacher> getByLastname(String lastname) throws TeacherDAOException;
    Teacher getById(int id) throws TeacherDAOException;
}
