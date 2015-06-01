/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni4factory;

import paterni1abstractfactoryproductinterfejsi.AI_BrojDokumenta;
import paterni1abstractfactoryproductinterfejsi.AI_Datum;
import paterni1abstractfactoryproductinterfejsi.AI_KojaKalk;
import paterni1abstractfactoryproductinterfejsi.AI_Opis;
import paterni1abstractfactoryproductinterfejsi.AI_RazUCeni;
import paterni4factory.AI4IZV;

/**
 *
 * @author neso
 */
public abstract class AI4_IZVESTAJ {//Creator
    //1,definise interfejs za kreiranje proizvoda
    public AI_Datum datum; 
    public AI_KojaKalk kojaKalk;
    public AI_Opis opis;
    public AI_RazUCeni razUCeni;
    public AI_BrojDokumenta brojDokumenta;
    
    public AI4IZV izvestaj;
    
    public void Kreiraj(){izvestaj = kreirajIzvestaj();}
    //2, ali samo kreiranje prepusta podklasama (abstract class)
    abstract AI4IZV kreirajIzvestaj();
    public String vratiIzvestaj(){
        return izvestaj.vratiIzv(); 
    }    
}
