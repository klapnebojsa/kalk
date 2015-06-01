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
@Table(name = "vpkalkstavka")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vpkalkstavka.findAll", query = "SELECT v FROM Vpkalkstavka v"),
    @NamedQuery(name = "Vpkalkstavka.findByVpIdKalk", query = "SELECT v FROM Vpkalkstavka v WHERE v.vpkalkstavkaPK.vpIdKalk = :vpIdKalk"),
    @NamedQuery(name = "Vpkalkstavka.findByVpIdVlasnik", query = "SELECT v FROM Vpkalkstavka v WHERE v.vpkalkstavkaPK.vpIdVlasnik = :vpIdVlasnik"),
    @NamedQuery(name = "Vpkalkstavka.findByVpIdStavke", query = "SELECT v FROM Vpkalkstavka v WHERE v.vpkalkstavkaPK.vpIdStavke = :vpIdStavke"),
    @NamedQuery(name = "Vpkalkstavka.findByVpIdArtikla", query = "SELECT v FROM Vpkalkstavka v WHERE v.vpIdArtikla = :vpIdArtikla"),
    @NamedQuery(name = "Vpkalkstavka.findByVpKol", query = "SELECT v FROM Vpkalkstavka v WHERE v.vpKol = :vpKol"),
    @NamedQuery(name = "Vpkalkstavka.findByVpNabCena", query = "SELECT v FROM Vpkalkstavka v WHERE v.vpNabCena = :vpNabCena"),
    @NamedQuery(name = "Vpkalkstavka.findByVpCena", query = "SELECT v FROM Vpkalkstavka v WHERE v.vpCena = :vpCena"),
    @NamedQuery(name = "Vpkalkstavka.findByVpNabCenaSt", query = "SELECT v FROM Vpkalkstavka v WHERE v.vpNabCenaSt = :vpNabCenaSt"),
    @NamedQuery(name = "Vpkalkstavka.findByVpCenaSt", query = "SELECT v FROM Vpkalkstavka v WHERE v.vpCenaSt = :vpCenaSt"),
    @NamedQuery(name = "Vpkalkstavka.findByVpRazUCeniSt", query = "SELECT v FROM Vpkalkstavka v WHERE v.vpRazUCeniSt = :vpRazUCeniSt"),
    @NamedQuery(name = "Vpkalkstavka.findByVpPorezSt", query = "SELECT v FROM Vpkalkstavka v WHERE v.vpPorezSt = :vpPorezSt")})
