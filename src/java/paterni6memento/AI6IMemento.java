/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni6memento;

/**
 *
 * @author neso
 */
public class AI6IMemento { //Memento
    //Cuva stanje mementa
    AI6I_IZVESTAJ izv;
    void postaviStanje(AI6I_IZVESTAJ izv1){
        izv=izv1;
    }
    AI6I_IZVESTAJ uzmiStanje(){
        return izv;
    }
}
