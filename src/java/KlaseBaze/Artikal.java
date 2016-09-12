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
@Table(name = "artikal")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Artikal.findAll", query = "SELECT a FROM Artikal a"),
    @NamedQuery(name = "Artikal.findByIdArtikal", query = "SELECT a FROM Artikal a WHERE a.idArtikal = :idArtikal"),
    @NamedQuery(name = "Artikal.findByArOpis", query = "SELECT a FROM Artikal a WHERE a.arOpis = :arOpis"),
    @NamedQuery(name = "Artikal.findByArDim", query = "SELECT a FROM Artikal a WHERE a.arDim = :arDim"),
    @NamedQuery(name = "Artikal.findByArPorStopa", query = "SELECT a FROM Artikal a WHERE a.arPorStopa = :arPorStopa")})
public class Artikal implements InterfaceDAO, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idArtikal")
    private String idArtikal;
    @Column(name = "ArVlasnik")
    private String arVlasnik;     
    @Column(name = "ArOpis")
    private String arOpis;
    @Column(name = "ArDim")
    private String arDim;
    @Column(name = "ArPorStopa")
    private String arPorStopa;

    String vred;
    public Artikal() {
    }

    public Artikal(String idArtikal) {
        this.idArtikal = idArtikal;
    }

    public String getIdArtikal() {
        return idArtikal;
    }

    public void setIdArtikal(String idArtikal) {
        this.idArtikal = idArtikal;
    }
    
    public String getArVlasnik() {
        return arVlasnik;
    }

    public void setArVlasnik(String arVlasnik) {
        this.arVlasnik = arVlasnik;
    }
    
    public String getArOpis() {
        return arOpis;
    }

    public void setArOpis(String arOpis) {
        this.arOpis = arOpis;
    }

    public String getArDim() {
        return arDim;
    }

    public void setArDim(String arDim) {
        this.arDim = arDim;
    }

    public String getArPorStopa() {
        return arPorStopa;
    }

    public void setArPorStopa(String arPorStopa) {
        this.arPorStopa = arPorStopa;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idArtikal != null ? idArtikal.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Artikal)) {
            return false;
        }
        Artikal other = (Artikal) object;
        if ((this.idArtikal == null && other.idArtikal != null) || (this.idArtikal != null && !this.idArtikal.equals(other.idArtikal))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "KlaseBaze.Artikal[ idArtikal=" + idArtikal + " ]";
    }
 ////////OVO PROVERITI    
    public String UslovTrazenjaSvih(){
        return "ArVlasnik= '" + arVlasnik + "'";
    }
    public String UslovTrazenjaSloga(int a){
        return "";
    }      
    public String UslovTrazenjaSloga(){
        return "IdArtikal='" + idArtikal + "'" + " and ArVlasnik='" + arVlasnik + "'";
    }

    public String ImeKlase(){
        return "Artikal";
    }    
    public String IspraviSlog(){
        return "ArOpis='"      + arOpis      + "' , " + 
               "ArDim='"       + arDim       + "' , " +  
               "ArPorStopa='"  + arPorStopa  + "'"; 
    }
    public String IspraviSlog(int a){
        return "";
    }    
    
    public String UpisiSlog(){
        return "'" + idArtikal   + "' , '"
                   + arVlasnik   + "' , '"                
                   + arOpis      + "' , '" 
                   + arDim       + "' , '" 
                   + arPorStopa  + "'";
    }
    
    public String ProveriVrednosti(){
        Provere provere = new Provere();
        if (provere.proveriPrazno(idArtikal)) {return "Morate uneti Sifru Artikla";}                 
        if (provere.proveriPrazno(arOpis)){return "Morate uneti Opis";} 
        if (provere.proveriPrazno(arDim)) {return "Morate uneti Dimenziju";}                 
        if (provere.proveriPrazno(arPorStopa)){return "Morate uneti Poresku Stopu";} 
        return null;
    }      
    public String VrednostiIspravke(){
        vred = arOpis + "####____" + arDim + "####____" + arPorStopa;
        return vred;
    } 
    public String VrednostiNovi(){
        vred = idArtikal + "####____" + 
               arOpis + "####____" + arDim + "####____" + arPorStopa;
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
