    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KlaseBaze;

import Povezivanje.Provere;
import Inerfejsi.InterfaceDAO;
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
@Table(name = "konto")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Konto.findAll", query = "SELECT k FROM Konto k"),
    @NamedQuery(name = "Konto.findByKonto", query = "SELECT k FROM Konto k WHERE k.konto = :konto"),  
    @NamedQuery(name = "Konto.findByNazivKonta", query = "SELECT k FROM Konto k WHERE k.nazivKonta = :nazivKonta")})
public class Konto implements InterfaceDAO, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Konto")
    private String konto;
    @Column(name = "Vlasnik")
    private String vlasnik;    
    @Column(name = "NazivKonta")
    private String nazivKonta;

    String vred;
    
    public Konto() {
    }

    public Konto(String konto) {
        this.konto = konto;
    }

    public String getKonto() {
        return konto;
    }

    public void setKonto(String konto) {
        this.konto = konto;
    }
    
    
    public String getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(String vlasnik) {
        this.vlasnik = vlasnik;
    }
    
    
    public String getNazivKonta() {
        return nazivKonta;
    }

    public void setNazivKonta(String nazivKonta) {      
        this.nazivKonta = nazivKonta;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (konto != null ? konto.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Konto)) {
            return false;
        }
        Konto other = (Konto) object;
        return !((this.konto == null && other.konto != null) || (this.konto != null && !this.konto.equals(other.konto)));
    }

    @Override
    public String toString() {
        return "Povezivanje.Konto[ konto=" + konto + " ]";
    }

    /**
     *
     * @return
     */
    public String UslovTrazenjaSloga(){
        return "Konto='" + konto + "'" + " and Vlasnik='" + vlasnik + "'";
    }
    
    public String UslovTrazenjaSvih(){
        return "Vlasnik= '" + vlasnik + "'";
    }
    public String UslovTrazenjaSloga(int a){
        return "";
    }    
    public String ImeKlase(){
        return "Konto";
    }    
    public String IspraviSlog(){
        return "NazivKonta= '" + nazivKonta + "'";
    }
    public String IspraviSlog(int a){
        return "";
    }    
    
    public String UpisiSlog(){
        return "'" + konto + "' , '" + vlasnik + "' , '" + nazivKonta + "'";
    } 
      
    public String ProveriVrednosti(){
        Provere provere = new Provere();
        if (provere.proveriPrazno(getKonto())) {return "Morate uneti Sifru";}                 
        if (provere.proveriPrazno(getNazivKonta())){return "Morate uneti Naziv Konta";}   
        return null;
    }     
    public String VrednostiIspravke(){
        vred = getNazivKonta(); 
        return vred;
    } 
    public String VrednostiNovi(){
        vred = getKonto() + "####____" + getNazivKonta();
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
