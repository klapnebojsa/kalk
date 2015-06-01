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
@Table(name = "mpkalkuk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mpkalkuk.findAll", query = "SELECT m FROM Mpkalkuk m"),
    @NamedQuery(name = "Mpkalkuk.findByMpIdKalkUK", query = "SELECT m FROM Mpkalkuk m WHERE m.mpkalkukPK.mpIdKalkUK = :mpIdKalkUK"),
    @NamedQuery(name = "Mpkalkuk.findByMpIdVlasnikUK", query = "SELECT m FROM Mpkalkuk m WHERE m.mpkalkukPK.mpIdVlasnikUK = :mpIdVlasnikUK"),
    @NamedQuery(name = "Mpkalkuk.findByMpBrDokumenta", query = "SELECT m FROM Mpkalkuk m WHERE m.mpBrDokumenta = :mpBrDokumenta"),
    @NamedQuery(name = "Mpkalkuk.findByMpDatumUK", query = "SELECT m FROM Mpkalkuk m WHERE m.mpDatumUK = :mpDatumUK"),
    @NamedQuery(name = "Mpkalkuk.findByMpValutaUK", query = "SELECT m FROM Mpkalkuk m WHERE m.mpValutaUK = :mpValutaUK"),
    @NamedQuery(name = "Mpkalkuk.findByMpKupac", query = "SELECT m FROM Mpkalkuk m WHERE m.mpKupac = :mpKupac"),
    @NamedQuery(name = "Mpkalkuk.findByMpNabCenaUK", query = "SELECT m FROM Mpkalkuk m WHERE m.mpNabCenaUK = :mpNabCenaUK"),
    @NamedQuery(name = "Mpkalkuk.findByMPCenaUK", query = "SELECT m FROM Mpkalkuk m WHERE m.mPCenaUK = :mPCenaUK"),
    @NamedQuery(name = "Mpkalkuk.findByMPRazUCeniUK", query = "SELECT m FROM Mpkalkuk m WHERE m.mPRazUCeniUK = :mPRazUCeniUK"),
    @NamedQuery(name = "Mpkalkuk.findByMpPorezUK", query = "SELECT m FROM Mpkalkuk m WHERE m.mpPorezUK = :mpPorezUK")})
public class Mpkalkuk implements InterfaceDAO, Serializable {
    private static final long serialVersionUID = 1L;
    @Column(name = "MpIdKalkUK")
    private String mpIdKalkUK;
    @Basic(optional = false)
    @Column(name = "MpIdVlasnikUK")
    private String mpIdVlasnikUK;
    @Column(name = "MpBrDokumenta")
    private String mpBrDokumenta;
    @Column(name = "MpDatumUK")
    @Temporal(TemporalType.DATE)
    private Date mpDatumUK;
    @Column(name = "MpValutaUK")
    @Temporal(TemporalType.DATE)
    private Date mpValutaUK;
    @Column(name = "MpKupac")
    private String mpKupac;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MpNabCenaUK")
    private Double mpNabCenaUK;
    @Column(name = "MPCenaUK")
    private Double mPCenaUK;
    @Column(name = "MPRazUCeniUK")
    private Double mPRazUCeniUK;
    @Column(name = "MpPorezUK")
    private Double mpPorezUK;
    
    String vred;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public Mpkalkuk() {
    }

    
    public String getMpIdKalkUK() {
        return mpIdKalkUK;
    }

    public void setMpIdKalkUK(String mpIdKalkUK) {
        this.mpIdKalkUK = mpIdKalkUK;
    }

    public String getMpIdVlasnikUK() {
        return mpIdVlasnikUK;
    }

    public void setMpIdVlasnikUK(String mpIdVlasnikUK) {
        this.mpIdVlasnikUK = mpIdVlasnikUK;
    }    

    public String getMpBrDokumenta() {
        return mpBrDokumenta;
    }

    public void setMpBrDokumenta(String mpBrDokumenta) {
        this.mpBrDokumenta = mpBrDokumenta;
    }

    public Date getMpDatumUK() {
        return mpDatumUK;
    }

    public void setMpDatumUK(Date mpDatumUK) {
        this.mpDatumUK = mpDatumUK;
    }

    public Date getMpValutaUK() {
        return mpValutaUK;
    }

    public void setMpValutaUK(Date mpValutaUK) {
        this.mpValutaUK = mpValutaUK;
    }

    public String getMpKupac() {
        return mpKupac;
    }

    public void setMpKupac(String mpKupac) {
        this.mpKupac = mpKupac;
    }

    public Double getMpNabCenaUK() {
        return mpNabCenaUK;
    }

    public void setMpNabCenaUK(Double mpNabCenaUK) {
        this.mpNabCenaUK = mpNabCenaUK;
    }

    public Double getMPCenaUK() {
        return mPCenaUK;
    }

    public void setMPCenaUK(Double mPCenaUK) {
        this.mPCenaUK = mPCenaUK;
    }

    public Double getMPRazUCeniUK() {
        return mPRazUCeniUK;
    }

    public void setMPRazUCeniUK(Double mPRazUCeniUK) {
        this.mPRazUCeniUK = mPRazUCeniUK;
    }

    public Double getMpPorezUK() {
        return mpPorezUK;
    }

