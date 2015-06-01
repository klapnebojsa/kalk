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
@Table(name = "korisnik")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Korisnik.findAll", query = "SELECT k FROM Korisnik k"),
    @NamedQuery(name = "Korisnik.findByUserName", query = "SELECT k FROM Korisnik k WHERE k.userName = :userName"),
    @NamedQuery(name = "Korisnik.findByPassword", query = "SELECT k FROM Korisnik k WHERE k.password = :password"),
    @NamedQuery(name = "Korisnik.findByVlasnik", query = "SELECT k FROM Korisnik k WHERE k.vlasnik = :vlasnik"),
    @NamedQuery(name = "Korisnik.findByPrezime", query = "SELECT k FROM Korisnik k WHERE k.prezime = :prezime"),
    @NamedQuery(name = "Korisnik.findByIme", query = "SELECT k FROM Korisnik k WHERE k.ime = :ime"),
    @NamedQuery(name = "Korisnik.findByNivo", query = "SELECT k FROM Korisnik k WHERE k.nivo = :nivo")})
public class Korisnik implements InterfaceDAO, Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "UserName")
    private String userName;
    @Column(name = "Password")
    private String password;
    @Column(name = "NewPassword")
    private String newpassword;    
    @Column(name = "Vlasnik")
    private String vlasnik;
    @Column(name = "Prezime")
    private String prezime;
    @Column(name = "Ime")
    private String ime;
    @Column(name = "Nivo")
    private Integer nivo;
    @Column(name = "Valid")
    private Boolean valid;
    
    String vred;
    public Korisnik() {
    }

    public Korisnik(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public String getNewPassword() {
        return newpassword;
    }

    public void setNewPassword(String newpassword) {
        this.newpassword = newpassword;
    }
    
    public String getVlasnik() {
        return vlasnik;
    }

    public void setVlasnik(String vlasnik) {
        this.vlasnik = vlasnik;
    }

    public String getPrezime() {
        return prezime;
    }

    public void setPrezime(String prezime) {
        this.prezime = prezime;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public Integer getNivo() {
        return nivo;
    }

    public void setNivo(Integer nivo) {
        this.nivo = nivo;
    }
    public boolean isValid() {
        return valid;
    }
    public void setValid(boolean valid) {
        this.valid = valid;
    } 
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userName != null ? userName.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Korisnik)) {
            return false;
        }
        Korisnik other = (Korisnik) object;
        if ((this.userName == null && other.userName != null) || (this.userName != null && !this.userName.equals(other.userName))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Povezivanje.Korisnik[ userName=" + userName + " ]";
    }
   
 ////////OVO PROVERITI    
    public String UslovTrazenjaSvih(){
        return "";
    }
    public String UslovTrazenjaSloga(){
        return "UserName='" + userName + "'";
    }
    public String UslovTrazenjaSloga(int a){
        return "UserName='" + userName + "' and Password='" + password + "'";
    }
    
    public String ImeKlase(){
        return "Korisnik";
    }    
    public String IspraviSlog(){
        return "Password='" + password + "' , " + 
               "Vlasnik='"  + vlasnik  + "' , " +  
               "Prezime='"  + prezime  + "' , " +  
               "Ime='"      + ime      + "' , " + 
               "Nivo="      + nivo     + "";
    }
    public String IspraviSlog(int a){
        return "Password='" + newpassword + "'";
    }    
    
    public String UpisiSlog(){
        return "'" + userName + "' , '" 
                   + password + "' , '" 
                   + vlasnik  + "' , '" 
                   + prezime  + "' , '" 
                   + ime      + "' , "
                   + nivo;
    }
    
    public String ProveriVrednosti(){
        Provere provere = new Provere();
        if (provere.proveriPrazno(getUserName())) {return "Morate uneti User Name";}                 
        if (provere.proveriPrazno(getPassword())){return "Morate uneti Password";} 
        if (provere.proveriPrazno(getVlasnik())) {return "Morate uneti Vlasnika";}                 
        if (provere.proveriPrazno(getPrezime())){return "Morate uneti Prezime";} 
        if (provere.proveriPrazno(getIme())) {return "Morate uneti Ime";} 
        
        Integer g = getNivo();
        String g1=null;
        try {g1 = Integer.toString(g);}catch (Exception e){}        
        if (provere.proveriNotInteger(g1)){return "Morate uneti Nivo kao broj";}
        
        return null;
    }      
    public String VrednostiIspravke(){
            vred = getPassword() + "####____" + getVlasnik() + "####____" + getPrezime() + "####____" + getIme() + "####____" + getNivo();
        return vred;
    } 
    public String VrednostiNovi(){
            vred = getUserName() + "####____" + getPassword() + "####____" + getVlasnik() + "####____" + getPrezime() + "####____" + getIme() + "####____" + getNivo();
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
