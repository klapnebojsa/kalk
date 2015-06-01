package Servleti;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.util.Date;
import Inerfejsi.InterfaceDAO;
import KlaseBaze.Konto;
import KlaseBaze.Korisnik;
import DAO.BrokerDAO;
import KlaseBaze.Artikal;
import KlaseBaze.Finnalogstavka;
import KlaseBaze.Finnaloguk;
import KlaseBaze.Mpkalkstavka;
import KlaseBaze.Mpkalkuk;
import KlaseBaze.Partner;
import KlaseBaze.Porez;
import KlaseBaze.Vlasnik;
import KlaseBaze.Vpkalkstavka;
import KlaseBaze.Vpkalkuk;
import Povezivanje.Izracunaj;
import Povezivanje.Odredi;
import Povezivanje.ProcitajCell;
import java.io.IOException;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import Povezivanje.Provere;
import SistOperacije.BrisiSO;
import SistOperacije.BrisiZavisniSO;
import SistOperacije.IspraviSO;
import SistOperacije.IspraviZavisniSO;
import SistOperacije.NoviSO;
import SistOperacije.NoviZavisniSO;
import java.math.BigDecimal;
import java.util.List;
import javax.servlet.http.HttpSession;
/**
 *
 * @author neso
 */
@WebServlet(name = "PiBrIzServlet", urlPatterns = {"/PiBrIzServlet"})
public class PiBrIzServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private InterfaceDAO k;
    private InterfaceDAO n;

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
        
        Boolean uReduDAO = false;
        Boolean postojiDAO = false;
        String TextPoruke = "";
        String imaNeispravnih = "false";
        String[] NizStr;                    
        String pomoc=null;
        Double CifraDbl = null;
        Double fnkD = null;
        Double fnkP = null;                    
        Double fnkS = null;
        Integer skI = null;
        Double skD = null;        
        Date dt;
        String VratiJSP="";
        Double procPor = 0.00;        
        try{
            String Poruka=null;
            @SuppressWarnings("unchecked")
            
            String cek = request.getParameter("Cekiran");
            String sif = request.getParameter("Sifra");
            String zeli = request.getParameter("Zeli");
            String BrPage = request.getParameter("BrPage");         
            String vred = request.getParameter("Vrednosti");
            String ipolja = request.getParameter("ImePolja");
            String jnovi = request.getParameter("JesteNovi");            

            //Obicna klasa ili kada ima nadredjena onda je podredjena
            InterfaceDAO k1 = k;
            //Nadredjeni interface (klasa). Kada klasa ima klasu u kojoj su zbirni podaci. Npr. finnalogStavka i finnalogUkupno
            InterfaceDAO kn = n;
            kn = null;
            //---------------------------------
                    
            HttpSession sesija = request.getSession(false); 
            
            //Podaci u cekiranom elementu
            @SuppressWarnings("unchecked")
            List myDataList=(List)sesija.getAttribute("myDList");

            String KoZove = sesija.getAttribute("KoZove").toString();
            //Ovde odrediti ko zove  i na osnovu toga definisati klasu k i definisati sifru klase
            Provere provere = new Provere();
            Vlasnik currVlasnik =((Vlasnik)(sesija.getAttribute("currentSessionVlasnik")));            
            switch (KoZove){
                case "Konto": 
                    VratiJSP="/KontniPlan.jsp?jNovi=";
                    Konto kKonto = new Konto();
                    if (request.getParameter("Novi") != null || "".equals(sif) || sif==null) {
                        //jesteNovi sluzi samo da bi se u jsp fajlu dodao jos jedan prazan red u listu
                        sesija.setAttribute("jesteNovi", "novi"); jnovi = "novi";
                        try{String[] SifreKonta= request.getParameterValues("SKonta");sif = SifreKonta[0];} catch(Exception e){}                                                             
                    }   
                    kKonto.setKonto(sif);
                    //Slozena sifra. Sluzi za tabele u kojima se vodi racuna kojoj firmi pripada
                    kKonto.setVlasnik(currVlasnik.getSifra());  
                    kKonto.setNazivKonta(request.getParameter("NKonta"));
                    k1 = kKonto; 
                    break;
                
                case "Porez": 
                    VratiJSP="/PoreskeStope.jsp?jNovi=";
                    Porez pPorez = new Porez();
                    if (request.getParameter("Novi") != null || "".equals(sif) || sif==null) {
                        //jesteNovi sluzi samo da bi se u jsp fajlu dodao jos jedan prazan red u listu
                        sesija.setAttribute("jesteNovi", "novi"); jnovi = "novi";
                        request.setAttribute("PPoreza", "");
                        try{String[] SifrePoreza= request.getParameterValues("SPoreza");sif = SifrePoreza[0];} catch(Exception e){}                                                             
                    }
                    pPorez.setIdPoPorez(sif);
                    BigDecimal kk = null;
                    if (!provere.proveriNotBigDecimal(request.getParameter("PPoreza"))) kk= new BigDecimal (request.getParameter("PPoreza"));
                    pPorez.setPoProcPoreza(kk);                      
                    pPorez.setPoOpis(request.getParameter("NPoreza"));
                    k1 = pPorez;                    
                    break;                      
                  
                case "Korisnik":
                    VratiJSP="/Korisnici.jsp?jNovi=";
                    Korisnik kKorisnik = new Korisnik();
                    if (request.getParameter("Novi") != null || "".equals(sif) || sif==null) {
                        sesija.setAttribute("jesteNovi", "novi"); jnovi = "novi";
                        try{String[] Kor1= request.getParameterValues("SifKor");sif = Kor1[0];} catch(Exception e){}    
                    }     
                    kKorisnik.setUserName(sif);
                    kKorisnik.setPassword(request.getParameter("PassKor")); 
                    //ComboBox
                    String VlKor = request.getParameter("VlaKor");
                    try{
                        NizStr = VlKor.split(" - ");
                        VlKor = NizStr[0];                        
                    }catch(Exception e){}
                    
                    kKorisnik.setVlasnik(VlKor);
                    kKorisnik.setPrezime(request.getParameter("PreKor"));
                    kKorisnik.setIme(request.getParameter("ImeKor")); 

                    if (!provere.proveriNotInteger(request.getParameter("NivoKor"))) skI= Integer.parseInt(request.getParameter("NivoKor"));
                    kKorisnik.setNivo(skI);
                    k1 = kKorisnik; 
                    break;
                    
                case "Partner":
                    VratiJSP="/Partneri.jsp?jNovi=";
                    Partner pPartner = new Partner();                                   
                    if (request.getParameter("Novi") != null || "".equals(sif) || sif==null) {
                        //jesteNovi sluzi samo da bi se u jsp fajlu dodao jos jedan prazan red u listu
                        sesija.setAttribute("jesteNovi", "novi"); jnovi = "novi";
                        try{String[] Kor1= request.getParameterValues("SifPar");sif = Kor1[0];} catch(Exception e){}    
                    }                         
                    pPartner.setIdPartner(sif);
                    pPartner.setNazivPartnera(request.getParameter("NazPar"));  
                    pPartner.setAdresa(request.getParameter("AdrPar"));
                    pPartner.setMesto(request.getParameter("MesPar"));
                    pPartner.setTel(request.getParameter("TelPar")); 
                    pPartner.setMb(request.getParameter("MbrPar"));  
                    pPartner.setPib(request.getParameter("PIBPar"));
                    pPartner.setPdv(request.getParameter("PDVPar"));
                    pPartner.setDlatnost(request.getParameter("DelPar"));                    
                    k1 = pPartner;
                    break;  
                    
                case "Vlasnik": 
                    VratiJSP="/Vlasnici.jsp?jNovi=";
                    Vlasnik vVlasnik = new Vlasnik();
                    if (request.getParameter("Novi") != null || "".equals(sif) || sif==null) {
                        //jesteNovi sluzi samo da bi se u jsp fajlu dodao jos jedan prazan red u listu
                        sesija.setAttribute("jesteNovi", "novi"); jnovi = "novi";
                        try{String[] SifreVlasnika= request.getParameterValues("SVlasnika");sif = SifreVlasnika[0];} catch(Exception e){}                                                            
                    }    
                    vVlasnik.setSifra(sif); 
                    vVlasnik.setNaziv(request.getParameter("NVlasnika"));
                    k1 = vVlasnik;                       
                    break;
                    
                case "Artikal": 
                    VratiJSP="/Artikli.jsp?jNovi=";
                    Artikal aArtikal = new Artikal();
                    if (request.getParameter("Novi") != null || "".equals(sif) || sif==null) {
                        sesija.setAttribute("jesteNovi", "novi"); jnovi = "novi";
                        try{String[] Kor1= request.getParameterValues("ASifr");sif = Kor1[0];} catch(Exception e){}    
                    }   
                    aArtikal.setIdArtikal(sif);
                    //Slozena sifra. Sluzi za tabele u kojima se vodi racuna kojoj firmi pripada
                    aArtikal.setArVlasnik(currVlasnik.getSifra());                     
                    aArtikal.setArOpis(request.getParameter("AOpis"));
                    aArtikal.setArDim(request.getParameter("ADime"));                    
                    //ComboBox
                    String APors = request.getParameter("APorS");
                    try{
                        NizStr = APors.split(" - ");
                        APors = NizStr[0];                        
                    }catch(Exception e){}
                    aArtikal.setArPorStopa(APors);

                    k1 = aArtikal; 
                    break;
                    
                case "Finnaloguk":
                    VratiJSP="/FinansijskiNalog.jsp?jNovi=";
                    Finnaloguk fFinnaloguk = new Finnaloguk();
                    if (request.getParameter("Novi") != null || "".equals(sif) || sif==null) {
                        //jesteNovi sluzi samo da bi se u jsp fajlu dodao jos jedan prazan red u listu
                        sesija.setAttribute("jesteNovi", "novi"); jnovi = "novi";
                        try{String[] SifreFN= request.getParameterValues("SIdFN");sif = SifreFN[0];} catch(Exception e){}                                                             
                    }   
                    //Slozena sifra. Sluzi za tabele u kojima se vodi racuna kojoj firmi pripada
                    fFinnaloguk.setIdFNUK(sif);                    
                    fFinnaloguk.setIdVlasnik(currVlasnik.getSifra());  
                    //regularno polje
                    fFinnaloguk.setFNBrDokumentaUK(request.getParameter("NBrDok"));
                    //Datum
                    fFinnaloguk.setFNBrDokumentaUK(request.getParameter("NBrDok"));
                    dt = provere.dajDate(request.getParameter("NDatum"));
                    fFinnaloguk.setFNDatumUK(dt);                    
                    
                    pomoc=null;
                    // Ovo mora za polja koja su readonly, jer polja koja su diabled ne mogu se videti vrednosti u servlet-u
                    try{String[] NDuguj= request.getParameterValues("NDuguj");pomoc = NDuguj[Integer.parseInt(cek)].replace(",","");} catch(Exception e){ pomoc=null;}
                    if (!provere.proveriNotDouble(pomoc)) fnkD=Double.parseDouble(pomoc);                      
                    fFinnaloguk.setFNDugujeUK(fnkD);
                    
                    try{String[] NPotra= request.getParameterValues("NPotra");pomoc = NPotra[Integer.parseInt(cek)].replace(",","");} catch(Exception e){ pomoc=null;}                    
                    if (!provere.proveriNotDouble(pomoc)) fnkP= Double.parseDouble(pomoc);                        
                    fFinnaloguk.setFNPotrazujeUK(fnkP);
                    
                    try{String[] NSaldo= request.getParameterValues("NSaldo");pomoc = NSaldo[Integer.parseInt(cek)].replace(",","");} catch(Exception e){ pomoc=null;}                    
                    if (!provere.proveriNotDouble(pomoc)) fnkS= Double.parseDouble(pomoc);                        
                    fFinnaloguk.setFNSaldoUK(fnkS);                  
                    
                    k1 = fFinnaloguk; 
                    sesija.setAttribute("BrDok", sif);
                    break; 
                    
                case "Finnalogstavka":
                    VratiJSP="/FinNalogStavka.jsp?jNovi=";
                    //Nadredjeni inerface (klasa) - Setovanje osnovnih parametara
                    Finnaloguk fFinnalogukn = new Finnaloguk();

                    fFinnalogukn.setIdFNUK(sesija.getAttribute("BrDok").toString());                    
                    fFinnalogukn.setIdVlasnik(currVlasnik.getSifra());
                    kn=fFinnalogukn;
                    //--------------------------------
                    
                    //Regularni interface (klasa)
                    Finnalogstavka fFinnalogustavka = new Finnalogstavka();                    
                    //Slozena sifra. Sluzi za tabele u kojima se vodi racuna kojoj firmi pripada                    
                    fFinnalogustavka.setFNIdFN(sesija.getAttribute("BrDok").toString());                   
                    fFinnalogustavka.setFNIdVlasnik(currVlasnik.getSifra());                     
                    if (request.getParameter("Novi") != null || "".equals(sif) || sif==null) {
                        //jesteNovi sluzi samo da bi se u jsp fajlu dodao jos jedan prazan red u listu
                        sesija.setAttribute("jesteNovi", "novi"); jnovi = "novi";
                        //Procedura za odredjivanje novog broja stavke
                        Odredi odredi = new Odredi();
                        k1 = fFinnalogustavka;
                        sif = odredi.BrStavke("FNIdStavke", k1);                      
                    } 
                    request.setAttribute("oznXX", sif);                                     
                    //Setovanje broja stavke
                    try {
                        skI=Integer.parseInt(sif);
                        fFinnalogustavka.setFNIdStavke(skI);                    
                    }catch(Exception e){}

                    fFinnalogustavka.setFNBrDokumenta(request.getParameter("NBrDok"));                                          
                    //ComboBox
                    String NPartne = request.getParameter("NPartn");
                    try{
                        NizStr = NPartne.split(" - ");
                        NPartne = NizStr[0];                        
                    }catch(Exception e){}                    
                    fFinnalogustavka.setFNPartner(NPartne);                   
                     //ComboBox
                    String KKonto = request.getParameter("NKonto");
                    try{
                        NizStr = KKonto.split(" - ");
                        KKonto = NizStr[0];                        
                    }catch(Exception e){}                    
                    fFinnalogustavka.setFNKonto(KKonto);
                    
                    skD=null;
                    if (!provere.proveriNotDouble(request.getParameter("NDuguj"))) skD= Double.parseDouble(request.getParameter("NDuguj"));
                    fFinnalogustavka.setFNDuguje(skD);
                    
                    skD=null;
                    if (!provere.proveriNotDouble(request.getParameter("NPotra"))) skD= Double.parseDouble(request.getParameter("NPotra"));
                    fFinnalogustavka.setFNPotrazuje(skD);                     
                       
                    fFinnalogustavka.setFNSaldo();

                    k1 = fFinnalogustavka; 
                    break;
                    
                case "VpKalkulacija":
                    VratiJSP="/VpKalkulacija.jsp?jNovi=";
                    Vpkalkuk vpkalkuk = new Vpkalkuk();
                    if (request.getParameter("Novi") != null || "".equals(sif) || sif==null) {
                        //jesteNovi sluzi samo da bi se u jsp fajlu dodao jos jedan prazan red u listu
                        sesija.setAttribute("jesteNovi", "novi"); jnovi = "novi";
                        try{String[] SifreFN= request.getParameterValues("VKalk");sif = SifreFN[0];} catch(Exception e){}                                                             
                    }   
                    //Slozena sifra. Sluzi za tabele u kojima se vodi racuna kojoj firmi pripada
                    vpkalkuk.setVpIdKalkUK(sif);                    
                    vpkalkuk.setVpIdVlasnikUK(currVlasnik.getSifra());  
                    //regularno polje
                    vpkalkuk.setVPBrDokumentaUK(request.getParameter("VBrDo"));
                    //Datum
                    dt = provere.dajDate(request.getParameter("VDatu"));
                    vpkalkuk.setVpDatumUK(dt);
                    //Datum
                    dt = provere.dajDate(request.getParameter("VValu"));
                    vpkalkuk.setVpValutaUK(dt);   
                     //ComboBox                    
                    String VDoba = request.getParameter("VDoba");
                    try{
                        NizStr = VDoba.split(" - ");
                        VDoba = NizStr[0];                        
                    }catch(Exception e){}                    
                    vpkalkuk.setVpDobavljac(VDoba);                                      
                                
                    pomoc=null;
                    try{pomoc = request.getParameter("VUPor").replace(",","");}catch(Exception e){pomoc=null;}
                    if (!provere.proveriNotDouble(pomoc)) {CifraDbl=Double.parseDouble(pomoc);}                      
                    vpkalkuk.setVpUplacenPorezUK(CifraDbl);
                    
                    // Ovo mora za polja koja su readonly, jer polja koja su diabled ne mogu se videti vrednosti u servlet-u                                        
                    CifraDbl=null;
                    try{String[] VNaba= request.getParameterValues("VNaba");pomoc = VNaba[Integer.parseInt(cek)].replace(",","");} catch(Exception e){ pomoc=null;}                    
                    if (!provere.proveriNotDouble(pomoc)) CifraDbl= Double.parseDouble(pomoc);                        
                    vpkalkuk.setVpNabCenaUK(CifraDbl);
                    
                    CifraDbl=null;
                    try{String[] VPCen= request.getParameterValues("VPCen");pomoc = VPCen[Integer.parseInt(cek)].replace(",","");} catch(Exception e){ pomoc=null;}                    
                    if (!provere.proveriNotDouble(pomoc)) CifraDbl= Double.parseDouble(pomoc);                        
                    vpkalkuk.setVPCenaUK(CifraDbl);
                    
                    CifraDbl=null;                    
                    try{String[] VPRaz= request.getParameterValues("VPRaz");pomoc = VPRaz[Integer.parseInt(cek)].replace(",","");} catch(Exception e){ pomoc=null;}                    
                    if (!provere.proveriNotDouble(pomoc)) CifraDbl= Double.parseDouble(pomoc);                        
                    vpkalkuk.setVPRazUCeniUK(CifraDbl);
                    
                    CifraDbl=null;                   
                    try{String[] VPPor= request.getParameterValues("VPPor");pomoc = VPPor[Integer.parseInt(cek)].replace(",","");} catch(Exception e){ pomoc=null;}                    
                    if (!provere.proveriNotDouble(pomoc)) CifraDbl= Double.parseDouble(pomoc);                        
                    vpkalkuk.setVpPorezUK(CifraDbl);
                    
                    k1 = vpkalkuk; 
                    sesija.setAttribute("BrDok", sif);
                    break;                   
                    
                case "VpKalkStavka":
                    VratiJSP="/VpKalkStavka.jsp?jNovi=";
                    //Nadredjeni inerface (klasa) - Setovanje osnovnih parametara
                    vpkalkuk = new Vpkalkuk();

                    vpkalkuk.setVpIdKalkUK(sesija.getAttribute("BrDok").toString());                    
                    vpkalkuk.setVpIdVlasnikUK(currVlasnik.getSifra());
                    kn=vpkalkuk;
                    //--------------------------------
                    
                    //Regularni interface (klasa)
                    Vpkalkstavka vpkalkstavka = new Vpkalkstavka();                  
                    //Slozena sifra. Sluzi za tabele u kojima se vodi racuna kojoj firmi pripada                    
                    vpkalkstavka.setVpIdKalk(sesija.getAttribute("BrDok").toString());                   
                    vpkalkstavka.setVpIdVlasnik(currVlasnik.getSifra());                     
                    if (request.getParameter("Novi") != null || "".equals(sif) || sif==null) {
                        //jesteNovi sluzi samo da bi se u jsp fajlu dodao jos jedan prazan red u listu
                        sesija.setAttribute("jesteNovi", "novi"); jnovi = "novi";   
                        //Procedura za odredjivanje novog broja stavke
                        Odredi odredi = new Odredi();
                        k1 = vpkalkstavka;
                        sif = odredi.BrStavke("VpIdStavke", k1);                      
                    } 
                    request.setAttribute("oznXX", sif);                    
                    //Setovanje broja stavke
                    try {
                        skI=Integer.parseInt(sif);
                        vpkalkstavka.setVpIdStavke(skI);                    
                    }catch(Exception e){}                      
                    //ComboBox
                    String AArtikal = request.getParameter("VIdAr");
                    try{
                        NizStr = AArtikal.split(" - ");
                        AArtikal = NizStr[0];                        
                    }catch(Exception e){}                    
                    vpkalkstavka.setVpIdArtikla(AArtikal);
                    //Double za unos
                    skD=null;
                    if (!provere.proveriNotDouble(request.getParameter("VKoli"))) skD= Double.parseDouble(request.getParameter("VKoli"));
                    vpkalkstavka.setVpKol(skD);
                    skD=null;
                    if (!provere.proveriNotDouble(request.getParameter("VNCen"))) skD= Double.parseDouble(request.getParameter("VNCen"));
                    vpkalkstavka.setVpNabCena(skD);                     
                    skD=null;
                    if (!provere.proveriNotDouble(request.getParameter("VVCen"))) skD= Double.parseDouble(request.getParameter("VVCen"));
                    vpkalkstavka.setVpCena(skD); 

                    //Double racunanje                                        
                    vpkalkstavka.setVpNabCenaSt();                    
                    vpkalkstavka.setVpCenaSt();                     
                    vpkalkstavka.setVpRazUCeniSt();
                    
                    //Odredjivanje procenta poreske stope            
                    String porStopa = null;
                    if (AArtikal !=null && AArtikal!=""){
                        Artikal a = new Artikal();
                        a.setIdArtikal(AArtikal);
                        a.setArVlasnik(currVlasnik.getSifra());
                        InterfaceDAO u=a;
                        ProcitajCell procitajCell = new ProcitajCell();
                        porStopa = procitajCell.Jedan(u,"ArPorStopa");
                        if (porStopa !=null && porStopa!=""){
                            Porez p = new Porez();
                            p.setIdPoPorez(porStopa);
                            u = p;                       
                            procPor = Double.parseDouble(procitajCell.Jedan(u,"PoProcPoreza"))/100;
                        }                        
                    }
                    Izracunaj izracunaj = new Izracunaj();
                    String porez = izracunaj.Proizvod(vpkalkstavka.getVpNabCenaSt().toString(), String.valueOf(procPor));
                    skD=null;
                    if (!provere.proveriNotDouble(porez)) skD= Double.parseDouble(porez);                  
                    vpkalkstavka.setVpPorezSt(skD); 
                    
                    k1 = vpkalkstavka; 
                    break; 
                    
                case "MpKalkulacija":
                    VratiJSP="/MpKalkulacija.jsp?jNovi=";
                    Mpkalkuk mpkalkuk = new Mpkalkuk();
                    if (request.getParameter("Novi") != null || "".equals(sif) || sif==null) {
                        //jesteNovi sluzi samo da bi se u jsp fajlu dodao jos jedan prazan red u listu
                        sesija.setAttribute("jesteNovi", "novi"); jnovi = "novi";
                        try{String[] SifreFN= request.getParameterValues("MKalk");sif = SifreFN[0];} catch(Exception e){}                                                             
                    }   
                    //Slozena sifra. Sluzi za tabele u kojima se vodi racuna kojoj firmi pripada
                    mpkalkuk.setMpIdKalkUK(sif);                    
                    mpkalkuk.setMpIdVlasnikUK(currVlasnik.getSifra());  
                    //regularno polje
                    mpkalkuk.setMpBrDokumenta(request.getParameter("MBrDo"));
                    //Datum
                    dt = provere.dajDate(request.getParameter("MDatu"));
                    mpkalkuk.setMpDatumUK(dt);
                    //Datum
                    dt = provere.dajDate(request.getParameter("MValu"));
                    mpkalkuk.setMpValutaUK(dt);   
                     //ComboBox                    
                    String MKupa = request.getParameter("MKupa");
                    try{
                        NizStr = MKupa.split(" - ");
                        MKupa = NizStr[0];                        
                    }catch(Exception e){}                    
                    mpkalkuk.setMpKupac(MKupa);                                      
                    
                    // Ovo mora za polja koja su readonly, jer polja koja su diabled ne mogu se videti vrednosti u servlet-u                                        
                    CifraDbl=null;
                    try{String[] MNaba= request.getParameterValues("MNaba");pomoc = MNaba[Integer.parseInt(cek)].replace(",","");} catch(Exception e){ pomoc=null;}                    
                    if (!provere.proveriNotDouble(pomoc)) CifraDbl= Double.parseDouble(pomoc);                        
                    mpkalkuk.setMpNabCenaUK(CifraDbl);
                    
                    CifraDbl=null;
                    try{String[] MPCen= request.getParameterValues("MPCen");pomoc = MPCen[Integer.parseInt(cek)].replace(",","");} catch(Exception e){ pomoc=null;}                    
                    if (!provere.proveriNotDouble(pomoc)) CifraDbl= Double.parseDouble(pomoc);                        
                    mpkalkuk.setMPCenaUK(CifraDbl);
                    
                    CifraDbl=null;                    
                    try{String[] MPRaz= request.getParameterValues("MPRaz");pomoc = MPRaz[Integer.parseInt(cek)].replace(",","");} catch(Exception e){ pomoc=null;}                    
                    if (!provere.proveriNotDouble(pomoc)) CifraDbl= Double.parseDouble(pomoc);                        
                    mpkalkuk.setMPRazUCeniUK(CifraDbl);
                    
                    CifraDbl=null;                   
                    try{String[] MPPor= request.getParameterValues("MPPor");pomoc = MPPor[Integer.parseInt(cek)].replace(",","");} catch(Exception e){ pomoc=null;}                    
                    if (!provere.proveriNotDouble(pomoc)) CifraDbl= Double.parseDouble(pomoc);                        
                    mpkalkuk.setMpPorezUK(CifraDbl);
                    
                    k1 = mpkalkuk; 
                    sesija.setAttribute("BrDok", sif);
                    break;
                    
                case "MpKalkStavka":
                    VratiJSP="/MpKalkStavka.jsp?jNovi=";
                    //Nadredjeni inerface (klasa) - Setovanje osnovnih parametara
                    mpkalkuk = new Mpkalkuk();

                    mpkalkuk.setMpIdKalkUK(sesija.getAttribute("BrDok").toString());                    
                    mpkalkuk.setMpIdVlasnikUK(currVlasnik.getSifra());
                    kn=mpkalkuk;
                    //--------------------------------
                    
                    //Regularni interface (klasa)
                    Mpkalkstavka mpkalkstavka = new Mpkalkstavka();                  
                    //Slozena sifra. Sluzi za tabele u kojima se vodi racuna kojoj firmi pripada                    
                    mpkalkstavka.setMpIdKalk(sesija.getAttribute("BrDok").toString());                   
                    mpkalkstavka.setMpIdVlasnik(currVlasnik.getSifra());                     
                    if (request.getParameter("Novi") != null || "".equals(sif) || sif==null) {
                        //jesteNovi sluzi samo da bi se u jsp fajlu dodao jos jedan prazan red u listu
                        sesija.setAttribute("jesteNovi", "novi"); jnovi = "novi";   
                        //Procedura za odredjivanje novog broja stavke
                        Odredi odredi = new Odredi();
                        k1 = mpkalkstavka;
                        sif = odredi.BrStavke("MpIdStavke", k1);                      
                    } 
                    request.setAttribute("oznXX", sif);                    
                    //Setovanje broja stavke
                    try {
                        skI=Integer.parseInt(sif);
                        mpkalkstavka.setMpIdStavke(skI);                    
                    }catch(Exception e){}                      
                    //ComboBox
                    AArtikal = request.getParameter("MIdAr");
                    try{
                        NizStr = AArtikal.split(" - ");
                        AArtikal = NizStr[0];                        
                    }catch(Exception e){}                    
                    mpkalkstavka.setMpIdArtikla(AArtikal);
                    //Double za unos
                    skD=null;
                    if (!provere.proveriNotDouble(request.getParameter("MKoli"))) skD= Double.parseDouble(request.getParameter("MKoli"));
                    mpkalkstavka.setMpKol(skD);
                    skD=null;
                    if (!provere.proveriNotDouble(request.getParameter("MNCen"))) skD= Double.parseDouble(request.getParameter("MNCen"));
                    mpkalkstavka.setMpNabCena(skD);                     
                    skD=null;
                    if (!provere.proveriNotDouble(request.getParameter("MMCen"))) skD= Double.parseDouble(request.getParameter("MMCen"));
                    mpkalkstavka.setMpCena(skD); 

                    //Double racunanje                                        
                    mpkalkstavka.setMpNabCenaSt();                    
                    mpkalkstavka.setMpCenaSt();                     
                    
                    //Odredjivanje procenta poreske stope            
                    porStopa = null;
                    procPor = 0.00;
                    if (AArtikal !=null && AArtikal!=""){
                        Artikal a = new Artikal();
                        a.setIdArtikal(AArtikal);
                        a.setArVlasnik(currVlasnik.getSifra());
                        InterfaceDAO u=a;
                        ProcitajCell procitajCell = new ProcitajCell();
                        porStopa = procitajCell.Jedan(u,"ArPorStopa");
                        if (porStopa !=null && porStopa!=""){
                            Porez p = new Porez();
                            p.setIdPoPorez(porStopa);
                            u = p;                       
                            procPor = 1 - (1 / (1 + Double.parseDouble(procitajCell.Jedan(u,"PoProcPoreza"))/100));
                        }                        
                    }
                    izracunaj = new Izracunaj();
                    porez = izracunaj.Proizvod(mpkalkstavka.getMpCenaSt().toString(), String.valueOf(procPor));
                    skD=null;
                    if (!provere.proveriNotDouble(porez)) skD= Double.parseDouble(porez);                  
                    mpkalkstavka.setMpPorezSt(skD); 
                    mpkalkstavka.setMpRazUCeniSt();
                    
                    k1 = mpkalkstavka; 
                    break;                     
                    
                default:
                    //k = new Konto();       
            }
            
            BrokerDAO brokerDAO = new BrokerDAO(k1);        
            if ("novi".equals(jnovi)) {               
                vred = brokerDAO.vredNoviDAO();                                                           
            } else {vred = brokerDAO.vredIsprDAO();}            
            
            if (Boolean.parseBoolean(zeli)){    
                TextPoruke = brokerDAO.proveriDAO();
                if (TextPoruke != null) {uReduDAO=true; imaNeispravnih = "true";} 
                postojiDAO=brokerDAO.citajDAO();
                
                    //Odustajanje od upisa
                if (request.getParameter("Odustani") != null) {
                    imaNeispravnih = "false";
                    uReduDAO=true; TextPoruke=" Odustali ste od upisa! ";
                    
                    //Brisanje postojeceg
                }else if (request.getParameter("Brisi") != null && postojiDAO) {
                    imaNeispravnih = "false"; 
                    if (kn==null) {
                        BrisiSO brisiSO = new BrisiSO(k1);
                        uReduDAO=(Boolean)brisiSO.Izvrsi();                    
                        TextPoruke="Uspešno brisanje";
                    }
                    else{
                        BrisiZavisniSO brisiZavisniSO = new BrisiZavisniSO(k1);
                        brisiZavisniSO.n = kn;
                        brisiZavisniSO.p = k1;
                        uReduDAO=(Boolean)brisiZavisniSO.Izvrsi();                                         
                        TextPoruke="Uspešno brisanje";} 
                    
                    //Ovde se samo otvaraju polja u tabeli za novi slog
                }else if (request.getParameter("Novi") != null) {
                    imaNeispravnih = "novi";TextPoruke="";
                    cek = sesija.getAttribute ("elemMyDataList").toString();
                    
                 //Pregled-Unos stavke dokumenata koji imaju zaglavlje i stavke
                }else if (request.getParameter("NovaStav") != null) {
                    imaNeispravnih = "false";TextPoruke="";
                    cek = sesija.getAttribute ("elemMyDataList").toString();
                    switch (KoZove){
                        case "Finnaloguk": 
                            request.getRequestDispatcher("/FinNalogStavka.jsp?jNovi=").forward(request, response); 
                            break; 
                        case "VpKalkulacija": 
                            request.getRequestDispatcher("/VpKalkStavka.jsp?jNovi=").forward(request, response);                             
                            break;
                        case "MpKalkulacija": 
                            request.getRequestDispatcher("/MpKalkStavka.jsp?jNovi=").forward(request, response);                             
                            break;                              
                    }
                   
                }
                //Izmena postojeceg sloga u bazi                
                if ("false".equals(imaNeispravnih)){
                    //Ako se pokusava upis novog sloga
                    if (sesija.getAttribute("jesteNovi")=="novi"){
                        if (request.getParameter("Upisi") != null && !postojiDAO) {
                            if (kn==null) {
                                NoviSO noviSO = new NoviSO(k1);
                                uReduDAO=(Boolean)noviSO.Izvrsi();                                
                            }
                            else{
                                NoviZavisniSO noviZavisniSO = new NoviZavisniSO(k1);
                                noviZavisniSO.n = kn;
                                noviZavisniSO.p = k1;
                                uReduDAO=(Boolean)noviZavisniSO.Izvrsi();                                 
                            }
                            TextPoruke="Uspešan upis";
                        }else if (request.getParameter("Upisi") != null && postojiDAO) {
                            TextPoruke="Uspešan upis. Postoji slog sa unetom šifrom"; 
                            imaNeispravnih = "novi"; uReduDAO =false;                     
                        }
                    //Ako se pokusava ispravka postojeceg sloga    
                        }else if (request.getParameter("Upisi") != null && postojiDAO && imaNeispravnih != "novi") {
                        if (kn==null) {
                            IspraviSO ispraviSO = new IspraviSO(k1);
                            uReduDAO=(Boolean)ispraviSO.Izvrsi();                        
                        }
                        else{
                            IspraviZavisniSO ispraviZavisniSO = new IspraviZavisniSO(k1);
                            ispraviZavisniSO.n = kn;
                            ispraviZavisniSO.p = k1;
                            uReduDAO=(Boolean)ispraviZavisniSO.Izvrsi();                        
                        }
                        TextPoruke= "Uspešna izmena";
                    }  
                }
                //Oznacavanje da je zavrseno sa manipulacijom novog slog
                if (request.getParameter("Novi") == null && uReduDAO && ("false".equals(imaNeispravnih) || "novi".equals(imaNeispravnih)))
                    {sesija.setAttribute("jesteNovi", "false"); jnovi = "";}                 
                // Kreiranje poruke za korisnika o izvrsenju operacija uspesno ili neuspesno                
                if (uReduDAO){
                    Poruka = TextPoruke;
                }else{ 
                    Poruka="!!! Ne" + TextPoruke + "!!!";                    
                }  
            //Ako se odabere opcija cancel u pitanju da li se zeli izmena, upis, brisanje i novi ...                 
            //if (Boolean.parseBoolean(zeli)){
            }else {
                Poruka = "";
                if (request.getParameter("Odustani") != null) {
                    imaNeispravnih = "cancelIzmeni";
                }else if (request.getParameter("Upisi") != null) {
                    Poruka = "Odustali ste od upisa !!!" ; 
                    imaNeispravnih = "cancelIzmeni";
                }else if (request.getParameter("Brisi") != null) {
                    Poruka = "Odustali ste od brisanja !!!" ;                     
                    imaNeispravnih = "cancelBrisi";
                }else if (request.getParameter("Novi") != null) {
                    sesija.setAttribute("jesteNovi", "false");
                    jnovi = "";
                    Poruka = "Odustali ste od otvaranja novog !!!" ; 
                    imaNeispravnih = "false";
                }else if (request.getParameter("NovaStav") != null) {
                    Poruka = "Odustali ste od unosa novih stavki !!!" ;                     
                    imaNeispravnih = "cancelNovaStav";
                }
            }

        request.setAttribute("Cekiran", cek);
        request.setAttribute("Sifra", sif);
        request.setAttribute("Zeli", zeli);
        request.setAttribute("Vrednosti", vred);  
        request.setAttribute("Poruka", Poruka);
        request.setAttribute("BrPage", BrPage);
        request.setAttribute("imaNeispravnih", imaNeispravnih);
        request.setAttribute("JesteNovi", jnovi);
        
        request.getRequestDispatcher(VratiJSP).forward(request, response);
        //switch (KoZove){
           /* case "Konto": 
                request.getRequestDispatcher("/KontniPlan.jsp?jNovi=").forward(request, response);
                break;   */                   
            /*case "Porez": 
                request.getRequestDispatcher("/PoreskeStope.jsp?jNovi=").forward(request, response);
                break;                  
            case "Korisnik": 
                request.getRequestDispatcher("/Korisnici.jsp?jNovi=").forward(request, response);
                break; 
            case "Partner": 
                request.getRequestDispatcher("/Partneri.jsp?jNovi=").forward(request, response);
                break;                 
            case "Vlasnik": 
                request.getRequestDispatcher("/Vlasnici.jsp?jNovi=").forward(request, response);
                break;
            case "Artikal": 
                request.getRequestDispatcher("/Artikli.jsp?jNovi=").forward(request, response);
                break;            
            case "Finnaloguk": 
                request.getRequestDispatcher("/FinansijskiNalog.jsp?jNovi=").forward(request, response);
                break;
            case "Finnalogstavka":
                request.getRequestDispatcher("/FinNalogStavka.jsp?jNovi=").forward(request, response);                
                break;*/               
        //}
            
        }catch (Throwable theException){
            System.out.println(theException); 
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
