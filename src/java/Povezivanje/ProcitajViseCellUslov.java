/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Povezivanje;

import DAO.BrokerDAO;
import Inerfejsi.InterfaceDAO;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author neso
 */
public class ProcitajViseCellUslov {
    public List ViseUslov (InterfaceDAO aa, String OpisCell, String Uslov) throws SQLException{    
        BrokerDAO brokerDAO = new BrokerDAO(aa);        
        List results = null;
        results = brokerDAO.citajViseCellDAOUslov(OpisCell,Uslov);
        return results;
    }     
}
