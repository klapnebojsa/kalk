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
public class ProcitajCell {
    public String Jedan (InterfaceDAO aa, String OpisCell) throws SQLException{    
        BrokerDAO brokerDAO = new BrokerDAO(aa);        
        String results = null;
        results = brokerDAO.citajCellDAO(OpisCell);
        return results;
    }  
}
