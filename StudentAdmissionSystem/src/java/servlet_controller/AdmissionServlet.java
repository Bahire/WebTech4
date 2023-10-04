
package servlet_controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.dao.StudentDao;
import model.entity.Student;

@WebServlet(name = "AdmissionServlet", urlPatterns = {"/AdmissionServlet"})
public class AdmissionServlet extends HttpServlet {

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
            Cookie[] cookies = request.getCookies();
            String email = "";
            if(cookies !=null){
                for(Cookie cookie : cookies){
                    if(cookie.getName().equals("login_email")){
                        email = cookie.getValue();
                        Student student = new Student();
                        StudentDao dao = new StudentDao();
                        student.setStudentId(request.getParameter("student_id"));
                        student.setDepartment(request.getParameter("department"));
                        Date dob_date = new Date(0);
                        student.setDob(dob_date.valueOf(request.getParameter("dob")));
                        student.setEmail(email);
                        student.setFaculty(request.getParameter("faculty"));
                        student.setFirstname(request.getParameter("firstname"));
                        student.setLastname(request.getParameter("lastname"));
                        dao.registerStudent(student);
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Admission Page</title>");            
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1> "+student.getStudentId()+" Successful admitted thank you</h1>");
                        out.println("<div><br/><a href='LogoutServlet'>Logout</a></div>");
                        out.println("</body>");
                        out.println("</html>");
                        
                        String to = "root@vmi984338.contaboserver.net";
                        String from = student.getEmail(); 
                        Properties properties = System.getProperties();
                        properties.setProperty("mail.smtp.host", "167.86.73.160");
                        Session session = Session.getDefaultInstance(properties); 
                        try{
                            MimeMessage message = new MimeMessage(session);  
                            message.setFrom(new InternetAddress(from));  
                            message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));  
                            message.setSubject("Admission");  
                            message.setText(student.getStudentId()+ " Successful Registered");  
                            Transport.send(message);  
                            System.out.println("message sent successfully....");  
                        }catch(MessagingException mex){
                            mex.printStackTrace();
                        }
                        
                    }
                }
            }
            
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

}
