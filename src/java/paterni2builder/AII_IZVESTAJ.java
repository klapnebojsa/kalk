/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni2builder;

/**
 *
 * @author neso
 */
public interface AII_IZVESTAJ { //Builder
    
    void izaberiDatum();
    void izaberiKalkulaciju();
    void izaberiOpis();
    void izaberiRazUCeni();
    void izaberiBrDokumenta(); 
    void KreirajIzvestaj();
    String VratiIzvestaj();
}
