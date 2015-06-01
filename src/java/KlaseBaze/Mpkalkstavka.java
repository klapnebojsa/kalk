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
@Table(name = "mpkalkstavka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mpkalkstavka.findAll", query = "SELECT m FROM Mpkalkstavka m"),
    @NamedQuery(name = "Mpkalkstavka.findByMpIdKalk", query = "SELECT m FROM Mpkalkstavka m WHERE m.mpkalkstavkaPK.mpIdKalk = :mpIdKalk"),
    @NamedQuery(name = "Mpkalkstavka.findByMpIdVlasnik", query = "SELECT m FROM Mpkalkstavka m WHERE m.mpkalkstavkaPK.mpIdVlasnik = :mpIdVlasnik"),
    @NamedQuery(name = "Mpkalkstavka.findByMpIdStavke", query = "SELECT m FROM Mpkalkstavka m WHERE m.mpkalkstavkaPK.mpIdStavke = :mpIdStavke"),
    @NamedQuery(name = "Mpkalkstavka.findByMpIdArtikla", query = "SELECT m FROM Mpkalkstavka m WHERE m.mpIdArtikla = :mpIdArtikla"),
    @NamedQuery(name = "Mpkalkstavka.findByMpKol", query = "SELECT m FROM Mpkalkstavka m WHERE m.mpKol = :mpKol"),
    @NamedQuery(name = "Mpkalkstavka.findByMpNabCena", query = "SELECT m FROM Mpkalkstavka m WHERE m.mpNabCena = :mpNabCena"),
    @NamedQuery(name = "Mpkalkstavka.findByMpCena", query = "SELECT m FROM Mpkalkstavka m WHERE m.mpCena = :mpCena"),
    @NamedQuery(name = "Mpkalkstavka.findByMpNabCenaSt", query = "SELECT m FROM Mpkalkstavka m WHERE m.mpNabCenaSt = :mpNabCenaSt"),
    @NamedQuery(name = "Mpkalkstavka.findByMpCenaSt", query = "SELECT m FROM Mpkalkstavka m WHERE m.mpCenaSt = :mpCenaSt"),
    @NamedQuery(name = "Mpkalkstavka.findByMpRazUCeniSt", query = "SELECT m FROM Mpkalkstavka m WHERE m.mpRazUCeniSt = :mpRazUCeniSt"),
    @NamedQuery(name = "Mpkalkstavka.findByMpPorezSt", query = "SELECT m FROM Mpkalkstavka m WHERE m.mpPorezSt = :mpPorezSt")})
