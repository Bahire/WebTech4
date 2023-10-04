/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet_controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.UserDao;
import model.entity.Users;


@WebServlet(name = "SignUpServlet", urlPatterns = {"/SignUpServlet"})
public class SignUpServlet extends HttpServlet {



    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try(PrintWriter out = response.getWriter()){
            Users user = new Users();
            user.setEmail(request.getParameter("email"));
            user.setPassword(request.getParameter("passwd"));
            UserDao dao = new UserDao();
            dao.registerUser(user);
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Registration successful</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1> "+user.getEmail()+" Successful Registered thank you</h1>");
            out.println("<div><br/> <span>Do you wish to Login or create new Account?</span><a href='Login.html'>Login</a> &nbsp <a href='Sign Up.html'>SignUp</a></div>");
            out.println("</body>");
            out.println("</html>");
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }


}
