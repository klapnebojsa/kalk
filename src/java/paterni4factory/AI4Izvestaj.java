/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paterni4factory;

/**
 *
 * @author neso
 */
public class AI4Izvestaj implements AI4IZV{ //ConcreteProduct
   //4.definise interfejs za proizvod koji ce biti kreiran
    public String izvestaj;
    public String vratiIzv(){
        return izvestaj;
    }
}
