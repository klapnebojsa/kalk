/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni4factory;

import paterni1abstractfactoryproductklase.AIBrDokMp;
import paterni1abstractfactoryproductklase.AIDatMp;
import paterni1abstractfactoryproductklase.AIKalkMp;
import paterni1abstractfactoryproductklase.AIOpisMp;
import paterni1abstractfactoryproductklase.AIRazMp;

/**
 *
 * @author neso
 */
public class AI4IZMP extends AI4_IZVESTAJ{ //ConcreteCreator1   
    public AI4IZV kreirajIzvestaj(){
        AI4Izvestaj izv = new AI4Izvestaj();
        
        datum = new AIDatMp();  
        kojaKalk = new AIKalkMp();
        opis = new AIOpisMp();
        razUCeni = new AIRazMp();
        brojDokumenta = new AIBrDokMp();
        
        //3,Kreira izvestaj uz pomoc kreirajIzvestaj
        izv.izvestaj = brojDokumenta.VratiBrDokumenta() + "," + kojaKalk.VratiKalkulaciju() + ","  + datum.VratiDatum() + "," + razUCeni.VratiRazUCeni() + "," + opis.VratiOpis();
        return izv;
    }    
}
