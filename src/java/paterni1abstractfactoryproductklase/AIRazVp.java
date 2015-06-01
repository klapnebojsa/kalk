/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni1abstractfactoryproductklase;

import paterni1abstractfactoryproductinterfejsi.AI_RazUCeni;

/**
 *
 * @author neso
 */
public class AIRazVp implements AI_RazUCeni{ //Product E2
    @Override    
    public String VratiRazUCeni(){
        return "VpRazUCeniUK";
    };    
}