public class Vpkalkstavka implements InterfaceDAO, Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Id    
    @Column(name = "VpIdKalk")
    private String vpIdKalk;
    @Basic(optional = false)
    @Column(name = "VpIdVlasnik")
    private String vpIdVlasnik;
    @Basic(optional = false)
    @Column(name = "VpIdStavke")
    private int vpIdStavke;    
    @Column(name = "VpIdArtikla")
    private String vpIdArtikla;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "VpKol")
    private Double vpKol;
    @Column(name = "VpNabCena")
    private Double vpNabCena;
    @Column(name = "VpCena")
    private Double vpCena;
    @Column(name = "VpNabCenaSt")
    private Double vpNabCenaSt;
    @Column(name = "VpCenaSt")
    private Double vpCenaSt;
    @Column(name = "VpRazUCeniSt")
    private Double vpRazUCeniSt;
    @Column(name = "VpPorezSt")
    private Double vpPorezSt;

    String vred;
    public Vpkalkstavka() {
    }

    public String getVpIdKalk() {
        return vpIdKalk;
    }

    public void setVpIdKalk(String vpIdKalk) {
        this.vpIdKalk = vpIdKalk;
    }

    public String getVpIdVlasnik() {
        return vpIdVlasnik;
    }

    public void setVpIdVlasnik(String vpIdVlasnik) {
        this.vpIdVlasnik = vpIdVlasnik;
    }

    public int getVpIdStavke() {
        return vpIdStavke;
    }

    public void setVpIdStavke(int vpIdStavke) {
        this.vpIdStavke = vpIdStavke;
    }
  
    public String getVpIdArtikla() {
        return vpIdArtikla;
    }

    public void setVpIdArtikla(String vpIdArtikla) {
        this.vpIdArtikla = vpIdArtikla;
    }

    public Double getVpKol() {
        return vpKol;
    }

    public void setVpKol(Double vpKol) {
        this.vpKol = vpKol;
    }

    public Double getVpNabCena() {
        return vpNabCena;
    }

    public void setVpNabCena(Double vpNabCena) {
        this.vpNabCena = vpNabCena;
    }

    public Double getVpCena() {
        return vpCena;
    }

    public void setVpCena(Double vpCena) {
        this.vpCena = vpCena;
    }

    public Double getVpNabCenaSt() {
        return vpNabCenaSt;
    }

    public void setVpNabCenaSt() throws SQLException {
        Izracunaj izracunaj = new Izracunaj();
        String rezultat = izracunaj.Proizvod(vpKol==null ? "" : vpKol.toString(),vpNabCena==null ? "" : vpNabCena.toString());
        this.vpNabCenaSt = Double.parseDouble(rezultat);        
    }

    public Double getVpCenaSt() {
        return vpCenaSt;
    }

    public void setVpCenaSt() throws SQLException {
        Izracunaj izracunaj = new Izracunaj();
        String rezultat = izracunaj.Proizvod(vpKol==null ? "" : vpKol.toString(),vpCena==null ? "" : vpCena.toString());
        this.vpCenaSt = Double.parseDouble(rezultat);            
    }

    public Double getVpRazUCeniSt() {
        return vpRazUCeniSt;
    }

    public void setVpRazUCeniSt() throws SQLException {
        Izracunaj izracunaj = new Izracunaj();
        String rezultat = izracunaj.Razliku(vpCenaSt==null ? "" : vpCenaSt.toString(),vpNabCenaSt==null ? "" : vpNabCenaSt.toString());
        this.vpRazUCeniSt = Double.parseDouble(rezultat);          
    }

    public Double getVpPorezSt() {
        return vpPorezSt;
    }

    public void setVpPorezSt(Double vpPorezSt) {
        this.vpPorezSt = vpPorezSt;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vpkalkstavka)) {
            return false;
        }
        Vpkalkstavka other = (Vpkalkstavka) object;
        if ((this.vpIdKalk == null && other.vpIdKalk != null) || (this.vpIdKalk != null && !this.vpIdKalk.equals(other.vpIdKalk))) {
            return false;
        }
        if ((this.vpIdVlasnik == null && other.vpIdVlasnik != null) || (this.vpIdVlasnik != null && !this.vpIdVlasnik.equals(other.vpIdVlasnik))) {
            return false;
        }
        if (this.vpIdStavke != other.vpIdStavke) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KlaseBaze.VpkalkstavkaPK[ vpIdKalk=" + vpIdKalk + ", vpIdVlasnik=" + vpIdVlasnik + ", vpIdStavke=" + vpIdStavke + " ]";
    }
