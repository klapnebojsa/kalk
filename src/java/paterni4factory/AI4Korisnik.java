/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni4factory;

/**
 *
 * @author neso
 */
public class AI4Korisnik {  
    AI4_IZVESTAJ izv;
    String parametri = null;

    public AI4Korisnik(AI4_IZVESTAJ izvestaj1){
        izv = izvestaj1;
    } 
    void Kreiraj(){izv.Kreiraj();}

    public String Izvrsi (String KojaKalk){
        String kojaKalk = KojaKalk;
        AI4Korisnik korisnik;
        
        switch (kojaKalk){
        case "Vp":
            AI4IZVP vp = new AI4IZVP();
            korisnik = new AI4Korisnik(vp);
            vp.Kreiraj();
            parametri = vp.vratiIzvestaj();            
            break;
        case "Mp":
            AI4IZMP mp = new AI4IZMP();
            korisnik = new AI4Korisnik(mp);
            mp.Kreiraj();
            parametri = mp.vratiIzvestaj();             
            break;
        }        
        return parametri;
    }
    
}
