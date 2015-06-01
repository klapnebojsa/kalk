/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni3decorator;

/**
 *
 * @author neso
 */
public class AI3Datum  extends AI3Dekorator{//Concrete Decotator 1  
    private static final long serialVersionUID = 1L;
    String datum;
    public AI3Datum(AI3KOMPONENTA komp1, String Datum){
        super(komp1);
        datum = Datum;
    }

    public String prikaziIzvestaj(){
        //2.Dopunjuje izvestaj sa datumom
        super.prikaziIzvestaj();
        return datum;
    }
    
}