public class Mpkalkstavka implements InterfaceDAO, Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "MpIdKalk")
    private String mpIdKalk;
    @Basic(optional = false)
    @Column(name = "MpIdVlasnik")
    private String mpIdVlasnik;
    @Column(name = "MpIdStavke")
    private int mpIdStavke;
    @Column(name = "MpIdArtikla")
    private String mpIdArtikla;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "MpKol")
    private Double mpKol;
    @Column(name = "MpNabCena")
    private Double mpNabCena;
    @Column(name = "MpCena")
    private Double mpCena;
    @Column(name = "MpNabCenaSt")
    private Double mpNabCenaSt;
    @Column(name = "MpCenaSt")
    private Double mpCenaSt;
    @Column(name = "MpRazUCeniSt")
    private Double mpRazUCeniSt;
    @Column(name = "MpPorezSt")
    private Double mpPorezSt;

    String vred;
    public Mpkalkstavka() {
    }
    public String getMpIdKalk() {
        return mpIdKalk;
    }

    public void setMpIdKalk(String mpIdKalk) {
        this.mpIdKalk = mpIdKalk;
    }

    public String getMpIdVlasnik() {
        return mpIdVlasnik;
    }

    public void setMpIdVlasnik(String mpIdVlasnik) {
        this.mpIdVlasnik = mpIdVlasnik;
    }

    public int getMpIdStavke() {
        return mpIdStavke;
    }

    public void setMpIdStavke(int mpIdStavke) {
        this.mpIdStavke = mpIdStavke;
    }
    
    public String getMpIdArtikla() {
        return mpIdArtikla;
    }

    public void setMpIdArtikla(String mpIdArtikla) {
        this.mpIdArtikla = mpIdArtikla;
    }

    public Double getMpKol() {
        return mpKol;
    }

    public void setMpKol(Double mpKol) {
        this.mpKol = mpKol;
    }

    public Double getMpNabCena() {
        return mpNabCena;
    }

    public void setMpNabCena(Double mpNabCena) {
        this.mpNabCena = mpNabCena;
    }

    public Double getMpCena() {
        return mpCena;
    }

    public void setMpCena(Double mpCena) {
        this.mpCena = mpCena;
    }

    public Double getMpNabCenaSt() {
        return mpNabCenaSt;
    }

    public void setMpNabCenaSt() throws SQLException {
        Izracunaj izracunaj = new Izracunaj();
        String rezultat = izracunaj.Proizvod(mpKol==null ? "" : mpKol.toString(),mpNabCena==null ? "" : mpNabCena.toString());
        this.mpNabCenaSt = Double.parseDouble(rezultat);         
    }

    public Double getMpCenaSt() {
        return mpCenaSt;
    }

    public void setMpCenaSt() throws SQLException {
        Izracunaj izracunaj = new Izracunaj();
        String rezultat = izracunaj.Proizvod(mpKol==null ? "" : mpKol.toString(),mpCena==null ? "" : mpCena.toString());
        this.mpCenaSt = Double.parseDouble(rezultat);          
    }

    public Double getMpRazUCeniSt() {
        return mpRazUCeniSt;
    }

    public void setMpRazUCeniSt() {
        this.mpRazUCeniSt = mpCenaSt - mpNabCenaSt - mpPorezSt;
    }

    public Double getMpPorezSt() {
        return mpPorezSt;
    }

    public void setMpPorezSt(Double mpPorezSt) {
        this.mpPorezSt = mpPorezSt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mpIdKalk != null ? mpIdKalk.hashCode() : 0);
        hash += (mpIdVlasnik != null ? mpIdVlasnik.hashCode() : 0);
        hash += (int) mpIdStavke;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mpkalkstavka)) {
            return false;
        }
        Mpkalkstavka other = (Mpkalkstavka) object;
        if ((this.mpIdKalk == null && other.mpIdKalk != null) || (this.mpIdKalk != null && !this.mpIdKalk.equals(other.mpIdKalk))) {
            return false;
        }
        if ((this.mpIdVlasnik == null && other.mpIdVlasnik != null) || (this.mpIdVlasnik != null && !this.mpIdVlasnik.equals(other.mpIdVlasnik))) {
            return false;
        }
        if (this.mpIdStavke != other.mpIdStavke) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "DAO.Mpkalkstavka[ mpIdKalk=" + mpIdKalk + ", mpIdVlasnik=" + mpIdVlasnik + ", mpIdStavke=" + mpIdStavke + " ]";
    }
    
