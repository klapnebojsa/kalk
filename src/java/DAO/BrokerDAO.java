/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Inerfejsi.InterfaceDAO;
import KlaseBaze.Korisnik;
import ParametriBaze.OpstaOperacija;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author neso
 */
public class BrokerDAO implements Serializable{
    InterfaceDAO a;
    OpstaOperacija opstaOperacija;     
    
    public BrokerDAO(){} 
    public BrokerDAO(InterfaceDAO interf) {
        a=interf;
        opstaOperacija = new OpstaOperacija(a);       
    }

 
/////////// OTVARANJE BAZE /////////////////////
    public void otvoriBazu() throws ClassNotFoundException, SQLException{
        opstaOperacija.OtvoriBazu();
    } 
/////////// ZAPOCINJANJE COMMIT ILI ROLLBACK ///
    public void zapocniComm() throws SQLException {
        opstaOperacija.ZapocniComm();
    } 

///////////FORMIANJE RECI //////////////////////   
    //Rec za Upis novog sloga    
    public String RecNovi() throws SQLException {
        return(opstaOperacija.FormUpisiJedan());
    }
    //Rec za brisanje sloga  Zavisnog  
    public String RecNoviZav(InterfaceDAO p, InterfaceDAO n) throws SQLException {
        return (opstaOperacija.FormNoviZav(p) + ";" + opstaOperacija.FormIspraviNoviZav(n));  
    }     
    //Rec za Upis ispravljenog sloga    
    public String RecIspravi() throws SQLException {
        return(opstaOperacija.FormIspraviJedan());
    }
    //Rec za ispravku lozinke
    public String RecIspravi(int xx) throws SQLException {
        return(opstaOperacija.FormIspraviJedan(555));
    }
    public String RecIspraviZav(InterfaceDAO p, InterfaceDAO n) throws SQLException {
        return (opstaOperacija.FormIspraviZav(p) + ";" + opstaOperacija.FormIspraviIspraviZav(n));  
    }     
    //Rec za brisanje sloga    
    public String RecBrisi() throws SQLException {
        return(opstaOperacija.FormBrisi());
    } 
    //Rec za brisanje sloga  Zavisnog  
    public String RecBrisiZav(InterfaceDAO p, InterfaceDAO n) throws SQLException {
        return (opstaOperacija.FormBrisiZav(p) + ";" + opstaOperacija.FormIspraviBrisiZav(n));  
    }     
    //Kontrola da li postoji Korisnik sa unetim UserName and Password   
    public String RecLogin() throws SQLException {
        return(opstaOperacija.FormSelectUsNamPass());
    }         
    //Rec za Formiranje Liste svih      
    public String RecListSve() throws SQLException {
        return(opstaOperacija.FormListSve());
    }
    
/////////// IZVRSAVANJE SQL UPITA //////////////
    //Upis novog sloga    
    public Object izvrsiNovi() throws SQLException {
        Object result = opstaOperacija.IzvrsiNovi();
        return result;
    }
    //Brisanje postojece zavisne stavke
    public Object izvrsiNoviZav() throws SQLException {
        Object result = opstaOperacija.IzvrsiNovi() && opstaOperacija.IzvrsiNoviNadr();
        return result;
    }     
    //Ispravka postojeceg sloga    
    public Object izvrsiIspravi() throws SQLException {
        Object result = opstaOperacija.IzvrsiNovi();
        return result;
    }
    //Ispravka postojece zavisne stavke
    public Object izvrsiIspraviZav() throws SQLException {
        Object result = opstaOperacija.IzvrsiNovi() && opstaOperacija.IzvrsiNoviNadr();
        return result;
    }     
    //Brisanje postojece stavke
    public Object izvrsiBrisi() throws SQLException {
        Object result = opstaOperacija.IzvrsiNovi();
        return result;
    }
    //Brisanje postojece zavisne stavke
    public Object izvrsiBrisiZav() throws SQLException {
        Object result = opstaOperacija.IzvrsiNovi() && opstaOperacija.IzvrsiNoviNadr();
        return result;
    }    
    
