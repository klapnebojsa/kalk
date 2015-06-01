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
public class U9_Korisnik { //Client
    public String donIzbor;
    U9_UPUTSTVO izb;
    public U9_Korisnik(U9_UPUTSTVO izb1){izb=izb1;}
    
    public String ProveriJezik (){
        String opis=null;
        if ("Sr".equals(donIzbor)) {
            U9_Srpski srp = new U9_Srpski();
            U9_Korisnik kor = new U9_Korisnik(srp);
            opis= kor.izb.Opis();
        }
        if ("Ki".equals(donIzbor)) {
            U9_Kineski kin = new U9_Kineski();
            U9_Korisnik kor = new U9_Korisnik(kin);
            opis= kor.izb.Opis();
        }
        return opis;
    }     
}
