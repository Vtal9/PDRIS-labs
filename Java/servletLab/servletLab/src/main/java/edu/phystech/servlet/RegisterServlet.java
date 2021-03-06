package edu.phystech.servlet;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

import static authorisation.AuthorisationManager.createUser;
import static authorisation.AuthorisationManager.userIsExist;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/formRegister.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter write = resp.getWriter();

        ServletContext servletContext = getServletConfig().getServletContext();

        String user = req.getParameter("userName");
        String pass = req.getParameter("userPass");

        if(user == null || pass == null){
            write.println("<h1>Wrong username or password!</h1>");
            RequestDispatcher rd = servletContext.getRequestDispatcher("/formRegister.html");
            rd.include(req, resp);
            return;
        }

        if ("admin".equals(user)) {
            write.println("<h1>You can't create user admin</h1>");
            RequestDispatcher rd = servletContext.getRequestDispatcher("/formRegister.html");
            rd.include(req, resp);
        }
        if (userIsExist(user)) {
            write.println("<h1>This user already exist</h1>");
            RequestDispatcher rd = servletContext.getRequestDispatcher("/formRegister.html");
            rd.include(req, resp);
        }
        createUser(user, pass);
        write.println("<h1>User created successful!</h1>");
        RequestDispatcher rd = servletContext.getRequestDispatcher("/formLogin.html");
        rd.include(req, resp);
    }
}