/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistOperacije;

import DAO.BrokerDAO;
import Inerfejsi.InterfaceDAO;
import apstraktne.Opsta;
import java.sql.SQLException;

/**
 *
 * @author neso
 */
public class NoviZavisniSO extends Opsta{
    public InterfaceDAO p;
    public InterfaceDAO n;    

    public NoviZavisniSO(InterfaceDAO interf) {
        super(interf);
    }
    protected String formirajRec(BrokerDAO brokerDAO) throws SQLException {
        return (brokerDAO.RecNoviZav(p,n));
    }
    protected Object izvrsiRec(BrokerDAO brokerDAO) throws SQLException {
        Object result = brokerDAO.izvrsiNoviZav();
        return result;
    }     
}
