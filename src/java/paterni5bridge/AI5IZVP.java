/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni5bridge;

import paterni1abstractfactoryproductklase.AIBrDokVp;
import paterni1abstractfactoryproductklase.AIDatVp;
import paterni1abstractfactoryproductklase.AIKalkVp;
import paterni1abstractfactoryproductklase.AIOpisVp;
import paterni1abstractfactoryproductklase.AIRazVp;

/**
 *
 * @author neso
 */
public class AI5IZVP extends AI5_IZVESTAJ{ //RefinedAbstraction1
    public AI5IZVP() {
        izv = new AI5Izvestaj();
    }
    
    @Override    
    public void izaberiDatum(){
        datum = new AIDatVp();
    }; 
    @Override    
    public void izaberiKalkulaciju(){
        kojaKalk = new AIKalkVp();
    };
    @Override    
    public void izaberiOpis(){
        opis = new AIOpisVp();
    }; 
    @Override    
    public void izaberiRazUCeni(){
        razUCeni = new AIRazVp();
    }; 
    @Override    
    public void izaberiBrDokumenta(){
        brojDokumenta = new AIBrDokVp();
    };
    //4.Dopunjuje izvestaj sa VP Pregled
    public String VratiIzvestaj(){
        return izv.izvestaj + ",VP Pregled";
    }     
}
