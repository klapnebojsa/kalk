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
@Table(name = "vpkalkuk")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vpkalkuk.findAll", query = "SELECT v FROM Vpkalkuk v"),
    @NamedQuery(name = "Vpkalkuk.findByVpIdKalkUK", query = "SELECT v FROM Vpkalkuk v WHERE v.vpkalkukPK.vpIdKalkUK = :vpIdKalkUK"),
    @NamedQuery(name = "Vpkalkuk.findByVpIdVlasnikUK", query = "SELECT v FROM Vpkalkuk v WHERE v.vpkalkukPK.vpIdVlasnikUK = :vpIdVlasnikUK"),
    @NamedQuery(name = "Vpkalkuk.findByVPBrDokumentaUK", query = "SELECT v FROM Vpkalkuk v WHERE v.vPBrDokumentaUK = :vPBrDokumentaUK"),
    @NamedQuery(name = "Vpkalkuk.findByVpDatumUK", query = "SELECT v FROM Vpkalkuk v WHERE v.vpDatumUK = :vpDatumUK"),
    @NamedQuery(name = "Vpkalkuk.findByVpValutaUK", query = "SELECT v FROM Vpkalkuk v WHERE v.vpValutaUK = :vpValutaUK"),
    @NamedQuery(name = "Vpkalkuk.findByVpDobavljac", query = "SELECT v FROM Vpkalkuk v WHERE v.vpDobavljac = :vpDobavljac"),
    @NamedQuery(name = "Vpkalkuk.findByVpUplacenPorezUK", query = "SELECT v FROM Vpkalkuk v WHERE v.vpUplacenPorezUK = :vpUplacenPorezUK"),
    @NamedQuery(name = "Vpkalkuk.findByVpNabCenaUK", query = "SELECT v FROM Vpkalkuk v WHERE v.vpNabCenaUK = :vpNabCenaUK"),
    @NamedQuery(name = "Vpkalkuk.findByVPCenaUK", query = "SELECT v FROM Vpkalkuk v WHERE v.vPCenaUK = :vPCenaUK"),
    @NamedQuery(name = "Vpkalkuk.findByVPRazUCeniUK", query = "SELECT v FROM Vpkalkuk v WHERE v.vPRazUCeniUK = :vPRazUCeniUK"),
    @NamedQuery(name = "Vpkalkuk.findByVpPorezUK", query = "SELECT v FROM Vpkalkuk v WHERE v.vpPorezUK = :vpPorezUK")})
public class Vpkalkuk implements  InterfaceDAO, Serializable {
    @Column(name = "VpIdKalkUK")
    private String vpIdKalkUK;
    @Column(name = "VpIdVlasnikUK")
    private String vpIdVlasnikUK;    
    @EmbeddedId
    private String vPBrDokumentaUK;
    @Column(name = "VpDatumUK")
    @Temporal(TemporalType.DATE)
    private Date vpDatumUK;
    @Column(name = "VpValutaUK")
    @Temporal(TemporalType.DATE)
    private Date vpValutaUK;
    @Column(name = "VpDobavljac")
    private String vpDobavljac;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VpUplacenPorezUK")
    private Double vpUplacenPorezUK;
    @Column(name = "VpNabCenaUK")
    private Double vpNabCenaUK;
    @Column(name = "VPCenaUK")
    private Double vPCenaUK;
    @Column(name = "VPRazUCeniUK")
    private Double vPRazUCeniUK;
    @Column(name = "VpPorezUK")
    private Double vpPorezUK;
    
    String vred;
    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    public Vpkalkuk() {
    }

    public String getVpIdKalkUK() {
        return vpIdKalkUK;
    }

    public void setVpIdKalkUK(String vpIdKalkUK) {
        this.vpIdKalkUK = vpIdKalkUK;
    }

    public String getVpIdVlasnikUK() {
        return vpIdVlasnikUK;
    }

    public void setVpIdVlasnikUK(String vpIdVlasnikUK) {
        this.vpIdVlasnikUK = vpIdVlasnikUK;
    }

    public String getVPBrDokumentaUK() {
        return vPBrDokumentaUK;
    }

    public void setVPBrDokumentaUK(String vPBrDokumentaUK) {
        this.vPBrDokumentaUK = vPBrDokumentaUK;
    }

    public Date getVpDatumUK() {
        return vpDatumUK;
    }

    public void setVpDatumUK(Date vpDatumUK) {
        this.vpDatumUK = vpDatumUK;
    }

    public Date getVpValutaUK() {
        return vpValutaUK;
    }

    public void setVpValutaUK(Date vpValutaUK) {
        this.vpValutaUK = vpValutaUK;
    }

    public String getVpDobavljac() {
        return vpDobavljac;
    }

    public void setVpDobavljac(String vpDobavljac) {
        this.vpDobavljac = vpDobavljac;
    }

    public Double getVpUplacenPorezUK() {
        return vpUplacenPorezUK;
    }

    public void setVpUplacenPorezUK(Double vpUplacenPorezUK) {
        this.vpUplacenPorezUK = vpUplacenPorezUK;
    }

    public Double getVpNabCenaUK() {
        return vpNabCenaUK;
    }

    public void setVpNabCenaUK(Double vpNabCenaUK) {
        this.vpNabCenaUK = vpNabCenaUK;
    }

    public Double getVPCenaUK() {
        return vPCenaUK;
    }

    public void setVPCenaUK(Double vPCenaUK) {
        this.vPCenaUK = vPCenaUK;
    }

    public Double getVPRazUCeniUK() {
        return vPRazUCeniUK;
    }

    public void setVPRazUCeniUK(Double vPRazUCeniUK) {
        this.vPRazUCeniUK = vPRazUCeniUK;
    }

