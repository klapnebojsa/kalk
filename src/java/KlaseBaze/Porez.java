/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package KlaseBaze;

import Inerfejsi.InterfaceDAO;
import Povezivanje.Provere;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author neso
 */
@Entity
@Table(name = "porez")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Porez.findAll", query = "SELECT p FROM Porez p"),
    @NamedQuery(name = "Porez.findByIdPoPorez", query = "SELECT p FROM Porez p WHERE p.idPoPorez = :idPoPorez"),
    @NamedQuery(name = "Porez.findByPoProcPoreza", query = "SELECT p FROM Porez p WHERE p.poProcPoreza = :poProcPoreza"),
    @NamedQuery(name = "Porez.findByPoOpis", query = "SELECT p FROM Porez p WHERE p.poOpis = :poOpis")})
public class Porez implements InterfaceDAO,Serializable {
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "porez")
    private Collection<Artikal> artikalCollection;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idPoPorez")
    private String idPoPorez;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "PoProcPoreza")
    private BigDecimal poProcPoreza;
    @Column(name = "PoOpis")
    private String poOpis;
    String vred;
    
    public Porez() {
    }

    public Porez(String idPoPorez) {
        this.idPoPorez = idPoPorez;
    }

    public String getIdPoPorez() {
        return idPoPorez;
    }

    public void setIdPoPorez(String idPoPorez) {
        this.idPoPorez = idPoPorez;
    }

    public BigDecimal getPoProcPoreza() {
        return poProcPoreza;
    }
    
    public void setPoProcPoreza(BigDecimal poProcPoreza) {
            this.poProcPoreza = poProcPoreza;            
    }
    
    public String getPoOpis() {
        return poOpis;
    }

    public void setPoOpis(String poOpis) {
        this.poOpis = poOpis;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPoPorez != null ? idPoPorez.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Porez)) {
            return false;
        }
        Porez other = (Porez) object;
        if ((this.idPoPorez == null && other.idPoPorez != null) || (this.idPoPorez != null && !this.idPoPorez.equals(other.idPoPorez))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Povezivanje.Porez[ idPoPorez=" + idPoPorez + " ]";
    }
    /**
     *
     * @return
     */
    public String UslovTrazenjaSloga(){
        return "idPoPorez='" + idPoPorez + "'";
    }
    
    public String UslovTrazenjaSvih(){
        return "";
    }
    public String UslovTrazenjaSloga(int a){
        return "";
    }
    
    public String ImeKlase(){
        return "Porez";
    }    
    public String IspraviSlog(){
        return "PoProcPoreza= '" + poProcPoreza + "' ," + "PoOpis= '" + poOpis + "'";
    }
    public String IspraviSlog(int a){
        return "";
    }    
    
    public String UpisiSlog(){
        return "'" + idPoPorez + "' , '" + poProcPoreza + "' , '" + poOpis + "'";
    }

    public String ProveriVrednosti(){
        Provere provere = new Provere();
        if (provere.proveriPrazno(getIdPoPorez())) {return "Morate uneti Sifru";}
        
        BigDecimal g = getPoProcPoreza();
        String g1=null;
        try {
            g1 = g.toEngineeringString();
        }catch (Exception e){}

        if (provere.proveriNotBigDecimal(g1)){return "Morate uneti stopu poreza kao broj";} 
        if (Double.parseDouble(g1) > 9999.99){return "Morate uneti stopu poreza kao broj maksimalne dužine 4 cifara i dva decimalna mesta";}        
        if (provere.proveriPrazno(getPoOpis())){return "Morate uneti Opis Poreske stope";}        
        return null;       
    }     
    public String VrednostiIspravke(){
        vred = vred = getPoProcPoreza() + "####____" + getPoOpis();
        return vred;
    } 
    public String VrednostiNovi(){
        vred = getIdPoPorez() + "####____" + getPoProcPoreza() + "####____" + getPoOpis();;
        return vred;
    }
    public String PoljaZbir(){
        return "" ;
    }  
    public String VredPoljaZbir(){
        return "";
    }     

    @XmlTransient
    public Collection<Artikal> getArtikalCollection() {
        return artikalCollection;
    }

    public void setArtikalCollection(Collection<Artikal> artikalCollection) {
        this.artikalCollection = artikalCollection;
    }
      public String getStavku(){
        return "";        
    }    
}