    //Izvrsi kontrolu da li postoji Koeisnik sa UserName i Password    
    public Object izvrsiLogin(Korisnik k) throws SQLException {
        Object result = opstaOperacija.IzvrsiImaLi();
        opstaOperacija.setujKorisnika(k);
        return result;
    }    
    //Formiranje Liste svih    
    public Object izvrsiListu() throws SQLException {
        opstaOperacija.IzvrsiRS();
        Object result = opstaOperacija.NapraviListu();        
        return result;
    }    

////////// COMMIT - POTVRDA KOMANDE //////////////
    public void izvrsiComm() throws SQLException {
        opstaOperacija.IzvrsiComm();
    }
////////// ROLLBACK - ODUSTAJANJE OD KOMANDE /////
    public void izvrsiRoll() throws SQLException {
        opstaOperacija.IzvrsiRoll();
    }
////////// ZATVARANJE BAZE ////////////////////////
    public void zatvoriBazu() throws SQLException {
        opstaOperacija.ZatvoriSve();
    }     
     
    
    
    
    
 

    
    //Kontrola da li postoji trazeni slog
    public boolean citajDAO() throws ClassNotFoundException, SQLException{
        //OpstaOperacija opstaOperacija = new OpstaOperacija(a);
        Boolean Uslov = false;
        try{
            opstaOperacija.OtvoriBazu();
            opstaOperacija.FormSelectJedan();
            Uslov = opstaOperacija.IzvrsiImaLi();
            opstaOperacija.ZatvoriSve();
        }catch(Exception e){Uslov=false;}
        return Uslov;
    }

    //Lista odre]enih koji se sabiraju
    public List citajDeoDAO(){
        //OpstaOperacija opstaOperacija = new OpstaOperacija(a);
        List results = new LinkedList();
        try{
            opstaOperacija.OtvoriBazu();
            opstaOperacija.FormListDeo();
            opstaOperacija.PozoviComm();
            results = opstaOperacija.NapraviListu();
            opstaOperacija.ZatvoriSve();
        }catch(Exception e){results=null;}
        return results;        
    }
    
    //Citanje samo jednog polja iz tabele   
    public String citajCellDAO(String Polje){
        //OpstaOperacija opstaOperacija = new OpstaOperacija(a);
        String results = null;
        try{
            opstaOperacija.OtvoriBazu();
            opstaOperacija.FormCell(Polje);
            opstaOperacija.PozoviComm();
            results = opstaOperacija.NapraviString();
            opstaOperacija.ZatvoriSve();
        }catch(Exception e){results=null;}
        return results;           
    } 
    
    //Citanje vi[e odre]enih polja iz tabele   
    public List citajViseCellDAO(String Polje){
        //OpstaOperacija opstaOperacija = new OpstaOperacija(a);
        List results = null;
        try{
            opstaOperacija.OtvoriBazu();
            opstaOperacija.FormViseCell(Polje);
            opstaOperacija.PozoviComm();
            results = opstaOperacija.NapraviListu();
            opstaOperacija.ZatvoriSve();
        }catch(Exception e){results=null;}
        return results;           
    }
    
    //Citanje vi[e odre]enih polja iz tabele sa Uslovom  
    public List citajViseCellDAOUslov(String Polje, String Uslov){
        List results = null;
        try{
            opstaOperacija.OtvoriBazu();
            opstaOperacija.FormViseCellUslov(Polje, Uslov);
            opstaOperacija.PozoviComm();
            results = opstaOperacija.NapraviListu();
            opstaOperacija.ZatvoriSve();
        }catch(Exception e){results=null;}
        return results;           
    }
    
    public int MaxSifraDAO(String PoCemu) throws SQLException{
        //OpstaOperacija opstaOperacija = new OpstaOperacija(a);
        int results=0;
        try{
            opstaOperacija.OtvoriBazu();
            opstaOperacija.DajNajveci(PoCemu);
            opstaOperacija.PozoviComm();
            results = opstaOperacija.NapraviInt();
            opstaOperacija.ZatvoriSve();
        }catch(Exception e){}
        return results;        
    }

    public List citajZbirDAO() throws SQLException{
        //OpstaOperacija opstaOperacija = new OpstaOperacija(a);
        List results = new LinkedList();
        try{
            opstaOperacija.OtvoriBazu();
            opstaOperacija.FormListZbir();
            opstaOperacija.PozoviComm();
            results = opstaOperacija.NapraviListu();
            opstaOperacija.ZatvoriSve();
        }catch(Exception e){results=null;}
        return results;         
    }   

    public String proveriDAO(){
        return a.ProveriVrednosti();
    }
    public String vredNoviDAO(){
        return a.VrednostiNovi();
    }
    public String vredIsprDAO(){
        return a.VrednostiIspravke();
    } 
}
