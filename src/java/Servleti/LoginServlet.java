/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servleti;

import DAO.BrokerDAO;
import KlaseBaze.Korisnik;
import KlaseBaze.Vlasnik;
import SistOperacije.LoginSO;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author neso
 * Servlet implementation class LoginServlet
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

public void doGet(HttpServletRequest request, HttpServletResponse response) 
			           throws ServletException, java.io.IOException {

    try{	    
        Korisnik k = new Korisnik();
        k.setUserName(request.getParameter("uname"));
        k.setPassword(request.getParameter("passw"));
             
        LoginSO loginSO = new LoginSO(k);
        loginSO.k = k;
        boolean Uslov=(Boolean)loginSO.Izvrsi();
                        
        Vlasnik v = new Vlasnik();
        v.setSifra(k.getVlasnik()); 
        BrokerDAO brokerDAO = new BrokerDAO(v);
        try{v.setNaziv(brokerDAO.citajCellDAO("Naziv"));
        }catch(Exception e){Uslov = false;}
        if (Uslov){            
            HttpSession session = request.getSession();
            try{
                session.invalidate();
            }catch(Exception e){
            }
    
            session = request.getSession(true);	    
            session.setAttribute("currentSessionUser",k);
            session.setAttribute("currentSessionVlasnik",v);            
            response.sendRedirect("Opcije.jsp");    		
        }else {
            PrintWriter out = response.getWriter();  
            response.setContentType("text/html");  
            out.println("<script type=\"text/javascript\">");  
            out.println("alert('Pogresan korisnik ili sifra');");
            out.println("window.location.href = 'Login.jsp';");               
            out.println("</script>"); 
        }
    }catch (Throwable theException){
        System.out.println(theException); 
    }
    }
}