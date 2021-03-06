package com.servlets;

import com.alldaos.EmployeeDAO;
import com.models.Employee;
import com.utils.CurrentUser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmployeeServlet extends HttpServlet {
    EmployeeDAO employeeDAO = new EmployeeDAO();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //super.doGet(req, resp);
        //Employee employee = employeeDAO.read(1);
        //resp.getWriter().println(employee.getUsername());
        String username = String.valueOf(req.getParameter("username_input_sign_in"));
        String password = String.valueOf(req.getParameter("password_input_sign_in"));

        CurrentUser.currentUser = employeeDAO.checkLoginInfo(username, password);

        req.getRequestDispatcher("employeePage.html").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = String.valueOf(req.getParameter("username_input"));
        String password = String.valueOf(req.getParameter("password_input"));
        String employee_type = String.valueOf(req.getParameter("type_input"));



            Employee employee = new Employee(username, password, employee_type);
            employeeDAO.create(employee);

            req.getRequestDispatcher("index.html").forward(req,resp);
        }
    }

