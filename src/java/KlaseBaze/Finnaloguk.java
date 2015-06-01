/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KlaseBaze;

import Inerfejsi.InterfaceDAO;
import Povezivanje.Provere;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neso
 */
@Entity
@Table(name = "finnaloguk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Finnaloguk.findAll", query = "SELECT f FROM Finnaloguk f"),
    @NamedQuery(name = "Finnaloguk.findByIdFNUK", query = "SELECT f FROM Finnaloguk f WHERE f.finnalogukPK.idFNUK = :idFNUK"),
    @NamedQuery(name = "Finnaloguk.findByIdVlasnik", query = "SELECT f FROM Finnaloguk f WHERE f.finnalogukPK.idVlasnik = :idVlasnik"),
    @NamedQuery(name = "Finnaloguk.findByFNBrDokumentaUK", query = "SELECT f FROM Finnaloguk f WHERE f.fNBrDokumentaUK = :fNBrDokumentaUK"),
    @NamedQuery(name = "Finnaloguk.findByFNDatumUK", query = "SELECT f FROM Finnaloguk f WHERE f.fNDatumUK = :fNDatumUK"),
    @NamedQuery(name = "Finnaloguk.findByFNDugujeUK", query = "SELECT f FROM Finnaloguk f WHERE f.fNDugujeUK = :fNDugujeUK"),
    @NamedQuery(name = "Finnaloguk.findByFNPotrazujeUK", query = "SELECT f FROM Finnaloguk f WHERE f.fNPotrazujeUK = :fNPotrazujeUK"),
    @NamedQuery(name = "Finnaloguk.findByFNSaldoUK", query = "SELECT f FROM Finnaloguk f WHERE f.fNSaldoUK = :fNSaldoUK")})
