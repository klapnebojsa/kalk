/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni5bridge;

/**
 *
 * @author neso
 */
public class AI5_Korisnik { //Client
    AI5_IZVESTAJ izv;
    public AI5_Korisnik(AI5_IZVESTAJ izv1){
        izv = izv1;
    }
    void Kreiraj(AI5_FormatIzvestaja fi){
        izv.izaberiBrDokumenta();
        izv.izaberiDatum();
        izv.izaberiKalkulaciju();
        izv.izaberiOpis();
        izv.izaberiRazUCeni();

        izv.KreirajIzvestaj(fi);
    }
    public String Izvrsi(String KojiIzvestaj, String KojiFormat){
        String poruka = "";
        AI5_Korisnik kor;
        AI5_FormatIzvestaja fi = null;
        if ("1".equals(KojiFormat)){
            fi = new AI5_FormatIzvestaja1();
        }
        if ("2".equals(KojiFormat)){
            fi = new AI5_FormatIzvestaja2();
        }
        switch (KojiIzvestaj){
        case "Vp":
            AI5IZVP vp = new AI5IZVP();
            kor = new AI5_Korisnik(vp);
            //1,Salje zahtev za kreiranje izvestaja formata fi
            kor.Kreiraj(fi);
            //3,Salje zahtev za dopunjavanje izvestaja u RefindAbstaction            
            poruka = vp.VratiIzvestaj();
            break;
        case "Mp":
            AI5IZMP mp = new AI5IZMP();
            kor = new AI5_Korisnik(mp);
            kor.Kreiraj(fi);
            poruka = mp.VratiIzvestaj();
            break;
        }        
        return poruka;
    }
}

