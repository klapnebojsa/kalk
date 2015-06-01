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
import paterni1abstractfactoryklase.AIZMP;
import paterni1abstractfactoryklase.AIZVP;
import paterni1abstractfactory.AIKorisnik;
import javax.servlet.http.HttpSession;
/**
 *
 * @author neso
 */
@WebServlet(name = "AI_IzvRazUCeni", urlPatterns = {"/AI_IzvRazUCeni"})
public class AI_IzvRazUCeni extends HttpServlet {
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
        
        AIKorisnik korisnik = new AIKorisnik(null);
        poruka = korisnik.KreirajIzvestaj(KojiIzvestaj);
        /*AIKorisnik korisnik = new AIKorisnik(null);
        switch (KojiIzvestaj){
        case "Vp":      
            AIZVP VP = new AIZVP();
            korisnik = new AIKorisnik(VP);
            break;
        case "Mp":
            AIZMP MP = new AIZMP();
            korisnik = new AIKorisnik(MP);            
            break;
        }*/
        
        /*try{poruka = korisnik.KreirajIzvestaj();
        } catch (Exception e){}*/
        
        HttpSession sesija = request.getSession(false); 
        sesija.setAttribute("parametri", poruka);
        request.getRequestDispatcher("/patAIRazUCeniIzv.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Map<String, String[]> getParameterMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
}