    public Double getVpPorezUK() {
        return vpPorezUK;
    }

    public void setVpPorezUK(Double vpPorezUK) {
        this.vpPorezUK = vpPorezUK;
    }
////////OVO PROVERITI    
    public String UslovTrazenjaSvih(){
        return "VpIdVlasnikUK= '" + vpIdVlasnikUK + "'";
    }   
    public String UslovTrazenjaSloga(int a){
        return "";
    } 
    public String UslovTrazenjaSloga(){
        return "VpIdKalkUK='" + vpIdKalkUK + "'" + " and VpIdVlasnikUK='" + vpIdVlasnikUK + "'";
    }
 
    public String ImeKlase(){
        return "vpkalkuk";
    }    

    public String IspraviSlog(){
        return "VPBrDokumentaUK='" + vPBrDokumentaUK       + "' , " +
               "VpDatumUK='"       + df.format(vpDatumUK)  + "' , " + 
               "VpValutaUK='"      + df.format(vpValutaUK) + "' , " + 
               "VpDobavljac='"     + vpDobavljac           + "' , " + 
               "VpUplacenPorezUK=" + vpUplacenPorezUK      + " , "  +               
               "VpNabCenaUK="      + vpNabCenaUK           + " , "  +
               "VPCenaUK="         + vPCenaUK              + " , "  +
               "VPRazUCeniUK="     + vPRazUCeniUK          + " , "  +
               "VpPorezUK="        + vpPorezUK             + ""  ;
    }
  
    public String IspraviSlog(int a){
        return "";
    }    
    
    public String UpisiSlog(){
        return "'" + vpIdKalkUK            + "' , '" 
                   + vpIdVlasnikUK         + "' , '" 
                   + vPBrDokumentaUK       + "' , '"                 
                   + df.format(vpDatumUK)  + "' , '"                 
                   + df.format(vpValutaUK) + "' , '"                 
                   + vpDobavljac           + "' , " 
                   + vpUplacenPorezUK      + " , "
                   + vpNabCenaUK           + " , " 
                   + vPCenaUK              + " , "                
                   + vPRazUCeniUK          + " , " 
                   + vpPorezUK             + "";            
    } 
    public String ProveriVrednosti(){
        Provere provere = new Provere();
        if (provere.proveriPrazno(vpIdKalkUK)) {return "Morate uneti Dokuent";}                 
        if (provere.proveriPrazno(vPBrDokumentaUK)){return "Morate uneti Broj Racuna";}
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt = vpDatumUK;
        try {
            String dd = sdf.format(dt);
            dt = provere.dajDate(dd);            
        }catch (Exception e){return "Morate uneti Datum Racuna";}
        dt = vpValutaUK;
        try {
            String dd = sdf.format(dt);
            dt = provere.dajDate(dd);            
        }catch (Exception e){return "Morate uneti Valutu Racuna";}      
        if (provere.proveriPrazno(vpDobavljac)){return "Morate uneti Dobavljaca";}        
        if (provere.proveriNotDouble(vpUplacenPorezUK==null ? "" : vpUplacenPorezUK.toString())) {return "Uplaceni porez morate uneti kao broj";}        
        
        if (provere.proveriNotDouble(vpNabCenaUK==null ? "" : vpNabCenaUK.toString())) {return "Nabavna Cena mora biti broj";}
        if (provere.proveriNotDouble(vPCenaUK==null ? "" : vPCenaUK.toString())) {return "VP Cena mora biti broj";}     
        if (provere.proveriNotDouble(vPRazUCeniUK==null ? "" : vPRazUCeniUK.toString())) {return "Razlika u Ceni mora biti broj";}
        if (provere.proveriNotDouble(vpPorezUK==null ? "" : vpPorezUK.toString())) {return "Porez Cena mora biti broj";}         
        //if (dt == null){return "Morate uneti Datim Racuna";}
        return null;        
    }     
    
    public String VrednostiIspravke(){
        String datumDK = null;
        String datumVA = null;        
        if (!(vpDatumUK==null  || vpDatumUK.equals(""))) datumDK = df.format(vpDatumUK);
        if (!(vpValutaUK==null || vpValutaUK.equals("")))datumVA = df.format(vpValutaUK);        
        
        vred = vPBrDokumentaUK  + "####____" + datumDK          + "####____" + datumVA      + "####____" + 
               vpDobavljac      + "####____" + vpUplacenPorezUK + "####____" + vpNabCenaUK  + "####____" + 
               vpNabCenaUK      + "####____" + vPCenaUK         + "####____" + vPRazUCeniUK + "####____" + vpPorezUK; 
        return vred;
    }
    public String VrednostiNovi(){
        String datumDK = null;
        String datumVA = null;        
        if (!(vpDatumUK==null  || vpDatumUK.equals(""))) datumDK = df.format(vpDatumUK);
        if (!(vpValutaUK==null || vpValutaUK.equals("")))datumVA = df.format(vpValutaUK);  
        vred = vpIdKalkUK       + "####____" + 
               vPBrDokumentaUK  + "####____" + datumDK          + "####____" + datumVA      + "####____" + 
               vpDobavljac      + "####____" + vpUplacenPorezUK + "####____" + vpNabCenaUK  + "####____" + 
               vpNabCenaUK      + "####____" + vPCenaUK         + "####____" + vPRazUCeniUK + "####____" + vpPorezUK; 
        return vred;
    }
    public String PoljaZbir(){
        return "VpNabCenaUK,VPCenaUK,VPRazUCeniUK,VpPorezUK" ;
    }    
    public String VredPoljaZbir(){
        return "";
    } 
     public String getStavku(){
        return "";        
    }     
    
}
