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
public class AIBrDokMp implements AI_BrojDokumenta{ //Product A2
    @Override    
    public String VratiBrDokumenta(){
        return "MpIdKalkUK";
    };     
}
