/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParametriBaze;

import DAO.BrokerDAO;
import Inerfejsi.InterfaceDAO;
import KlaseBaze.Korisnik;
import Povezivanje.Izracunaj;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


/**
 *
 * @author neso
 */
public class OpstaOperacija implements Serializable {
    private InterfaceDAO a;
    ResultSet rs;
    String sql;
    String sqln;
    Parametri par = new Parametri();  
    String JDBC_DRIVER = par.getJDBC_DRIVER();
    String DB_URL = par.getDB_URL();
    String USER = par.getUSER();
    String PASS = par.getPASS();

    Connection conn = null;
    Statement stmt = null; 
    
    //Pola za zavisne klase
    String PoljaZbirP;
    String[] PoljaZP;    
    String VredZbirP;
    String[] VredZP; 
    String PoljaZbirN;
    String[] PoljaZN; 
    int brCol;
    private InterfaceDAO p;
    private InterfaceDAO n;
    
    public OpstaOperacija(InterfaceDAO a){
        this.rs = null;        
        this.a = a;
        this.sql = null;
        this.sqln = null;
    }

    public void OtvoriBazu() throws ClassNotFoundException, SQLException{
        //try{
        Class.forName(JDBC_DRIVER);
        conn = DriverManager.getConnection(DB_URL,USER,PASS);
        stmt = conn.createStatement(); 
        //}catch(SQLException | ClassNotFoundException se){}//greske JDBC  //greske Class.forName    
    }
    //Komanda za Trazenje da li postoji trazeni slog
    public String FormSelectJedan(){
        String UslovTrazenjaSloga = a.UslovTrazenjaSloga();
        String ImeKlase = a.ImeKlase(); 
        sql = "SELECT * FROM " +  ImeKlase + " where " +  UslovTrazenjaSloga;
        return sql;
    }
    
    //Komanda za ispravku podredjenog zavisnog sloga
    public String FormIspraviZav(InterfaceDAO interfp){
        //Podredjena klasa   
        p=interfp;
            try{
                //Podredjena klasa
                BrokerDAO brokerDAO = new BrokerDAO(p);
                List results = brokerDAO.citajDeoDAO();
                    //Stare vrednosti vrednosti
                PoljaZbirP = p.PoljaZbir();
                PoljaZP = PoljaZbirP.split(",");
                int brPolja = PoljaZP.length;
                VredZP = new String[brPolja];                
                Arrays.fill(VredZP, "0.00");
                try{
                    for(Object category : results) {
                        List el = (List)category; 
                        for (int i = 0; i <= brPolja-1; i++) {
                            VredZP[i]=el.get(i)==null? "" : el.get(i).toString();
                        }                                        
                    }
                } catch (Exception e){}

                String UslovTrazenjaSloga = p.UslovTrazenjaSloga();
                String ImeKlase = p.ImeKlase();
                String IspraviSlog = p.IspraviSlog();
                    //Novo Unete vrednosti
                Izracunaj izracunaj = new Izracunaj();
                String VredZbirPnovo = p.VredPoljaZbir();
                String[] VredZPnovo = new String[brPolja];                
                VredZPnovo = VredZbirPnovo.split(",");
                //Razlika unetih
                for (int i = 0; i <= brPolja-1; i++) {
                    VredZP[i]=VredZPnovo[i]==null? VredZP[i] : izracunaj.Razliku(VredZP[i],VredZPnovo[i]);
                }                  
                sql =  "UPDATE " +  ImeKlase + " SET " +  IspraviSlog + " WHERE " + UslovTrazenjaSloga;
            }catch(Exception e){sql = "";}
        return sql;
    }    
    //Komanda za Ispravku nadredjenog pri isprvci podredjene stavke sloga
    public String FormIspraviIspraviZav(InterfaceDAO interfn) throws SQLException{
        n=interfn;
        try{
            String ImeKlase = n.ImeKlase();
            String IspraviSlog = "";            
            Double[] zbirovi = saberiPodredjeni();
            Izracunaj izracunaj = new Izracunaj();
            for (int i = 0; i <= brCol; i++) {
                IspraviSlog += PoljaZN[i] + "=" + izracunaj.Razliku(zbirovi[i].toString(),VredZP[i]);
                if (i!=brCol) IspraviSlog += ", ";
            }
            String UslovTrazenjaSloga = n.UslovTrazenjaSloga();           
            sqln =  "UPDATE " +  ImeKlase + " SET " +  IspraviSlog + " WHERE " + UslovTrazenjaSloga;         
        }catch(Exception e){sqln="";}
        return sqln;
    }    

