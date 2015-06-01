/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni6memento;

/**
 *
 * @author neso
 */
public class AI6IKorisnik { //CareTaker
    AI6IMemento memVP;
    AI6IMemento memMP;
    AI6I_IZVESTAJ izvestaj;
    String parametri = null;
    
    AI6IZVP vp; 
    AI6IZMP mp;    
    public AI6IKorisnik korVp;
    public AI6IKorisnik korMp;      
    AI6IKreirajMemento kreirajMemVP;
    AI6IKreirajMemento kreirajMemMP;
    
    public AI6IKorisnik(AI6I_IZVESTAJ izvestaj1){
        izvestaj = izvestaj1;
    } 
    
    public String Kreiraj(){
        izvestaj.izaberiBrDokumenta();
        izvestaj.izaberiDatum();
        izvestaj.izaberiKalkulaciju();
        izvestaj.izaberiOpis();
        izvestaj.izaberiRazUCeni();
        izvestaj.KreirajIzvestaj();
        return izvestaj.VratiIzvestaj();
    }
    public void PripremiVp(){
       vp = new AI6IZVP();        
       korVp = new AI6IKorisnik(vp);        
       kreirajMemVP = new AI6IKreirajMemento();
       kreirajMemVP.poveziSaIzvestajem(vp);
       //1,Salje zahtev za kreiranje izvestaja- Nije bitno na koji nacin kreira izvestaj. To radi bilo koji kreacionu patern       
       korVp.Kreiraj(); 
       //3,Salje zahtev za kreiranje memento vp na osnovu kreiranog izvestaja(memorise memento)
       korVp.memVP = kreirajMemVP.kreirajMemento();
       korVp.kreirajMemVP = kreirajMemVP;
    }
    public void PripremiMp(){
       mp = new AI6IZMP();        
       korMp = new AI6IKorisnik(mp);        
       kreirajMemMP = new AI6IKreirajMemento();
       kreirajMemMP.poveziSaIzvestajem(mp);
       //1,Salje zahtev za kreiranje izvestaja- Nije bitno na koji nacin kreira izvestaj. To radi bilo koji kreacionu patern       
       korMp.Kreiraj();
       //3,Salje zahtev za kreiranje memento vp na osnovu kreiranog izvestaja(memorise memento)     
       korMp.memMP = kreirajMemMP.kreirajMemento();
       korMp.kreirajMemMP = kreirajMemMP;         
    }    
    public String Izvrsi (String KojaKalk){
        String kojaKalk = KojaKalk;
        switch (kojaKalk){
        case "Vp":                   
            kreirajMemVP.postaviMemento(memVP);
            // Pozivanje podataka iz mementa            
            parametri = kreirajMemVP.izv.VratiIzvestaj();
            break;
            
        case "Mp":           
            kreirajMemMP.postaviMemento(memMP);
            // Pozivanje podataka iz mementa             
            parametri = kreirajMemMP.izv.VratiIzvestaj();
            break;
        }        
        return parametri;
    }
    public void setkorVp(AI6IKorisnik korVp1) {
        this.korVp = korVp1;
    }    
    public AI6IKorisnik getkorVp() {
        return korVp;
    }
    public void setkorMp(AI6IKorisnik korMp1) {
        this.korMp = korMp1;
    }    
    public AI6IKorisnik getkorMp() {
        return korMp;
    }    
}