////////OVO PROVERITI    
    public String UslovTrazenjaSvih(){
        return "MpIdKalk='" + mpIdKalk + "'" + " and MpIdVlasnik= '" + mpIdVlasnik + "'";
    } 
    public String UslovTrazenjaSloga(int a){
        return "";
    }   
    public String UslovTrazenjaSloga(){
        return "MpIdKalk='" + mpIdKalk + "'" + " and MpIdVlasnik='" + mpIdVlasnik + "'" + " and MpIdStavke=" + mpIdStavke + "";
    }
 
    public String ImeKlase(){
        return "mpkalkstavka";
    }    

    public String IspraviSlog(){
        return "MpIdArtikla='" + mpIdArtikla  + "', " +            
               "MpKol="        + mpKol        + ", "  +
               "MpNabCena="    + mpNabCena    + ", "  +                                
               "MpCena="       + mpCena       + ", "  + 
               "MpNabCenaSt="  + mpNabCenaSt  + ", "  + 
               "MpCenaSt="     + mpCenaSt     + ", "  +
               "MpRazUCeniSt=" + mpRazUCeniSt + ", "  + 
               "MpPorezSt="    + mpPorezSt    + "" ;        
    }
  
    public String IspraviSlog(int a){
        return "";
    }    
    
    public String UpisiSlog(){
        return "'" + mpIdKalk      + "', '" 
                   + mpIdVlasnik   + "',  " 
                   + mpIdStavke    + " , '" 
                   + mpIdArtikla   + "', "                 
                   + mpKol         + ", "
                   + mpNabCena     + ", "                
                   + mpCena        + ", "                 
                   + mpNabCenaSt   + ", " 
                   + mpCenaSt      + ", "
                   + mpRazUCeniSt  + ", " 
                   + mpPorezSt     + "";        
    } 
    public String ProveriVrednosti(){
        Provere provere = new Provere();
        /*if (provere.proveriPrazno(getIdFN())) {return "Morate uneti Dokument";}                 
        if (provere.proveriNotInteger(getIdStavke())){return "Morate uneti Broj Racuna";}*/
         
        if (provere.proveriPrazno(mpIdArtikla)){return "Morate uneti Broj Dokumenta";}
        if (provere.proveriNotDouble(mpKol==null ?        "" : mpKol.toString())) {return "Morate uneti Kolicinu kao broj";}
        if (provere.proveriNotDouble(mpNabCena==null ?    "" : mpNabCena.toString())) {return "Morate uneti Nabavnu Cenu kao broj";}        
        if (provere.proveriNotDouble(mpCena==null ?       "" : mpCena.toString())) {return "Morate uneti MP Cenu kao broj";}        
        if (provere.proveriNotDouble(mpNabCenaSt==null ?  "" : mpNabCenaSt.toString())) {return "Nabavna Cena mora biti broj";}
        if (provere.proveriNotDouble(mpCenaSt==null ?     "" : mpCenaSt.toString())) {return "Ukupna cena mora biti broj";}        
        if (provere.proveriNotDouble(mpRazUCeniSt==null ? "" : mpRazUCeniSt.toString())) {return "Ukupna Raylika u Ceni mora biti broj";}
        if (provere.proveriNotDouble(mpPorezSt==null ?    "" : mpPorezSt.toString())) {return "Porez mora biti broj";}        
            
        return null;        
    }  

    /**
     *
     * @param PoCemu
     * @return
     * @throws SQLException
     */
    public String VrednostiIspravke(){
        vred = mpIdArtikla + "####____" + mpKol    + "####____" + mpNabCena    + "####____" + mpCena + "####____" +
               mpNabCenaSt + "####____" + mpCenaSt + "####____" + mpRazUCeniSt + "####____" + mpPorezSt;
        return vred;
    } 
    public String VrednostiNovi(){
        vred = mpIdArtikla + "####____" + mpKol    + "####____" + mpNabCena    + "####____" + mpCena + "####____" +
               mpNabCenaSt + "####____" + mpCenaSt + "####____" + mpRazUCeniSt + "####____" + mpPorezSt;
        return vred;
    }
    
    public String OdrediStavku(){
        String stavka = null;
        int BrStavke = 0;
        
        return stavka;
    }
    
    public String PoljaZbir(){
        return "MpNabCenaSt,MpCenaSt,MpRazUCeniSt,MpPorezSt";      
    } 
    public String VredPoljaZbir(){
        return mpNabCenaSt + "," + mpCenaSt + "," + mpRazUCeniSt + "," + mpPorezSt;        
    }
    public String getStavku(){
        return "MpIdStavke";        
    }       
    
}
