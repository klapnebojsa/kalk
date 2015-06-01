/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni1abstractfactory;

import paterni1abstractfactoryproductinterfejsi.AI_BrojDokumenta;
import paterni1abstractfactoryproductinterfejsi.AI_RazUCeni;
import paterni1abstractfactoryproductinterfejsi.AI_Opis;
import paterni1abstractfactoryproductinterfejsi.AI_KojaKalk;
import paterni1abstractfactoryproductinterfejsi.AI_Datum;

/**
 *
 * @author neso
 */
public interface AI_IZVESTAJ {
    AI_Datum izaberiDatum();
    AI_KojaKalk izaberiKalkulaciju();
    AI_Opis izaberiOpis();
    AI_RazUCeni izaberiRazUCeni();
    AI_BrojDokumenta izaberiBrDokumenta();    
}
