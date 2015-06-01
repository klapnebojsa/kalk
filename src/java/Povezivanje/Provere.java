/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Povezivanje;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author neso
 */
public class Provere {
    public Boolean proveriPrazno(String donos) {
        Boolean prazno = false;
        if (donos == null || donos == ""){
            prazno = true;
        } else {
            //prazno = false;
        }
        return prazno;
    } 

    public Boolean proveriNotBigDecimal(String donos) {
        Boolean proveriNotBigDecimal = false;
        if (!proveriPrazno(donos)){
            try{
                BigDecimal xx = new BigDecimal(donos);
            }catch(Exception e){
                proveriNotBigDecimal = true;            
            }
        }else {
            proveriNotBigDecimal = true;                    
        }

        return proveriNotBigDecimal;
    } 
    
    public Boolean proveriNotInteger(String donos) {
        Boolean proveriNotInteger = false;
        if (!proveriPrazno(donos)){
            try{
                Integer xx = Integer.parseInt(donos);
            }catch(Exception e){
                proveriNotInteger = true;            
            }
        }else {
            proveriNotInteger = true;                    
        }

        return proveriNotInteger;
    }
    
    public Boolean proveriNotDouble(String donos) {
        Boolean proveriNotDouble = false;
        if (!proveriPrazno(donos)){
            try{
                Double xx = Double.parseDouble(donos);
            }catch(Exception e){
                proveriNotDouble = true;            
            }
        }else {
            proveriNotDouble = true;                    
        }

        return proveriNotDouble;
    }
    
    public Date dajDate(String donos) {
        Date date = null;
        try{
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            date = sdf.parse(donos);
        }catch(Exception e){}

        return date;
    }    
}
