/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni1abstractfactoryklase;

import paterni1abstractfactoryproductinterfejsi.AI_BrojDokumenta;
import paterni1abstractfactoryproductinterfejsi.AI_Datum;
import paterni1abstractfactoryproductinterfejsi.AI_KojaKalk;
import paterni1abstractfactoryproductinterfejsi.AI_Opis;
import paterni1abstractfactoryproductinterfejsi.AI_RazUCeni;
import paterni1abstractfactory.AI_IZVESTAJ;
import paterni1abstractfactoryproductklase.AIBrDokMp;
import paterni1abstractfactoryproductklase.AIDatMp;
import paterni1abstractfactoryproductklase.AIKalkMp;
import paterni1abstractfactoryproductklase.AIOpisMp;
import paterni1abstractfactoryproductklase.AIRazMp;

/**
 *
 * @author neso
 */
public class AIZMP implements AI_IZVESTAJ{ //AbstractProductB
    //2. Odgovorne za kreiranje elemenata izvestaja
    @Override    
    public AI_Datum izaberiDatum(){
        return new AIDatMp();
    }; 
    @Override    
    public AI_KojaKalk izaberiKalkulaciju(){
        return new AIKalkMp();
    };
    @Override    
    public AI_Opis izaberiOpis(){
        return new AIOpisMp();
    }; 
    @Override    
    public AI_RazUCeni izaberiRazUCeni(){
        return new AIRazMp();
    }; 
    @Override    
    public AI_BrojDokumenta izaberiBrDokumenta(){
        return new AIBrDokMp();
    };     
}
