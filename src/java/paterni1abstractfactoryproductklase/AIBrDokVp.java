/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni1abstractfactoryproductklase;

import paterni1abstractfactoryproductinterfejsi.AI_BrojDokumenta;

/**
 *
 * @author neso
 */
public class AIBrDokVp implements AI_BrojDokumenta{ //Product A1
    @Override    
    public String VratiBrDokumenta(){
        return "VpIdKalkUK";
    };     
}