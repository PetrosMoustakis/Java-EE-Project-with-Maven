package gr.aueb.cf.schoolappcf22.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import gr.aueb.cf.schoolappcf22.dao.ITeacherDAO;
import gr.aueb.cf.schoolappcf22.dao.TeacherDAOImpl;
import gr.aueb.cf.schoolappcf22.dao.exceptions.TeacherDAOException;
import gr.aueb.cf.schoolappcf22.dto.TeacherDTO;
import gr.aueb.cf.schoolappcf22.service.ITeacherService;
import gr.aueb.cf.schoolappcf22.service.TeacherServiceImpl;
import gr.aueb.cf.schoolappcf22.validation.Validator;

@WebServlet("/schoolapp/insert")
public class InsertTeacherController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final ITeacherDAO teacherDAO = new TeacherDAOImpl();
	private final ITeacherService teacherServ = new TeacherServiceImpl(teacherDAO);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html; charset=UTF-8");
		request.setAttribute("error", "");
		String firstname = request.getParameter("firstname").trim();
		String lastname = request.getParameter("lastname").trim();
		TeacherDTO teacherDTO = new TeacherDTO();
		teacherDTO.setFirstname(firstname);
		teacherDTO.setLastname(lastname);
		request.setAttribute("insertedTeacher", teacherDTO);
		try {
			String error = Validator.validate(teacherDTO);
			if (!error.equals("")) {
				request.setAttribute("error", error);
				request.getRequestDispatcher("/schoolapp/static/templates/teachersmenu.jsp")
						.forward(request, response);
			}
			teacherServ.insertTeacher(teacherDTO);
			request.getRequestDispatcher("/schoolapp/static/templates/teacherinserted.jsp")
					.forward(request, response);
		} catch (TeacherDAOException e) {
			//e.printStackTrace();
			request.setAttribute("sqlError", true);
			request.getRequestDispatcher("/schoolapp/static/templates/teachersmenu.jsp")
					.forward(request, response);
		}
	}
}

