/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni1abstractfactoryproductklase;

import paterni1abstractfactoryproductinterfejsi.AI_Opis;

/**
 *
 * @author neso
 */
public class AIOpisMp implements AI_Opis{ //Product D1
    @Override    
    public String VratiOpis(){
        return "Maloprodajne";
    }; 
}
