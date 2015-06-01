/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni1abstractfactoryproductklase;

import paterni1abstractfactoryproductinterfejsi.AI_Datum;

/**
 *
 * @author neso
 */
public class AIDatVp implements AI_Datum{ //Product B2
    @Override    
    public String VratiDatum(){
        return "VpDatumUK";
    };     
}
