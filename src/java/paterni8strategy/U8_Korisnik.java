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
public class U8_Korisnik { //Contex
    public String donIzbor;
    U8_UPUTSTVO izb;
    public U8_Korisnik(){izb=null;}
    
    public String ProveriJezik (){
        U8_Korisnik kor = new U8_Korisnik();
        if ("Sr".equals(donIzbor)) kor.izb = new U8_Srpski(); 
        if ("Ki".equals(donIzbor)) kor.izb = new U8_Kineski();
        if ("Ar".equals(donIzbor)) kor.izb = new U8_Arapski();
        if ("Ji".equals(donIzbor)) kor.izb = new U8_Jidis();        
        return kor.izb.Opis();
    } 
}