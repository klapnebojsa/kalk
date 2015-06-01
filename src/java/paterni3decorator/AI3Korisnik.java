/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni3decorator;

import paterni2builder.AIIZMP;
import paterni2builder.AIIZVP;
import paterni1abstractfactory.AI_IZVESTAJ;
import paterni2builder.AII_IZVESTAJ;

/**
 *
 * @author neso
 */
public class AI3Korisnik implements AI3KOMPONENTA{ //ConcreteComponent 
    
    AII_IZVESTAJ izv;
    String parametri = null;
    String datum, dat;
    public AI3Korisnik(AII_IZVESTAJ izv1, String Datum){izv = izv1; dat = Datum;}
    String konstruisi(){
        izv.izaberiBrDokumenta();
        izv.izaberiDatum();
        izv.izaberiKalkulaciju();
        izv.izaberiOpis();
        izv.izaberiRazUCeni();
        izv.KreirajIzvestaj();
        //1,Vraca kreiran izvestaj - Nije bitno na koji nacin kreira izvestaj. To radi bilo koji kreacionu patern
        //Definise interfejs za objekat kome ce biti dodata odgovornost dinamicki
        parametri = izv.VratiIzvestaj();
        return parametri;
    }
    public void Izvrsi (String KojaKalk){
        String kojaKalk = KojaKalk;        
        AI3Korisnik kor = null;
        switch (kojaKalk){
        case "Vp": 
            AIIZVP vp = new AIIZVP(); //ConcreteBuilder1
            kor = new AI3Korisnik(vp, dat);
            parametri = kor.konstruisi();
            break;
        case "Mp":
            AIIZMP mp = new AIIZMP(); //ConcreteBuilder2         
            kor = new AI3Korisnik(mp, dat);
            parametri = kor.konstruisi();            
            break;
        } 
        AI3Datum datum = new AI3Datum(kor, dat);
        parametri += "," + datum.prikaziIzvestaj(); 
    }
    public String prikaziIzvestaj(){
        return parametri;
    }
}
