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
@Table(name = "vlasnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Vlasnik.findAll", query = "SELECT v FROM Vlasnik v"),
    @NamedQuery(name = "Vlasnik.findBySifra", query = "SELECT v FROM Vlasnik v WHERE v.sifra = :sifra"),
    @NamedQuery(name = "Vlasnik.findByNaziv", query = "SELECT v FROM Vlasnik v WHERE v.naziv = :naziv")})
public class Vlasnik implements InterfaceDAO, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Sifra")
    private String sifra;
    @Column(name = "Naziv")
    private String naziv;

    String vred;
    public Vlasnik() {
    }

    public Vlasnik(String sifra) {
        this.sifra = sifra;
    }

    public String getSifra() {
        return sifra;
    }

    public void setSifra(String sifra) {
        this.sifra = sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (sifra != null ? sifra.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Vlasnik)) {
            return false;
        }
        Vlasnik other = (Vlasnik) object;
        if ((this.sifra == null && other.sifra != null) || (this.sifra != null && !this.sifra.equals(other.sifra))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Povezivanje.Vlasnik[ sifra=" + sifra + " ]";
    }
    
 ////////OVO PROVERITI    
    public String UslovTrazenjaSvih(){
        return "";
    }    
    public String UslovTrazenjaSloga(){
        return "Sifra='" + sifra + "'";
    }
    public String UslovTrazenjaSloga(int a){
        return "";
    }
 
    public String ImeKlase(){
        return "Vlasnik";
    }    
    public String IspraviSlog(){
        return "Naziv='" + naziv + "'";
    }
    public String IspraviSlog(int a){
        return "";
    }    
    
    public String UpisiSlog(){
        return "'" + sifra + "' , '" + naziv + "'";
    } 

    public String ProveriVrednosti(){
        Provere provere = new Provere();
        if (provere.proveriPrazno(getSifra())) {return "Morate uneti Sifru";}                 
        if (provere.proveriPrazno(getNaziv())){return "Morate uneti Naziv Vlasnika";}   
        return null;        
    }     
    public String VrednostiIspravke(){
            vred = getNaziv() + "####____";
        return vred;
    } 
    public String VrednostiNovi(){
            vred = getSifra() + "####____" + getNaziv();
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
