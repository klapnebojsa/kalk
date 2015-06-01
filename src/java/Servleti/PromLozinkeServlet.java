/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servleti;

import Inerfejsi.InterfaceDAO;
import KlaseBaze.Korisnik;
import SistOperacije.IspraviSO;
import SistOperacije.LoginSO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author neso
 * Servlet implementation class PromLozinkeServlet
 */
@WebServlet(name = "PromLozinkeServlet", urlPatterns = {"/PromLozinkeServlet"})
public class PromLozinkeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
@Override
protected void doGet(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    Boolean uReduUser, uReduNoviPass;
    PrintWriter out = response.getWriter(); 
    try{
        Korisnik k = new Korisnik();
        k.setUserName(request.getParameter("unamePL"));
        k.setPassword(request.getParameter("passwPL"));
        k.setNewPassword(request.getParameter("newpasswPL"));
        
        LoginSO loginSO = new LoginSO(k);
        loginSO.k = k;
        uReduUser=(Boolean)loginSO.Izvrsi();        

        String newpassPL = request.getParameter("newpasswPL");
        String renewpasswPL = request.getParameter("renewpasswPL");        
        if (uReduUser && newpassPL.equals(renewpasswPL)){
            IspraviSO ispraviSO = new IspraviSO(k);
            ispraviSO.KoJe = 555;
            uReduNoviPass=(Boolean)ispraviSO.Izvrsi();                       
            if (uReduNoviPass){               
                response.setContentType("text/html");  
                out.println("<script type=\"text/javascript\">");  
                out.println("alert('Podaci su uspesno upisani');");
                out.println("window.location.href = 'PromLozinke.jsp';");               
                out.println("</script>");                
            }else{
                response.setContentType("text/html");  
                out.println("<script type=\"text/javascript\">");  
                out.println("alert('Podaci nisu upisani');");
                out.println("window.location.href = 'PromLozinke.jsp';");               
                out.println("</script>");                
            }
                
        }else{ 
            response.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Neispravni podaci');");
            out.println("window.location.href = 'PromLozinke.jsp';");               
            out.println("</script>");             
        } 
    }catch (Throwable theException){
        System.out.println(theException); 
    }    
        
    }
    
    /*protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PromLozinkeServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PromLozinkeServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }*/

    private InterfaceDAO Korisnik() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }*/

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    /*@Override
    public String getServletInfo() {
        return "Short description";
    }*/
    // </editor-fold>

}