    //Komanda za Ispravku postojeceg sloga
    public String FormIspraviJedan(){
        String UslovTrazenjaSloga = a.UslovTrazenjaSloga();
        String ImeKlase = a.ImeKlase(); 
        String IspraviSlog = a.IspraviSlog();
        sql =  "UPDATE " +  ImeKlase + " SET " +  IspraviSlog + " WHERE " + UslovTrazenjaSloga;        
        return sql;
    }
    //Komanda za Ispravku lozinke
    public String FormIspraviJedan(int xx){
        String UslovTrazenjaSloga = a.UslovTrazenjaSloga();
        String ImeKlase = a.ImeKlase();
        String IspraviSlog = a.IspraviSlog(555);  
        sql =  "UPDATE " +  ImeKlase + " SET " +  IspraviSlog + " WHERE " + UslovTrazenjaSloga;        
        return sql;
    }    
    
    //Komanda za Upisivanje novog sloga
    public String FormUpisiJedan(){
        String ImeKlase = a.ImeKlase();
        String UpisiSlog = a.UpisiSlog(); 
        sql =  "INSERT INTO " +  ImeKlase + " VALUES (" +  UpisiSlog + ")";        
        return sql;
    }
    public String FormNoviZav(InterfaceDAO interfp){
        //Podredjena klasa   
        p=interfp;
            try{
                VredZbirP = p.VredPoljaZbir();
                VredZP = VredZbirP.split(",");                
                String ImeKlase = p.ImeKlase();
                String UpisiSlog = p.UpisiSlog();          
                sql =  "INSERT INTO " +  ImeKlase + " VALUES (" +  UpisiSlog + ")";
            }catch(Exception e){sql = "";}
        return sql;
    }    
    //Komanda za Ispravku nadredjenog pri brisanju podredjene stavke sloga
    public String FormIspraviNoviZav(InterfaceDAO interfn) throws SQLException{
        n=interfn;
        try{
            String ImeKlase = n.ImeKlase();
            String IspraviSlog = "";
                
            Double[] zbirovi = saberiPodredjeni();
            Izracunaj izracunaj = new Izracunaj();
            for (int i = 0; i <= brCol; i++) {
                IspraviSlog += PoljaZN[i] + "=" + izracunaj.Zbir(zbirovi[i].toString(),VredZP[i]);
                if (i!=brCol) IspraviSlog += ", ";
            }
            String UslovTrazenjaSloga = n.UslovTrazenjaSloga();           
            sqln =  "UPDATE " +  ImeKlase + " SET " +  IspraviSlog + " WHERE " + UslovTrazenjaSloga;         
        }catch(Exception e){sqln="";}
        return sqln;
    }
    
    //Komanda za Brisanje slog
    public String FormBrisi(){
        String UslovTrazenjaSloga = a.UslovTrazenjaSloga();
        String ImeKlase = a.ImeKlase(); 
        sql = "DELETE FROM " +  ImeKlase + " where " +  UslovTrazenjaSloga;
        return sql;
    }
    
    //Komanda za brisanje zavisnog/podredenog
    public String FormBrisiZav(InterfaceDAO interfp){
        //Podredjena klasa   
        p=interfp;
            try{
                BrokerDAO brokerDAO = new BrokerDAO(p);
                List results = brokerDAO.citajDeoDAO();
                PoljaZbirP = p.PoljaZbir();
                PoljaZP = PoljaZbirP.split(",");
                VredZP = new String[PoljaZP.length];                
                Arrays.fill(VredZP, "0.00");
                try{
                    for(Object category : results) {
                        List el = (List)category; 
                        for (int i = 0; i <= PoljaZP.length-1; i++) {
                            VredZP[i]=el.get(i)==null? "" : el.get(i).toString();
                        }                                        
                    }
                } catch (Exception e){}

                String ImeKlase = p.ImeKlase();
                String UslovTrazenjaSloga = p.UslovTrazenjaSloga();
                sql = "DELETE FROM " +  ImeKlase + " where " +  UslovTrazenjaSloga;
            }catch(Exception e){sql = "";}
        return sql;
    } 
    //Komanda za Ispravku nadredjenog pri brisanju podredjene stavke sloga
    public String FormIspraviBrisiZav(InterfaceDAO interfn) throws SQLException{
        n=interfn;
        String ImeKlase = n.ImeKlase();
        String IspraviSlog = "";            
        Double[] zbirovi = saberiPodredjeni();
        Izracunaj izracunaj = new Izracunaj();
        for (int i = 0; i <= brCol; i++) {
            IspraviSlog += PoljaZN[i] + "=" + izracunaj.Razliku(zbirovi[i].toString(),VredZP[i]);
            if (i!=brCol) IspraviSlog += ", ";
        }
        String UslovTrazenjaSloga = n.UslovTrazenjaSloga();           
        sqln =  "UPDATE " +  ImeKlase + " SET " +  IspraviSlog + " WHERE " + UslovTrazenjaSloga;       
        return sqln;
    }
    
