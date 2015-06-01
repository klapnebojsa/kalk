/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Povezivanje;

import DAO.BrokerDAO;
import Inerfejsi.InterfaceDAO;
import java.sql.SQLException;

/**
 *
 * @author neso
 */
public class Odredi {
    public String BrStavke(String PoCemu, InterfaceDAO aa) throws SQLException{         
        BrokerDAO BrStDAO = new BrokerDAO(aa);        
        String results = "0";
        int koliko=0;              
        koliko = BrStDAO.MaxSifraDAO(PoCemu) + 1;
        results = Integer.toString(koliko);
        return results;
    }  
}