public class Finnaloguk implements InterfaceDAO, Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    @Column(name = "IdFNUK")
    private String idFNUK;
    @Column(name = "IdVlasnik")
    private String idVlasnik;
    @Column(name = "FNBrDokumentaUK")
    private String fNBrDokumentaUK;
    @Column(name = "FNDatumUK")
    @Temporal(TemporalType.DATE)
    private Date fNDatumUK;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "FNDugujeUK")
    private Double fNDugujeUK;
    @Column(name = "FNPotrazujeUK")
    private Double fNPotrazujeUK;
    @Column(name = "FNSaldoUK")
    private Double fNSaldoUK;

    String vred;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    
    public Finnaloguk() {
    }

    public String getIdFNUK() {
        return idFNUK;
    }

    public void setIdFNUK(String idFNUK) {
        this.idFNUK = idFNUK;
    }

    public String getIdVlasnik() {
        return idVlasnik;
    }

    public void setIdVlasnik(String idVlasnik) {
        this.idVlasnik = idVlasnik;
    }

    public String getFNBrDokumentaUK() {
        return fNBrDokumentaUK;
    }

    public void setFNBrDokumentaUK(String fNBrDokumentaUK) {
        this.fNBrDokumentaUK = fNBrDokumentaUK;
    }

    public Date getFNDatumUK() {
        return fNDatumUK;
    }

    public void setFNDatumUK(Date fNDatumUK) {
        this.fNDatumUK = fNDatumUK;
    }

    public Double getFNDugujeUK() {
        return fNDugujeUK;
    }

    public void setFNDugujeUK(Double fNDugujeUK) {
        this.fNDugujeUK = fNDugujeUK;
    }

    public Double getFNPotrazujeUK() {
        return fNPotrazujeUK;
    }

    public void setFNPotrazujeUK(Double fNPotrazujeUK) {
        this.fNPotrazujeUK = fNPotrazujeUK;
    }

    public Double getFNSaldoUK() {
        return fNSaldoUK;
    }

    public void setFNSaldoUK(Double fNSaldoUK) {
        this.fNSaldoUK = fNSaldoUK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idFNUK != null ? idFNUK.hashCode() : 0);
        hash += (idVlasnik != null ? idVlasnik.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Finnaloguk)) {
            return false;
        }
        Finnaloguk other = (Finnaloguk) object;
        if ((this.idFNUK == null && other.idFNUK != null) || (this.idFNUK != null && !this.idFNUK.equals(other.idFNUK))) {
            return false;
        } else {
        }
        if ((this.idVlasnik == null && other.idVlasnik != null) || (this.idVlasnik != null && !this.idVlasnik.equals(other.idVlasnik))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KlaseBaze.Finnaloguk[ idFNUK=" + idFNUK + ", idVlasnik=" + idVlasnik + " ]";
    }
 
 ////////OVO PROVERITI    
    public String UslovTrazenjaSvih(){
        return "IdVlasnik= '" + idVlasnik + "'";
    } 
    public String UslovTrazenjaSloga(int a){
        return "";
    }   
    public String UslovTrazenjaSloga(){
        return "IdFNUK='" + idFNUK + "'" + " and IdVlasnik='" + idVlasnik + "'";
    }
 
    public String ImeKlase(){
        return "finnaloguk";
    }    

    public String IspraviSlog(){
        return "FNBrDokumentaUK='" + fNBrDokumentaUK      + "' , " +
               "FNDatumUK='"       + df.format(fNDatumUK) + "', " + 
               "FNDugujeUK="       + fNDugujeUK           + ", " + 
               "FNPotrazujeUK="    + fNPotrazujeUK        + ", " + 
               "FNSaldoUK="        + fNSaldoUK            + "" ;
    }
  
    public String IspraviSlog(int a){
        return "";
    }    
    
    public String UpisiSlog(){
        return "'" + idFNUK               + "' , '" 
                   + idVlasnik            + "' , '" 
                   + fNBrDokumentaUK      + "' , '"                 
                   + df.format(fNDatumUK) + "' , "                 
                   + fNDugujeUK           + " , "                 
                   + fNPotrazujeUK        + " , " 
                   + fNSaldoUK            + "";
    } 
    public String ProveriVrednosti(){
        Provere provere = new Provere();
        if (provere.proveriPrazno(getIdFNUK())) {return "Morate uneti Dokument";}                 
        if (provere.proveriPrazno(getFNBrDokumentaUK())){return "Morate uneti Broj Racuna";}
        
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = getFNDatumUK();
        try {
            String dd = sdf.format(dt);
            dt = provere.dajDate(dd);            
        }catch (Exception e){return "Morate uneti Datum Racuna";}
        if (provere.proveriNotDouble(fNDugujeUK==null ? "" : fNDugujeUK.toString())) {return "Duguje morate uneti kao broj";}        
        if (provere.proveriNotDouble(fNPotrazujeUK==null ? "" : fNPotrazujeUK.toString())) {return "Potrazuje morate uneti kao broj";}
        if (provere.proveriNotDouble(fNSaldoUK==null ? "" : fNSaldoUK.toString())) {return "Saldo morate uneti kao broj";}        
       
        //if (dt == null){return "Morate uneti Datim Racuna";}
        return null;        
    }     
    
    public String VrednostiIspravke(){
        String datum = null;
        if (!(fNDatumUK==null || fNDatumUK.equals("")))datum = df.format(fNDatumUK);  
        vred = fNBrDokumentaUK + "####____" + datum + "####____" + fNDugujeUK + "####____" +
               fNPotrazujeUK + "####____" +   fNSaldoUK;
        return vred;
    } 
    public String VrednostiNovi(){
        String datum = null;
        if (!(fNDatumUK==null || fNDatumUK.equals("")))datum = df.format(fNDatumUK);        
        vred = getIdFNUK() + "####____" + 
               getFNBrDokumentaUK() + "####____" + datum + "####____" + getFNDugujeUK() + "####____" +
               getFNPotrazujeUK() + "####____" +   getFNSaldoUK();
            
        return vred;
    }
    public String PoljaZbir(){
        return "FNDugujeUK,FNPotrazujeUK,FNSaldoUK" ;
    }
    public String VredPoljaZbir(){
        return "";
    }
    public String getStavku(){
        return "";        
    }      
}