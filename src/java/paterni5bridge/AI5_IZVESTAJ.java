/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni5bridge;

import paterni1abstractfactoryproductinterfejsi.AI_BrojDokumenta;
import paterni1abstractfactoryproductinterfejsi.AI_Datum;
import paterni1abstractfactoryproductinterfejsi.AI_KojaKalk;
import paterni1abstractfactoryproductinterfejsi.AI_Opis;
import paterni1abstractfactoryproductinterfejsi.AI_RazUCeni;

/**
 *
 * @author neso
 */
public abstract class AI5_IZVESTAJ { //Abstraction
    AI_Datum datum;
    AI_KojaKalk kojaKalk;
    AI_Opis opis;
    AI_RazUCeni razUCeni;
    AI_BrojDokumenta brojDokumenta;
    
    AI5Izvestaj izv;
    
    abstract void izaberiDatum();
    abstract void izaberiKalkulaciju();
    abstract void izaberiOpis();
    abstract void izaberiRazUCeni();
    abstract void izaberiBrDokumenta();
    public String VratiIzvestaj(){return izv.izvestaj;}     
    public void KreirajIzvestaj(AI5_FormatIzvestaja fi){izv.izvestaj= fi.vratiFormatIzvestaja(this);}
}
