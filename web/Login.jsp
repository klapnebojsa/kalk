<%-- 
    Document   : Login
    Created on : Jan 25, 2015, 2:59:21 PM
    Author     : neso
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="KlaseBaze.Korisnik" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel='stylesheet' href='resursi/css/MojStil.css'>       
        <link rel='stylesheet' href='resursi/css/MenuBar.css'>     
        
        <title>Prijava</title>
    </head>
    <body class="LoginBody">    
        <div class="anim3D" >
            <a class="FontAgency">ACCOUNTING AGENCY </a><br>          
        </div>
        <div style="width:400px;  text-align: center;">
             <a class="OpisAgency">Dvadeset godina sa Vama</a>           
        </div>
        <div class="Drustvo">
            <a href="https://www.facebook.com/"><img class="IcoDrustvene" src="resursi/slike/social/Facebook-icon2.png"></a>       
            <a href="https://twitter.com/"> <img class="IcoDrustvene" src="resursi/slike/social/Twitter-2-icon2.png"></a>
            <a href="https://plus.google.com/"><img class="IcoDrustvene" src="resursi/slike/social/Google-Plus-icon2.png"></a>
            <a href="http://www.flickr.com/"><img class="IcoDrustvene" src="resursi/slike/social/Flickr-icon2.png"></a>        
        </div>
        <div class="Linkovi">
            <a href="http://www.privsav.rs/"><img class="LinkoviSlike" src="resursi/slike/PrivredniSavetnik.jpg"></a> <br>         
            <a href="http://www.cekos.rs/"><img class="LinkoviSlike" src="resursi/slike/Cekos.jpg"></a>  <br>        
            <a href="http://www.poreskauprava.gov.rs/sr/e-porezi/portal.html"><img class="LinkoviSlike" src="resursi/slike/Eporezi.jpg"></a>  <br>   
        </div> 
        
            <fieldset class="OkvirMC" >
            <legend align="right" class='dugmeLe'>Prijavite se</legend>
            <form class="Prijava" action="LoginServlet">             
                <table>
                    <tr>
                        <td style='width:170px; text-align:right'>Korisničko ime:</td>
                        <td> <input type="text" name="uname" /> </td>
                    </tr>
                    <tr>
                        <td style='width:170px; text-align:right'>Lozinka:</td>                
                        <td><input type="password" name="passw" /></td>
                    </tr>                    
                    <tr>
                        <td></td>
                        <td><a href="PromLozinke.jsp">Promena Lozinke</a></td>
                    </tr>    
 
                </table>
                <div align="center">
                    <input type="submit" value="OK" class="dugmeOK" style="visibility:visible;">
                </div> 
            </form>            
        </fieldset>      
    </body>
</html>
