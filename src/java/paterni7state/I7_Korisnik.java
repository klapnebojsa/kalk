/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni7state;

/**
 *
 * @author neso
 */
public class I7_Korisnik { //Contex
    public String donIzbor;
    I7_IZBOR izb;
    public String ProveriIzbor (){
        I7_Korisnik kor = new I7_Korisnik(); 
        //1. Na osnovu unete opcije preko (state)IZBOR vraca IzabraniIzbor iz (ConcreteState) Vp ili Mp
        if ("Vp".equals(donIzbor)) {
            kor.izb = new I7_Vp();
        } else {
            kor.izb = new I7_Mp();        
        }
        return kor.izb.IzabraniIzbor();
    }    
}
