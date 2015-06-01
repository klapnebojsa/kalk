/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ParametriBaze;

import java.io.Serializable;

/**
 *
 * @author neso
 */
public class Parametri implements Serializable{
    String JDBC_DRIVER = null;
    String DB_URL/* */= null;
    String USER = null;
    String PASS = null;    
    
    public Parametri() {
        JDBC_DRIVER = "com.mysql.jdbc.Driver";
        DB_URL = "jdbc:mysql://localhost/finko";
        //DB_URL = "jdbc:mysql://localhost/finko?characterEncoding=UTF-8&useUnicode=true";       
        USER = "root";
        PASS = "";        
    }
    
    public String getJDBC_DRIVER() {
        return JDBC_DRIVER;
    }
    public void setJDBC_DRIVER(String JDBC_DRIVER) {
        this.JDBC_DRIVER = JDBC_DRIVER;
    }   
    
    public String getDB_URL() {
        return DB_URL;
    }
    public void setDB_URL(String DB_URL) {
        this.DB_URL = DB_URL;
    }     
    
    public String getUSER() {
        return USER;
    }
    public void setUSER(String USER) {
        this.USER = USER;
    }
    
      public String getPASS() {
        return PASS;
    }
    public void setPASS(String PASS) {
        this.PASS = PASS;
    }   
}
