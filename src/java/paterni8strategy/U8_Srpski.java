/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni8strategy;

/**
 *
 * @author neso
 */
public class U8_Srpski extends U8_UPUTSTVO { //ConcreteStrategyA
    //1, Familija algoritama
    String Opis(){
        String opis="";
        opis += "STRATEGY: Pregledom razlike u ceni dobija se uvid u sledeće podatke: ";
        opis += "Broj kalkulacije - interni broj kalkulacije. ";
        opis += "Datum - datum nabavka robe. ";
        opis += "Razlika u ceni - formirana razlika u ceni za navedenu kalkulaciju. ";
        return opis;
    }
}
