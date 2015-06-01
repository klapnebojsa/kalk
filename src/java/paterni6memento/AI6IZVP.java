/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni6memento;

import paterni1abstractfactoryproductinterfejsi.AI_RazUCeni;
import paterni1abstractfactoryproductklase.AIBrDokVp;
import paterni1abstractfactoryproductklase.AIDatVp;
import paterni1abstractfactoryproductklase.AIKalkVp;
import paterni1abstractfactoryproductklase.AIOpisVp;
import paterni1abstractfactoryproductklase.AIRazVp;

/**
 *
 * @author neso
 */
public class AI6IZVP  implements AI6I_IZVESTAJ{
    AI6IZVP izvestaj;
    AI6I_IzvestajKreiran izv;
    AIDatVp aIDatVp;
    AIKalkVp aIKalkVp;
    AIOpisVp aIOpisVp;
    AI_RazUCeni aI_RazUCeni;
    AIBrDokVp aIBrDokVp;
    
    public AI6IZVP() {
        izv = new AI6I_IzvestajKreiran();
    }
    @Override    
    public void izaberiDatum(){
        aIDatVp = new AIDatVp();
    }; 
    @Override    
    public void izaberiKalkulaciju(){
        aIKalkVp = new AIKalkVp();
    };
    @Override    
    public void izaberiOpis(){
        aIOpisVp = new AIOpisVp();
    }; 
    @Override    
    public void izaberiRazUCeni(){
        aI_RazUCeni = new AIRazVp();
    }; 
    @Override    
    public void izaberiBrDokumenta(){
        aIBrDokVp = new AIBrDokVp();
    }; 
    @Override
    public void KreirajIzvestaj(){ 
        //2, kreira izvestaj        
        izv.Izvestaj = aIBrDokVp.VratiBrDokumenta() + "," + aIKalkVp.VratiKalkulaciju() + "," + aIDatVp.VratiDatum() + "," + aI_RazUCeni.VratiRazUCeni() + "," + aIOpisVp.VratiOpis(); 
    }
    public String VratiIzvestaj(){
        return izv.Izvestaj;
    }    
}