    //Komanda za Listanje Svih
    public String FormListSve(){
        String UslovTrazenjaSvih = a.UslovTrazenjaSvih();
        String ImeKlase = a.ImeKlase();
        sql = "SELECT * FROM " +  ImeKlase;
        if (UslovTrazenjaSvih != "") sql = "SELECT * FROM " +  ImeKlase + " where " +  UslovTrazenjaSvih;
        return sql;
    } 
    
    // Zapocni commit
    public void ZapocniComm() throws SQLException{
        conn.setAutoCommit(false);  
    }  
    //OK izvrsi commit 
    public void IzvrsiComm() throws SQLException{
        conn.commit(); 
    }
    //Nije OK rollback
    public void IzvrsiRoll() throws SQLException{
        conn.rollback();
    }     
    //Spremi komandu - OVO NECE TREBATI KADA SE ZAVRSI PROGTAM SA OPSTASO 
    public void PozoviComm() throws SQLException{
        rs = stmt.executeQuery(sql); 
    }   
    //Izvrsi komandu i vrati Record Set
    public ResultSet IzvrsiRS() throws SQLException{
        rs = stmt.executeQuery(sql); 
        return rs;
    }
    //Izvrsi komandu i vrati da li je upis/brisanje Izvrseno
    public boolean IzvrsiNovi() throws SQLException{
        boolean Uslov = false;
        int rows = stmt.executeUpdate(sql);          
        if (rows > 0) Uslov = true; 
        return Uslov;
    }
    //Izvrsi komandu nadredjenog i vrati da li je upis/brisanje Izvrseno
    public boolean IzvrsiNoviNadr() throws SQLException{
        boolean Uslov = false;
        int rows = stmt.executeUpdate(sqln);          
        if (rows > 0) Uslov = true; 
        return Uslov;
    }    
    //Trazenje Korisnika preko UserName i Password-a
    public String FormSelectUsNamPass(){
        String UslovTrazenjaSloga = a.UslovTrazenjaSloga(1);
        String ImeKlase = a.ImeKlase(); 
        sql = "SELECT * FROM " +  ImeKlase + " where " +  UslovTrazenjaSloga;
        return sql;
    }    
    //Setovanje Korisnika pri logovanju
    public void setujKorisnika(Korisnik k) throws SQLException{
        try{
        k.setPrezime(rs.getString("Prezime"));
        k.setIme(rs.getString("Ime")); 
        k.setVlasnik(rs.getString("Vlasnik"));
        k.setNivo(rs.getInt("Nivo"));            
        }catch(Exception e){}
  
    }      
    
    
    //Izvrsi komandu i vrati da li je upis/brisanje Izvrseno
    public boolean IzvrsiBool() throws SQLException{
        boolean Uslov = false;
        int rows = stmt.executeUpdate(sql);          
        if (rows > 0) Uslov = true; 
        return Uslov;
    } 
    //Izvrsi komandu i vrati da li ima slogova u Result Setu
    public boolean IzvrsiImaLi() throws SQLException{
        boolean Uslov = false;
        rs = stmt.executeQuery(sql);
        boolean ImaJos = rs.next();
        if(ImaJos) Uslov = true; 
        return Uslov;        
    }
    
    //Result Set Prebaci u List
    public List NapraviListu() throws SQLException{
        List results = new LinkedList();
        ResultSetMetaData meta = rs.getMetaData();
        int columnCount = meta.getColumnCount();
            
        while (rs.next()) {
            List singleRow = new ArrayList();
            for (int i = 1; i <= columnCount; i++) {
                //String xx =rs.getObject(i).toString();
                singleRow.add(rs.getObject(i));
            }
            results.add(singleRow);
        } 
        return results;
    }  
    //Result Set Prebaci u String (Samo za jedno polje)
    public String NapraviString() throws SQLException{
        Object r = null;
        String results =  null;
        while (rs.next()) {r=rs.getObject(1);} 
        results = r.toString();
        return results;
    } 
    //Result Set Prebaci u Int (Samo za jedno polje)
    public int NapraviInt() throws SQLException{
        int results = 0;
        while (rs.next()) {results = rs.getInt(a.getStavku());}
        return results;
    }

