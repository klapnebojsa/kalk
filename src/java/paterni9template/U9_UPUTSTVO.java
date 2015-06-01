/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni9template;

/**
 *
 * @author neso
 */
abstract class U9_UPUTSTVO { //AbstractClass
    String Opis(){ //Template method
        //1, Definise skelet algoritma
        String opis = "";
        opis += Naslov();
        opis += BrojKalkulacije();
        opis += Datum();
        opis += RazlikaUCeni();        
        return opis;
    }
    abstract String Naslov(); //Primitive Operation1
    abstract String BrojKalkulacije(); //Primitive Operation2
    abstract String Datum(); //Primitive Operation3
    abstract String RazlikaUCeni();  //Primitive Operation4   
}
