/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Povezivanje;

import java.sql.SQLException;

/**
 *
 * @author neso
 */
public class Izracunaj {
    public String Razliku(String Prvi, String Drugi) throws SQLException{               
        String razlika = "0.00";
        if (Prvi==null) Prvi="0.00";
        if (Drugi==null) Drugi="0.00";
        try {
            Double prvi = Double.parseDouble(Prvi);
            Double drugi = Double.parseDouble(Drugi);
            Double rez  = prvi - drugi;
            razlika = rez.toString();
        }catch(Exception e){razlika = "0.00";}
        return razlika;
    }
    public String Zbir(String Prvi, String Drugi) throws SQLException{               
        String zbir = "0.00";
        if (Prvi==null) Prvi="0.00";
        if (Drugi==null) Drugi="0.00";
        try {
            Double prvi = Double.parseDouble(Prvi);
            Double drugi = Double.parseDouble(Drugi);
            Double rez  = prvi + drugi;
            zbir = rez.toString();
        }catch(Exception e){zbir = "0.00";}
        return zbir;
    }     
    public String Proizvod(String Prvi, String Drugi) throws SQLException{               
        String zbir = "0.00";
        if (Prvi==null) Prvi="0.00";
        if (Drugi==null) Drugi="0.00";
        try {
            Double prvi = Double.parseDouble(Prvi);
            Double drugi = Double.parseDouble(Drugi);
            Double rez  = prvi * drugi;
            zbir = rez.toString();
        }catch(Exception e){zbir = "0.00";}
        return zbir;
    }
    public String Kolicnik(String Prvi, String Drugi) throws SQLException{               
        String kolicnik = "0.00";
        if (Prvi==null) Prvi="0.00";
        if (Drugi==null) Drugi="0.00";
        try {
            Double prvi = Double.parseDouble(Prvi);
            Double drugi = Double.parseDouble(Drugi);
            if (drugi == 0){
                kolicnik="0.00";
            } else{
                Double rez  = prvi / drugi;
                kolicnik = rez.toString();
            }
        }catch(Exception e){kolicnik = "0.00";}
        return kolicnik;
    }    
}
