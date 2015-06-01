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
public class AI3Dekorator implements AI3KOMPONENTA { //ConcreteDecorator1 
    AI3KOMPONENTA komp;
    public AI3Dekorator(AI3KOMPONENTA komp1){komp=komp1;}
    public String prikaziIzvestaj(){
        komp.prikaziIzvestaj();
        return "";
    }
}
