package paterni0servleti;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import paterni6memento.AI6IKorisnik;
/**
 *
 * @author neso
 */
@WebServlet(name = "AI6_IzvRazUCeniMem", urlPatterns = {"/AI6_IzvRazUCeniMem"})
public class AI6_IzvRazUCeniMem extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
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
        
        String poruka = "";       
        String KojiIzvestaj = request.getParameter("VpMp"); 
        HttpSession sesija = request.getSession(false);
        
        AI6IKorisnik korVp = AI6IKorisnik.class.cast(sesija.getAttribute("korisnikVp6"));
        AI6IKorisnik korMp = AI6IKorisnik.class.cast(sesija.getAttribute("korisnikMp6"));             
        
        switch (KojiIzvestaj){
        case "Vp":
            korVp.setkorVp(korVp);      
            poruka = korVp.Izvrsi("Vp");            
            break;
        case "Mp":           
            korMp.setkorMp(korMp);            
            poruka = korMp.Izvrsi("Mp");
            break;
        }
        /*AI6IKorisnik korisnik = new AI6IKorisnik(null);
        poruka = korisnik.Izvrsi(KojiIzvestaj);*/

        //HttpSession sesija = request.getSession(false); 
        sesija.setAttribute("parametri", poruka);
        request.getRequestDispatcher("/patAI6RazUCeniIzv.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Map<String, String[]> getParameterMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
}