/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KlaseBaze;

import Inerfejsi.InterfaceDAO;

import Povezivanje.Izracunaj;
import Povezivanje.Provere;
import java.io.Serializable;
import java.sql.SQLException;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neso
 */
@Entity
@Table(name = "finnalogstavka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Finnalogstavka.findAll", query = "SELECT f FROM Finnalogstavka f"),
    @NamedQuery(name = "Finnalogstavka.findByIdFN", query = "SELECT f FROM Finnalogstavka f WHERE f.finnalogstavkaPK.idFN = :idFN"),
    @NamedQuery(name = "Finnalogstavka.findByIdStavke", query = "SELECT f FROM Finnalogstavka f WHERE f.finnalogstavkaPK.idStavke = :idStavke"),
    @NamedQuery(name = "Finnalogstavka.findByFNBrDokumenta", query = "SELECT f FROM Finnalogstavka f WHERE f.fNBrDokumenta = :fNBrDokumenta"),
    @NamedQuery(name = "Finnalogstavka.findByFNDuguje", query = "SELECT f FROM Finnalogstavka f WHERE f.fNDuguje = :fNDuguje"),
    @NamedQuery(name = "Finnalogstavka.findByFNPotrazuje", query = "SELECT f FROM Finnalogstavka f WHERE f.fNPotrazuje = :fNPotrazuje"),
    @NamedQuery(name = "Finnalogstavka.findByFNSaldo", query = "SELECT f FROM Finnalogstavka f WHERE f.fNSaldo = :fNSaldo"),
    @NamedQuery(name = "Finnalogstavka.findByFNPartner", query = "SELECT f FROM Finnalogstavka f WHERE f.fNPartner = :fNPartner")})
public class Finnalogstavka implements InterfaceDAO, Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @Column(name = "FNIdFN")
    private String fNIdFN;
    @Column(name = "FNIdVlasnik")
    private String fNIdVlasnik;    
    @Basic(optional = false)
    @Column(name = "FNIdStavke")
    private int fNIdStavke;
    @Column(name = "FNBrDokumenta")
    private String fNBrDokumenta;
    @Column(name = "FNPartner")
    private String fNPartner; 
    @Column(name = "FNKonto")
    private String fNKonto;      
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FNDuguje")
    private Double fNDuguje;
    @Column(name = "FNPotrazuje")
    private Double fNPotrazuje;
    @Column(name = "FNSaldo")
    private Double fNSaldo;


    String vred;
    public Finnalogstavka() {
    }

    public String getFNIdFN() {
        return fNIdFN;
    }

    public void setFNIdFN(String fNIdFN) {
        this.fNIdFN = fNIdFN;
    }
    public String getFNIdVlasnik() {
        return fNIdVlasnik;
    }

    public void setFNIdVlasnik(String fNIdVlasnik) {
        this.fNIdVlasnik = fNIdVlasnik;
    }

    public int getFNIdStavke() {
        return fNIdStavke;
    }

    public void setFNIdStavke(int fNIdStavke) {
        this.fNIdStavke = fNIdStavke;
    }

    public String getFNBrDokumenta() {
        return fNBrDokumenta;
    }

    public void setFNBrDokumenta(String fNBrDokumenta) {
        this.fNBrDokumenta = fNBrDokumenta;
    }
    public String getFNPartner() {
        return fNPartner;
    }

    public void setFNPartner(String fNPartner) {
        this.fNPartner = fNPartner;
    }
    
    public String getFNKonto() {
        return fNKonto;
    }

    public void setFNKonto(String fNKonto) {
        this.fNKonto = fNKonto;
    }
    
    public Double getFNDuguje() {
        return fNDuguje;
    }

    public void setFNDuguje(Double fNDuguje) {
        this.fNDuguje = fNDuguje;
    }

    public Double getFNPotrazuje() {
        return fNPotrazuje;
    }

    public void setFNPotrazuje(Double fNPotrazuje) {
        this.fNPotrazuje = fNPotrazuje;
    }

    public Double getFNSaldo() {
        return fNSaldo;
    }

    public void setFNSaldo() throws SQLException{
        Izracunaj izracunaj = new Izracunaj();
        String razlika = izracunaj.Razliku(fNDuguje==null ? "" : fNDuguje.toString(),fNPotrazuje==null ? "" : fNPotrazuje.toString());
        this.fNSaldo = Double.parseDouble(razlika);
        //this.fNSaldo = fNSaldo;
    }


    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fNIdFN != null ? fNIdFN.hashCode() : 0);
        hash += (fNIdVlasnik != null ? fNIdVlasnik.hashCode() : 0);      
        hash += (int) fNIdStavke;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Finnalogstavka)) {
            return false;
        }
        Finnalogstavka other = (Finnalogstavka) object;
        if ((this.fNIdFN == null && other.fNIdFN != null) || (this.fNIdFN != null && !this.fNIdFN.equals(other.fNIdFN))) {
            return false;
        }
        if (this.fNIdVlasnik != other.fNIdVlasnik) {
            return false;
        }        
        if (this.fNIdStavke != other.fNIdStavke) {
            return false;
        }
        return true;
    }

