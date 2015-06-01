/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Inerfejsi.InterfaceDAO;
import ParametriBaze.Parametri;
import Povezivanje.Izracunaj;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author neso
 */
public class ZavisniDAO  implements Serializable{
    public InterfaceDAO p;
    public InterfaceDAO n;    
    public ZavisniDAO(InterfaceDAO podr, InterfaceDAO nadr) {
        p=podr;
        n=nadr;
    }
    String PoljaZbirP;
    String[] PoljaZP;    
    String VredZbirP;
    String[] VredZP; 
    String PoljaZbirN;
    String[] PoljaZN;     
    int brCol;
    // JDBC driver name, database URL, User i password baza
    Parametri par = new Parametri();  
    String JDBC_DRIVER = par.getJDBC_DRIVER();
    String DB_URL = par.getDB_URL();
    String USER = par.getUSER();
    String PASS = par.getPASS();

    Connection conn = null;
    Statement stmt = null;     
    
    public boolean noviDAO() throws SQLException{
        Boolean Uslov  = false;               
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
 
            try{
                conn.setAutoCommit(false);
                //Podredjena klasa
                VredZbirP = p.VredPoljaZbir();
                VredZP = VredZbirP.split(",");                
                String UslovTrazenjaSloga = p.UslovTrazenjaSloga();
                String ImeKlase = p.ImeKlase();
                String UpisiSlog = p.UpisiSlog();          
                String sqlp =  "INSERT INTO " +  ImeKlase + " VALUES (" +  UpisiSlog + ")";
                
                //Nadredjena klasa
                ImeKlase = n.ImeKlase();
                String IspraviSlog = "";
                
                Double[] zbirovi = saberiPodredjeni();
                Izracunaj izracunaj = new Izracunaj();
                for (int i = 0; i <= brCol; i++) {
                    IspraviSlog += PoljaZN[i] + "=" + izracunaj.Zbir(zbirovi[i].toString(),VredZP[i]);
                    if (i!=brCol) IspraviSlog += ", ";
                }
                UslovTrazenjaSloga = n.UslovTrazenjaSloga();           
                String sqln =  "UPDATE " +  ImeKlase + " SET " +  IspraviSlog + " WHERE " + UslovTrazenjaSloga;
                
                //Upis u bazu
                try{
                    stmt.executeUpdate(sqlp);
                    stmt.executeUpdate(sqln);          
                    conn.commit();
                    Uslov=true;                    
                }catch(Exception e){
                    Uslov=false;
                    conn.rollback();
                }
            } catch(Exception e){ }

            stmt.close();
            conn.close();
        }catch(SQLException | ClassNotFoundException se){//greske JDBC  //greske Class.forName
        }finally{                                        //finally block za zatvaranje resursa
           try{ if(stmt!=null) stmt.close();
           }catch(SQLException s){ }
           try{ if(conn!=null) conn.close();
           }catch(SQLException s){ 
        }//end finally
        }//end try        
        return Uslov;        
    }
    
    public boolean izbrisanDAO() throws SQLException{
        Boolean Uslov  = false;               
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
 
            try{
                conn.setAutoCommit(false);
                //Podredjena klasa
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
              
                //String sqlp =  "INSERT INTO " +  ImeKlase + " VALUES (" +  UpisiSlog + ")";
                String sqlp = "DELETE FROM " +  ImeKlase + " where " +  UslovTrazenjaSloga;
                
                //Nadredjena klasa
                ImeKlase = n.ImeKlase();
                String IspraviSlog = "";            
                Double[] zbirovi = saberiPodredjeni();
                Izracunaj izracunaj = new Izracunaj();
                for (int i = 0; i <= brCol; i++) {
                    IspraviSlog += PoljaZN[i] + "=" + izracunaj.Razliku(zbirovi[i].toString(),VredZP[i]);
                    if (i!=brCol) IspraviSlog += ", ";
                }
                UslovTrazenjaSloga = n.UslovTrazenjaSloga();           
                String sqln =  "UPDATE " +  ImeKlase + " SET " +  IspraviSlog + " WHERE " + UslovTrazenjaSloga;
                
                //Upis u bazu
                try{
                    stmt.executeUpdate(sqlp);
                    stmt.executeUpdate(sqln);          
                    conn.commit();
                    Uslov=true;                    
                }catch(Exception e){
                    Uslov=false;
                    conn.rollback();
                }
            } catch(Exception e){ }

            stmt.close();
            conn.close();
        }catch(SQLException | ClassNotFoundException se){//greske JDBC  //greske Class.forName
        }finally{                                        //finally block za zatvaranje resursa
           try{ if(stmt!=null) stmt.close();
           }catch(SQLException s){ }
           try{ if(conn!=null) conn.close();
           }catch(SQLException s){ 
        }//end finally
        }//end try        
        return Uslov;          
    }
    
    
    
    
    
    
    public boolean ispraviDAO() throws SQLException{
        Boolean Uslov  = false;               
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL,USER,PASS);
            stmt = conn.createStatement();
 
            try{
                conn.setAutoCommit(false);
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
              
                //String sqlp =  "INSERT INTO " +  ImeKlase + " VALUES (" +  UpisiSlog + ")";
                //String sqlp = "DELETE FROM " +  ImeKlase + " where " +  UslovTrazenjaSloga;
                String sqlp =  "UPDATE " +  ImeKlase + " SET " +  IspraviSlog + " WHERE " + UslovTrazenjaSloga;
                
                //Nadredjena klasa
                ImeKlase = n.ImeKlase();
                IspraviSlog = "";            
                Double[] zbirovi = saberiPodredjeni();
                izracunaj = new Izracunaj();
                for (int i = 0; i <= brCol; i++) {
                    IspraviSlog += PoljaZN[i] + "=" + izracunaj.Razliku(zbirovi[i].toString(),VredZP[i]);
                    if (i!=brCol) IspraviSlog += ", ";
                }
                UslovTrazenjaSloga = n.UslovTrazenjaSloga();           
                String sqln =  "UPDATE " +  ImeKlase + " SET " +  IspraviSlog + " WHERE " + UslovTrazenjaSloga;                
                //Upis u bazu
                try{
                    stmt.executeUpdate(sqlp);
                    stmt.executeUpdate(sqln);          
                    conn.commit();
                    Uslov=true;                    
                }catch(Exception e){
                    Uslov=false;
                    conn.rollback();
                }
            } catch(Exception e){ }

            stmt.close();
            conn.close();
        }catch(SQLException | ClassNotFoundException se){//greske JDBC  //greske Class.forName
        }finally{                                        //finally block za zatvaranje resursa
           try{ if(stmt!=null) stmt.close();
           }catch(SQLException s){ }
           try{ if(conn!=null) conn.close();
           }catch(SQLException s){ 
        }//end finally
        }//end try        
        return Uslov;          
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
