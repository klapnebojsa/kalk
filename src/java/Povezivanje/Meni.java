/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Povezivanje;

import java.io.Serializable;


/**
 *
 * @author neso
 */
public class Meni implements Serializable{
    public String IspisiMeni;
    public String IspisiStatusnuLiniju;
    
    public Integer Nivo;
    public String UserName;
    public String Prezime;
    public String Ime; 
    public String Naziv;
    public String Vlasnik; 
            
    
    public String IspisiMeni (Integer Nivo){
        this.Nivo = Nivo;
        IspisiMeni = 
        " <div id='navigator'> " +
            " <ul> " +
            "     <li><a href='#'> Osnovni podaci </a> " +
            "         <ul> " +
            "             <li><a href='/Kalk/KontniPlan.jsp?jNovi=Cisto'> Kontni plan </a></li>  " +
            "             <li><a href='/Kalk/PoreskeStope.jsp?jNovi=Cisto'> Poreske stope </a></li> ";
        if (this.Nivo == 10){
            IspisiMeni +=         
                "             <li><a href='/Kalk/Korisnici.jsp?jNovi=Cisto'> Korisnici </a></li>    " +
                "             <li><a href='/Kalk/Vlasnici.jsp?jNovi=Cisto'> Vlasnici </a></li>    "  ;
        }
        
        IspisiMeni += 
            "             <li><a href='/Kalk/Partneri.jsp?jNovi=Cisto'> Partneri </a></li>    " + 
            "             <li><a href='/Kalk/Artikli.jsp?jNovi=Cisto'> Artikli </a></li>    " +                 
            "         </ul>   " +
            "     </li>    " + 
            "     <li><a href='#'> Unos kalkulacija</a>    " +           
            "         <ul> " +
            "             <li><a href='/Kalk/FinansijskiNalog.jsp?jNovi=Cisto'>Finansijski Nalog</a></li>  " +   
            "             <li><a href='/Kalk/VpKalkulacija.jsp?jNovi=Cisto'>Vp Kalkulacija</a></li> " +
            "             <li><a href='/Kalk/MpKalkulacija.jsp?jNovi=Cisto'>Mp Kalkulacija</a></li> " +      
            "         </ul> " +
            "     </li>   " +  
            "     <li><a href='#'> Izvestaji</a>    " +           
            "         <ul> " +
            "             <li><a href='/Kalk/patAIRazUCeniMenu.jsp'>Razlika u ceni Abstract Factory</a></li>  " +   
            "             <li><a href='/Kalk/patAIIRazUCeniMenu.jsp'>Razlika u ceni Builder</a></li> " +
            "             <li><a href='/Kalk/patAI4RazUCeniMenu.jsp'>Razlika u ceni Factory</a></li> " + 
            "             <li><a href='/Kalk/patAI3RazUCeniMenu.jsp'>Razlika u ceni Decorater</a></li> " + 
            "             <li><a href='/Kalk/patAI5RazUCeniMenu.jsp'>Razlika u ceni Bridge</a></li> " + 
            "             <li><a href='/Kalk/patAI6RazUCeniMenu.jsp'>Razlika u ceni Memento</a></li> " + 
            "             <li><a href='/Kalk/patAI7IzborMenu.jsp'>Izbor State</a></li> " +
            "             <li><a href='/Kalk/patAI8UputstvoMenu.jsp'>Uputstvo Strategy</a></li> " + 
            "             <li><a href='/Kalk/patAI9UputstvoMenu.jsp'>Uputstvo Tempate</a></li> " +                  
            "         </ul> " +
            "     </li>   " +                                  
            "     <li><a href='/Kalk'> Prijava</a></li>     " +                
            " </ul>    " +      
        " </div>      ";   
        return IspisiMeni ;  
    }
}
