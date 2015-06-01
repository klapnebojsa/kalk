package paterni0servleti;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Povezivanje.Provere;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import paterni3decorator.AI3Korisnik;
import paterni3decorator.AI3Datum;
/**
 *
 * @author neso
 */
@WebServlet(name = "AI3_IzvRazUCeni", urlPatterns = {"/AI3_IzvRazUCeni"})
public class AI3_IzvRazUCeni extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public String dat;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");        
        processRequest(request, response);
                                   
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    @SuppressWarnings("fallthrough")
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");        
        processRequest(request, response);
        
        HttpSession sesija = request.getSession(false);         
        String poruka = "";          
        String KojiIzvestaj = request.getParameter("VpMp");
        
        Date dt;
        Provere provere = new Provere();        
        dt = provere.dajDate(request.getParameter("MDatu"));
        String datum = dt.toString();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            datum = sdf.format(dt);
            //dt = provere.dajDate(dd);            
        }catch(Exception e){}
    
        AI3Korisnik korisnik = new AI3Korisnik(null,datum);
        switch (KojiIzvestaj){
        case "Vp":
            korisnik.Izvrsi("Vp"); 
            poruka = korisnik.prikaziIzvestaj();             
            break;
        case "Mp":
            korisnik.Izvrsi("Mp");
            poruka = korisnik.prikaziIzvestaj();            
            break;
        }
        
        /*try{poruka = korisnik.KreirajIzvestaj();
        } catch (Exception e){}*/
        
        sesija.setAttribute("parametri", poruka);
        request.getRequestDispatcher("/patAI3RazUCeniIzv.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Map<String, String[]> getParameterMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
}
