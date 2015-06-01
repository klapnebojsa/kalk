/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni2builder;

import paterni1abstractfactoryproductinterfejsi.AI_RazUCeni;
import paterni1abstractfactoryproductklase.AIBrDokMp;
import paterni1abstractfactoryproductklase.AIDatMp;
import paterni1abstractfactoryproductklase.AIKalkMp;
import paterni1abstractfactoryproductklase.AIOpisMp;
import paterni1abstractfactoryproductklase.AIRazMp;

/**
 *
 * @author neso
 */
public class AIIZMP implements AII_IZVESTAJ{ //ConcreteBuilder1
    AIIZMP izvestaj;
    AII_IzvestajKreiran izv;
    AIDatMp aIDatMp;
    AIKalkMp aIKalkMp;
    AIOpisMp aIOpisMp;
    AI_RazUCeni aI_RazUCeni;
    AIBrDokMp aIBrDokMp;
    
    public AIIZMP() {
        izv = new AII_IzvestajKreiran();
    }
    @Override    
    public void izaberiDatum(){
        aIDatMp = new AIDatMp();
    }; 
    @Override    
    public void izaberiKalkulaciju(){
        aIKalkMp = new AIKalkMp();
    };
    @Override    
    public void izaberiOpis(){
        aIOpisMp = new AIOpisMp();
    }; 
    @Override    
    public void izaberiRazUCeni(){
        aI_RazUCeni = new AIRazMp();
    }; 
    @Override    
    public void izaberiBrDokumenta(){
        aIBrDokMp = new AIBrDokMp();
    }; 
    @Override
    //2.Kreira izvestaj
    public void KreirajIzvestaj(){     
        izv.Izvestaj = aIBrDokMp.VratiBrDokumenta() + "," + aIKalkMp.VratiKalkulaciju() + "," + aIDatMp.VratiDatum() + "," + aI_RazUCeni.VratiRazUCeni() + "," + aIOpisMp.VratiOpis(); 
    }
    public String VratiIzvestaj(){
        return izv.Izvestaj;
    }
}
