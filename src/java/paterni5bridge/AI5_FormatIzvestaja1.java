/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni5bridge;

/**
 *
 * @author neso
 */
public class AI5_FormatIzvestaja1 extends AI5_FormatIzvestaja{ //Concrete Implementator A
    String vratiFormatIzvestaja(AI5_IZVESTAJ izv){
        //2,Daje format izvestaja
        return izv.brojDokumenta.VratiBrDokumenta() + "," + izv.kojaKalk.VratiKalkulaciju() + "," + izv.datum.VratiDatum() + "," + izv.razUCeni.VratiRazUCeni() + "," + izv.opis.VratiOpis() + ",Format 1";
    }
}
