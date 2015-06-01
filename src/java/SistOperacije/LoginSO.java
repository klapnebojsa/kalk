/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SistOperacije;

import DAO.BrokerDAO;
import Inerfejsi.InterfaceDAO;
import KlaseBaze.Korisnik;
import apstraktne.Opsta;
import java.sql.SQLException;

/**
 *
 * @author neso
 */
public class LoginSO extends Opsta{
    public Korisnik k;
    public LoginSO(InterfaceDAO interf) {
        super(interf);
    }
    protected String formirajRec(BrokerDAO brokerDAO) throws SQLException {
        return (brokerDAO.RecLogin());
    }
    protected Object izvrsiRec(BrokerDAO brokerDAO) throws SQLException {
        Object result = brokerDAO.izvrsiLogin(k);
        return result;
    }     
}
