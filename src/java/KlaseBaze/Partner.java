/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KlaseBaze;

import Inerfejsi.InterfaceDAO;
import Povezivanje.Provere;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author neso
 */
@Entity
@Table(name = "partner")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Partner.findAll", query = "SELECT p FROM Partner p"),
    @NamedQuery(name = "Partner.findByIdPartner", query = "SELECT p FROM Partner p WHERE p.idPartner = :idPartner"),
    @NamedQuery(name = "Partner.findByNazivPartnera", query = "SELECT p FROM Partner p WHERE p.nazivPartnera = :nazivPartnera"),
    @NamedQuery(name = "Partner.findByAdresa", query = "SELECT p FROM Partner p WHERE p.adresa = :adresa"),
    @NamedQuery(name = "Partner.findByMesto", query = "SELECT p FROM Partner p WHERE p.mesto = :mesto"),
    @NamedQuery(name = "Partner.findByTel", query = "SELECT p FROM Partner p WHERE p.tel = :tel"),
    @NamedQuery(name = "Partner.findByMb", query = "SELECT p FROM Partner p WHERE p.mb = :mb"),
    @NamedQuery(name = "Partner.findByPib", query = "SELECT p FROM Partner p WHERE p.pib = :pib"),
    @NamedQuery(name = "Partner.findByPdv", query = "SELECT p FROM Partner p WHERE p.pdv = :pdv"),
    @NamedQuery(name = "Partner.findByDlatnost", query = "SELECT p FROM Partner p WHERE p.dlatnost = :dlatnost")})
public class Partner implements InterfaceDAO, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "IdPartner")
    private String idPartner;
    @Column(name = "NazivPartnera")
    private String nazivPartnera;
    @Column(name = "Adresa")
    private String adresa;
    @Column(name = "Mesto")
    private String mesto;
    @Column(name = "Tel")
    private String tel;
    @Column(name = "MB")
    private String mb;
    @Column(name = "PIB")
    private String pib;
    @Column(name = "PDV")
    private String pdv;
    @Column(name = "Dlatnost")
    private String dlatnost;

    String vred;
    public Partner() {
    }

    public Partner(String idPartner) {
        this.idPartner = idPartner;
    }

    public String getIdPartner() {
        return idPartner;
    }

    public void setIdPartner(String idPartner) {
        this.idPartner = idPartner;
    }

    public String getNazivPartnera() {
        return nazivPartnera;
    }

    public void setNazivPartnera(String nazivPartnera) {
        this.nazivPartnera = nazivPartnera;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getMesto() {
        return mesto;
    }

    public void setMesto(String mesto) {
        this.mesto = mesto;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getMb() {
        return mb;
    }

    public void setMb(String mb) {
        this.mb = mb;
    }

    public String getPib() {
        return pib;
    }

    public void setPib(String pib) {
        this.pib = pib;
    }

    public String getPdv() {
        return pdv;
    }

    public void setPdv(String pdv) {
        this.pdv = pdv;
    }

    public String getDlatnost() {
        return dlatnost;
    }

    public void setDlatnost(String dlatnost) {
        this.dlatnost = dlatnost;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPartner != null ? idPartner.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Partner)) {
            return false;
        }
        Partner other = (Partner) object;
        if ((this.idPartner == null && other.idPartner != null) || (this.idPartner != null && !this.idPartner.equals(other.idPartner))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Partner[ idPartner=" + idPartner + " ]";
    }
    
  ////////OVO PROVERITI    
    public String UslovTrazenjaSvih(){
        return "";
    } 
    public String UslovTrazenjaSloga(int a){
        return "";
    }   
    public String UslovTrazenjaSloga(){
        return "IdPartner='" + idPartner + "'";
    }
 
    public String ImeKlase(){
        return "Partner";
    }    
    public String IspraviSlog(){
        return "NazivPartnera='" + nazivPartnera + "', " +
               "Adresa='"        + adresa        + "', " + 
               "Mesto='"         + mesto         + "', " + 
               "Tel='"           + tel           + "', " + 
               "MB='"            + mb            + "', " +
               "PIB='"           + pib           + "', " + 
               "PDV='"           + pdv           + "', " + 
               "Dlatnost='"      + dlatnost     + "'" ;
    }
    public String IspraviSlog(int a){
        return "";
    }    
    
    public String UpisiSlog(){
        return "'" + idPartner     + "' , '" 
                   + nazivPartnera + "' , '" 
                   + adresa        + "' , '"                 
                   + mesto         + "' , '"                 
                   + tel           + "' , '"                 
                   + mb            + "' , '" 
                   + pib           + "' , '"                 
                   + pdv           + "' , '"                 
                   + dlatnost      + "'";
    }

    public String ProveriVrednosti(){
        Provere provere = new Provere();
        if (provere.proveriPrazno(getIdPartner())) {return "Morate uneti Sifru";}                 
        if (provere.proveriPrazno(getNazivPartnera())){return "Morate uneti Naziv";}
        if (provere.proveriPrazno(getAdresa())) {return "Morate uneti Adresu";}                 
        if (provere.proveriPrazno(getMesto())){return "Morate uneti Mesto";}        
        if (provere.proveriPrazno(getTel())) {return "Morate uneti Telefon";}                 
        if (provere.proveriPrazno(getMb())){return "Morate uneti Maticni broj";}        
        if (provere.proveriPrazno(getPib())) {return "Morate uneti PIB";}                 
        if (provere.proveriPrazno(getPdv())){return "Morate uneti PDV";}        
        if (provere.proveriPrazno(getDlatnost())) {return "Morate uneti Delatnost";}                      
        return null;        
    }
    
    public String VrednostiIspravke(){
        vred = getNazivPartnera() + "####____" + getAdresa() + "####____" + getMesto() + "####____" + getTel() + "####____" + 
               getMb() + "####____" + getPib() + "####____" + getPdv() + "####____" + getDlatnost();
        return vred;
    } 
    public String VrednostiNovi(){
        vred = getIdPartner() + "####____" +
               getNazivPartnera() + "####____" + getAdresa() + "####____" + getMesto() + "####____" + getTel() + "####____" + 
               getMb() + "####____" + getPib() + "####____" + getPdv() + "####____" + getDlatnost();
        return vred;
    }
    public String PoljaZbir(){
        return "" ;
    }
    public String VredPoljaZbir(){
        return "";
    }
    public String getStavku(){
        return "";        
    }      
}