    //Zatvori RecordSet, stmt, connection 
    public void ZatvoriSve(){
        try{
            rs.close();
            stmt.close();
            conn.close();            
        }catch(Exception e){
        }finally{                                       
           try{ if(stmt!=null) stmt.close();
           }catch(SQLException s){ }
           try{ if(conn!=null) conn.close();
           }catch(SQLException s){
        }//end finally
        }//end try         
    }
    //Komanda za Listanje polja za zbir
    public String FormListZbir(){
        String UslovTrazenjaSvih = null;        
        UslovTrazenjaSvih = a.UslovTrazenjaSvih();
        String ImeKlase = a.ImeKlase(); 
        String PoljaZbir = a.PoljaZbir();
        sql = "SELECT " + PoljaZbir + " FROM " +  ImeKlase;
        if (UslovTrazenjaSvih != "") sql = "SELECT " + PoljaZbir + " FROM " +  ImeKlase + " where " +  UslovTrazenjaSvih;
        return sql;
    }      
    //Vraca najvecu vrednost polja tj. dotadasnju najvecu vrednost (maksimalnu (int) sifru)
    public String DajNajveci(String PoCemu){
        String UslovTrazenjaSvih = null;        
        UslovTrazenjaSvih = a.UslovTrazenjaSvih();
        String ImeKlase = a.ImeKlase(); 
        sql = "SELECT * FROM " +  ImeKlase + " order by " + PoCemu ;
        if (!"".equals(UslovTrazenjaSvih)) sql = "SELECT * FROM " +  ImeKlase + " where " +  UslovTrazenjaSvih+ " order by " + PoCemu ; 
        return sql;
    } 
  
    //Komanda za Listanje polja koja se sabiraju
    public String FormListDeo(){
        String UslovTrazenjaSloga = a.UslovTrazenjaSloga();
        String ImeKlase = a.ImeKlase();
        String PoljaZbir = a.PoljaZbir(); 
        sql = "SELECT "  +  PoljaZbir + " FROM " +  ImeKlase + " where " +  UslovTrazenjaSloga;
        return sql;
    } 
    //Komanda za podatke jednog polja iz klase
    public String FormCell(String Polje){
        String UslovTrazenjaSloga = a.UslovTrazenjaSloga();
        String ImeKlase = a.ImeKlase();  
        sql = "SELECT "  +  Polje + " FROM " +  ImeKlase + " where " +  UslovTrazenjaSloga;
        return sql;
    }
    //Komanda za podatke viise polja iz klase
    public String FormViseCell(String Polje){
        String UslovTrazenjaSvih = a.UslovTrazenjaSvih();
        String ImeKlase = a.ImeKlase();  
        sql = "SELECT "  +  Polje + " FROM " +  ImeKlase + " where " +  UslovTrazenjaSvih;
        return sql;
    }  
    //Komanda za podatke viise polja iz klase sa donetim uslovom trayenja
    public String FormViseCellUslov(String Polje, String UslovTrazenja){
        String ImeKlase = a.ImeKlase();  
        sql = "SELECT "  +  Polje + " FROM " +  ImeKlase + " where " + UslovTrazenja;
        return sql;
    }     
    public Double[] saberiPodredjeni() throws SQLException{
        brCol =0;
        BrokerDAO brokerDAO = new BrokerDAO(p);
        List Sabirci = new LinkedList();
        Sabirci = brokerDAO.citajZbirDAO();
        /*VredZbirP = p.VredPoljaZbir();
        VredZP = VredZbirP.split(",");*/
        PoljaZbirN = n.PoljaZbir();
        PoljaZN = PoljaZbirN.split(",");        
        brCol=PoljaZN.length-1;
                
        Double[] zbirKol = new Double[PoljaZN.length];
        Arrays.fill(zbirKol, 0.00);
        try{
            for(Object category : Sabirci) {
                List element = (List)category;              
                for (int i = 0; i <= brCol; i++) {
                    zbirKol[i]+=Double.parseDouble(element.get(i).toString());
                }            
            }
        }catch(Exception e){}     
        return zbirKol;
    }    
    
}