////////OVO PROVERITI    
    public String UslovTrazenjaSvih(){
        return "VpIdKalk='" + vpIdKalk + "'" + " and VpIdVlasnik= '" + vpIdVlasnik + "'";
    }  
    public String UslovTrazenjaSloga(int a){
        return "";
    }  
    public String UslovTrazenjaSloga(){
        return "VpIdKalk='" + vpIdKalk + "'" + " and VpIdVlasnik='" + vpIdVlasnik + "'" + " and VpIdStavke=" + vpIdStavke + "";
    }
 
    public String ImeKlase(){
        return "vpkalkstavka";
    }    

    public String IspraviSlog(){
        return "VpIdArtikla='" + vpIdArtikla  + "', " +            
               "VpKol="        + vpKol        + ", "  +
               "VpNabCena="    + vpNabCena    + ", "  +                                
               "VpCena="       + vpCena       + ", "  + 
               "VpNabCenaSt="  + vpNabCenaSt  + ", "  + 
               "VpCenaSt="     + vpCenaSt     + ", "  +
               "VpRazUCeniSt=" + vpRazUCeniSt + ", "  + 
               "VpPorezSt="    + vpPorezSt    + "" ;        
    }
  
    public String IspraviSlog(int a){
        return "";
    }    
    
    public String UpisiSlog(){
        return "'" + vpIdKalk      + "', '" 
                   + vpIdVlasnik   + "',  " 
                   + vpIdStavke    + " , '" 
                   + vpIdArtikla   + "', "                 
                   + vpKol         + ", "
                   + vpNabCena     + ", "                
                   + vpCena        + ", "                 
                   + vpNabCenaSt   + ", " 
                   + vpCenaSt      + ", "
                   + vpRazUCeniSt  + ", " 
                   + vpPorezSt     + "";        
    } 
    public String ProveriVrednosti(){
        Provere provere = new Provere();
        /*if (provere.proveriPrazno(getIdFN())) {return "Morate uneti Dokument";}                 
        if (provere.proveriNotInteger(getIdStavke())){return "Morate uneti Broj Racuna";}*/
         
        if (provere.proveriPrazno(vpIdArtikla)){return "Morate uneti Broj Dokumenta";}
        if (provere.proveriNotDouble(vpKol==null ?        "" : vpKol.toString())) {return "Morate uneti Kolicinu kao broj";}
        if (provere.proveriNotDouble(vpNabCena==null ?    "" : vpNabCena.toString())) {return "Morate uneti Nabavnu Cenu kao broj";}        
        if (provere.proveriNotDouble(vpCena==null ?       "" : vpCena.toString())) {return "Morate uneti VP Cenu kao broj";}        
        if (provere.proveriNotDouble(vpNabCenaSt==null ?  "" : vpNabCenaSt.toString())) {return "Nabavna Cena mora biti broj";}
        if (provere.proveriNotDouble(vpCenaSt==null ?     "" : vpCenaSt.toString())) {return "Ukupna cena mora biti broj";}        
        if (provere.proveriNotDouble(vpRazUCeniSt==null ? "" : vpRazUCeniSt.toString())) {return "Ukupna Raylika u Ceni mora biti broj";}
        if (provere.proveriNotDouble(vpPorezSt==null ?    "" : vpPorezSt.toString())) {return "Porez mora biti broj";}        
            
        return null;        
    }  

    /**
     *
     * @param PoCemu
     * @return
     * @throws SQLException
     */
    public String VrednostiIspravke(){
        vred = vpIdArtikla + "####____" + vpKol    + "####____" + vpNabCena    + "####____" + vpCena + "####____" +
               vpNabCenaSt + "####____" + vpCenaSt + "####____" + vpRazUCeniSt + "####____" + vpPorezSt;
        return vred;
    } 
    public String VrednostiNovi(){
        vred = vpIdArtikla + "####____" + vpKol       + "####____" + vpNabCena    + "####____" + vpCena + "####____" +
               vpNabCenaSt + "####____" + vpCenaSt    + "####____" + vpRazUCeniSt + "####____" + vpPorezSt; 
        return vred;
    }
    
    public String OdrediStavku(){
        String stavka = null;
        int BrStavke = 0;
        
        return stavka;
    }
    
    public String PoljaZbir(){
        return "VpNabCenaSt,VpCenaSt,VpRazUCeniSt,VpPorezSt";      
    } 
    public String VredPoljaZbir(){
        return vpNabCenaSt + "," + vpCenaSt + "," + vpRazUCeniSt + "," + vpPorezSt;        
    }
    public String getStavku(){
        return "VpIdStavke";        
    }      
}