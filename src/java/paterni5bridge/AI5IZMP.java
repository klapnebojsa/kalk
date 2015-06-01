/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni5bridge;

import paterni1abstractfactoryproductklase.AIBrDokMp;
import paterni1abstractfactoryproductklase.AIDatMp;
import paterni1abstractfactoryproductklase.AIKalkMp;
import paterni1abstractfactoryproductklase.AIOpisMp;
import paterni1abstractfactoryproductklase.AIRazMp;

/**
 *
 * @author neso
 */
public class AI5IZMP extends AI5_IZVESTAJ{//RefinedAbstraction2     
    public AI5IZMP() {
        izv = new AI5Izvestaj();
    }
    @Override    
    public void izaberiDatum(){
        datum = new AIDatMp();
    }; 
    @Override    
    public void izaberiKalkulaciju(){
        kojaKalk = new AIKalkMp();
    };
    @Override    
    public void izaberiOpis(){
        opis = new AIOpisMp();
    }; 
    @Override    
    public void izaberiRazUCeni(){
        razUCeni = new AIRazMp();
    }; 
    @Override    
    public void izaberiBrDokumenta(){
        brojDokumenta = new AIBrDokMp();
    }; 
    //4.Dopunjuje izvestaj sa praznim
    public String VratiIzvestaj(){
        return izv.izvestaj;
    }    
}