////////OVO PROVERITI    
    public String UslovTrazenjaSvih(){
        return "FNIdFN='" + fNIdFN + "'" + " and FNIdVlasnik= '" + fNIdVlasnik + "'";
    }  
    public String UslovTrazenjaSloga(int a){
        return "";
    }  
    public String UslovTrazenjaSloga(){
        return "FNIdFN='" + fNIdFN + "'" + " and FNIdVlasnik='" + fNIdVlasnik + "'" + " and FNIdStavke=" + fNIdStavke + "";
    }
 
    public String ImeKlase(){
        return "finnalogstavka";
    }    

    public String IspraviSlog(){
        return "FNBrDokumenta='" + fNBrDokumenta + "', " +            
               "FNPartner='"     + fNPartner     + "', " +
               "FNKonto='"       + fNKonto       + "', " +                                
               "FNDuguje="       + fNDuguje      + ", " + 
               "FNPotrazuje="    + fNPotrazuje   + ", " + 
               "FNSaldo="        + fNSaldo       + "" ;
    }
  
    public String IspraviSlog(int a){
        return "";
    }    
    
    public String UpisiSlog(){
        return "'" + fNIdFN        + "' , '" 
                   + fNIdVlasnik   + "' ,  " 
                   + fNIdStavke    + "  , '" 
                   + fNBrDokumenta + "' , '"                 
                   + fNPartner     + "' , '"
                   + fNKonto       + "' , "                
                   + fNDuguje      + " , "                 
                   + fNPotrazuje   + " , " 
                   + fNSaldo       + "";
    } 
    public String ProveriVrednosti(){
        Provere provere = new Provere();
        /*if (provere.proveriPrazno(getIdFN())) {return "Morate uneti Dokument";}                 
        if (provere.proveriNotInteger(getIdStavke())){return "Morate uneti Broj Racuna";}*/
         
        if (provere.proveriPrazno(fNBrDokumenta)) {return "Morate uneti Broj Dokumenta";}
        if (provere.proveriPrazno(fNPartner)) {return "Morate uneti Partnera";}
        if (provere.proveriPrazno(fNKonto)) {return "Morate uneti Konto";}        
        if (provere.proveriNotDouble(fNDuguje==null ? "" : fNDuguje.toString())) {return "Duguje morate uneti kao broj";}        
        if (provere.proveriNotDouble(fNPotrazuje==null ? "" : fNPotrazuje.toString())) {return "Potrazuje morate uneti kao broj";}
        if (provere.proveriNotDouble(fNSaldo==null ? "" : fNSaldo.toString())) {return "Saldo morate uneti kao broj";}        
        
        return null;        
    }  

    /**
     *
     * @param PoCemu
     * @return
     * @throws SQLException
     */
    public String VrednostiIspravke(){
        vred = getFNBrDokumenta() + "####____" + getFNPartner() + "####____" + getFNKonto() + "####____" +
               getFNDuguje() + "####____" + getFNPotrazuje() + "####____" +   getFNSaldo();
        return vred;
    } 
    public String VrednostiNovi(){
        vred = getFNBrDokumenta() + "####____" + getFNPartner() + "####____" + getFNKonto() + "####____" +
               getFNDuguje() + "####____" + getFNPotrazuje() + "####____" +   getFNSaldo();
            //getFNIdFN() + "####____" + getFNIdVlasnik() + "####____" + getFNIdStavke() + "####____" +     
        return vred;
    }
    
    public String OdrediStavku(){
        String stavka = null;
        int BrStavke = 0;
        
        return stavka;
    }

    @Override
    public String toString() {
        return "KlaseBaze.Finnalogstavka[ idFN=" + fNIdFN + ",FNIdVlasnik=" + fNIdVlasnik + ", FNIdStavke=" + fNIdStavke + " ]";
    }
    
    public String PoljaZbir(){
        return "FNDuguje,FNPotrazuje,FNSaldo";      
    } 
    public String VredPoljaZbir(){
        return fNDuguje + "," + fNPotrazuje + "," + fNSaldo;        
    }
    public String getStavku(){
        return "FNIdStavke";        
    }       
}