    public void setMpPorezUK(Double mpPorezUK) {
        this.mpPorezUK = mpPorezUK;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mpIdKalkUK != null ? mpIdKalkUK.hashCode() : 0);
        hash += (mpIdVlasnikUK != null ? mpIdVlasnikUK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mpkalkuk)) {
            return false;
        }
        Mpkalkuk other = (Mpkalkuk) object;
        if ((this.mpIdKalkUK == null && other.mpIdKalkUK != null) || (this.mpIdKalkUK != null && !this.mpIdKalkUK.equals(other.mpIdKalkUK))) {
            return false;
        }
        if ((this.mpIdVlasnikUK == null && other.mpIdVlasnikUK != null) || (this.mpIdVlasnikUK != null && !this.mpIdVlasnikUK.equals(other.mpIdVlasnikUK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Mpkalkuk[ mpIdKalkUK=" + mpIdKalkUK + ", mpIdVlasnikUK=" + mpIdVlasnikUK + " ]";
    }
////////OVO PROVERITI    
    public String UslovTrazenjaSvih(){
        return "MpIdVlasnikUK= '" + mpIdVlasnikUK + "'";
    }   
    public String UslovTrazenjaSloga(int a){
        return "";
    } 
    public String UslovTrazenjaSloga(){
        return "MpIdKalkUK='" + mpIdKalkUK + "'" + " and MpIdVlasnikUK='" + mpIdVlasnikUK + "'";
    }
 
    public String ImeKlase(){
        return "mpkalkuk";
    }    

    public String IspraviSlog(){
        return "MpBrDokumenta='" + mpBrDokumenta       + "' , " +
               "MpDatumUK='"     + df.format(mpDatumUK)  + "' , " + 
               "MpValutaUK='"    + df.format(mpValutaUK) + "' , " + 
               "MpKupac='"       + mpKupac           + "' , " +               
               "MpNabCenaUK="    + mpNabCenaUK           + " , "  +
               "MPCenaUK="       + mPCenaUK              + " , "  +
               "MPRazUCeniUK="   + mPRazUCeniUK          + " , "  +
               "MpPorezUK="      + mpPorezUK             + ""  ;
    }
  
    public String IspraviSlog(int a){
        return "";
    }    
    
    public String UpisiSlog(){
        return "'" + mpIdKalkUK            + "' , '" 
                   + mpIdVlasnikUK         + "' , '" 
                   + mpBrDokumenta         + "' , '"                 
                   + df.format(mpDatumUK)  + "' , '"                 
                   + df.format(mpValutaUK) + "' , '"                 
                   + mpKupac               + "' , " 
                   + mpNabCenaUK           + " , " 
                   + mPCenaUK              + " , "                
                   + mPRazUCeniUK          + " , " 
                   + mpPorezUK             + "";            
    } 
    public String ProveriVrednosti(){
        Provere provere = new Provere();
        if (provere.proveriPrazno(mpIdKalkUK)) {return "Morate uneti Dokuent";}                 
        if (provere.proveriPrazno(mpBrDokumenta)){return "Morate uneti Broj Racuna";}
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = mpDatumUK;
        try {
            String dd = sdf.format(dt);
            dt = provere.dajDate(dd);            
        }catch (Exception e){return "Morate uneti Datum Racuna";}
        dt = mpValutaUK;
        try {
            String dd = sdf.format(dt);
            dt = provere.dajDate(dd);            
        }catch (Exception e){return "Morate uneti Valutu Racuna";}      
        if (provere.proveriPrazno(mpKupac)){return "Morate uneti Dobavljaca";}              
        
        if (provere.proveriNotDouble(mpNabCenaUK==null  ? "" : mpNabCenaUK.toString())) {return "Nabavna Cena mora biti broj";}
        if (provere.proveriNotDouble(mPCenaUK==null     ? "" : mPCenaUK.toString())) {return "VP Cena mora biti broj";}     
        if (provere.proveriNotDouble(mPRazUCeniUK==null ? "" : mPRazUCeniUK.toString())) {return "Razlika u Ceni mora biti broj";}
        if (provere.proveriNotDouble(mpPorezUK==null    ? "" : mpPorezUK.toString())) {return "Porez Cena mora biti broj";}         
        //if (dt == null){return "Morate uneti Datim Racuna";}
        return null;        
    }     
    
    public String VrednostiIspravke(){
        String datumDK = null;
        String datumVA = null;        
        if (!(mpDatumUK==null  || mpDatumUK.equals(""))) datumDK = df.format(mpDatumUK);
        if (!(mpValutaUK==null || mpValutaUK.equals("")))datumVA = df.format(mpValutaUK);        
        
        vred = mpBrDokumenta + "####____" + datumDK      + "####____" + datumVA      + "####____" + 
               mpKupac       + "####____" + mpNabCenaUK  + "####____" + 
               mpNabCenaUK   + "####____" + mPCenaUK     + "####____" + mPRazUCeniUK + "####____" + mpPorezUK; 
        return vred;
    }

    public String VrednostiNovi(){
        String datumDK = null;
        String datumVA = null;        
        if (!(mpDatumUK==null  || mpDatumUK.equals(""))) datumDK = df.format(mpDatumUK);
        if (!(mpValutaUK==null || mpValutaUK.equals("")))datumVA = df.format(mpValutaUK);  
        vred = mpIdKalkUK    + "####____" + 
               mpBrDokumenta + "####____" + datumDK      + "####____" + datumVA      + "####____" + 
               mpKupac       + "####____" + mpNabCenaUK  + "####____" + 
               mpNabCenaUK   + "####____" + mPCenaUK     + "####____" + mPRazUCeniUK + "####____" + mpPorezUK; 
        return vred;
    }
    public String PoljaZbir(){
        return "MpNabCenaUK,MPCenaUK,MPRazUCeniUK,MpPorezUK" ;
    }    
    public String VredPoljaZbir(){
        return "";
    } 
     public String getStavku(){
        return "";        
    }     
}
