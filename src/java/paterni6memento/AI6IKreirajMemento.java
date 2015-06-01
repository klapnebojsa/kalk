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
public class AI6IKreirajMemento { //Originator
    AI6I_IZVESTAJ izv;
    public AI6IKreirajMemento(){}
    void poveziSaIzvestajem (AI6I_IZVESTAJ izv1){
        izv=izv1;
    }
    void postaviMemento (AI6IMemento mem){ 
        //5.Vraca stanje mementa na osnovu stanja mementa AI6IMemento
        izv=mem.uzmiStanje();
    }
    AI6IMemento kreirajMemento(){
        AI6IMemento mem = new AI6IMemento();
        //4,Kreira memento - pamti stanje kreiranog izvestaja      
        mem.postaviStanje(izv);
        return mem;
    }    
}
