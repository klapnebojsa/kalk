/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni2builder;

import paterni2builder.AII_IZVESTAJ;
import paterni2builder.AIIZMP;
import paterni2builder.AIIZVP;

/**
 *
 * @author neso
 */
public class AIIKorisnik { //Director
    AII_IZVESTAJ izvestaj;
    String parametri = null;

    public AIIKorisnik(AII_IZVESTAJ izvestaj1){
        izvestaj = izvestaj1;
    }  
    public String Kreiraj(){
        izvestaj.izaberiBrDokumenta();
        izvestaj.izaberiDatum();
        izvestaj.izaberiKalkulaciju();
        izvestaj.izaberiOpis();
        izvestaj.izaberiRazUCeni();
        
        //1,Poziva kreiranje izvestaja a kreiranje se odvija u ConcreteBuilder i kontrolise izgradnju
        izvestaj.KreirajIzvestaj();
        
        return izvestaj.VratiIzvestaj();
    }
    public String Izvrsi (String KojaKalk){
        String kojaKalk = KojaKalk;
        AIIKorisnik aIIkor;
        
        switch (kojaKalk){
        case "Vp": 
            AIIZVP vp = new AIIZVP();
            aIIkor = new AIIKorisnik(vp);
            parametri = aIIkor.Kreiraj();            
            break;
        case "Mp":
            AIIZMP mp = new AIIZMP();
            aIIkor = new AIIKorisnik(mp);
            parametri = aIIkor.Kreiraj();
            break;
        }        
        return parametri;
    }   
}
