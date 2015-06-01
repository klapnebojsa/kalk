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
import paterni1abstractfactoryproductklase.AIBrDokVp;
import paterni1abstractfactoryproductklase.AIDatVp;
import paterni1abstractfactoryproductklase.AIKalkVp;
import paterni1abstractfactoryproductklase.AIOpisVp;
import paterni1abstractfactoryproductklase.AIRazVp;

/**
 *
 * @author neso
 */
public class AIZVP implements AI_IZVESTAJ{ //ConcreteFactory1
    //2. Odgovorne za kreiranje elemenata izvestaja    
    @Override    
    public AI_Datum izaberiDatum(){
        return new AIDatVp();
    }; 
    @Override    
    public AI_KojaKalk izaberiKalkulaciju(){
        return new AIKalkVp();
    };
    @Override    
    public AI_Opis izaberiOpis(){
        return new AIOpisVp();
    }; 
    @Override    
    public AI_RazUCeni izaberiRazUCeni(){
        return new AIRazVp();
    }; 
    @Override    
    public AI_BrojDokumenta izaberiBrDokumenta(){
        return new AIBrDokVp();
    };      
}    
