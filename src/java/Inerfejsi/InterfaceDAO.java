/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Inerfejsi;

/**
 *
 * @author neso
 */
public interface InterfaceDAO {

    /**
     *
     * @return
     */
    public abstract String UslovTrazenjaSvih();
    public abstract String UslovTrazenjaSloga();
    public abstract String UslovTrazenjaSloga(int a);    
    public abstract String ImeKlase();
    public abstract String UpisiSlog();
    public abstract String IspraviSlog(); 
    public abstract String IspraviSlog(int a);
    public abstract String ProveriVrednosti();
    public abstract String VrednostiIspravke();    
    public abstract String VrednostiNovi();    
    public abstract String PoljaZbir();
    public abstract String VredPoljaZbir();
    public abstract String getStavku();
}
