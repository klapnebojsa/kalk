/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni1abstractfactory;

import paterni1abstractfactoryproductinterfejsi.AI_BrojDokumenta;
import paterni1abstractfactoryproductinterfejsi.AI_Datum;
import paterni1abstractfactoryproductinterfejsi.AI_KojaKalk;
import paterni1abstractfactoryproductinterfejsi.AI_Opis;
import paterni1abstractfactoryproductinterfejsi.AI_RazUCeni;
import paterni1abstractfactoryklase.AIZMP;
import paterni1abstractfactoryklase.AIZVP;

/**
 *
 * @author neso
 */
public class AIKorisnik{ //Client
    AI_IZVESTAJ izvestaj;               //AbstracFactory
    AI_BrojDokumenta kojiBrojDokumenta; //AbstracProductA
    AI_Datum kojiDatum;                 //AbstracProductB
    AI_KojaKalk kojaKalk;               //AbstracProductC
    AI_Opis kojiOpis;                   //AbstracProductD
    AI_RazUCeni kojaRazUCeni;           //AbstracProductE
    
    AI_Izvest izv;
    public AIKorisnik(AI_IZVESTAJ izvestaj1){izvestaj=izvestaj1; izv=new AI_Izvest();}

    public String KreirajIzvestaj(String KojaKalk){
        AIKorisnik korisnik = null;
        switch (KojaKalk){
        case "Vp":      
            AIZVP VP = new AIZVP();         //ConcreteFactory1
            korisnik = new AIKorisnik(VP);
            break;
        case "Mp":
            AIZMP MP = new AIZMP();         //ConcreteFactory2
            korisnik = new AIKorisnik(MP);            
            break;
        }     
        return korisnik.Kreiraj();
    }
    String Kreiraj(){
        kojaKalk = izvestaj.izaberiKalkulaciju();
        kojiDatum = izvestaj.izaberiDatum();
        kojaRazUCeni = izvestaj.izaberiRazUCeni();
        kojiOpis = izvestaj.izaberiOpis();
        kojiBrojDokumenta = izvestaj.izaberiBrDokumenta();        
        
        //1,Kreira izvestaj i nadzire proces pravljenja izvestaja - sve radi sam
        izv.izv = kojiBrojDokumenta.VratiBrDokumenta() + "," + kojaKalk.VratiKalkulaciju() + "," + kojiDatum.VratiDatum() + "," + kojaRazUCeni.VratiRazUCeni() + "," + kojiOpis.VratiOpis(); 
        return izv.izv;   
    }
}
