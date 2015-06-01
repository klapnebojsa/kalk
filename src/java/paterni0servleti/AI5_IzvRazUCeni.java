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
import paterni5bridge.AI5_Korisnik;
import paterni4factory.AI4Korisnik;
/**
 *
 * @author neso
 */
@WebServlet(name = "AI5_IzvRazUCeni", urlPatterns = {"/AI5_IzvRazUCeni"})
public class AI5_IzvRazUCeni extends HttpServlet {
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
        String KojiFormat = request.getParameter("Izv");
        
        AI5_Korisnik korisnik = new AI5_Korisnik(null);
        switch (KojiIzvestaj){
        case "Vp":
            if ("1".equals(KojiFormat)){
                poruka = korisnik.Izvrsi("Vp","1");
            }else{
                poruka = korisnik.Izvrsi("Vp","2");           
            }
            
            break;
        case "Mp":
            if ("1".equals(KojiFormat)){
                poruka = korisnik.Izvrsi("Mp","1");                
            }else{
                 poruka = korisnik.Izvrsi("Mp","2");           
            }
            break;
        }

        HttpSession sesija = request.getSession(false); 
        sesija.setAttribute("parametri", poruka);
        switch (KojiFormat){
        case "1":
            request.getRequestDispatcher("/patAI5_1RazUCeniIzv.jsp").forward(request, response);
            break;
        case "2":
            request.getRequestDispatcher("/patAI5_2RazUCeniIzv.jsp").forward(request, response);
            break;
        }        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private Map<String, String[]> getParameterMap() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.

    }
}