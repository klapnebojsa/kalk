/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni1abstractfactoryproductklase;

import paterni1abstractfactoryproductinterfejsi.AI_KojaKalk;

/**
 *
 * @author neso
 */
public class AIKalkMp implements AI_KojaKalk{ //Product C1
    @Override    
    public String VratiKalkulaciju(){
        return "mpkalkuk";
    };
}
