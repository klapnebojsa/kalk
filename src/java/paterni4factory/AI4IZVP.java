/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni4factory;

import paterni4factory.AI4IZV;
import paterni1abstractfactoryproductklase.AIBrDokVp;
import paterni1abstractfactoryproductklase.AIDatVp;
import paterni1abstractfactoryproductklase.AIKalkVp;
import paterni1abstractfactoryproductklase.AIOpisVp;
import paterni1abstractfactoryproductklase.AIRazVp;

/**
 *
 * @author neso
 */
public class AI4IZVP extends AI4_IZVESTAJ{ //ConcreteCreator2   
    public AI4IZV kreirajIzvestaj(){
        AI4Izvestaj izv = new AI4Izvestaj();
        datum = new AIDatVp();        
        kojaKalk = new AIKalkVp();
        opis = new AIOpisVp();
        razUCeni = new AIRazVp();
        brojDokumenta = new AIBrDokVp();
        
        //3,Kreira izvestaj uz pomoc kreirajIzvestaj        
        izv.izvestaj = brojDokumenta.VratiBrDokumenta() + "," + kojaKalk.VratiKalkulaciju() + ","  + datum.VratiDatum() + "," + razUCeni.VratiRazUCeni() + "," + opis.VratiOpis();
        return izv;
    }    
}
