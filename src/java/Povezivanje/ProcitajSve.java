/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Povezivanje;

import DAO.BrokerDAO;
import Inerfejsi.InterfaceDAO;
import SistOperacije.NoviSO;
import SistOperacije.VratiListuSO;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.PersistenceContext;


/**
 *
 * @author neso
 */
public class ProcitajSve {
    /**
     *
     * @param KoZove
     * @param vlasnikStr
     * @return
     * @throws java.sql.SQLException
     */
    @PersistenceContext 
    @SuppressWarnings({"unchecked", "fallthrough"})
    public List ProcitajSvexx(InterfaceDAO aa) throws SQLException, Exception{           
        List results = new LinkedList();
        results = null;
        
        VratiListuSO vratiListu = new VratiListuSO(aa);
        Object rez = vratiListu.Izvrsi();        
        results = (List)rez;
        
        return results;
    }
    
    
                /*EntityManager em = Persistence.createEntityManagerFactory("KalkPU").createEntityManager();;
                em.getEntityManagerFactory().getCache().evictAll();
                TypedQuery<Konto> query = em.createNamedQuery("Konto.findAll",Konto.class);
                List<Konto> results = query.getResultList();
                return results;*/    
    
    
}
